package ahjz.edu.dao;

import ahjz.edu.entity.User;
import ahjz.edu.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {

    public User login(String username, String password) {
        //获取连接
        try (Connection conn = DBUtils.getConn();){
            String sql = "select id from user where username=? and password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int id = rs.getInt(1);
                return  new User(id,username,password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String check(String username) {
        try {
            Connection conn = DBUtils.getConn();
            String sql = "select id from user where username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return "用户名已存在!";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "用户名可用！";
    }




}
