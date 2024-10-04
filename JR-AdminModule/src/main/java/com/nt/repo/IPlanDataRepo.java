package com.nt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.PlanEntity;

public interface IPlanDataRepo extends JpaRepository<PlanEntity, Integer> {

}
