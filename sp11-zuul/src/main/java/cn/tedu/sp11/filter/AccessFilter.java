package cn.tedu.sp11.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import cn.tedu.web.util.JsonResult;

@Component
public class AccessFilter extends ZuulFilter{
	//对于当前请求是否执行此处的过滤代码，返回true才执行过滤代码
	@Override
	public boolean shouldFilter() {
		// 指定item-service进行过滤，别的服务不过滤
		//判断请求服务是否是item-service,获取请求中的服务ID
		RequestContext ctx = RequestContext.getCurrentContext();
		Object serviceId = ctx.get(FilterConstants.SERVICE_ID_KEY);
		if (serviceId.equals("item-service")) {
			return true;
		}
		return false;
	}
	//权限校验
	@Override
	public Object run() throws ZuulException {
		// 从路径中中获取token参数，参数有认为登录过来的，可以访问后台服务
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest req = ctx.getRequest();
		String token = req.getParameter("token");
		if (token == null || token.length() == 0) {
			//阻止请求后执行，直接返回一个未登录的提示
			ctx.setSendZuulResponse(false);
			//向客户端返回未登录的指示
			ctx.setResponseStatusCode(200);
			ctx.setResponseBody(JsonResult.err("Not Login!").code(JsonResult.NOT_LOGIN).toString());
		}
		return null;//目前该值没有使用，zuul为了将来扩展，设计了一个返回值
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		/*
		 * zuul默认过滤器中的第五个过滤器，向ctx对象保存一个service-id
		 * 那么必须在第五个过滤器之后，才能访问这个service-id
		 */
		return FilterConstants.PRE_DECORATION_FILTER_ORDER+1;
	}

}
