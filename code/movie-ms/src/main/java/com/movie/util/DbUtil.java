package com.movie.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DbUtil {

	static String url = "jdbc:mysql://127.0.0.1:3306/movie-ms?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=false";
	static String username = "root";
	static String password = "root";
	static Connection conn = null;

	static {
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取资源连接
	 * 
	 * @return
	 */
	public Connection getConnection() {
		try {
			conn = DriverManager.getConnection(url, username, password);
			if (!conn.isClosed()) {
				System.out.println("数据库连接正常!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 关闭资源
	 * 
	 * @param conn
	 * @param rs
	 * @param ps
	 */
	public void closeResource(Connection conn, ResultSet rs, PreparedStatement ps) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 增删改
	 * 
	 * @param sql    要执行的语句
	 * @param params 要注入的参数
	 * @return
	 */
	public int doUpdate(String sql, List<Object> params) {
		int result = -1;
		try {
			Connection conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			// 如果有问号，有参数，要注入参数
			doParams(ps, params);
			result = ps.executeUpdate();
			closeResource(conn, null, ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 增删改
	 * 
	 * @param sql    要执行的语句
	 * @param params 要注入的参数
	 * @return
	 */
	public int doUpdate(String sql, Object... params) {
		// 定义返回值
		int result = -1;
		try {
			Connection conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			doParams(ps, params);
			result = ps.executeUpdate();
			closeResource(conn, null, ps);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;

	}

	/**
	 * 注入参数
	 * 
	 * @param ps
	 * @param params
	 */
	public void doParams(PreparedStatement ps, List<Object> params) {
		if (ps != null && params != null && params.size() > 0) {
			for (int i = 0; i < params.size(); i++) {
				try {
					ps.setObject(i + 1, params.get(i));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 注入参数（重载）
	 * 
	 * @param ps
	 * @param params
	 */
	public void doParams(PreparedStatement ps, Object... params) {
		if (ps != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				try {
					ps.setObject(i + 1, params[i]);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
