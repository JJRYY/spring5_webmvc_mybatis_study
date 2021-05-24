package spring5_webmvc_mybatis_study.mapper;

import java.util.List;

import spring5_webmvc_mybatis_study.dto.Member;

public interface MemberMapper {
	
	List<Member> selectMemberAll();
	Member selectMemberByEmail(String email);
	int countMember();
	int insertMember(Member member);
	int updateMember(Member member);
	int deleteMember(Member member);
}
