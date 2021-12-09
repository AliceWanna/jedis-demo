package com.gt.utils;

import java.util.Random;

public class randomUtils {

    public static String randomCode(int num){
        Random random = new Random();
        StringBuilder codeSum = new StringBuilder();
        for(int i = 0;i<num;i++){
            int code = random.nextInt(10);
            codeSum.append(code);
        }
        return codeSum.toString();
    }
}
