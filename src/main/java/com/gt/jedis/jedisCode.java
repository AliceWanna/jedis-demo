package com.gt.jedis;





import com.gt.utils.jedisUtils;
import com.gt.utils.randomUtils;
import redis.clients.jedis.Jedis;

import java.util.Scanner;

public class jedisCode {

    //要求：
//        1、输入手机号，点击发送后随机生成6位数字码，2分钟有效
//        2、输入验证码，点击验证，返回成功或失败
//        3、每个手机号每天只能输入3次
    public static void main(String[] args) {
        String code = getCode("8717");
        if(code == null){
            System.out.println("验证码超过三次!");
            return;
        }
        System.out.println("验证码为:" + code);
        System.out.println("输入验证码:");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        boolean b = veryfiyCode("8717", next);
        if(b)
            System.out.println("验证成功!");
        else
            System.out.println("验证失败!");
    }

    public static boolean veryfiyCode(String phone,String userCode) {
        Jedis jedis = jedisUtils.getJedis();
        String codeKey = "ver" + phone + "code";
        String code = jedis.get(codeKey);
        jedis.close();
        return code.equals(userCode);
    }

        public static String getCode(String phone){
        Jedis jedis = jedisUtils.getJedis();
        String countKey = "ver" + phone + "count";
        String codeKey = "ver" + phone + "code";
        String count = jedis.get(countKey);
        if(count == null){
            jedis.setex(countKey,24*60*60L,"1");
            String randomCode = randomUtils.randomCode(6);
            jedis.setex(codeKey,2*60L,randomCode);
            jedis.close();
            return randomCode;
        }
        int countNum = Integer.parseInt(count);
        if(countNum >2){
            jedis.close();
            return null;
        }
        else {
            countNum++;
            jedis.set(countKey,String.valueOf(countNum));
            String randomCode = randomUtils.randomCode(6);
            jedis.setex(codeKey,2*60L, randomCode);
            jedis.close();
            return randomCode;
        }
    }
}
