package com.bihang.seaya;

import com.bihang.seaya.environment.Environment;

/**
 * Created By bihang
 * 2018/12/29 15:43
 */
public class Seaya {

    public static Environment environment = Environment.getInstance();

    public static void text(String text){
        environment.setText(text);
    }
}
