package ahjz.edu.controller;

import ahjz.edu.dao.BannerDao;
import ahjz.edu.entity.Banner;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.UUID;

@MultipartConfig
@WebServlet(name = "AddBannerServlet",urlPatterns = "/addbanner")
public class AddBannerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part part = request.getPart("file");
        //得到上传文件信息
        String info = part.getHeader("content-disposition");
        //得到文件的后缀名
        String suffix = info.substring(info.lastIndexOf("."),info.length()-1);
        //得到文件的唯一名称
        String fileName = UUID.randomUUID()+suffix;
        //得到文件的完整路径 此方法只能获取文件夹路径 不能去直接获取文件路径
        String filePath = request.getServletContext()
                .getRealPath("images");
        System.out.println(filePath);
        part.write(filePath+"/"+fileName);
        //把不能播图路径封装到对象中并调用dao的方法进行保存
        Banner banner = new Banner(0,"images/"+fileName);
        BannerDao dao = new BannerDao();
        dao.insert(banner);

        //重定向到轮播图修改页面
        response.sendRedirect("/showbanners");





    }
}
