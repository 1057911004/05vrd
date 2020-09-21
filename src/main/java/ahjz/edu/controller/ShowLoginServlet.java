package ahjz.edu.controller;

import ahjz.edu.utils.ThUtils;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ShowLoginServlet",urlPatterns = "/login")
public class ShowLoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //显示登陆页面
        Context context = new Context();
        //取出Cookie中的用户名和密码 装进容器最后显示到页面中
        Cookie[] cookies = request.getCookies();
        //由于有可能浏览器中没有任何cookie 从数组中取数据之前需要先加非空判断避免空指针
        if(cookies!=null){
            //遍历所有cookie
            for (Cookie c : cookies) {
                 String name = c.getName();
                 String value = c.getValue();
                 if(name.equals("username")){//判断是否是用户名
                     //把用户存进容器中
                     context.setVariable("name",value);
                 }
                 if(name.equals("password")){//判断是否是密码
                     context.setVariable("pwd",value);
                 }
            }
        }

        ThUtils.print("login.html",context,response);
    }
}
