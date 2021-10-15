package Training.SampleApplication;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import Training.SampleApplication.model.Employee;

@Configuration
public class AppConfig {
	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		return new LettuceConnectionFactory();
	}
	
	@Bean
	public RedisTemplate<String,Employee> redisTemplate(){
		  RedisTemplate<String, Employee> empTemplate = new RedisTemplate<>();
	      empTemplate.setConnectionFactory(redisConnectionFactory());
	      return empTemplate;
	}
}
