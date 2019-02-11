`%~dp0%`用来获取当前脚本路径
```shell
@echo off
set project=car-api-web
%~dp0%project%.exe start
echo The %project% service current state:
%~dp0%project%.exe status
pause
```