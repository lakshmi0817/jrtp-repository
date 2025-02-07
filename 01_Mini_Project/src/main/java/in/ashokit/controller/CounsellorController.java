package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.DTO.CounsellorDTO;
import in.ashokit.DTO.DashboardDTO;
import in.ashokit.service.CounsellorService;
import in.ashokit.service.EnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {
	
	@Autowired
	private CounsellorService counsellorService;
	
	@Autowired
	private EnquiryService enqService;
	
	@GetMapping("/")
	public String login(Model model) {
		CounsellorDTO cdto = new CounsellorDTO();
		model.addAttribute("counsellor",cdto);
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest req,Model model) {
		
		HttpSession session = req.getSession(false);
		session.invalidate();
		
		CounsellorDTO cdto = new CounsellorDTO();
		model.addAttribute("counsellor",cdto);
		
		return "login";
		
		
	}
	
	@PostMapping("/login")
	public String handleLogin(HttpServletRequest req,@ModelAttribute("counsellor")CounsellorDTO counsellor,Model model) {
		
		CounsellorDTO counsellorDto = counsellorService.login(counsellor);
		
		if(counsellorDto == null) {
			model.addAttribute("emsg","Invalid Credentials");
			
			return "login";
		}else {
			Integer counsellorId = counsellorDto.getCounsellorId();
			
			HttpSession session = req.getSession(true);
			session.setAttribute("counsellorId", counsellorId);
			
			
			DashboardDTO dbDTO = enqService.getDashboardInfo(counsellorId);
			model.addAttribute("dbDTO",dbDTO);
			return "dashboard";
		}
		
		}
	
	@GetMapping("/register")
	public String register(Model model) {
		CounsellorDTO cdto = new CounsellorDTO();
		model.addAttribute("counsellor",cdto);
		return "register";
	}
	
	@PostMapping("/register")
	public String handleRegister( @ModelAttribute("counsellor") CounsellorDTO counsellor,Model model) {
		boolean unique = counsellorService.uniqueEmailCheck(counsellor.getEmail());
		if(unique) {
			
			boolean register = counsellorService.register(counsellor);
			
			if(register) {
				model.addAttribute("smsg","Registration Success");
			}
			else {
				model.addAttribute("emsg","Failed to Register");
			}


			
		}else {
			model.addAttribute("emsg","Enter Unique Email");
			
			
		}
		return "register";
	}
	
	@GetMapping("/dashboad")
	public String displayDashboard(HttpServletRequest req,Model model) {
		HttpSession session = req.getSession(false);
		Integer counsellorId =(Integer)session.getAttribute("counsellorId");
		
		DashboardDTO dashboardDto = enqService.getDashboardInfo(counsellorId);
		model.addAttribute("dashboardDto",dashboardDto);
		
		return "dashboard";
		
	}
	
	
	
	
	
	
	
}
