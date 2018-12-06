### Hexo的安装与初始化
1.确保电脑已经安装`Node.js`，`Git`

打开GUI Bash,输入以下代码安装Hexo:
```
npm install -g hexo-cli
```
2.运行以下命令在目标文件夹初始化Hexo
```
hexo init <folder>
cd <folder>
npm install
```
### 下载主题

**1**. 找到Hexo的主目录，将**themes**文件夹删掉。
**2**. 在此目录下打开**GUI Bash**,输入以下命令重新下载主题重新生成**themes**文件夹：

```
git clone https://github.com/iissnan/hexo-theme-next themes/next
```

**3**. 打开本地的MyBlog文件夹项目内的_config.yml配置文件，将其中的theme设置为next

```
theme: next
```

### 与Github相关联

**1**. 在Github上创建名字为XXX.github.io的项目，XXX为自己的github用户名。
**2**. 打开本地的MyBlog文件夹项目内的_config.yml配置文件，将其中的type设置为git

```
deploy:
  type: git
  repository: https://github.com/Bihanghang/Bihanghang.github.io.git
  branch: master
```

如果报**bash: /dev/tty: No such device or address**的错，就修改**repository**为:

```
ssh://git@github.com/Bihanghang/Bihanghang.github.io.git 
```

运行以下命令:
```
npm install --save hexo-deployer-git
hexo g（本地生成静态文件）
hexo d（将本地静态文件推送至Github）
```

此时，打开浏览器，访问http://Bihanghang.github.io



