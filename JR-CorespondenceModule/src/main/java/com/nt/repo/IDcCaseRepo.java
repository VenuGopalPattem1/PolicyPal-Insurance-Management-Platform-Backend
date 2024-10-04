package com.nt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.DcCasesEntity;

public interface IDcCaseRepo extends JpaRepository<DcCasesEntity, Integer> {
}
