package com.nt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.CaseWorkersEntity;

public interface IUserMngRepo extends JpaRepository<CaseWorkersEntity, Integer> {
	public CaseWorkersEntity findByEmailAndPwd(String email,String password);
	public CaseWorkersEntity findByNameAndEmail(String name,String email);
}
