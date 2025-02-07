package in.ashokit.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.ashokit.DTO.DashboardDTO;
import in.ashokit.DTO.EnqFilterDTO;
import in.ashokit.DTO.EnquiryDTO;
import in.ashokit.entity.Counsellor;
import in.ashokit.entity.Enquiry;
import in.ashokit.repo.CounsellorRepo;
import in.ashokit.repo.EnquiryRepo;
import in.ashokit.service.EnquiryService;

@Service
public class EnquiryServiceImpl implements EnquiryService{

	@Autowired
	private EnquiryRepo enqRepo;
	
	@Autowired
	private CounsellorRepo counsellorRepo;
	
	@Override
	public DashboardDTO getDashboardInfo(Integer counsellorId) {
		List<Enquiry> enqList = enqRepo.findByCounsellorCounsellorId(counsellorId);
		
		DashboardDTO dto = new DashboardDTO();
		dto.setTotEnquiry(enqList.size());
		
		int opencnt = enqList.stream()
				       .filter(enq->enq.getStatus()
				       .equals("open"))
				       .collect(Collectors.toList())
				       .size();
		
		dto.setOpenEnquiry(opencnt);
		
		int enrollcnt = enqList.stream()
				           .filter(enq->enq.getStatus()
				           .equals("enrolled"))
				           .collect(Collectors.toList())
				           .size();
		
		dto.setEnrolledEnquiry(enrollcnt);
		
		int lostcnt = enqList.stream()
		           .filter(enq->enq.getStatus()
		           .equals("lost"))
		           .collect(Collectors.toList())
		           .size();

      dto.setLostEnquiry(lostcnt);
		
		return dto;
	}

	@Override
	public boolean addEnquiry(EnquiryDTO enqDTO, Integer counsellorId) {
		
		
		Enquiry enquiry = new Enquiry();
		BeanUtils.copyProperties(enqDTO, enquiry);
		
		Optional<Counsellor> byId = counsellorRepo.findById(counsellorId);
		if(byId.isPresent()) {
			Counsellor counsellor = byId.get();
			enquiry.setCounsellor(counsellor);
		}
		
		Enquiry save = enqRepo.save(enquiry);
		return save.getEnquiryId()!=null;
	}

	@Override
	public List<EnquiryDTO> getEnquiry(Integer counsellorId) {
		
		List<EnquiryDTO> enqDtoList = new ArrayList<>();
		
		List<Enquiry> enqList = enqRepo.findByCounsellorCounsellorId(counsellorId);
		
		for(Enquiry enquiry : enqList) {
			EnquiryDTO dto = new EnquiryDTO();
			BeanUtils.copyProperties(enquiry, dto);
			enqDtoList.add(dto);
		}
		return enqDtoList;
	}

	@Override
	public List<EnquiryDTO> getEnquiry(EnqFilterDTO filterDTO, Integer counsellorId) {
		Enquiry enquiry = new Enquiry();
		
		if(filterDTO.getClassMode()!=null && !filterDTO.getClassMode().equals("")) {
			enquiry.setClassMode(filterDTO.getClassMode());
		}
		
		if(filterDTO.getCourse()!= null && !filterDTO.getCourse().equals("")) {
			enquiry.setCourse(filterDTO.getCourse());
		}
		
		if(filterDTO.getStatus()!= null && !filterDTO.getStatus().equals("")) {
			enquiry.setStatus(filterDTO.getStatus());
		}
		
		Counsellor counsellor = new Counsellor();
		counsellor.setCounsellorId(counsellorId);
		enquiry.setCounsellor(counsellor);
		
		Example<Enquiry> of = Example.of(enquiry);
		
		List<Enquiry> enqList = enqRepo.findAll(of);
		
		List<EnquiryDTO> enqDtoList = new ArrayList<>();
		for(Enquiry enq : enqList) {
			EnquiryDTO dto = new EnquiryDTO();
			BeanUtils.copyProperties(enq, dto);
			enqDtoList.add(dto);
			
		}
		
		return enqDtoList;
		
	}

	@Override
	public EnquiryDTO getEnqById(Integer enqiryId) {
		Optional<Enquiry> byId = enqRepo.findById(enqiryId);
		
		if(byId.isPresent()) {
			Enquiry enquiry = byId.get();
			EnquiryDTO dto = new EnquiryDTO();
			BeanUtils.copyProperties(enquiry, dto);
			return dto;
		}
		return null;
	}

	
}
