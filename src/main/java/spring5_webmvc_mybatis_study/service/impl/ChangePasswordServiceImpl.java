package spring5_webmvc_mybatis_study.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring5_webmvc_mybatis_study.dto.Member;
import spring5_webmvc_mybatis_study.exception.MemberNotFoundException;
import spring5_webmvc_mybatis_study.mapper.MemberMapper;
import spring5_webmvc_mybatis_study.service.ChangePasswordService;

@Service
public class ChangePasswordServiceImpl implements ChangePasswordService {
	@Autowired
	private MemberMapper memberMapper;

	@Transactional
	public void changePassword(String email, String oldPwd, String newPwd) {
		Member member = memberMapper.selectMemberByEmail(email);

		if (member == null) {
			throw new MemberNotFoundException();
		}

		member.changePassword(oldPwd, newPwd);
		memberMapper.updateMember(member);

	}

}
