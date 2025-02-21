package in.ashokit.service;

import java.util.Collections;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.ashokit.dto.CustomerDto;
import in.ashokit.dto.ResetPwdDto;
import in.ashokit.entity.Customer;
import in.ashokit.mapper.CustomerMapper;
import in.ashokit.repo.CustomerRepo;
import in.ashokit.response.AuthResponse;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private AuthenticationManager authManager;	
	Random rnd = new Random();

	@Override
	public Boolean isEmailUnique(String email) {
        Customer c = customerRepo.findByEmail(email);
		return c==null;
	}

	@Override
	public Boolean register(CustomerDto customerDto) {
        String orgPwd = getRandomPwd();
        String encodedPwd = pwdEncoder.encode(orgPwd);
        Customer entity = CustomerMapper.convertToEntity(customerDto);
        entity.setPwd(encodedPwd);
        entity.setPwdUpdated("NO");
        Customer savedEntity = customerRepo.save(entity);
        
        if(savedEntity.getId()!=null) {
        	String subject = "Registration Success";
        	String body = "Your Login Password :: " +orgPwd;
        	return emailService.sendMail(customerDto.getEmail(), subject, body);
        }
		return false;
	}

	@Override
	public Boolean resetPwd(ResetPwdDto resetPwdDto) {
        Customer c = customerRepo.findByEmail(resetPwdDto.getEmail());
        if(c!=null) {
        	String newPwd = resetPwdDto.getNewPwd();
            String encodedPwd = pwdEncoder.encode(newPwd);
            c.setPwd(encodedPwd);
            c.setPwdUpdated("NO");
            customerRepo.save(c);
            return true;
        }
		return false;
	}

	@Override
	public CustomerDto getCustomerByEmail(String email) {
		Customer c = customerRepo.findByEmail(email);
		if(c!=null) {
			return CustomerMapper.convertToDto(c);
		}
		return null;
	}

	@Override
	public AuthResponse login(CustomerDto customerDto) {
		AuthResponse response = null;
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(customerDto.getEmail(), customerDto.getPwd());
		Authentication authenticate = authManager.authenticate(token);
		
		if(authenticate.isAuthenticated()) {
			response= new AuthResponse();
			Customer c = customerRepo.findByEmail(customerDto.getEmail());
			response.setCustomer(CustomerMapper.convertToDto(c));
			response.setToken("");
		}
		return response;
	}
	
	@Override
	public Boolean forgotPwd(String email) {
		Customer c = customerRepo.findByEmail(email);
		if(c!=null) {
			String subject = "Reset Pwd Request";
			String body = "temp body";
			emailService.sendMail(email, subject, body);
			return true;
		}
		return false;
	}
	
	private String getRandomPwd() {
		
		String saltChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
		StringBuilder pwd = new StringBuilder();
		
		while((pwd.length()<5)) {
			int index = rnd.nextInt()*saltChars.length();
			pwd.append(saltChars.charAt(index));
		}
		return pwd.toString();
		
		
	}
	
	

}
