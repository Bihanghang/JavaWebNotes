Windows用的注册表，感觉略麻烦。

关闭Navicat
Win + R，输入regedit回车
删除HKEY_CURRENT_USER\Software\PremiumSoft\Data
展开HKEY_CURRENT_USER\Software\Classes\CLSID
展开每一个子文件夹，如果里面只包含一个名为Info的文件夹，就删掉它。 