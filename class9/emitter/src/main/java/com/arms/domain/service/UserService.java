package com.arms.domain.service;

import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
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
}
