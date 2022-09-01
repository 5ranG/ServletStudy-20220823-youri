package com.servlet.study.web.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.servlet.study.web.domain.user.UserRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	@NonNull
	private final UserRepository userRepository; //필수생성자
	
	@Override
	public String getUserList() {
		Gson gson = new GsonBuilder()
				.setPrettyPrinting()
				.serializeNulls()
				.create();
		return gson.toJson(userRepository.getUserList());
	}
}
