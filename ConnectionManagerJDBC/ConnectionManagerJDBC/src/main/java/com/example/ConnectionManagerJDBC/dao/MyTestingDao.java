/**
 * 
 */
package com.example.ConnectionManagerJDBC.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.ConnectionManagerJDBC.model.MyTestingEntity;
import com.example.ConnectionManagerJDBC.projectConstants.DataBaseConstants;
import com.example.ConnectionManagerJDBC.utils.ConnectionManager;

/**
 * @author Aritra
 *
 */
@Repository
public class MyTestingDao {

	public List<MyTestingEntity> getAll(){
		List<MyTestingEntity> dataList = new ArrayList<MyTestingEntity>();
		try(Connection conn = ConnectionManager.getJDBCConnection()){ // Creating a connection to execute the SQL query.
			String sql ="select * from Testing_Table";
			CallableStatement cs = conn.prepareCall(sql);
			ResultSet rs = cs.executeQuery(); // To use when a SQL call returns some schema which value needs to be set somewhere. 
			if (rs != null) {
				while (rs.next()) {
					MyTestingEntity entity = new MyTestingEntity();
					entity.setAge(rs.getInt("age"));
					entity.setId(rs.getInt("id"));
					entity.setName(rs.getString("name"));
					dataList.add(entity);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}
	
	public void addDetails(MyTestingEntity entity) {
		try(Connection conn = ConnectionManager.getJDBCConnection()){ // Creating a connection to execute the SQL query.
			String sql ="insert into Testing_Table(age,name) values(?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1,entity.getAge()); // 1 is the position of the question mark and next(23) is the value which will replace it in actual query.
			cs.setString(2,entity.getName());
			cs.executeUpdate(); //To use when we insert anything in the table and doestn't return any values.
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MyTestingEntity selected(int id) {
		MyTestingEntity entity =null;
		try(Connection conn = ConnectionManager.getJDBCConnection()){ // Creating a connection to execute the SQL query.
			String sql ="select * from Testing_Table where id = ?";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1,id); // 1 is the position of the question mark and next(23) is the value which will replace it in actual query.
			ResultSet rs = cs.executeQuery(); // To use when a SQL call returns some schema which value needs to be set somewhere. 
			if (rs != null) {
				while (rs.next()) {
					entity = new MyTestingEntity();
					entity.setAge(rs.getInt("age"));
					entity.setId(rs.getInt("id"));
					entity.setName(rs.getString("name"));
					
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}
	
	/**
	 * If we execute any stored procedure and simultaneously it returns some output value.
	 * */
	public void example(MyTestingEntity entity) {
		try(Connection conn = ConnectionManager.getJDBCConnection()){ // Creating a connection to execute the SQL query.
			String sql ="call stored_procedure_name(?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1,entity.getAge()); 
			cs.setString(2,entity.getName());
			//considering in the stored procedure the return value is set in the 3rd parameter and the return value is of type int
			cs.registerOutParameter(3,Types.INTEGER);
			cs.executeUpdate();
			//storing the return value somewhere
			entity.setId(cs.getInt(3));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
