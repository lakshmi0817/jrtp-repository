package in.ashokit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.ashokit.DTO.DashboardDTO;
import in.ashokit.DTO.EnqFilterDTO;
import in.ashokit.DTO.EnquiryDTO;


public interface EnquiryService {

	
	public DashboardDTO getDashboardInfo(Integer counsellorId);
	
	public boolean addEnquiry(EnquiryDTO enqDTO,Integer counsellorId);
	
	public List<EnquiryDTO> getEnquiry(Integer counsellorId);
	
	public List<EnquiryDTO> getEnquiry(EnqFilterDTO filterDTO, Integer counsellorId);
	
	public EnquiryDTO getEnqById(Integer enquiryId);
}
