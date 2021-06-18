package cn.tedu.sp09.servcie;

import org.springframework.stereotype.Component;

import cn.tedu.sp01.pojo.Order;
import cn.tedu.sp01.pojo.User;
import cn.tedu.web.util.JsonResult;

@Component
public class OrderFeignServiceFB implements OrderFeignService{

	@Override
	public JsonResult<Order> getOrder(String orderId) {
		// TODO Auto-generated method stub
		return JsonResult.err("获取订单失败");
	}

	@Override
	public JsonResult addOrder() {
		// TODO Auto-generated method stub
		return JsonResult.err("保存订单失败");
	}

}
