package vttp.ssf.assessment.eventmanagement.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    
    @Value("${spring.redis.host}") // Railway: REDIS_HOST
    private String redisHost;
    @Value("${spring.redis.port}") // Railway: REDIS_PORT
    private Integer redisPort;
    @Value("${spring.redis.username}") // Railway: REDIS_USERNAME
    private String redisUser;
    @Value("${spring.redis.password}") // Railway: REDIS_PASSWORD
    private String redisPassword;
    @Value("${spring.redis.database}") // Railway: REDIS_DATABASE
    private Integer redisDatabase;

    @Bean("eventRedisTemplate")
    public RedisTemplate<String, String> createRedisTemplate() {
        final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(redisHost);
        config.setPort(redisPort);
        config.setDatabase(redisDatabase);
        if (!redisUser.isEmpty()) {
            config.setUsername(redisUser);
        }
        if (!redisPassword.isEmpty()) {
            config.setPassword(redisPassword);
        }
        
        JedisClientConfiguration jedisClient = JedisClientConfiguration.builder().build();

        JedisConnectionFactory jedisFac = new JedisConnectionFactory(config, jedisClient);
        jedisFac.afterPropertiesSet();
        // Create template with the client
        final RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisFac);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new StringRedisSerializer());
        return template;
    }
}
