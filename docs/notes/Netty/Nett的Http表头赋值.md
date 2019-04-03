```java
DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
        HttpResponseStatus.NOT_FOUND, Unpooled.copiedBuffer(JSON.toJSONString(request.getUri()), CharsetUtil.UTF_8)) ;
HttpHeaders.setHeader(response,"content-type","application/json");
HttpHeaders.setContentLength(response,response.content().readableBytes());
```