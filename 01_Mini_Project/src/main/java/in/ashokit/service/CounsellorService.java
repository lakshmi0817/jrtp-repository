package in.ashokit.service;

import org.springframework.stereotype.Service;

import in.ashokit.DTO.CounsellorDTO;


public interface CounsellorService {

	
	public CounsellorDTO login(CounsellorDTO counsellorDTO);
	
	public boolean uniqueEmailCheck(String email);
	
	public boolean register(CounsellorDTO counsellorDTO);
}
