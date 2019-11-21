package com.spring.batch.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.batch.models.User;
import com.spring.batch.repositories.UserRepository;
import com.spring.batch.tasks.TaskTwo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repository;

	private static final Logger logger = LoggerFactory.getLogger(TaskTwo.class);

	@Override
	public void insertUsers() {
		List<User> users = new ArrayList<User>();
		for (int i = 1; i < 6; i++) {
			User user = new User();
			user.setId((long) i);
			user.setUsername("user" + i);
			user.setEmail("user" + i + "@gmail.com");
			users.add(user);
		}
		repository.saveAll(users);

	}

	@Override
	public void getUsers() {
		repository.findAll().forEach(user -> {
			logger.info("ID: " + user.getId());
			logger.info("Username: " + user.getUsername());
			logger.info("Email: " + user.getEmail());
		});

	}

}
