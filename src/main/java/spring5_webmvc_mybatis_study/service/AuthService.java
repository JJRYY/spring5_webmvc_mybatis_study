package spring5_webmvc_mybatis_study.service;

import org.springframework.stereotype.Service;

import spring5_webmvc_mybatis_study.dto.AuthInfo;

@Service
public interface AuthService {
	
	public AuthInfo authenicate(String email, String password);
}
