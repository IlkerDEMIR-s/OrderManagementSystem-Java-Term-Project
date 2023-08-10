package me.anant.OMS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.anant.OMS.dao.UsersRepository;
import me.anant.OMS.exceptions.UserNotFoundException;
import me.anant.OMS.model.Users;

@Service
public class UsersService {
	@Autowired
	UsersRepository ur;
	
	public Users findByEmail(String email) {
		return ur.findByEmail(email);
	}

	public List<Users> get(){
		return ur.findAll();
	}
	
	public void save(Users user) {
		ur.save(user);
	}
	
	public boolean doesEmailExist(String email) {
	    return ur.existsByEmail(email);
	}
	
	public void delete(Long id) {
		ur.deleteById(id);
	}
	
	public Optional<Users> findById(long id) throws UserNotFoundException {
		Optional<Users> optionalUser = ur.findById(id);
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("User not available");
		}
		return optionalUser;
	}
}
