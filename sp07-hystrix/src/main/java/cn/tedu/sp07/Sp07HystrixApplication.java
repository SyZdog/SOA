package cn.tedu.sp07;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

//@EnableCircuitBreaker//启用断路
//@EnableDiscoveryClient
//@SpringBootApplication
@SpringCloudApplication
public class Sp07HystrixApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sp07HystrixApplication.class, args);
	}
	//创建RestTemplate实例，并存入spring容器中
	@LoadBalanced//负载均衡注解
	@Bean
	public RestTemplate getRestTemplate() {
		SimpleClientHttpRequestFactory f = new SimpleClientHttpRequestFactory();
		f.setConnectTimeout(1000);
		f.setReadTimeout(1000);
		return new RestTemplate(f);
	}
}
