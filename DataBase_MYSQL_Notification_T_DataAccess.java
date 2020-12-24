package infrastructure;

import java.sql.*;

import com.mysql.jdbc.Connection;

import model.notification;
import model.response;

public class DataBase_MYSQL_Notification_T_DataAccess {

	public DataBase_MYSQL_Notification_T_DataAccess() throws SQLException, ClassNotFoundException  
	{
		   Class.forName("com.mysql.jdbc.Driver");
			Connection conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/notification_api", "root", "admin");
	}
	
public notification ReadNotification(int id) {
		
		return templetes.get(id);
	}

	
	public response CreateNotification(notification n) {
		response r=new response();
		if(templetes.get(n.getId())!=null)
		{
			r.setStatus(false);
			return r;
		}
		else
		{
			templetes.put(n.getId(),n);
			r.setStatus(true);
			return r;
		}
	}

	public response UpdateNotification(int id,notification n) {
		response r=new response();
		if(templetes.get(id)==null)
		{
			r.setStatus(false);
			return r;
		}
		else
		{
			templetes.put(id,n);
			r.setStatus(true);
			return r;
		}
	}

	
	public response deleteNotification(int id) {
		response r=new response();
		if(templetes.get(id)==null)
		{
			r.setStatus(false);
			return r;
		}
		else
		{
			templetes.remove(id);
			r.setStatus(true);
			return r;
		}
	}
	
	
	
}
