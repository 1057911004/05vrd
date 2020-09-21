package ahjz.edu.controller;


import ahjz.edu.dao.CategoryDao;
import ahjz.edu.dao.ProductDao;
import ahjz.edu.entity.Category;
import ahjz.edu.entity.Product;
import ahjz.edu.entity.User;
import ahjz.edu.utils.ThUtils;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DetailServlet",urlPatterns = "/detail")
public class DetailServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        //创建dao并调用findById方法
        ProductDao pDao = new ProductDao();
        //让浏览量+1
        pDao.viewById(id);

        Product product = pDao.findById(id);


        Context context = new Context();
        context.setVariable("product",product);

        //分类 浏览最多 最受欢迎 数据
        List<Product> viewList = pDao.findViewList();
        context.setVariable("viewList",viewList);
        List<Product> likeList = pDao.findLikeList();
        context.setVariable("likeList",likeList);
        CategoryDao cDao = new CategoryDao();
        List<Category> cList = cDao.findAll();
        context.setVariable("cList",cList);
        //得到当前登录的用户对象装进Context容器中
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        context.setVariable("user",user);

        ThUtils.print("detail.html",context,response);

    }
}
