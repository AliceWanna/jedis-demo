package com.gt.jedis;

import com.gt.utils.jedisUtils;
import redis.clients.jedis.Jedis;

public class jedisDemo1 {



    public static void main(String[] args) {

        Jedis jedis = jedisUtils.getJedis();
        System.out.println(jedis.ping());
        String je = jedis.get("je");
        if(je == null)
            System.out.println("yes");
    }
}
