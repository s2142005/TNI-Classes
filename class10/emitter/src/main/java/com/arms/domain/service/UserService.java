package com.arms.domain.service;

import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.arms.app.user.UserAddForm;
import com.arms.domain.component.PasswordEncoder;
import com.arms.domain.entity.User;

@Service
public class UserService extends AppService {
	
	@Autowired
	PasswordEncoder passwordEncoder;

	public void createUser(UserAddForm userAddForm) throws NoSuchAlgorithmException {
		Date nowDate = Calendar.getInstance().getTime();
		User user = new User();
		user.setName(userAddForm.getName());
		user.setEmail(userAddForm.getEmail());
		user.setPassword(passwordEncoder.hashMD5(userAddForm.getPassword()));
		user.setCreated(nowDate);
		user.setUpdated(nowDate);
		userRepository.save(user);
	}
	public Page<User> findAllUser(Pageable pageable){
		return userRepository.findAll(pageable);
	}


}
