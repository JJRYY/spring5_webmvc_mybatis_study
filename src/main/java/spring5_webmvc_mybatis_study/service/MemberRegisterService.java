package spring5_webmvc_mybatis_study.service;

import org.springframework.stereotype.Service;

import spring5_webmvc_mybatis_study.dto.RegisterRequest;

@Service
public interface MemberRegisterService {
	
	public Long regist(RegisterRequest req);
}
