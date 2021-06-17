package cn.tedu.sp08;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
@EnableHystrixDashboard//启用仪表盘
@EnableDiscoveryClient//注册发现客户端
@SpringBootApplication
public class Sp08HystrixDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sp08HystrixDashboardApplication.class, args);
	}

}
