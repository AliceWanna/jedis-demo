package com.gt.jedis;

import com.gt.utils.jedisUtils;
import redis.clients.jedis.Jedis;

public class killdemo1 {


    public static void main(String[] args) {

    }

    public static boolean doKill(String uid,String pid){
        if(uid == null | pid == null){
            return false;
        }
        Jedis jedis = jedisUtils.getJedis();
        String kcKey = "prokill:" + pid + ":pro";
        String userKey = "prokill:" + uid + ":user";

        jedis.
        return false;
    }
}
