package com.nt.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.model.ActiveUser;
import com.nt.model.CaseWorkersOutput;
import com.nt.model.LoginCradentials;
import com.nt.model.RecoverPassword;
import com.nt.service.UserMngServieImpl;

@RestController
@RequestMapping("/app")
@CrossOrigin
public class UserController {
	@Autowired
	private UserMngServieImpl ser;
	
	@PostMapping("/save")
	public ResponseEntity<?> saveUser(@RequestBody CaseWorkersOutput user){
		try {
			String msg = ser.registerUser(user);
			return new ResponseEntity<String>(msg,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/activate")
	public ResponseEntity<?> activateUser(@RequestBody ActiveUser user){
		try {
			String msg = ser.activateUser(user);
			return new ResponseEntity<String>(msg,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginCradentials login){
		try {
			String msg = ser.login(login);
			return new ResponseEntity<String>(msg,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllUsers(){
		try {
			List<CaseWorkersOutput> msg = ser.getAllUsers();
			return new ResponseEntity<List<CaseWorkersOutput>>(msg,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getUser/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Integer id){
		try {
			CaseWorkersOutput msg = ser.getUserById(id);
			return new ResponseEntity<CaseWorkersOutput>(msg,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getUser/{email}/{name}")
	public ResponseEntity<?> getUserByEmailAndName(@PathVariable String email, @PathVariable String name){
		try {
			CaseWorkersOutput msg = ser.showUserByEmailAndName(email,name);
			return new ResponseEntity<CaseWorkersOutput>(msg,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable Integer id){
		try {
			String msg = ser.deleteUserById(id);
			return new ResponseEntity<String>(msg,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> deleteUserById(@RequestBody CaseWorkersOutput user){
		try {
			String msg = ser.updateUser(user);
			return new ResponseEntity<String>(msg,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PatchMapping("/status/{id}/{status}")
	public ResponseEntity<?> updateUserStatus(@PathVariable Integer id,@PathVariable String status){
		try {
			String msg = ser.updateActiveStatus(id,status);
			return new ResponseEntity<String>(msg,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/recover")
	public ResponseEntity<?> recoverPassword(@RequestBody RecoverPassword pass){
		try {
			String msg = ser.recoverPassword(pass);
			return new ResponseEntity<String>(msg,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
