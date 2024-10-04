package com.nt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.ElgibilityDetailsEntity;

public interface IElgibilityDetailsRepo extends JpaRepository<ElgibilityDetailsEntity, Integer> {

}
