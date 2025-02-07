package in.ashokit.test;

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

import in.ashokit.rest.MsgController;
import in.ashokit.service.MsgService;

@WebMvcTest(value = MsgController.class)
public class MsgControllerTest {
	
	@MockitoBean
	private MsgService msgService;
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getMsg() throws Exception {
		
		when(msgService.getWelcomeMsg()).thenReturn("Welcome");
		 MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/welcome");
		 
		 MvcResult result = mockMvc.perform(reqBuilder).andReturn();
		 
		 MockHttpServletResponse response = result.getResponse();
		 
		 String actual = response.getContentAsString();
		 String expected = "Welcome";
		 
		 assertEquals(expected,actual);
	}
	
	@Test
	public void greet() throws Exception {
		
		when(msgService.getGreetMsg()).thenReturn("Good Afternoon");
		
	    MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/greet");
		
	    MvcResult result = mockMvc.perform(reqBuilder).andReturn();
	    
	    MockHttpServletResponse response = result.getResponse();
	    int actual = response.getStatus();
	    int expected = 200;
	    
	    assertEquals(expected,actual);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
