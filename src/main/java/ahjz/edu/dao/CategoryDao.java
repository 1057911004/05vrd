package ahjz.edu.dao;

import ahjz.edu.entity.Category;
import ahjz.edu.utils.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
    public List<Category> findAll() {
        ArrayList<Category> list = new ArrayList<>();
        //获取连接
        try (Connection conn = DBUtils.getConn();){
            String sql = "select * from category";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                list.add(new Category(id,name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
