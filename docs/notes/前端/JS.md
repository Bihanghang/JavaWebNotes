## 引入JQuery的两种方式
```html
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
<script src="jquery.js"></script>
</head>
```
## JQuery两种写法
`$(document).ready(function(){});``$(function(){});`
## 小知识
`$('#redisContent').html(html)`html是不会保留的，每次都刷新。
## disabled
html标签input中有disabled,这个属性会使当前要输入或选择的东西不可用，但是我们在设计用户注册表时，当我们同意注册后希望disabled不在是禁用的状态,那就要使disabled=false，解决这个问题还要用到Javascript。
