package com.nt.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.nt.binding.ElgibilityDetailsOutputs;
import com.nt.entity.ElgibilityDetailsEntity;
@Component
public class BIModuleItemProcessor implements ItemProcessor<ElgibilityDetailsEntity, ElgibilityDetailsOutputs> {

	@Override
	public ElgibilityDetailsOutputs process(ElgibilityDetailsEntity item) throws Exception {
		if(item.getPlanStatus().equalsIgnoreCase("approved")) {
			ElgibilityDetailsOutputs output = new ElgibilityDetailsOutputs();
			BeanUtils.copyProperties(item, output);
			return output;
		}
		return null;
	}

}
