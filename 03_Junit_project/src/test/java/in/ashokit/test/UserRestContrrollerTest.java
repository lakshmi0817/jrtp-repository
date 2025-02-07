package in.ashokit.test;

import static org.mockito.ArgumentMatchers.any;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.ashokit.model.User;
import in.ashokit.rest.UserRestController;
import in.ashokit.service.UserService;

@WebMvcTest(value = UserRestController.class)
public class UserRestContrrollerTest {
	
	@MockitoBean
	private UserService userService;
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testUser_TC1() throws Exception{
		
		User user = new User();
		user.setId(101);
		user.setName("Sree");
		
		ObjectMapper objMapper = new ObjectMapper();
		String asString = objMapper.writeValueAsString(user);
		
		when(userService.saveUser(any())).thenReturn(true);
		
		MockHttpServletRequestBuilder reqBuilder = 
				           MockMvcRequestBuilders.post("/users")
				                                 .content(asString)
				                                 .contentType("application/json");
		
		MvcResult result = mockMvc.perform(reqBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		int actual = response.getStatus();
		int expected = 201;
		
		assertEquals(expected,actual);
	}
	
	@Test
	public void testUser_TC2() throws Exception{
		
		User user = new User();
		user.setId(101);
		user.setName("Sree");
		
		ObjectMapper objMapper = new ObjectMapper();
		String asString = objMapper.writeValueAsString(user);
		
		when(userService.saveUser(any())).thenReturn(false);
		
		MockHttpServletRequestBuilder reqBuilder = 
				                MockMvcRequestBuilders.post("/users")
				                                      .content(asString)
                                                      .contentType("application/json");
		
		MvcResult result = mockMvc.perform(reqBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		int actual = response.getStatus();
		int expected = 500;
		
		assertEquals(expected,actual);
	}
}
