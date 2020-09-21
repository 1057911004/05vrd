package ahjz.edu.controller;

import ahjz.edu.dao.BannerDao;
import ahjz.edu.entity.Banner;
import ahjz.edu.utils.ThUtils;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowBannerServlet",urlPatterns = "/showbanners")
public class ShowBannerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //得到轮播图信息
        BannerDao dao = new BannerDao();
        List<Banner> list = dao.findAll();
        Context context = new Context();
        context.setVariable("list",list);
        ThUtils.print("banners.html",context,response);

    }
}
