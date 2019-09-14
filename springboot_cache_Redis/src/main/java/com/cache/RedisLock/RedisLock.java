package com.cache.RedisLock;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Auther: jerry.feng
 * @Date: 2019/9/10
 * @Description:
 */
//@Component
public class RedisLock {


    private RedisTemplate redisTemplate;


    private static String INSTANCE_ID = new GuidUtil().newGuid();

    /**
     * 重试时间
     */
    private static final int DEFAULT_ACQUIRY_RETRY_MILLIS = 100;
    /**
     * 锁的后缀
     */
    private static final String LOCK_SUFFIX = "_redis_lock";
    /**
     * 锁的key
     */
    private String lockKey;
    /**
     * 锁超时时间，防止线程在入锁以后，防止阻塞后面的线程无法获取锁
     */
    private int expireMsecs = 10 * 1000;
    /**
     * 线程获取锁的等待时间
     */
    private int timeoutMsecs = 5 * 1000;
    /**
     * 是否锁定标志
     */
    private volatile boolean locked = false;

    /**
     * 构造器
     * @param redisTemplate
     * @param lockKey 锁的key
     */
    public RedisLock(RedisTemplate redisTemplate, String lockKey) {
        this.redisTemplate = redisTemplate;
        this.lockKey = lockKey + LOCK_SUFFIX;
    }

    public RedisLock( String lockKey) {
        this.lockKey = lockKey + LOCK_SUFFIX;
    }



    /**
     * 封装和jedis方法
     * @param key
     * @param value
     * @return
     */
    private boolean setNX(final String key, final String value) {
        return redisTemplate.opsForValue().setIfAbsent(key,value);
    }

    /**
     * 封装和jedis方法
     * @param key
     * @param value
     * @return
     */
    private String getSet(final String key, final String value) {
        Object obj = redisTemplate.opsForValue().getAndSet(key,value);
        return obj != null ? (String) obj : null;
    }

    /**
     * 封装和jedis方法
     * @param key
     * @return
     */
    private String get(final String key) {
        Object obj = redisTemplate.opsForValue().get(key);
        return obj != null ? obj.toString() : null;
    }


    /**
     * 获取锁
     * @return 获取锁成功返回ture，超时返回false
     * @throws InterruptedException
     */
    public synchronized boolean lock() throws InterruptedException {
        int timeout = timeoutMsecs;
        while (timeout >= 0) {
            long expires = System.currentTimeMillis() + expireMsecs + 1;//过期时间
            String expiresStr = String.valueOf(expires)+"_"+INSTANCE_ID; //锁到期时间+实例ID
            boolean b = this.setNX(lockKey, expiresStr);
            System.out.println(b);
            if (b) {
                locked = true;
                return true;
            }
            //redis里key的时间
            String currentValue = this.get(lockKey);
            if (currentValue != null){
                String[] currentValues = currentValue.split("_");
                //判断锁是否已经过期，过期则重新设置并获取
                if (Long.parseLong(currentValues[0]) < System.currentTimeMillis()) {
                    //设置锁并返回旧值
                    String oldValue = this.getSet(lockKey, expiresStr);
                    if (oldValue != null){
                        String[] oldValues = oldValue.split("_");
                        //比较锁的时间，如果不一致则可能是其他锁已经修改了值并获取
                        if (oldValues[0].equals(currentValues[0])&&INSTANCE_ID.equals(currentValues[1])) {
                            locked = true;
                            return true;
                        }
                    }
                }
            }
            timeout -= DEFAULT_ACQUIRY_RETRY_MILLIS;
            //延时
            Thread.sleep(DEFAULT_ACQUIRY_RETRY_MILLIS);
        }
        return false;
    }


    /**
     * 释放获取到的锁
     */
    public synchronized void unlock() {
//        if (locked) {
//            redisTemplate.delete(lockKey);
//            locked = false;
//        }
        /*
        因为setNX没成功后会get是否为null
        没有null才会塞进去，所以现在直接delete
        是没逻辑问题的
         */
        String currentValue = this.get(lockKey);
        if (currentValue!=null){
            String[] currentValues = currentValue.split("_");
            if (INSTANCE_ID.equals(currentValues[1])){
                redisTemplate.delete(lockKey);
                System.out.println("redis lock unlock ...");
            }
        }
        locked = false;
    }


    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
