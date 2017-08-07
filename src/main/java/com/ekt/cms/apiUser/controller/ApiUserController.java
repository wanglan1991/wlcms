package com.ekt.cms.apiUser.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.TextUI;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ekt.cms.account.entity.CmsAccount;
import com.ekt.cms.apiUser.entity.ApiUser;
import com.ekt.cms.apiUser.entity.CmsUserBusiness;
import com.ekt.cms.apiUser.entity.DiyTreeData;
import com.ekt.cms.apiUser.service.IApiUserService;
import com.ekt.cms.common.BaseController;
import com.ekt.cms.common.entity.Result;
import com.ekt.cms.utils.CMSConstants;
import com.ekt.cms.utils.EktHeadPictureUtil;
import com.ekt.cms.utils.EncryptUtil;
import com.ekt.cms.utils.TextUtil;
import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.utils.pageHelper.PageContext;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author wanglan
 * @email wanglan-TD@foxmail.com
 * @version 创建时间：2016年6月22日 下午4:45:08 程序的简单说明
 */

@Controller
@RequestMapping(value = "/user")
public class ApiUserController extends BaseController {

	@Resource
	private IApiUserService apiUserService;

	@RequestMapping("/manage")
	public String toVideoPage() {
		return "main/user/apiUserManage";
	}

	/**
	 * 根据用户属性条件查询获取ektapi用户列表
	 * 
	 * @param apiUser
	 * @return
	 */
	@RequestMapping(value = "/listPage")
	@ResponseBody
	public PageBean<ApiUser> listPage(PageContext page, ApiUser apiUser) {
		CmsAccount account = getCurrentAccount();
		if (account.getRole() != CMSConstants.ADMIN && account.getRole() != CMSConstants.ROOT
				&& account.getEktapiUserId() != 0 && apiUserService.getUserById(account.getEktapiUserId()) != null) {
			
			apiUser.setInviteUserId(account.getEktapiUserId());
		}
		page.paging();
		return new PageBean<ApiUser>(apiUserService.listPage(apiUser));
	}

	@RequestMapping(value = "/generateUser")
	@ResponseBody
	public Result getEktUserByTelephone(@RequestParam("telephone") String telephone) {
		// 如果用户已经存在
		if (apiUserService.getEktUserByTelephone(telephone) != null) {
			return Result.getResults(1);
		} else {
			ApiUser newUser = new ApiUser();
			String username = apiUserService.codeReactor();
			newUser.setUsername(username);
			newUser.setNickname(username);
			newUser.setTelephone(telephone);
			newUser.setRegisterType("telephone");
			newUser.setPassword(EncryptUtil.encryptPassword(telephone.substring(5, 11)));
			newUser.setHeadPicture(EktHeadPictureUtil.getEktSysHeadPicture(-1));
			int result = apiUserService.addUser(newUser);
			if (result > 0) {
				apiUserService.insertExperience(newUser.getId(), 50, "绑定手机");
				return Result.getResults(2);
			} else {
				return Result.getResults(0);
			}

		}

	}

	/**
	 * 封停或启用用户
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/confine")
	@ResponseBody
	public Result confine(int id) {
		Result result = Result.getResults();
		if (getCurrentAccount() == null) {
			result.setResult(-1);
		} else {
			result.setResult(apiUserService.confine(id));
		}
		return result;
	}

	/**
	 * 根据用户id获取二课堂用户权限
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/treePermission")
	@ResponseBody
	public Result getEktUserPermissionDetail(int userId) {
		CmsAccount account = getCurrentAccount();
		if (account != null && account.getRole() != CMSConstants.ADMIN && account.getRole() != CMSConstants.ROOT) {

			return Result.getResults(apiUserService.getEktUserPermissionDetail2(account.getEktapiUserId(), userId));
		} else {
			return Result.getResults(apiUserService.getEktUserPermissionDetail(userId));
		}

	}

	/**
	 * 用于标记是否为真实用户
	 * 
	 * @param ektUserId
	 * @param isReal
	 * @return
	 */
	@RequestMapping(value = "/isReal")
	@ResponseBody
	public Result isInternalUser(int ektUserId, int isReal) {
		Result result = Result.getResults();
		result.setResult(apiUserService.isRealUser(ektUserId, isReal == 1 ? isReal : 0));
		return result;
	}

