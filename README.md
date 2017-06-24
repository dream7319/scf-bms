# scf-bms
git init	初始化<br/>
git add .	添加文件夹<br/>
git add xxx 添加文件<br/>
git commit -m "xxx" 提交<br/>
git remote add origin git@github.com:dream7319/scf-bms.git 添加文件到远程库<br/>
git pull origin master 从远程库中pull数据<br/>
git push -f origin master 把本地文件push到远程库<br/>
git push -u origin master 第一次推送master分支的所有内容<br/>
git status  查看当前版本库状态<br/>
git checkout -- xxx 撤销修改版本--未commit<br/>
git reset HEAD xxx  撤销修改 已经commit 未push<br/>
git log 查看日志<br/>
git log --pretty=oneline	查看日志<br/>
git reset --hard HEAD^	回退到上一个版本<br/>
git reset --hard HEAD^^	回退到上上一个版本<br/>
git rm xxx 删除<br/>
git clone git@github.com:dream7319/scf-bms.git克隆<br/>
Git支持多种协议，包括https，但通过ssh支持的原生git协议速度最快<br/>
git checkout -b xxx 创建+切换xxx分支(git checkout命令加上-b参数表示创建并切换)<br/>
git branch 查看当前分支<br/>
git checkout master 切换到master 分支<br/>
git merge xxx 合并xxx分支到master分支<br/>
git merge命令用于合并指定分支到当前分支<br/>
git branch -d xxx 删除xxx分支<br/>
git log --graph --pretty=oneline --abbrev-commit查看分支合并图<br/>
git push origin xxx (xxx为分支名称，把分支xxx push到git库)<br/>
git branch -D <name> 丢弃一个没有被合并过的分支<br/>
git remote 查看远程库的信息<br/>
git remote -v显示更详细的信息<br/>
多人协作的工作模式通常是这样：<br/>
首先，可以试图用git push origin branch-name推送自己的修改；<br/>
如果推送失败，则因为远程分支比你的本地更新，需要先用git pull试图合并；<br/>
如果合并有冲突，则解决冲突，并在本地提交；<br/>
没有冲突或者解决掉冲突后，再用git push origin branch-name推送就能成功！<br/>
如果git pull提示“no tracking information”，则说明本地分支和远程分支的链接关系没有创建，<br/>
用命令git branch --set-upstream branch-name origin/branch-name。<br/>
git tag xxx创建标签xxx<br/>
git tag 查看标签<br/>
git tag xxx commit id 根据commit id 创建标签( git log --pretty=oneline --abbrev-commit查看commit id)<br/>
git show <tagname>查看标签信息<br/>
git tag -a v0.1 -m "xxx" commit id 创建带有说明的标签，用-a指定标签名，-m指定说明文字<br/>
git tag -s v0.2 -m "xxx" commit id-s用私钥签名一个标签<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>