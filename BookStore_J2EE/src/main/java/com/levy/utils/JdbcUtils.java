package com.levy.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author LevyXie
 * @create 2022-01-11 10:49
 * @description：数据库相关的工具类，采用druid数据库连接池
 */
public class JdbcUtils {
    //创建数据库连接池
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();

    static {
        Properties properties = new Properties();
        InputStream ras = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            properties.load(ras);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接池中的连接
     * @return
     */
    public static Connection getConnection(){
        Connection conn = conns.get();//从ThreaLocal中取用conn
        if(conn == null){//为空则创建
            try {
                conn = dataSource.getConnection();
                conns.set(conn);//保存于ThreadLocal中，便于后续取用
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
    public static void commitAndClose(){
        Connection conn = conns.get();
        if(conn != null){//说明之前使用过该连接
            try {
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        conns.remove();//用完之后需要清除该ThreadLocal，因tomcat使用了线程池技术
    }
    public static void rollBackAndClose(){
        Connection conn = conns.get();
        if(conn != null){//说明之前使用过该连接
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        conns.remove();//用完之后需要清除该ThreadLocal，因tomcat使用了线程池技术
    }
}
