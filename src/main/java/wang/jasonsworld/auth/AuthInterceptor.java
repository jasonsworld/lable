package wang.jasonsworld.auth;


import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * @brief 权限验证拦截器
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {


    /**
     * @author chenyongsheng
     * @brief 重载preHandle方法，实现权限的验证
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            AuthPassport authPassport = ((HandlerMethod) handler).getMethodAnnotation(AuthPassport.class);
            //没有声明需要权限,或者声明不验证权限
            if (authPassport == null || authPassport.validate() == false)
                return true;
            else {
                //在这里实现自己的权限验证逻辑
                Map<String, String[]> params = request.getParameterMap();
                if (params.containsKey("id")) { //app 验证过程
                    String sessionId = request.getParameter("id");
                    //System.out.println(sessionId);
                    if (sessionId == null) {
                        return false;
                    }

                    request.setAttribute(sessionId, new Date().toString());
                    //System.out.println("session id = " + sessionId);
                    //获取用户的加密后的sessionid以及用户的等级，进行合理性验证
                    if (true)//如果验证成功返回true（这里直接写false来模拟验证失败的处理）
                        return true;
                    else//如果验证失败
                    {
                        //返回用户验证失败
                        response.sendRedirect("/agriculture/error/user?code=1");
                        return false;
                    }
                } else { // web验证
                    if (request.getSession().getAttribute("user_id") != null) {
                        response.sendRedirect("/agriculture/error/user?code=1");
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        } else
            return true;
    }


}
