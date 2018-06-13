package com.battcn.interceptor;

import com.battcn.annotation.CacheLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * redis 方案
 *
 * @author Levin
 * @since 2018/6/12 0012
 */
@Aspect
@Configuration
public class LockMethodInterceptor {

    @Autowired
    public LockMethodInterceptor(StringRedisTemplate lockRedisTemplate, CacheKeyGenerator cacheKeyGenerator) {
        this.lockRedisTemplate = lockRedisTemplate;
        this.cacheKeyGenerator = cacheKeyGenerator;
    }

    private final StringRedisTemplate lockRedisTemplate;
    private final CacheKeyGenerator cacheKeyGenerator;


    @Around("execution(public * *(..)) && @annotation(com.battcn.annotation.CacheLock)")
    public Object interceptor(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        CacheLock lock = method.getAnnotation(CacheLock.class);
        if (StringUtils.isEmpty(lock.prefix())) {
            throw new RuntimeException("lock key don't null...");
        }
        final String lockKey = cacheKeyGenerator.getLockKey(pjp);
        try {
            final Boolean success = lockRedisTemplate.opsForValue().setIfAbsent(lockKey, "1");
            if (!success) {
                // TODO 按理来说 我们应该抛出一个自定义的 CacheLockException 异常;这里偷下懒
                throw new RuntimeException("请勿重复请求");
            }
            lockRedisTemplate.expire(lockKey, lock.expire(), lock.timeUnit());
            try {
                return pjp.proceed();
            } catch (Throwable throwable) {
                throw new RuntimeException("系统异常");
            }
        } finally {
            // TODO 如果演示的话需要注释该代码;实际应该放开
            // lockRedisTemplate.delete(lockKey);
        }
    }
}
