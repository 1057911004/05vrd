package ahjz.edu.utils;

import com.alibaba.druid.pool.DruidDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DBUtils {
    private static DruidDataSource dataSource;
    static {
        //读取配置文件
        Properties p = new Properties();
        //获取配置文件的输入流
        InputStream ips = DBUtils.class.getClassLoader()
                .getResourceAsStream("jdbc.properties");
        //让配置对象加载文件
        try {
            p.load(ips);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //读取数据
        String url = p.getProperty("db.url");
        String driver = p.getProperty("db.driver");
        String username = p.getProperty("db.username");
        String password = p.getProperty("db.password");
        String maxActive = p.getProperty("db.maxActive");
        String initalSize = p.getProperty("db.initialSize");

        //创建数据库连接池对象
        dataSource = new DruidDataSource();
        //设置数据库连接信息
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        //设置初始连接数量
        dataSource.setInitialSize(Integer.parseInt(initalSize));
        //设置最大连接数量
        dataSource.setMaxActive(Integer.parseInt(maxActive));
    }
    public static Connection getConn() throws Exception {

        //从连接池中获取连接
        Connection conn = dataSource.getConnection();
        return conn;
    }
}
