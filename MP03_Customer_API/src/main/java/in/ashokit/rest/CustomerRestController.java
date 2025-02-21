package in.ashokit.rest;

import javax.swing.text.Highlighter.HighlightPainter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.dto.CustomerDto;
import in.ashokit.dto.ResetPwdDto;
import in.ashokit.response.ApiResponse;
import in.ashokit.response.AuthResponse;
import in.ashokit.service.CustomerService;

@RestController
public class CustomerRestController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse<String>> register(@RequestBody CustomerDto customerDto){
		
		ApiResponse<String> response = new ApiResponse<>();
		Boolean emailUnique = customerService.isEmailUnique(customerDto.getEmail());
		if(!emailUnique) {
			response.setStatus(400);
			response.setMessage("Failed");
			response.setData("Duplicate Email");
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		
		Boolean register = customerService.register(customerDto);
		if(register) {
			response.setStatus(201);
			response.setMessage("Success");
			response.setData("Registration Success");
			return new ResponseEntity<>(response,HttpStatus.CREATED);
			
		}
		else {
			response.setStatus(500);
			response.setMessage("Failed");
			response.setData("Registration Failed");
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody CustomerDto customerDto){
		
		ApiResponse<AuthResponse> response = new ApiResponse<>();
		
		AuthResponse authResp = customerService.login(customerDto);
		
		if(authResp != null) {
			response.setStatus(200);
			response.setMessage("Login Success");
			response.setData(authResp);
			return new ResponseEntity<ApiResponse<AuthResponse>>(response,HttpStatus.OK);
		}else {
			response.setStatus(400);
			response.setMessage("Invalid Credentials");
			response.setData(null);
			return new ResponseEntity<ApiResponse<AuthResponse>>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/reset-pwd")
	public ResponseEntity<ApiResponse<String>> resetPwd(@RequestBody ResetPwdDto resetPwdDto){
		ApiResponse<String> response = new ApiResponse<>();
		
		CustomerDto customer = customerService.getCustomerByEmail(resetPwdDto.getEmail());
		
		if(!pwdEncoder.matches(customer.getPwd(),resetPwdDto.getOldPwd())) {
			response.setStatus(400);
			response.setMessage("Failed");
			response.setData("Old pwd is incorrect");
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		
		Boolean status = customerService.resetPwd(resetPwdDto);
		
		if(status) {
			response.setStatus(200);
			response.setMessage("Success");
			response.setData("Password Updated");
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			response.setStatus(500);
			response.setMessage("Failed");
			response.setData("Pwd Reset Failed");
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
    @GetMapping("/forgot-pwd/{email}")
	public ResponseEntity<ApiResponse<String>> forgotPwd(@PathVariable String email){
		ApiResponse<String> response = new ApiResponse<>();
		
		Boolean status = customerService.forgotPwd(email);
		
		if(status) {
			response.setStatus(200);
			response.setMessage("Success");
			response.setData("Email Sent to Reset Pwd");
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			response.setStatus(400);
			response.setMessage("Failed");
			response.setData("No account found");
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
			
		}
	}
	
	
	
	
	
	
	
	
	
	
}
