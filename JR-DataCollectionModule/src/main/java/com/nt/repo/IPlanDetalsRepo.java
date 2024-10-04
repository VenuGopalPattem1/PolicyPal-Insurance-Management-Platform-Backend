package com.nt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.PlanEntity;

public interface IPlanDetalsRepo extends JpaRepository<PlanEntity, Integer> {

}
