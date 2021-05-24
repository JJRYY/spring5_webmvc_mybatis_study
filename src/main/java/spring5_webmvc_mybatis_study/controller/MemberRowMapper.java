package spring5_webmvc_mybatis_study.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import spring5_webmvc_mybatis_study.dto.Member;

public class MemberRowMapper implements RowMapper<Member> {

	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		Member member = new Member(rs.getString("email"), rs.getString("password"), rs.getString("name")
				, rs.getTimestamp("regdate").toLocalDateTime());
		member.setId(rs.getLong("id"));
		return member;
	}

}
