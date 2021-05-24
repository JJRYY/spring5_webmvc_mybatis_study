package spring5_webmvc_mybatis_study.mapper;

import spring5_webmvc_mybatis_study.dto.Member;

public interface MemberMapper {
	int insertMember(Member member);
	
	Member selectMemberByEmail(String email);
}
