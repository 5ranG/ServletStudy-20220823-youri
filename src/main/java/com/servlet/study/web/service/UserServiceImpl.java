package com.servlet.study.web.service;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.servlet.study.web.domain.user.User;
import com.servlet.study.web.domain.user.UserRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	@NonNull
	private final UserRepository userRepository; //필수생성자
	
	@Override
	public String getUserList() {
//		Gson gson = new GsonBuilder()
//				.setPrettyPrinting()
//				.serializeNulls()
//				.create();
		return getGson().toJson(userRepository.getUserList());
	}

	@Override
	public String checkUserId(String userId) {
		int result = userRepository.checkUserId(userId);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("checkFlag", result > 0 ? false : true); //1이면 회원가입 불가능
		
		return getGson().toJson(resultMap);
	}
	
	private Gson getGson() {
		return new GsonBuilder()
				.setPrettyPrinting()
				.serializeNulls()
				.create();
	}

	@Override
	public String addUser(User user) {
		int result = userRepository.save(user);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", result > 0);
		
		return getGson().toJson(resultMap);
	}
	
}
