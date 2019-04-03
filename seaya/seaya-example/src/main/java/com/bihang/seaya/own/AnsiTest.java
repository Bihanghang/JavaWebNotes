package com.bihang.seaya.own;

import lombok.extern.slf4j.Slf4j;

/**
 * Created By bihang
 * 2018/12/26 19:33
 */
@Slf4j
public class AnsiTest {
    public static void main(String[] args) {
        System.out.println(String.format("%1$70s", "John"));
        log.info(String.format("%1$3s", "__")+String.format("%1$8s", "__"));
        log.info("|__"+String.format("%1$8s", "|__"));
        log.info(String.format("%1$4s", "__|")+String.format("%1$7s", "|__"));
        log.info("__  __   _       _");
        log.info("}_  }_  /_\\ |_| /_\\");
        log.info("__| }_  { }  _| { }");
    }
}
