package club.justca.studentmanager.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class RoleInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String loginRole = (String) session.getAttribute("loginRole");
        String requestURI = request.getRequestURI();
        int i = requestURI.indexOf("/"+loginRole);
        if(i==0){
            return true;
        }
        if(loginRole.equals("teacher")){
            response.sendRedirect(request.getContextPath() + "/teacher/index");
        }else {
            response.sendRedirect(request.getContextPath() + "/student/index");
        }
        return false;
    }
}