	/**
	 * 修改二课堂用户权限
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/permission/edit")
	@ResponseBody
	@Transactional
	public Result editEktUserPermissionDetail(int userId, String ids, int couponId) {
		int result = 0;
		apiUserService.delPermissionByUserId(userId);
		Gson gson = new Gson();
		List<DiyTreeData> treeData = gson.fromJson(ids, new TypeToken<List<DiyTreeData>>() {
		}.getType());
		for (DiyTreeData data : treeData) {
			result += apiUserService.insertEktUserPermission(userId, data.getId(), data.getExpireTime());

		}
		// 如果优惠券不为0
		if (couponId != 0 && treeData.size() > 0) {
			apiUserService.updateCouponIdUseStatus(couponId);
		}
		return Result.getResults(result);
	}

	/**
	 * 生成CMS账户
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/generate/cmsAccount")
	@ResponseBody
	public Result generateCmsAccount(int userId, int roleId) {
		CmsAccount account = getCurrentAccount();
		if (account == null) {
			return Result.getResults(-1, "请重新登录后再尝试！");
		}
		CmsAccount apiUserAccount = new CmsAccount();
		apiUserAccount.setEktapiUserId(userId);
		apiUserAccount.setRole(roleId);
		if (account != null && account.getRole() != CMSConstants.ADMIN && account.getRole() != CMSConstants.ROOT) {
			apiUserAccount.setParentId(account.getId());
		}
		return Result.getResults(apiUserService.generateCmsAccount(apiUserAccount));

	}

	/**
	 * 根据用户id获取用户事物
	 * 
	 * @param cub
	 * @return
	 */
	@RequestMapping(value = "/business")
	@ResponseBody
	public Result getUserBusinessByUserId(CmsUserBusiness cub) {
		cub.setAcceptanceStatus(0);
		CmsUserBusiness ub = apiUserService.getUserBusiness(cub);// 获取最近一次
																	// 未受理的该用户的事务
		if (ub != null) {// 如果事务不为空就返回该事务
			return Result.getResults(1, ub);
		} else {// 如果未处理事务为空 就直接返回最近一次受理的记录
			cub.setAcceptanceStatus(1);
			return Result.getResults(1, apiUserService.getUserBusiness(cub));
		}

	}

	/**
	 * 用户事物处理action
	 * 
	 * @param cub
	 * @return
	 */
	@RequestMapping(value = "business/acceptance")
	@ResponseBody
	public Result updateUserBusiness(CmsUserBusiness cub) {
		CmsAccount account = getCurrentAccount();
		if (account == null) {
			return Result.getResults(-1, "请重新登录后再尝试！");
		} else {
			cub.setAcceptanceStatus(0);
			CmsUserBusiness userBusiness = apiUserService.getUserBusiness(cub);
			if (userBusiness != null) {
				cub.setAcceptanceStatus(1);
				cub.setAcceptanceAccountId(account.getId());
				cub.setAcceptanceTime(new Date());
				return Result.getResults(apiUserService.acceptanceUserBusiness(cub));
			} else {
				return Result.getResults(-1, "有人已经抢先你一步处treePermission理了！");
			}

		}

	}

	/**
	 * 根据用户Id获取当前用户的事务记录
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "business/list")
	@ResponseBody
	public Result getUserBusinessListByUserId(@RequestParam("userId") int userId) {
		return Result.getResults(apiUserService.getUserBusinessListByUserId(userId));
	}

	/**
	 * 根据用户id获取赠送的课程列表包含未赠送的
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/giftCourse/list")
	@ResponseBody
	public Result giftCourse(@RequestParam("userId") int userId) {
		return Result.getResults(apiUserService.getgiftCourseDetailByUserId(userId));
	}

	/**
	 * 编辑赠送课程
	 * 
	 * @param userId
	 * @param ids
	 * @param couponId
	 * @return
	 */
	@RequestMapping(value = "/giftCourse/edit")
	@ResponseBody
	public Result giftCourseEdit(int userId, String ids, int couponId) {
		int result = 0;
		apiUserService.delGiftCourseByUserId(userId);
		int accountId = getCurrentAccount().getId();
		Gson gson = new Gson();
		List<DiyTreeData> treeData = gson.fromJson(ids, new TypeToken<List<DiyTreeData>>() {
		}.getType());
		for (DiyTreeData data : treeData) {
			result += apiUserService.insertGiftCourse(userId, data.getId(), accountId, data.getExpireTime());
		}
		// 如果优惠券不为0
		if (couponId != 0 && treeData.size() > 0) {
			apiUserService.updateCouponIdUseStatus(couponId);
		}
		return Result.getResults(result);
	}

