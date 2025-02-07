package in.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.model.User;
import in.ashokit.service.UserService;

@RestController
public class UserRestController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/users")
	public ResponseEntity<String> saveUser(@RequestBody User user){
		boolean saveUser = userService.saveUser(user);
		if(saveUser) {
			sendEmail();
			return new ResponseEntity<String>("User is saved",HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<String>("User is not Saved",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private String sendEmail() {
		
		//logic
		
		return null;
	}
}
