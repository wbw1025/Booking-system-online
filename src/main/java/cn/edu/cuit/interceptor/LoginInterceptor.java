package cn.edu.cuit.interceptor;

import cn.edu.cuit.pojo.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by admin on 2017/5/28.
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Value("${filterUrl}")
    private String filterUrl;
    /**
     * Handler执行之前调用这个方法
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        //1.检测访问页面是否需要拦截
        for (String u : filterUrl.split(",")){ //循环需要拦截的地址
            if (url.indexOf(u) > 0){  // 大于0表示需要拦截页面
                //2.判断是否已经登录
                User user = (User) request.getSession().getAttribute("loginUser");
                if (user != null){
                    return true;
                }
                //3、非法请求 即这些请求需要登录后才能访问
                //重定向到登录页面
                response.sendRedirect(request.getContextPath() + "login");
                return false;
            }
        }
        return true;
    }
    /**
     * Handler执行之后，ModelAndView返回之前调用这个方法
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
    /**
     * Handler执行完成之后调用这个方法
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
