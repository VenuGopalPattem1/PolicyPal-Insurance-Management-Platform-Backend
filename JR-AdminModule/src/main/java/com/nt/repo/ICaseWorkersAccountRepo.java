package com.nt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.CaseWorkersEntity;

public interface ICaseWorkersAccountRepo extends JpaRepository<CaseWorkersEntity, Integer> {
	
}
