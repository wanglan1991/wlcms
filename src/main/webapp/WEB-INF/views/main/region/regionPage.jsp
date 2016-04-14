<%--
  Created by IntelliJ IDEA.
  User: sxjun
  Date: 15-9-3
  Time: 上午8:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title></title>
</head>
<body>



<div id="content-wrapper">
<div class="table-responsive" id="totalElement">
	<div class="row">
				<div class="col-xs-3"><input type="text" id="queryRegionName" class="form-control input-sm mb15" placeholder="请输入查询地域名称"></div>
				<div class="col-xs-2"><input type="text" id="queryLevel" class="form-control input-sm mb15" placeholder="请输入地域级别"></div>
<!-- 				<input type="text" id="queryRegionName" class="form-control input-sm mb15" placeholder="请输入名称"> -->
<!-- 					<select class="form-control" id="level" name="level" -->
<!-- 									onchange="showItem();"> -->
<!-- 									<option value="-1">--请选择--</option> -->
<!-- 									<option value="0">国家</option> -->
<!-- 									<option value="1">省</option> -->
<!-- 									<option value="2">市</option> -->
<!-- 									<option value="3">区</option> -->
<!-- 									<option value="4">街道</option> -->
<!-- 					</select> -->
<!-- <input type="text" id="queryRegionName" class="form-control input-sm mb15" placeholder="请输入名称"> -->
				<button type="button" class="btn btn-success" onclick="loadData()">查询</button>
				<button type="button" class="btn btn-success" data-toggle="modal" data-target="#addRegionModal">新增</button>
			
	</div>
</div>
	
	<table id="RegionListTable" class="table table-striped table-hover table-bordered responsive"></table>
	
	<div class="modal fade" id="addRegionModal" tabindex="-1" role="dialog"
		aria-labelledby="addRegionModal" aria-hide="true">
		<form id="addRegionForm">
			<input type="hidden" id="parentId" name="parentId" />
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hide="true">&times;</button>
						<h4 class="modal-title">新增地域</h4>
					</div>
					<div class="modal-body">

						<div class="form-group">
							<div id="info"></div>
							<label for="regionName" class="col-sm-2 control-label">地域名称</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="regionName"
									name="regionName" required placeholder="请输入地域名称">
							</div>
						</div>
						<div class="form-group">
							<label for="level" class="col-sm-2 control-label">地域级别</label>
							<div class="col-sm-10">
								<select class="form-control" id="level" name="level" onchange="showItem();">
									<option value="-1">--请选择--</option>
<!-- 									<option value="0">国家</option> -->
									<option value="1">省</option>
									<option value="2">市</option>
									<option value="3">区</option>
									<option value="4">街道</option>
								</select>
							</div>
						</div>
						<div class="form-group" id="countryDiv">
							<label for="country" class="col-sm-2 control-label">国家</label>
							<div class="col-sm-10">
								<select class="form-control" id="country" onchange="loadRegion('country','province')">
									<option value='1' selected>中国</option>
								</select>
							</div>
						</div>
						<div class="form-group" id="provinceDiv">
							<label for="province" class="col-sm-2 control-label">省份</label>
							<div class="col-sm-10">
								<select class="form-control" id="province" name="province"
									onchange="loadRegion('province','city')">
									<option value="-1">--请选择--</option>
<!-- 									<option value="2">湖南省</option> -->
								</select>
							</div>
						</div>
						<div class="form-group" id="cityDiv">
							<label for="city" class="col-sm-2 control-label">市</label>
							<div class="col-sm-10">
								<select class="form-control" id="city"
									onchange="loadRegion('city','area')">
									<option value="-1">--请选择--</option>
								</select>
							</div>
						</div>
						<div class="form-group" id="areaDiv">
							<label for="area" class="col-sm-2 control-label">区</label>
							<div class="col-sm-10">
								<select class="form-control" id="area"
									onchange="loadRegion('area','street')">
									<option value="-1">--请选择--</option>
								</select>
							</div>
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button class="btn btn-primary" onclick="setParentId()" id="btnAddRegion">保存</button>
					</div>
				</div>
			</div>
		</form>
	</div>

<!-- /.modal-content -->

<!-- update modal -->
<div class="modal fade" id="updateRegionModal" tabindex="-1" role="dialog"
		aria-labelledby="updateRegionModal" aria-hide="true">
		<form id="addRegionForm">
		<input type="hidden" id="updateId" name="updateId" />
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hide="true">&times;</button>
						<h4 class="modal-title">修改地域名称</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<div id="info"></div>
							<label for="updateRegionName" class="col-sm-2 control-label">地域名称</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="updateRegionName" name="updateRegionName" required >
							</div>
						</div>
						<div class="form-group">
							<label for="updateLevel" class="col-sm-2 control-label">地域级别</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="updateLevelName" name="updateLevelName" readonly required >
							</div>
						</div>
						<div class="form-group">
							<label for="updateLevel" class="col-sm-2 control-label">父级地域</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="updateParentName" name="updateParentName" readonly required >
							</div>
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-default" onclick="submitUpdate();">保存</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>






<script src="${path}/static/js/market/region/regionPageList.js"></script>
<script type="text/javascript">
	function showItem() {
		var control = $("#level").val();
		if (control == '0') {
			$("#countryDiv").hide();
			$("#provinceDiv").hide();
			$("#cityDiv").hide();
			$("#areaDiv").hide();
		}
		if (control == '1') {
			$("#countryDiv").show();
			$("#provinceDiv").hide();
			$("#cityDiv").hide();
			$("#areaDiv").hide();
		}
		if (control == '2') {
			$("#countryDiv").show();
			$("#provinceDiv").show();
			$("#cityDiv").hide();
			$("#areaDiv").hide();
		}
		if (control == '3') {
			$("#countryDiv").show();
			$("#provinceDiv").show();
			$("#cityDiv").show();
			$("#areaDiv").hide();
		}
		if (control == '4') {
			$("#countryDiv").show();
			$("#provinceDiv").show();
			$("#cityDiv").show();
			$("#areaDiv").show();
		}
	}
	
	function setParentId() {
		var control = $("#level").val();
		if (control == '0') {
			$("#parentId").val(0);
		}
		if (control == '1') {
			alert($("#country").val());
			$("#parentId").val($("#country").val());
		}
		if (control == '2') {
			$("#parentId").val($("#province").val());
		}
		if (control == '3') {
			$("#parentId").val($("#city").val());
		}
		if (control == '4') {
			$("#parentId").val($("#area").val());
		}
	}

</script>
</body>

</body>
</html>


