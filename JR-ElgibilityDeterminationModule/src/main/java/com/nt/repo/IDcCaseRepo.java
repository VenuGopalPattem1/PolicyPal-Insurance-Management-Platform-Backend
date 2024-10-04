package com.nt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.DcCasesEntity;
import com.nt.entity.DcChildrenEntity;

public interface IDcCaseRepo extends JpaRepository<DcCasesEntity, Integer> {
}
