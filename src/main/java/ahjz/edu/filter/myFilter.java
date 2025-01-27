package ahjz.edu.filter;

import ahjz.edu.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "myFilter",urlPatterns = {"/showsend","/addbanner","/deletebanner","/delete","/showbanners"})
public class myFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request =(HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        User user  =(User) session.getAttribute("user");
        if (user==null){//没有登录过
            response.sendRedirect("/login");
        }else{//登录过
            //放行，执行后面的servlet
            chain.doFilter(req, resp);
        }


    }

    public void init(FilterConfig config) throws ServletException {

    }

}