package spring5_webmvc_mybatis_study.service;

import org.springframework.stereotype.Service;

@Service
public interface ChangePasswordService {

	public void changePassword(String email, String oldPwd, String newPwd);

}
