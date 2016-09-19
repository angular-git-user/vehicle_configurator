package app.translator;

import org.springframework.stereotype.Component;

import app.dto.UserDto;
import app.entityClasses.User;

@Component
public class RequestTranslator {

	public User translateUserDtoToDAO(UserDto user) {
		User dao = new User();
		if(user != null){
			dao.setFirstName(user.getFirstName());
			dao.setEmailId(user.getEmailId());
			dao.setMobileNumber(user.getMobileNumber());
			dao.setUserId(user.getUserId());
			dao.setPassword(user.getPassword());
		}
		return dao;
	}
}
