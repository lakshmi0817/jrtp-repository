package in.ashokit.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.DTO.CounsellorDTO;
import in.ashokit.entity.Counsellor;
import in.ashokit.repo.CounsellorRepo;
import in.ashokit.service.CounsellorService;

@Service
public class CounsellorServiceImpl implements CounsellorService {

	@Autowired
	private CounsellorRepo counsellorRepo;
	
	@Override
	public CounsellorDTO login(CounsellorDTO counsellorDTO) {

		Counsellor counsellor = counsellorRepo.findByEmailAndPassword(counsellorDTO.getEmail(),counsellorDTO.getPassword());
		if(counsellor !=null) {
			CounsellorDTO dto = new CounsellorDTO();
			BeanUtils.copyProperties(counsellor, dto);
			return dto;
		}
		return null;
	}

	@Override
	public boolean uniqueEmailCheck(String email) {
		
		Counsellor counsellor = counsellorRepo.findByEmail(email);
		
		return counsellor == null;
	}

	@Override
	public boolean register(CounsellorDTO counsellorDTO) {
		Counsellor counsellor = new Counsellor();
		BeanUtils.copyProperties(counsellorDTO, counsellor);
		
		Counsellor savedCoun = counsellorRepo.save(counsellor);
		return null != savedCoun.getCounsellorId();
	}

	
}
