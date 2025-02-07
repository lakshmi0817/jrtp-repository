package in.ashokit.test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import in.ashokit.dao.UserDao;
import in.ashokit.model.User;
import in.ashokit.service.UserService;

@SpringBootTest
@ExtendWith(value = MockitoExtension.class)
public class UserServiceTest {
	
	@MockitoBean
	private UserDao userDao;

	@InjectMocks
	private UserService userService;
	
	@Test
	public void testUserService() {
		
		User user = new User();
		user.setId(101);
		user.setName("sree");
		
		boolean actual = userService.saveUser(user);
		
		assertTrue(actual);
	}
}
