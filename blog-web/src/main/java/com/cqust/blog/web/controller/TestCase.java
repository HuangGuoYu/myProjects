package com.cqust.blog.web.controller;

import com.cqust.blog.common.resp.GeneralResult;
import com.cqust.blog.web.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * Created by Administrator on 2018/3/24.
 */
@Controller
@RequestMapping("/test")
public class TestCase extends BaseController {

    @Autowired private StringRedisTemplate template;

    @RequestMapping("/jedis")
    @ResponseBody
    public String testJedis() {
        String str = "hello redis";

        template.opsForValue().set("huang", str);

        return template.opsForValue().get("huang");
    }
}
