package spring5_webmvc_mybatis_study.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import spring5_webmvc_mybatis_study.dto.ListCommand;
import spring5_webmvc_mybatis_study.dto.Member;

@Component
public interface MemberMapper {
	
	List<Member> selectMemberAll();
	Member selectMemberByEmail(String email);
	int countMember();
	int insertMember(Member member);
	int updateMember(Member member);
	int deleteMember(Member member);
	
	List<Member> selectMemberByRegdate(ListCommand listCommand);
	Member selectById(Long memId);
}
