package spring5_webmvc_mybatis_study.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring5_webmvc_mybatis_study.dto.AuthInfo;
import spring5_webmvc_mybatis_study.dto.ChangePwdCommand;
import spring5_webmvc_mybatis_study.exception.WrongIdPasswordException;
import spring5_webmvc_mybatis_study.service.ChangePasswordService;
import spring5_webmvc_mybatis_study.validator.ChangePwdCommandValidator;

@Controller
@RequestMapping("/edit/changePassword")
public class ChangePwdController {
	@Autowired
	private ChangePasswordService changePasswordService;
	
	@GetMapping
	public String form(@ModelAttribute("command") ChangePwdCommand pwdCommand) {
		return "edit/changePwdForm";
	}
	
	@PostMapping
	public String submit(@ModelAttribute("command") ChangePwdCommand pwdCommand, Errors errors, HttpSession session) {
		new ChangePwdCommandValidator();
		
		if (errors.hasErrors())
			return "edit/changePwdForm";
		
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		
		try {
			changePasswordService.changePassword(authInfo.getEmail(), pwdCommand.getCurrentPassword(), pwdCommand.getNewPassword());
			return "edit/changedPwd";
		} catch (WrongIdPasswordException e) {
			errors.rejectValue("currentPassword", "notMatching");
			return "edit/changePwdForm";
		}
	}
}
