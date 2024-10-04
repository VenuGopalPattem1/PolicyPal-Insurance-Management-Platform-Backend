package com.nt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.binding.ElgibilityDetailsOutputs;
import com.nt.entity.ElgibilityDetailsEntity;
import com.nt.repo.IElgibilityDetailsRepo;

@Service
public class ElgiServiceImpl implements IElgiService {
	
	@Autowired
	private IElgibilityDetailsRepo ser;
	
	@Override
	public List<ElgibilityDetailsOutputs> getAllData() {
		List<ElgibilityDetailsEntity> list = ser.findAll();
		List<ElgibilityDetailsOutputs> out=new ArrayList<ElgibilityDetailsOutputs>();
		list.forEach(data->{
			ElgibilityDetailsOutputs el= new ElgibilityDetailsOutputs();
			BeanUtils.copyProperties(data,el);
			out.add(el);
		});
		return out;
	}

	@Override
	public List<ElgibilityDetailsOutputs> showDetailsByFilter() {
		// TODO Auto-generated method stub
		return null;
	}

}
