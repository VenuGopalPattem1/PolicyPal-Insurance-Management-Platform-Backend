package com.nt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.CitizenAppRegistartionEntity;

public interface ICitizenAppRepo extends JpaRepository<CitizenAppRegistartionEntity, Integer> {

}
