package com.bihang.seaya.log;

import com.bihang.seaya.environment.Environment;
import lombok.extern.slf4j.Slf4j;

/**
 * Created By bihang
 * 2018/12/29 11:14
 */
@Slf4j
public class PrintSeayaLogo {

    public static Environment environment = Environment.getInstance();

    public static void print(){
        String NEW_LINE = "\r\n";
        log.info("Seaya is starting..."+NEW_LINE+
                String.format("%1$54s","__  __   _       _")+NEW_LINE+
                String.format("%1$55s","}_  }_  /_\\ |_| /_\\")+NEW_LINE+
                String.format("%1$55s","__| }_  { }  _| { }")+NEW_LINE+
                String.format("%1$50s","::"+environment.getVersion()+"::")+NEW_LINE);

    }
}
