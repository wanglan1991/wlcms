#reademe
####### git 常用操作 以git@OSC为例

####准备工作
# 绑定本地ssh密钥到git@OSC账户
# fork添加项目到我的分支
# 克隆我的分支ssh地址到本地git 默认为origin分支
$ git clone [sshURL]
# 设置线上主分支为upstream上游分支
$ git remote add upstream [sshURL]

# 代码合并、提交工作
#步骤1.新增本地改动 同git add ./ 或 git add --all
$ git add .

#步骤2.添加改动注释
$ git commit -m "改动注释"

#步骤3.拉取最新主分支
$ git fetch upstream

#步骤4.合并主分支到本地
$ git merge upstream/master

#步骤5.提交到改动到我的分支（默认分支origin）
$ git push origin

#步骤6.登录git@OSC进入我的最新提交master分支，pull Request 提交到主分支

#步骤7.若提示代码无冲突则合并pull Request ；否则关闭pull Request 、解决冲突后 从步骤1执行

####需求和步骤
1.合并线上代码步骤：1、2、3、4
2.提交到我的线上分支步骤：1、2、5
3.提交到我的线上分支并合并到线上主分支步骤：1、2、3、4、5、6、7