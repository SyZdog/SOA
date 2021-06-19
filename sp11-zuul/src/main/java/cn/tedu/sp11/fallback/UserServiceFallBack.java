package cn.tedu.sp11.fallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import cn.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class UserServiceFallBack implements FallbackProvider{
	//返回一个服务ID,来确定哪个服务失败时，执行这个类中的降级代码
	@Override
	public String getRoute() {
		//可以是具体服务
		return "user-service";
		//也可以是所有的服务。"*"或者null
		//return "*";
		//return null;
	}
	//ClientHttpResponse对象封装了响应数据
	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		// TODO Auto-generated method stub
		return new ClientHttpResponse() {
			
			@Override
			public HttpHeaders getHeaders() {
				//Content-Type:application/json
				HttpHeaders h = new HttpHeaders();
				h.setContentType(MediaType.APPLICATION_JSON);
				return h;
			}
			
			@Override
			public InputStream getBody() throws IOException {
				//响应数据:"{code:400,msg:'请求失败',date:null}"
				String json = JsonResult.err("用户服务请求失败").toString();
				return new ByteArrayInputStream(json.getBytes("UTF-8"));
			}
			
			@Override
			public String getStatusText() throws IOException {
				// TODO Auto-generated method stub
				return HttpStatus.OK.getReasonPhrase();
			}
			//HttpStatus封装了状态码、状态文本
			@Override
			public HttpStatus getStatusCode() throws IOException {
				// TODO Auto-generated method stub
				return HttpStatus.OK;
			}
			
			@Override
			public int getRawStatusCode() throws IOException {
				// TODO Auto-generated method stub
				return HttpStatus.OK.value();
			}
			
			@Override
			public void close() {}
		};
	}

}
