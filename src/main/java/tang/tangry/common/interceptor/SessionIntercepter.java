package tang.tangry.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import tang.tangry.user.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by tryu on 2017/7/1.
 */
public class SessionIntercepter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object o) throws Exception {


        if (req.getRequestURI().indexOf("login") >= 0) {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("userInfo");
            if (user != null) {
                req.getRequestDispatcher("/user/already_logged_in").forward(req, resp);
                return false;
            } else {
                return true;
            }
        } else {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("userInfo");
            if (user != null) {
                return true;
            } else {
                req.getRequestDispatcher("/login").forward(req, resp);
                return false;
            }

        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
