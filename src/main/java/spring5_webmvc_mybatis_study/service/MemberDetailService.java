package spring5_webmvc_mybatis_study.service;

import org.springframework.stereotype.Service;

import spring5_webmvc_mybatis_study.dto.Member;

@Service
public interface MemberDetailService {
	public Member showMemberById(Long memId);
}
