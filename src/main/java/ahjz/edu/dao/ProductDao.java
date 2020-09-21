package ahjz.edu.dao;

import ahjz.edu.entity.Product;
import ahjz.edu.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    public void insert(Product product) {
        //获取连接
        try (Connection conn = DBUtils.getConn();){
            String sql = "insert into product values(null,?,?,?,?,?,?,0,0)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,product.getTitle());
            ps.setString(2,product.getAuthor());
            ps.setString(3,product.getIntro());
            ps.setString(4,product.getImgPath());
            ps.setLong(5,product.getCreated());
            ps.setInt(6,product.getCategoryId());
            ps.executeUpdate();
            System.out.println("保存完成!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Product> findAll() {
        ArrayList<Product> list = new ArrayList<>();
        //获取连接
        try (Connection conn = DBUtils.getConn();){
            String sql = "select * from product order by created desc limit 0,4";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String author = rs.getString(3);
                String intro = rs.getString(4);
                String imgPath = rs.getString(5);
                long created = rs.getLong(6);
                int categoryId = rs.getInt(7);
                int viewCount = rs.getInt(8);
                int likeCount = rs.getInt(9);
                list.add(new Product(id,title,author,intro,
                        imgPath,created,categoryId,viewCount,likeCount));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    public List<Product> findViewList() {
        ArrayList<Product> list = new ArrayList<Product>();
        //获取连接
        try (Connection conn = DBUtils.getConn();){
            String sql = "select * from product order by viewCount desc limit 0,4";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String author = rs.getString(3);
                String intro = rs.getString(4);
                String imgPath = rs.getString(5);
                long created = rs.getLong(6);
                int categoryId = rs.getInt(7);
                int viewCount = rs.getInt(8);
                int likeCount = rs.getInt(9);
                list.add(new Product(id,title,author,intro,
                        imgPath,created,categoryId,viewCount,likeCount));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> findLikeList() {
        ArrayList<Product> list = new ArrayList<Product>();
        //获取连接
        try (Connection conn = DBUtils.getConn();){
            String sql = "select * from product order by likeCount desc limit 0,4";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String author = rs.getString(3);
                String intro = rs.getString(4);
                String imgPath = rs.getString(5);
                long created = rs.getLong(6);
                int categoryId = rs.getInt(7);
                int viewCount = rs.getInt(8);
                int likeCount = rs.getInt(9);
                list.add(new Product(id,title,author,intro,
                        imgPath,created,categoryId,viewCount,likeCount));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> findByCid(String cid) {
        ArrayList<Product> list = new ArrayList<>();
        //获取连接
        try (Connection conn = DBUtils.getConn();){
            String sql = "select * from product where categoryId=? order by created desc";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,Integer.parseInt(cid));
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String author = rs.getString(3);
                String intro = rs.getString(4);
                String imgPath = rs.getString(5);
                long created = rs.getLong(6);
                int categoryId = rs.getInt(7);
                int viewCount = rs.getInt(8);
                int likeCount = rs.getInt(9);
                list.add(new Product(id,title,author,intro,
                        imgPath,created,categoryId,viewCount,likeCount));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> findByKeyword(String keyword) {
        ArrayList<Product> list = new ArrayList<>();
        //获取连接
        try (Connection conn = DBUtils.getConn();){
            String sql = "select * from product where title like ? order by created desc";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,"%"+keyword+"%");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String author = rs.getString(3);
                String intro = rs.getString(4);
                String imgPath = rs.getString(5);
                long created = rs.getLong(6);
                int categoryId = rs.getInt(7);
                int viewCount = rs.getInt(8);
                int likeCount = rs.getInt(9);
                list.add(new Product(id,title,author,intro,
                        imgPath,created,categoryId,viewCount,likeCount));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Product findById(String pid) {
        //获取连接
        try (Connection conn = DBUtils.getConn();){
            String sql = "select * from product where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,Integer.parseInt(pid));
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String author = rs.getString(3);
                String intro = rs.getString(4);
                String imgPath = rs.getString(5);
                long created = rs.getLong(6);
                int categoryId = rs.getInt(7);
                int viewCount = rs.getInt(8);
                int likeCount = rs.getInt(9);
                return new Product(id,title,author,intro,
                        imgPath,created,categoryId,viewCount,likeCount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteById(String id) {
        //获取连接
        try (Connection conn = DBUtils.getConn();){
            String sql = "delete from product where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,Integer.parseInt(id));
            ps.executeUpdate();
            System.out.println("删除完成!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void likeById(String id) {
        //获取连接
        try (Connection conn = DBUtils.getConn();){
            String sql = "update product set likeCount=likeCount+1 where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,Integer.parseInt(id));
            ps.executeUpdate();
            System.out.println("点赞完成!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewById(String id) {
        //获取连接
        try (Connection conn = DBUtils.getConn();){
            String sql = "update product set viewCount=viewCount+1 where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,Integer.parseInt(id));
            ps.executeUpdate();
            System.out.println("浏览量+1");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Product> loadMore(String count) {
        ArrayList<Product> list = new ArrayList<>();
        //获取连接
        try (Connection conn = DBUtils.getConn();){
            String sql = "select * from product order by created desc limit ?,4";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,Integer.parseInt(count));
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String author = rs.getString(3);
                String intro = rs.getString(4);
                String imgPath = rs.getString(5);
                long created = rs.getLong(6);
                int categoryId = rs.getInt(7);
                int viewCount = rs.getInt(8);
                int likeCount = rs.getInt(9);
                list.add(new Product(id,title,author,intro,
                        imgPath,created,categoryId,viewCount,likeCount));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
