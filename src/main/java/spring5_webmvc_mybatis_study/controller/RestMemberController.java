package spring5_webmvc_mybatis_study.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import spring5_webmvc_mybatis_study.dto.ErrorResponse;
import spring5_webmvc_mybatis_study.dto.Member;
import spring5_webmvc_mybatis_study.dto.RegisterRequest;
import spring5_webmvc_mybatis_study.exception.DuplicateMemberException;
import spring5_webmvc_mybatis_study.exception.MemberNotFoundException;
import spring5_webmvc_mybatis_study.service.MemberRegisterService;
import spring5_webmvc_mybatis_study.service.RestMemberService;
import spring5_webmvc_mybatis_study.validator.RegisterRequestValidator;

@RestController
public class RestMemberController {
	@Autowired
	private RestMemberService restMemberService;
	
	@Autowired
	private MemberRegisterService memberRegisterService;
	
	@GetMapping("/api/members")
	public List<Member> members(){
		return restMemberService.showMembers();
	}
	
	@GetMapping("/api/members/{id}")
	public ResponseEntity<Object> member (@PathVariable Long id, HttpServletResponse response) throws IOException {
		Member member = restMemberService.showMemberById(id);
		if (member == null) {
//			response.sendError(HttpServletResponse.SC_NOT_FOUND);
//			return null;
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("no member"));
			throw new MemberNotFoundException();
		}
//		return member;
		return ResponseEntity.status(HttpStatus.OK).body(member);
	}
	
	@ExceptionHandler(MemberNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNoData(){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("no member"));
	}
	
	@PostMapping("/api/members")
	public ResponseEntity<Object> newMember (@RequestBody RegisterRequest regReq, Errors errors, HttpServletResponse response) throws IOException {
		try {
			new RegisterRequestValidator().validate(regReq, errors);
			if (errors.hasErrors()) {
//				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
//				return ResponseEntity.badRequest().build();
				String errorCode = errors.getAllErrors()
						.stream()
						.map(error -> error.getCodes()[0])
						.collect(Collectors.joining(","));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(new ErrorResponse("errorCodes = " + errorCode));
			}
			Long newMemberId = memberRegisterService.regist(regReq);
//			response.setHeader("Location", "/api/members/" + newMemberId);
//			response.setStatus(HttpServletResponse.SC_CREATED);
			URI uri = URI.create("/api/members/" + newMemberId);
			return ResponseEntity.created(uri).build();
		} catch (DuplicateMemberException e) {
//			response.sendError(HttpServletResponse.SC_CONFLICT);
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
