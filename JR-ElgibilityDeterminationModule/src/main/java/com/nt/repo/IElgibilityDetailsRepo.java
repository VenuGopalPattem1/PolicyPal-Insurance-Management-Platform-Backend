package com.nt.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.ElgibilityDetailsEntity;

public interface IElgibilityDetailsRepo extends JpaRepository<ElgibilityDetailsEntity, Integer> {
	public Optional<ElgibilityDetailsEntity> findByCaseNo(int caseNo);
}
