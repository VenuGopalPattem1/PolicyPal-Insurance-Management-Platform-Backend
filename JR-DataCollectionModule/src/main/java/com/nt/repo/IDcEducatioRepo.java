package com.nt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.DcEducationEntity;

public interface IDcEducatioRepo extends JpaRepository<DcEducationEntity,Integer> {
	public DcEducationEntity findByCaseNumber(Integer caseNo);
}
