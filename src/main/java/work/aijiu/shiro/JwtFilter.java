package work.aijiu.shiro;


import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import work.aijiu.common.utils.JSONResult;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * jwt 拦截器
 */
public class JwtFilter extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        if(StringUtils.isNoneBlank(request.getHeader("Authorization"))){
            return true;
        }
        //放行websocket
        if(request.getRequestURI().contains("/websocket")){
            return true;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("msg","不允许请求");
        response.getWriter().println(JSON.toJSONString(map));
        return false;
    }
}
