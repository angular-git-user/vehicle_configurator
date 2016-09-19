package app.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.dto.UserDto;
import app.entityClasses.User;
import app.translator.RequestTranslator;
import app.translator.ResponseTranslator;

@Component
public class UserService {
	
	@Autowired
	private RequestTranslator requestTranslator;
	
	@Autowired
	private ResponseTranslator responseTranslator;
	
	@Autowired
	PersistenceService hibernatePersistence;
	
	@Autowired
	EmailService email;
	
	public String sendVerificationEmail(String userEmailId){
		
		return null;
	}
	
	private int generateRandomCode() {
		Random r = new Random( System.currentTimeMillis() );
	    return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
	}

	public Integer createUser(UserDto userDto) {
		User user = requestTranslator.translateUserDtoToDAO(userDto);
		user.setVerificationCode(generateRandomCode());
		Integer id = hibernatePersistence.createUser(user);
		if(id!=null){
			email.sendEmail(user.getEmailId(), user.getVerificationCode(), user.getUserId());
		}
		return id;
	}

	public String login(String userId, String password) {
		User user = hibernatePersistence.getUserWithLoginId(userId);
		String message;
		if(!user.isVerified()){
			message = "Please click on the verification email sent to you on registered email-id :" + user.getEmailId() + "or click to generate OTP/ verification link once again.";
		}

		if (user != null && (user.getUserId().equals(userId)) && (user.getPassword().equals(password))) {
			message = "Success";
		} else {
			message = "Invalid user_id - password combination. Try again!";
		}
		return message;
	}

	public Boolean verifyUser(String userId, int verificationCode) {
		User u = hibernatePersistence.getUserWithLoginId(userId);
		UserDto user = responseTranslator.getUser(u);
		if(userId.equals(user.getUserId()) && !user.isVerified()){
			if(verificationCode == user.getVerificationCode()){
				u.setVerified(true);
				u.setVerificationCode(0);
				hibernatePersistence.updateUser(u);
				return true;	
			}
			return false;
		}
		return null;
	}

	public int regenrateVerificationCode(String userId) {
		User user = hibernatePersistence.getUserWithLoginId(userId);
		int newCode = generateRandomCode();
		if(!user.isVerified()){
			user.setVerificationCode(newCode);
			hibernatePersistence.updateUser(user);
			email.sendEmail(user.getEmailId(), user.getVerificationCode(), user.getUserId());
		}
		return newCode;
	}
}
