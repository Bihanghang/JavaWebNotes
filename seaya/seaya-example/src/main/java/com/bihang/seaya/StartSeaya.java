package com.bihang.seaya;


import static com.bihang.seaya.Seaya.text;

/**
 * Created By bihang
 * 2018/12/21 17:31
 */
public class StartSeaya {
    public static void main(String[] args) throws Exception {
        new SeayaServer().start(()->{ text("Who are you?"); });
//        new SeayaServer().start(8009);
    }
}
