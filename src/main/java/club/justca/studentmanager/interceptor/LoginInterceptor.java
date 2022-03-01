package club.justca.studentmanager.interceptor;

import club.justca.studentmanager.VO.LoginError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("loginUser");
        if (loginUser != null) {
            return true;
        }
        log.info("拦截的请求路径是" + request.getRequestURI());
        LoginError loginError = new LoginError();
        loginError.setErrorResult("请先登录");
        loginError.setUsername("");
        loginError.setRole(1);
        session.setAttribute("loginError", loginError);
        response.sendRedirect(request.getContextPath() + "/login");
        return false;
    }
}
