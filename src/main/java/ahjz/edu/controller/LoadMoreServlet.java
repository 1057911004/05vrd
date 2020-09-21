package ahjz.edu.controller;

import ahjz.edu.dao.ProductDao;
import ahjz.edu.entity.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "LoadMoreServlet",urlPatterns = "/loadmore")
public class LoadMoreServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String count = request.getParameter("count");
        System.out.println("页面已有:"+count);
        //创建Dao 调用查询更多的方法
        ProductDao dao = new ProductDao();
        List<Product> list = dao.loadMore(count);
        System.out.println(list);
        //将集合里面的数据转换成Json字符串
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(list);
        System.out.println(json);

        //把得到的json返回给客户端浏览器
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.print(json);
        pw.close();


    }
}
