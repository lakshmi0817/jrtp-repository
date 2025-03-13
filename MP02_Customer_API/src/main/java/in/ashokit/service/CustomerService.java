package in.ashokit.service;

import in.ashokit.dto.CustomerDto;
import in.ashokit.dto.ResetPwdDto;
import in.ashokit.response.AuthResponse;

public interface CustomerService {
	
	public Boolean isEmailUnique(String email);
	
	public Boolean register(CustomerDto cutomerDto);
	
	public Boolean resetPwd(ResetPwdDto resetPwdDto);
	
	public CustomerDto getCustomerByEmail(String email);
	
	public AuthResponse login(CustomerDto customerDto);

	public Boolean forgotPwd(String email);
}
