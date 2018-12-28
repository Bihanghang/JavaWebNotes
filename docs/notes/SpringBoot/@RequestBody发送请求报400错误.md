### 参数不使用@RequestBody
在使用Postman进行Post请求时，通常做法是填入`key`和`value`的值即可。

### 参数使用@RequestBody
使用`@RequestBody`注解时，在发送请求时，就需要在Postman的“headers”添加key:`Content-Type`,value:`application/json`<br>
在"body"中选择"raw"添加json数据，类似于:<br>
```json
{"appId":3}
```