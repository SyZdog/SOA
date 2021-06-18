package cn.tedu.sp04.service.order.feignclient;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.web.util.JsonResult;
@Component
public class ItemFeignServiceFB implements ItemFeignService{

	@SuppressWarnings("unchecked")
	@Override
	public JsonResult<List<Item>> getItems(String orderId) {
		//模拟缓存数据，50%概率获取到缓存数据
		if (Math.random() < 0.5) {
			return JsonResult.ok().data(
					Arrays.asList(new Item[] {
							new Item(1, "缓存奥特曼玩具", 2),
							new Item(2, "缓存Mac电脑", 1),
							new Item(3, "缓存IPhone12", 3),
							new Item(4, "缓存喜羊羊玩具", 1),
							new Item(5, "缓存熊出没玩具", 5)
					})
				);
		}
		return JsonResult.err("获取订单商品失败");
	}

	@Override
	public JsonResult decreaseNumbers(List<Item> items) {
		// TODO Auto-generated method stub
		return JsonResult.err("修改库存失败");
	}

}
