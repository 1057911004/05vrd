package ahjz.edu.controller;

import ahjz.edu.dao.ProductDao;
import ahjz.edu.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "DeleteServlet",urlPatterns = "/delete")
public class DeleteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String id = request.getParameter("id");

        ProductDao dao = new ProductDao();
        //获得作品的完整信息
        Product p = dao.findById(id);
        //得到作品图片的完整路径
        String filePath = request.getServletContext()
                .getRealPath(p.getImgPath());
        System.out.println(filePath);
        //根据图片的完整路径创建文件对象并删除文件
        File file = new File(filePath);
        file.delete();
        //从数据库中删除数据
         dao.deleteById(id);

        //重定向到首页
        response.sendRedirect("/home");

    }
}
