package com.nt.service;

import java.util.List;

import com.nt.model.ActiveUser;
import com.nt.model.CaseWorkersOutput;
import com.nt.model.LoginCradentials;
import com.nt.model.RecoverPassword;

public interface IUserMngService {
	
	public String registerUser(CaseWorkersOutput user) throws Exception ;
	public String activateUser(ActiveUser active);
	public String login(LoginCradentials cred);
	public List<CaseWorkersOutput> getAllUsers();
	public CaseWorkersOutput getUserById(Integer id);
	public CaseWorkersOutput showUserByEmailAndName(String email,String name);
	public String deleteUserById(Integer id);
	public String updateUser(CaseWorkersOutput user);
	public String updateActiveStatus(Integer id,String status);
	public String recoverPassword(RecoverPassword recover) throws Exception;
}
