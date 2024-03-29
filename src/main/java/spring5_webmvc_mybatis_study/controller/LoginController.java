package spring5_webmvc_mybatis_study.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring5_webmvc_mybatis_study.dto.AuthInfo;
import spring5_webmvc_mybatis_study.dto.LoginCommand;
import spring5_webmvc_mybatis_study.exception.WrongIdPasswordException;
import spring5_webmvc_mybatis_study.service.AuthService;
import spring5_webmvc_mybatis_study.validator.LoginCommandValidator;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private AuthService authService;
	
	@GetMapping
	public String form(LoginCommand loginCommand, @CookieValue(value="REMEMBER", required = false) Cookie rCookie) {
		if (rCookie != null) {
			loginCommand.setEmail(rCookie.getValue());
			loginCommand.setRememberEmail(true);
		}
		return "/login/loginForm";
	}
	
	@PostMapping
	public String submit(LoginCommand loginCommand, Errors errors, HttpSession session, HttpServletResponse response) {
		new LoginCommandValidator().validate(loginCommand, errors);
		
		if (errors.hasErrors()) {
			return "/login/loginForm";
		}
		try {
			AuthInfo authInfo = authService.authenicate(loginCommand.getEmail(), loginCommand.getPassword());
			// 세션에 authInfo 저장해야함
			session.setAttribute("authInfo", authInfo);
			
			Cookie rememberCookie = new Cookie("REMEMBER", loginCommand.getEmail());
			rememberCookie.setPath("/");
			if (loginCommand.isRememberEmail()) {
				rememberCookie.setMaxAge(60 * 60 * 24 * 30);
			} else {
				rememberCookie.setMaxAge(0);
			}
			response.addCookie(rememberCookie);
			
			return "/login/loginSuccess";
		} catch (WrongIdPasswordException ex) {
			errors.reject("idPasswordNotMatching");
			return "/login/loginForm";
		}
	}
	
}
