/**
 * 
 */
package com.example.ConnectionManagerJDBC.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ConnectionManagerJDBC.dao.MyTestingDao;
import com.example.ConnectionManagerJDBC.model.MyTestingEntity;

/**
 * @author Aritra
 *
 */
@Service
public class MyTestingService {
	
	@Autowired
	MyTestingDao dao;
	
	public List<MyTestingEntity> getAll(){
		return dao.getAll();
	}
	
	public void create(MyTestingEntity entity) {
		dao.addDetails(entity);
	}
	public MyTestingEntity selected(int id) {
		return dao.selected(id);
	}

}
