package spring5_webmvc_mybatis_study.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring5_webmvc_mybatis_study.dto.AuthInfo;
import spring5_webmvc_mybatis_study.dto.Member;
import spring5_webmvc_mybatis_study.exception.WrongIdPasswordException;
import spring5_webmvc_mybatis_study.mapper.MemberMapper;
import spring5_webmvc_mybatis_study.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
	private MemberMapper memberMapper;
	
	public AuthInfo authenicate(String email, String password) {
		Member member = memberMapper.selectMemberByEmail(email);
		
		if (member == null) {
			throw new WrongIdPasswordException();
		}
		
		if (!member.matchPassword(password)) {
			throw new WrongIdPasswordException();
		}
		
		return new AuthInfo(member.getId(), member.getEmail(), member.getName());
	}
}
