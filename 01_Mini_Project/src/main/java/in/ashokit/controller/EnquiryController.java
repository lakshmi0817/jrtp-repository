package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.DTO.EnqFilterDTO;
import in.ashokit.DTO.EnquiryDTO;
import in.ashokit.service.EnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {
	
	@Autowired
	private EnquiryService enqService;
	
	@GetMapping("/edit-enquiry")
	public String editEnquiry(@RequestParam("enquiryId") Integer enquiryId,Model model)	{
		
		 EnquiryDTO enquiryDTO = enqService.getEnqById(enquiryId);
		 
		 model.addAttribute("enquiry",enquiryDTO);
		 
		 return "add-enquiry";
	}
	
	@GetMapping("/enquiry-page")
	public String loadEnq(Model model) {
		
		EnquiryDTO enqDto = new EnquiryDTO();
		model.addAttribute("enquiry",enqDto);
		return "add-enquiry";
	
	}
	@PostMapping("/add-enquiry")
	public String addEnquiry( HttpServletRequest req,@ModelAttribute("enquiry") EnquiryDTO enquiry,Model model) {
		
		HttpSession session = req.getSession(false);
		Integer counsellorId = (Integer)session.getAttribute("counsellorId");
		
		boolean status = enqService.addEnquiry(enquiry,counsellorId);
		if(status) {
			model.addAttribute("smsg","Enquiry Saved");
		}else {
			model.addAttribute("emsg","Failed to save Enquiry");
		}
		
		return "add-enquiry";
		
	}
	
	@GetMapping("/view-enquiry")
	public String getEnquiries(HttpServletRequest req,Model model) {
		
		HttpSession session = req.getSession(false);
		Integer counsellorId =(Integer)session.getAttribute("counsellorId");
		List<EnquiryDTO> enqList = enqService.getEnquiry(counsellorId);
		model.addAttribute("enquiries",enqList);
		
		EnqFilterDTO filterDTO = new EnqFilterDTO();
		model.addAttribute("filterDTO",filterDTO);
		
		return "view-enquiry";
	}
	
	@PostMapping("/filter-enquiry")
	public String filterEnquiries(HttpServletRequest req,@ModelAttribute("filterDTO") EnqFilterDTO filterDTO,Model model) {
		
		HttpSession session = req.getSession(false);
		Integer counsellorId =(Integer)session.getAttribute("counsellorId");
		List<EnquiryDTO> enquiry = enqService.getEnquiry(filterDTO, counsellorId);
		model.addAttribute("enquiries",enquiry);
		
		return "view-enquiry";
	}
	
	

	
	
	
	
	
}
