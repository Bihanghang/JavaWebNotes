package com.bihang.seaya.environment;

import lombok.Data;

/**
 * 环境变量
 * 綫程不安全的单例模式，因为用不到多线程
 * * Created By bihang
 * 2018/12/25 11:45
 */
@Data
public class Environment {

    private int port = 8007;

    private String version = "v1.0.0";

    private String text = "Hello World!";

    private String rootPackageName = "";

    private static Environment environment = null;

    public static Environment getInstance() {
        if (environment == null)
            environment = new Environment();
        return environment;
    }

}
