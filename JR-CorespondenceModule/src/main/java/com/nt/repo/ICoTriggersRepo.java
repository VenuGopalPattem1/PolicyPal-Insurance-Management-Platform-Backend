package com.nt.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.CoTriggersEntity;

public interface ICoTriggersRepo extends JpaRepository<CoTriggersEntity, Integer> {
	public List<CoTriggersEntity> findByTriggerStatus(String status);
	public CoTriggersEntity findByCaseNo(Integer caseNo);
}
