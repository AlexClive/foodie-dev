package com.imooc.controller.interceptor;

import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.RedisOperator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

public class UserTokenInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisOperator redisOperator;

    public static final String REDIS_USER_TOKEN = "redis_user_token";

    /**
     * 拦截请求，在访问Controller之前
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入到拦截器，被拦截");

        String cookieToken = request.getHeader("headerUserToken");
        String userId = request.getHeader("headerUserId");
        if (StringUtils.isNotBlank(cookieToken) && StringUtils.isNotBlank(userId)) {
            String redisToken = redisOperator.get(REDIS_USER_TOKEN + ":" + userId);
            if (StringUtils.isBlank(redisToken)) {
                retuenErrorResponse(response,IMOOCJSONResult.errorMsg("请登录。。。"));
                return false;
            } else {
                if (!redisToken.equals(cookieToken)) {
                    retuenErrorResponse(response,IMOOCJSONResult.errorMsg("账户在异地登录。。。"));
                    return false;
                }
            }

        } else {
            retuenErrorResponse(response,IMOOCJSONResult.errorMsg("请登录。。。"));
            return false;
        }
        /**
         * false: 请求被拦截
         * true：请求在经过验证校验之后，是ok的，是可以放行的
         */

        // return HandlerInterceptor.super.preHandle(request, response, handler);
        return true;
    }

    public void retuenErrorResponse(HttpServletResponse response, IMOOCJSONResult result) {
        OutputStream out = null;
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json");
            response.getOutputStream();
            out.write(JsonUtils.objectToJson(result).getBytes("utf-8"));
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 拦截请求，在访问Controller之后，渲染视图之前
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 拦截请求，在访问Controller之后，渲染视图之后
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
