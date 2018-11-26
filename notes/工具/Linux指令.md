* 查看当前目录:pwd
* 提示su: Authentication failure 只要sudo passwd root过一次之后，下次再su的时候只要输入密码就可以成功登录了
* 建立文件:touch

FROM first-docker-commit:0.1

RUN echo "deb http://archive.ubuntu.com/ubuntu precise main universe">/etc/apt/sources.list
RUN wget -qO- https://get.docker.com/gpg | sudo apt-key add -
RUN apt-get update
RUN apt-get-y install nginx

RUN echo "daemon off;">>/etc/nginx/nginx.conf
RUN mkdir /etc/nginx/ssl
ADD default /etc/nginx/sites-available/default

EXPOSE 80

CMD ["nginx"]

RUN mv /etc/apt/sources.list /etc/apt/sources.list.bak && \
echo 'deb http://mirrors.163.com/debian/ jessie main non-free contrib' > /etc/apt/sources.list && \
echo 'deb http://mirrors.163.com/debian/ jessie-updates main non-free contrib' >> /etc/apt/sources.list && \
echo 'deb http://mirrors.163.com/debian-security/ jessie/updates main non-free contrib' >> /etc/apt/sources.list