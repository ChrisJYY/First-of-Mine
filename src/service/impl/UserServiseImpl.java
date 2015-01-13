package service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pojo.Users;
import service.UserService;

public class UserServiseImpl implements UserService{
	public List getStudentList(){
		List userList=new ArrayList();

		
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			//端口号；数据库用户名；密码；
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			
			stmt=conn.createStatement();
			
			rs=stmt.executeQuery("select * from user");
			
			while(rs.next()){
				Users users=new Users();
				
				users.setUserid(rs.getString("USER_ID"));
				users.setPassword(rs.getString("PASSWORD"));
				
				userList.add(users);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				
				throw new RuntimeException("error when querying students ",e);
			}
		}
		return userList;
	}

}
