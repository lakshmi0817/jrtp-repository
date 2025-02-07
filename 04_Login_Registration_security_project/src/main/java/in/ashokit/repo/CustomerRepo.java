package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.Customer;
import java.util.List;


public interface CustomerRepo extends JpaRepository<Customer,Integer>{

	public Customer findByEmail(String email);
}
