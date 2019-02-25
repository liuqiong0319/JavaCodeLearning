package learning.self.jedisDemo;

import org.testng.annotations.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by qiong.liu on 2018/6/12.
 */
public class JedisDemo1 {

    @Test(description = "单例形式")
    public void demo1(){
        Jedis jedis=new Jedis("10.12.36.137",6379);
        jedis.set("name","慎哥哥");
//        jedis.get("name");
        jedis.close();
    }

    @Test(description = "连接池方式连接")
    public void demo2(){
        //获得连接池的配置对象
        JedisPoolConfig config=new JedisPoolConfig();
        //设置最大连接数
        config.setMaxTotal(20);
        //设置最大空闲连接数
        config.setMaxIdle(10);
        JedisPool jedisPool=new JedisPool(config,"10.12.36.137",6379);
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            jedis.set("age","15");
            String value=jedis.get("age");
            System.out.println(value);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放资源
            if(jedis!=null)
                jedis.close();
            if (jedisPool!=null)
                jedisPool.close();
        }

    }
}
