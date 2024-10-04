package com.nt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.DcIncomeEntity;

public interface IDcIncomeRepo extends JpaRepository<DcIncomeEntity, Integer> {
	public DcIncomeEntity findByCaseNumber(Integer caseNo);
}
