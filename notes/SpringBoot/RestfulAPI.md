## 简单入门
REST -- REpresentational State Transfer，英语的直译就是“表现层状态转移”

是目前最流行的 API 设计规范，用于 Web 数据接口的设计。

由客户端发出数据操作指令，后台接受决定如何操作。

粗暴解释：用`HTTP`动词（`GET`,`POST`,`PUT`,`PATCH`,`DELETE`)描述操作,其后接URL定位资源。
* `GET`：读取（Read）
* `POST`：新建（Create）
* `PUT`：更新（Update）
* `PATCH`：更新（Update），通常是部分更新
* `DELETE`：删除（Delete）

类似于:

`GET`:http://www.ccc.com/source/id 就是获取指定ID的某一类资源

`POST`:http://www.ccc.com/vips/23 表示为指定ID为23的会员新增好友。

注意!: HTTP动词均为大写！

## 动词的覆盖
有些客户端只能使用`Get`和`POST`这两种方法。服务器必须接受`POST`模拟其他三个方法（`PUT`、`PATCH`、`DELETE`）。

这时，客户端发出的 `HTTP` 请求，要加上`X-HTTP-Method-Override`属性，告诉服务器应该使用哪一个动词，覆盖POST方法。
```http
POST /api/Person/4 HTTP/1.1  
X-HTTP-Method-Override: PUT
```
上面代码中，`X-HTTP-Method-Override`指定本次请求的方法是`PUT`，而不是`POST`。

## 设计原则
`RESTful API`设计原则是无状态的。

所谓无状态即所有的资源都可以`URI`定位，而且这个定位与其他资源无关，也不会因为其他资源的变化而变化。

有状态和无状态的区别，举个例子说明一下，例如要查询员工工资的步骤为

第一步：登录系统。

第二步：进入查询工资的页面。

第三步：搜索该员工。

第四步：点击姓名查看工资。

这样的操作流程就是有状态的，查询工资的每一个步骤都依赖于前一个步骤，只要前置操作不成功，后续操作就无法执行。

如果输入一个`URL`就可以得到指定员工的工资，则这种情况就是无状态的，因为获取工资不依赖于其他资源或状态，且这种情况下，员工工资是一个资源，由一个`URL`与之对应可以通过`HTTP`中的`GET`方法得到资源，这就是典型的`RESTful`风格。

## 服务器回应

客户端的每一次请求，服务器都必须给出回应。回应包括 HTTP 状态码和数据两部分。

<font size=3>状态码:</font>

HTTP 状态码就是一个三位数，分成五个类别。
* 1xx：相关信息
* 2xx：操作成功
* 3xx：重定向
* 4xx：客户端错误
* 5xx：服务器错误

<font size=3>返回信息:</font>

API 返回的数据格式应该为Json格式，所以，服务器回应的`HTTP`头的`Content-Type`属性要设为`application/json`.
```http
HTTP/1.1 400 Bad Request
Content-Type: application/json
{
  "error": "Invalid payoad.",
  "detail": {
     "surname": "This field is required."
  }
}
```

## 参考链接
[RESTful API 最佳实践](http://www.ruanyifeng.com/blog/2018/10/restful-api-best-practices.html) by 阮一峰

[什么是RESTful API？](https://blog.csdn.net/hjc1984117/article/details/77334616) by hjc1984117


