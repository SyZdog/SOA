package cn.tedu.sp03.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;

import cn.tedu.sp01.pojo.User;
import cn.tedu.sp01.service.UserService;
import cn.tedu.web.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;

@RefreshScope
@Slf4j
@Service
public class UserServiceImpl implements UserService{
	@Value("${sp.user-service.users}")
	private String userJson;
	@Override
	public User getUser(Integer id) {
		List<User> list = JsonUtil.from(userJson, new TypeReference<List<User>>(){});
		for (User user : list) {
			return user;
		}
		return new User(id,"name:" + id, "pwd:" + id);
				
	}

	@Override
	public void addScore(Integer id, Integer score) {
		//增加积分
		log.info("user" + id + "-增加积分" + score);
	}
}
