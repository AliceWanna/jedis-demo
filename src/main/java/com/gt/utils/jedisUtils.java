package com.gt.utils;


import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class jedisUtils {

    private static Jedis jedis;
    static {
        InputStream is = jedisUtils.class.getClassLoader().getResourceAsStream("jedis.properties");
        Properties pros = new Properties();
        try {
            pros.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String ip = pros.getProperty("ip");
        int port = Integer.parseInt(pros.getProperty("port"));
        String auth = pros.getProperty("auth");
        jedis = new Jedis(ip,port);
        jedis.auth(auth);
    }
    public static Jedis getJedis(){
        return jedis;
    }

    public static void closeJedis(Jedis jedis){
        jedis.close();
    }
}
