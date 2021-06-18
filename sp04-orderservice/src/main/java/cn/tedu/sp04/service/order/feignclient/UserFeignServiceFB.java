package cn.tedu.sp04.service.order.feignclient;

import org.springframework.stereotype.Component;

import cn.tedu.sp01.pojo.User;
import cn.tedu.web.util.JsonResult;

@Component
public class UserFeignServiceFB implements UserFeignService{

	@Override
	public JsonResult<User> getUser(Integer userId) {
		if(Math.random() < 0.4) {
			return JsonResult.ok(new User(userId, "缓存 name" + userId, "缓存 pwd" + userId)); 
			
		}
			return JsonResult.err("获取用户信息失败");
	}

	@Override
	public JsonResult addScore(Integer userId, Integer score) {
		return JsonResult.err("增加用户积分失败");
	}

}
