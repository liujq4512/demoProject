package com.redis;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by liujq on 17-11-28.
 */
public class RedisTest {
  public static void main(String[] args) {
    Jedis jedis = new Jedis("localhost");
    System.out.println(jedis.ping());

    List<String> list = jedis.lrange("list", 0, 5);
    System.out.println(list);
  }
}
