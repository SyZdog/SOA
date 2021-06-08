package cn.tedu.sp04.service;

import org.springframework.stereotype.Service;

import cn.tedu.sp01.pojo.Order;
import cn.tedu.sp01.service.OrderService;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class OrderServiceImpl implements OrderService{
	
	@Override
	public Order getOrder(String orderId) {
		// TODO 调用item-service，获取商品列表
		//调用user-service，获取用户信息
		Order order = new Order();
		order.setId(orderId);
		return order;
	}

	@Override
	public void addOrder(Order order) {
		//TODO: 调用 item-service 减少商品库存 
		//TODO: 调用 user-service 增加用户积分
		log.info("保存订单" + order);
		
	}

}
