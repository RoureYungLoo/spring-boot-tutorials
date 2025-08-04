package com.xxx.springboot15redis;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class Springboot15RedisApplicationTests {
  @Autowired
  private RedisTemplate<String, Object> redisTemplate;

  @Test
  void contextLoads() {
  }

  @Test
  public void testCommonCMD() {
    System.out.println(redisTemplate.keys("*"));
    System.out.println(redisTemplate.hasKey("langs"));
    System.out.println(redisTemplate.type("langs"));
    System.out.println(redisTemplate.delete("langs"));
    System.out.println(redisTemplate.hasKey("langs"));
  }

  @Test
  public void testString() {
    ValueOperations<String, Object> ops = redisTemplate.opsForValue();
    ops.set("name", "lisi", 60, TimeUnit.SECONDS);
    ops.set("address", "zhengzhou", 60, TimeUnit.SECONDS);
    try {
      ops.setIfAbsent("lock", "mylock", 60, TimeUnit.SECONDS);
    } catch (Exception e) {
      System.out.println("setnx lock failed");
      // throw new RuntimeException(e);
    }
    Object o1 = ops.get("name");
    System.out.println(o1);
    Object o2 = ops.get("address");
    System.out.println(o2);
    String o3 = (String) ops.get("lock");
    System.out.println(o3);
    
  }

  @Test
  public void testList() {
    ListOperations<String, Object> ops = redisTemplate.opsForList();
    if (Boolean.TRUE.equals(redisTemplate.hasKey("langs"))) {
      redisTemplate.delete("langs");
    }

    ops.leftPush("langs", "C");
    ops.rightPush("langs", "C++");
    ops.leftPush("langs", "Java");
    ops.rightPush("langs", "Python");

    List<Object> langs = ops.range("langs", 0, -1);
    System.out.println(langs);

    System.out.println(ops.rightPop("langs"));
    System.out.println(ops.leftPop("langs"));

    System.out.println(ops.range("langs", 0, -1));

    System.out.println(ops.size("langs"));

  }

  @Test
  public void testSet() {
    SetOperations<String, Object> ops = redisTemplate.opsForSet();
    redisTemplate.delete(Arrays.asList("langs", "hotLangs", "allLangs"));

    ops.add("langs", "c", "c++", "java", "python");
    ops.add("hotLangs", "hongmeng", "java");

    Set<Object> intersect = ops.intersect("langs", "hotLangs");
    System.out.println(intersect);
    System.out.println(ops.size("langs"));


    Set<Object> union = ops.union("langs", "hotLangs");
    System.out.println(union);
    ops.unionAndStore("langs", "hotLangs", "allLangs");

    System.out.println(ops.members("allLangs"));

  }

  @Test
  public void testZSET() {
    ZSetOperations<String, Object> ops = redisTemplate.opsForZSet();
    if (Boolean.TRUE.equals(redisTemplate.hasKey("langs"))) {
      redisTemplate.delete("langs");
    }
    ops.add("langs", "c", 0.11);
    DefaultTypedTuple<String> tuple = new DefaultTypedTuple<>("c++", 0.12);

    Set<ZSetOperations.TypedTuple<Object>> set = new HashSet<>();
    Collections.addAll(set,
        new DefaultTypedTuple<Object>("c++", 0.12),
        new DefaultTypedTuple<Object>("java", 0.13),
        new DefaultTypedTuple<Object>("golang", 0.14),
        new DefaultTypedTuple<Object>("lua", 0.15),
        new DefaultTypedTuple<Object>("rust", 0.16)
    );

    ops.add("langs", set);
    ops.add("langs", "lua", 0.1);

    System.out.println(ops.range("langs", 0, -1));
    System.out.println(ops.rangeWithScores("langs", 0, -1));
  }

  @Test
  public void testHASH() {

    HashOperations<String, Object, Object> ops = redisTemplate.opsForHash();
    ops.put("p1", "name", "lisi");
    ops.put("p1", "address", "zhengzhou");
    ops.put("p1", "age", 60);

    ops.delete("p1", "address");

    List<Object> p1 = ops.values("p1");
    System.out.println(p1);

    Set<Object> keys = ops.keys("p1");
    System.out.println(keys);

  }

}
