package ahjz.edu.controller;

import ahjz.edu.dao.BannerDao;
import ahjz.edu.dao.CategoryDao;
import ahjz.edu.dao.ProductDao;
import ahjz.edu.entity.Banner;
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

@WebServlet(name = "HomeServlet",urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //获取传递过来的参数
        String cid = request.getParameter("cid");
        String keyword= request.getParameter("keyword");
        System.out.println("搜索内容:"+keyword);
        System.out.println("分类id:"+cid);

        Context context = new Context();
        //创建CategoryDAo 并调用findAll方法
        CategoryDao cDao = new CategoryDao();
        List<Category> cList = cDao.findAll();
        context.setVariable("cList",cList);

        //得到当前登录的用户对象装进Context容器中
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        context.setVariable("user",user);
        //查询出所有的轮播图 并装进容器中
        BannerDao bDao = new BannerDao();
        List<Banner> bList = bDao.findAll();
        context.setVariable("bList",bList);

        //查询出所有作品信息 并装进容器
        ProductDao pDao = new ProductDao();
        List<Product> pList = null;
        if(cid!=null){//分类id有值查询和这个分类相关的作品
            pList = pDao.findByCid(cid);
        }else if(keyword!=null){//搜索
            pList = pDao.findByKeyword(keyword);
        }else{//查询所有数据
             pList = pDao.findAll();
        }
        context.setVariable("pList",pList);


        //查询浏览最多的作品信息
        List<Product> viewList = pDao.findViewList();
        context.setVariable("viewList",viewList);
        //查询最受欢迎的作品信息
        List<Product> likeList = pDao.findLikeList();
        context.setVariable("likeList",likeList);

        ThUtils.print("home.html",context,response);
    }
}
