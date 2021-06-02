//package cn.shizg.study.project.util.config.redis;
//
//import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnection;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.RedisOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
///**
// * lettuce 整合
// */
//@Configuration
//@ConditionalOnClass(RedisOperations.class)
//@EnableConfigurationProperties(RedisProperties.class)
//public class RedisLettueConfig {
//
//    /**
//     * redis 相关配置
//     *
//     * @param redisConnectionFactory
//     * @return
//     */
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory redisConnectionFactory) {
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate();
//        redisConnectionFactory.setValidateConnection(true);
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//
//        FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);
//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//        // key采用String的序列化方式
//        redisTemplate.setKeySerializer(stringRedisSerializer);
//        // hash的key也采用String的序列化方式
//        redisTemplate.setHashKeySerializer(stringRedisSerializer);
//        // valuevalue采用jackson序列化方式
//        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
//        // hash的value采用jackson序列化方式
//        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }
//
//    @Bean
//    public RedisConnection redisConnection(LettuceConnectionFactory lettuceConnectionFactory){
//        return lettuceConnectionFactory.getConnection();
//    }
//
//
//}
