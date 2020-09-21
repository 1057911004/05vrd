package ahjz.edu.controller;

import ahjz.edu.dao.BannerDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "DeleteBannerServlet",urlPatterns = "/deletebanner")
public class DeleteBannerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        BannerDao dao = new BannerDao();
        //通过id查询轮播图路径
        String imgPath = dao.findPathById(id);
        String filePath = request.getServletContext().getRealPath(imgPath);
        //删除文件
        new File(filePath).delete();

        //删除数据库数据
        dao.deleteById(id);
        response.sendRedirect("/showbanners");
    }
}
