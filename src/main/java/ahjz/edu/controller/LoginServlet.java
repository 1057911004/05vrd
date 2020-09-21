package ahjz.edu.controller;

import ahjz.edu.dao.UserDao;
import ahjz.edu.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LoginServle",urlPatterns = "/loginaction")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //是否记住用户名和密码
        String rem = request.getParameter("rem");

        System.out.println(username+":"+password+":"+rem);
        //创建Dao并调用登录方法
        UserDao dao = new UserDao();
        User user = dao.login(username,password);
        if(user!=null){
            //记住登录状态
            HttpSession session = request.getSession();
            session.setAttribute("user",user);

            //判断是否需要记住用户名
            if (rem!=null){//不等null说明rem值为on  也就是需要记住用户名和密码
                //创建两个Cookie把用户名和密码装进去
                Cookie c1 = new Cookie("username",username);
                Cookie c2 = new Cookie("password",password);
                //设置Cookie数据保存时间
                //如果不设置时间 数据保存在内存中 浏览器关闭则删除
                //设置时间后数据保存在磁盘中只有时间到之后才会被删除
                c1.setMaxAge(30*24*60*60); //秒为单位 保存一个月
                //将cookie下发到客户端浏览器中
                response.addCookie(c1);
                response.addCookie(c2);
                System.out.println("cookie添加完成!");
            }

            //登录成功重定向到首页
            response.sendRedirect("/home");
        }else{
            //登录失败重定向到登录页面
            response.sendRedirect("/login");
        }
    }


}
