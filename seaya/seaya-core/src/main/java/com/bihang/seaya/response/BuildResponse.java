package com.bihang.seaya.response;

import com.alibaba.fastjson.JSON;
import com.bihang.seaya.environment.Environment;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * Created By bihang
 * 2018/12/29 15:15
 */
@Slf4j
public class BuildResponse {
    public static Environment environment = Environment.getInstance();

    public static DefaultFullHttpResponse build(String uri, DefaultHttpRequest request) throws IOException {

        if ("/".equals(uri)){
            DefaultFullHttpResponse response = null;
            ResponJson<String> responJson = new ResponJson<>();
            responJson.setCode(1);
            responJson.setMessage("成功");
            responJson.setData(environment.getText());
            response= new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.NOT_FOUND, Unpooled.copiedBuffer(JSON.toJSONString(responJson), CharsetUtil.UTF_8));
            response.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"application/json");
            return response;
        } else {
            //获取工程包下的所有类
            String rootPackageName = environment.getRootPackageName();
            Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(rootPackageName.replace(".","/"));
            while (dirs.hasMoreElements()){
                URL url = dirs.nextElement();
                //获取包的物理路径
                String url.getFile();
            }

            //获取SeayaMapping

            //执行


        }
        return new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                HttpResponseStatus.NOT_FOUND, Unpooled.copiedBuffer(JSON.toJSONString("Something is wrong"), CharsetUtil.UTF_8));
    }
}
