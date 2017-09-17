package com.dragon.servelet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.dragon.utils.PropertiesUtils;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DataServelet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// TODO Auto-generated method stub
		try {
			Connection	connection = (Connection) PropertiesUtils.getConnection();

			String sql = "select * from orders";
			PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rSet = statement.executeQuery();
			
			JSONArray jArray = new JSONArray();
//			HashMap map = new HashMap();//通常用

			// 获取列数  
			   ResultSetMetaData metaData = rSet.getMetaData();  
//			   int columnCount = metaData.getColumnCount();  
			   while (rSet.next()) {
					System.out.println(rSet.getString("cid")+":>"+rSet.getString("processing_order"));

			        JSONObject jsonObj = new JSONObject();  
			         
			        // 遍历每一列  
			        for (int i = 1; i <= rSet.getMetaData().getColumnCount(); i++) {  
			            String columnName =metaData.getColumnLabel(i);  
			            String value = rSet.getString(columnName);  
			            jsonObj.put(columnName, value);  
			        }   
			        jArray.add(jsonObj);
				}
				HashMap<String,Object>  map = new HashMap<String, Object>();//泛型 map
				map.put("data", jArray);
				map.put("code", "2002");
				map.put("success", true);
				JSONObject mapJson = JSONObject.fromObject(map);
				System.out.println(mapJson);
				resp.setContentType("text/plain");  
			    resp.setCharacterEncoding("UTF-8");  
			    PrintWriter out = new PrintWriter(resp.getOutputStream());
				out.print(mapJson);  
			    out.flush(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
