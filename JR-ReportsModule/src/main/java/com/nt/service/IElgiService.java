package com.nt.service;

import java.util.List;

import com.nt.binding.ElgibilityDetailsOutputs;

public interface IElgiService {
	
	public List<ElgibilityDetailsOutputs> getAllData();
	
	public List<ElgibilityDetailsOutputs> showDetailsByFilter();
}
