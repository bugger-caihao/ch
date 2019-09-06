package com.ch;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName: RedisTest
 * @Description: redis的测试类
 * @Author: caihao
 * @Date: 2019/7/27 20:37
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    //  <Object, Object> 键值都为对象类型
    @Autowired
    RedisTemplate redisTemplate;

    //  <String, String> 键值都为String类型的
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * @Description 测试 StringRedisTemplate 操作
     * @Author caihao
     * @Date 2019/7/27 21:13
     * @Param []
     * @Return void
     */
    @Test
    public void test01(){
        //stringRedisTemplate.opsForValue().set("redis", "hello");
        stringRedisTemplate.opsForValue().append("redis", " world!");
        //stringRedisTemplate.delete("redis");//删除key
    }

    /**
     * @Description  测试 redisTemplate 操作
     * @Author caihao
     * @Date 2019/7/30 14:16
     * @Param []
     * @Return void
     */
    @Test
    public void test02() throws JsonProcessingException {
        //UserInfo user = new UserInfo(5l,"蔡浩", MD5Utils.MD5Encode("0926"), "2019-7-30", null);
        /*user.setUserId(5L);
        user.setUserName("蔡浩");
        user.setUserPassword("0926");
        user.setCreateTime("2019-7-30");*/

        //redisTemplate.opsForValue().set("user", user);

        Object user = redisTemplate.opsForValue().get("user");
        System.out.println(user);
    }

}
