package in.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.service.MsgService;

@RestController	
public class MsgController {
	
	@Autowired
	private MsgService msgService;
	
	@GetMapping("/welcome")
	public String getWelcome() {
		String welcomeMsg = msgService.getWelcomeMsg();
		return welcomeMsg;
	}
	
	@GetMapping("/greet")
	public String getGreet() {
		String greetMsg = msgService.getGreetMsg();
		return greetMsg;
	}

}
