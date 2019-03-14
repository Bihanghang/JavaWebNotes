## net.sf.json
1.添加依赖
```
<dependency>
    <groupId>net.sf.json-lib</groupId>
    <artifactId>json-lib</artifactId>
    <classifier>jdk15</classifier><!--指定jdk版本 -->
    <version>2.4</version>
</dependency>
```
2.使用方式
```
JSONObject jsonObject = new JSONObject();
JSONObject jo = new JSONObject();
JSONArray ja = new JSONArray();
PageEntity<Rediscontent> rediscontentPageEntity = rediscontentService.selectByPage(page, pageSize);
for (Rediscontent rediscontent : rediscontentPageEntity.getList()) {
    JSONObject jo1 = new JSONObject();
    jo1.put("rediscontent", rediscontent);
    ja.add(jo1);
}
jo.put("redisContents", ja);
jo.put("count", rediscontentPageEntity.getCount());
String json = "";
json = JSONUtils.valueToString(jo);
```
## fastjson
1.添加依赖
```
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.31</version>
</dependency>
```
2.将对象转为json数据
```
JSON.toJSONString(对象)
```
3.将json字符串转化为对象列表
```java
String json = "[{\"userId\":\"2\",\"userName\":\"bihang\"},{\"userId\":\"2\",\"userName\":\"bihang\"},{\"userId\":\"2\",\"userName\":\"bihang\"}]";
List<User> users = JSON.parseObject(json, new TypeReference<List<User>>() {
});
```
4.根据json自动生成对象

http://jsongen.byingtondesign.com/

5.Json映射对象失败多半是因为没有写Get，Set方法。

## com.fasterxml.jackson
1.添加依赖
```
version:2.5.0
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-annotations</artifactId>
</dependency>

<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-core</artifactId>
</dependency>

<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
</dependency>
```
2.使用ObjectMapper将Object转为json
```
String json = "";
ObjectMapper objectMapper = new ObjectMapper();
json = objectMapper.writeValueAsString(jo);
```
## Gson
```java
new Gson().toJson(proxyReq)
new Gson().fromJson(httpResult, new TypeToken<Train_ProxyBookingOrderResponse>() {}.getType()
```
1.将Json字符串转换成JsonObject对象：
```java
JsonObject returnData = new JsonParser().parse(jsonstr).getAsJsonObject();
```