package in.ashokit.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import in.ashokit.entity.Customer;
import in.ashokit.repo.CustomerRepo;

public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Customer c = customerRepo.findByEmail(email);
		return new User(c.getEmail(),c.getPwd(),Collections.emptyList());
	}

}
