
找到想要新建项目的位置，打开Git Bush输入:
```
git clone git@github.com:michaelliao/learngit.git
```
默认情况下，只能看到本地的master分支。不信可以用git branch命令看看

要在dev分支上开发,先创建本地dev分支，再创建远程origin的dev分支到本地：
```
git checkout -b dev
git checkout -b dev origin/dev
```
接下来就和之前一个工作点一样操作了
```
git add env.txt
git commit -m "add env"
git push origin dev
```