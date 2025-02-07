package in.ashokit.service;

import org.springframework.stereotype.Service;

@Service
public class MsgService {
	
	public String getWelcomeMsg() {
		return "Welcome to AshokIT";
	}
	
	public String getGreetMsg() {
		return "Good Morning";
	}

}
