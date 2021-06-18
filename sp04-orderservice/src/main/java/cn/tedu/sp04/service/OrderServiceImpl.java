package cn.tedu.sp04.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.pojo.Order;
import cn.tedu.sp01.pojo.User;
import cn.tedu.sp01.service.OrderService;
import cn.tedu.sp04.service.order.feignclient.ItemFeignService;
import cn.tedu.sp04.service.order.feignclient.UserFeignService;
import cn.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	private ItemFeignService itemService;
	@Autowired
	private UserFeignService userService;
	
	@Override
	public Order getOrder(String orderId) {
		// TODO 调用item-service，获取商品列表
		JsonResult<User> user = userService.getUser(7);
		//调用user-service，获取用户信息
		JsonResult<List<Item>> items = itemService.getItems(orderId);
		
		Order order = new Order();
		order.setId(orderId);
		order.setItems(items.getData());
		order.setUser(user.getData());
		return order;
	}

	@Override
	public void addOrder(Order order) {
		//TODO: 调用 item-service 减少商品库存 
		itemService.decreaseNumbers(order.getItems());
		//TODO: 调用 user-service 增加用户积分
		userService.addScore(7, 100);
		
		log.info("保存订单" + order);
		
	}

}