	@RequestMapping(value = "/batchGenerate", method = { RequestMethod.POST })
	@ResponseBody
	public Result batchGenerate(HttpServletRequest request) {
		Result result = Result.getResults();
		// 方便地得到文件名和文件内容
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 获得文件：
		MultipartFile file = multipartRequest.getFile("fileData");
		// 获取原始文件名
		String sourceName = file.getOriginalFilename();
		System.out.println("上传文件类型为:" + sourceName.substring(sourceName.lastIndexOf(".")) + "文件名为:" + sourceName);
		String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/");
		String msg = "";
		int successCount = 0;
		try {

			InputStream input = file.getInputStream();
			byte[] b = new byte[1048576];
			int length = input.read(b);
			path += "\\" + sourceName;
			// 文件流写到服务器端
			FileOutputStream outputStream = new FileOutputStream(path);
			outputStream.write(b, 0, length);
			input.close();
			outputStream.close();
			// 构造 XSSFWorkbook 对象，strPath 传入文件路径
			XSSFWorkbook xwb = new XSSFWorkbook(path);
			// 读取第一章表格内容
			XSSFSheet sheet = xwb.getSheetAt(0);
			// 定义 row、cell
			XSSFRow row;
			if (sheet.getPhysicalNumberOfRows() < 2) {
				return new Result(-1, "【ERROR】:导入失败 ，请丰富内容后再来尝试导入。");
			}

			for (int i = sheet.getFirstRowNum() + 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				if (row.getPhysicalNumberOfCells() > 2) {
					msg += "【ERROR】: 超出模板的列数，无法识别，请严格按照模板规范。\n";
					break;

				}
				ApiUser user = new ApiUser();
				for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {
					XSSFCell cell = row.getCell(j);
					switch (j) {
					case 0:
						String phone = "";
						if (cell.toString().contains(" ")) {
							phone = cell.toString().replace(" ", "");
						} else {
							phone = new DecimalFormat("#").format(row.getCell(j).getNumericCellValue());
						}

						user.setTelephone(phone);
						break;
					case 1:
						user.setNickname(cell.getStringCellValue());
						;
						break;
					default:
						break;
					}
				}
				if (!TextUtil.isPhoneNum(user.getTelephone())) {
					msg += "【ERROR】:[" + user.getTelephone() + "  -  " + user.getNickname() + "] 不是一个有效的手机号码！\n";
					continue;
				}
				if (apiUserService.getEktUserByTelephone(user.getTelephone()) != null) {
					msg += "【ERROR】:[" + user.getTelephone() + "  -  " + user.getNickname() + "] 的用户已存在，无法被创建。\n";
					continue;
				}
				if (generateUser(user.getTelephone(), user.getNickname()) > 0) {
					successCount += 1;
					msg += "【INFO】:    [" + user.getTelephone() + "  -  " + user.getNickname() + "]  的用户添加成功。\n";
				} else {
					msg += "【WARN】:[" + user.getTelephone() + "  -  " + user.getNickname() + "]  的用户添加失败。\n";
				}
			}

		} catch (Exception e) {
			msg += "【ERROR】:请检查上传的文件是否符合上传规范 ，再来尝试上传。\n";
		} finally {
			File temp = new File(path);
			temp.delete();
		}
		msg += "【INFO】:    成功添加" + successCount + "个用户。\n";
		return Result.getResults(msg);

	}

	/**
	 * 生成用户
	 * 
	 * @param telephone
	 *            手机号码
	 * @param nickname
	 *            昵称 或姓名
	 * @return
	 */
	@Transactional
	public int generateUser(String telephone, String nickname) {

		ApiUser newUser = new ApiUser();
		String username = apiUserService.codeReactor();
		String realname = nickname == null || nickname.equals("") ? username : nickname;
		newUser.setUsername(username);
		newUser.setNickname(realname);
		newUser.setRealName(realname);
		newUser.setTelephone(telephone);
		newUser.setRegisterType("telephone");
		newUser.setPassword(EncryptUtil.encryptPassword(telephone.substring(5, 11)));
		newUser.setHeadPicture(EktHeadPictureUtil.getEktSysHeadPicture(-1));
		int result = apiUserService.addUser(newUser);
		if (result > 0) {
			apiUserService.insertExperience(newUser.getId(), 50, "绑定手机");
			return 1;
		} else {
			return 0;
		}
	};

}
