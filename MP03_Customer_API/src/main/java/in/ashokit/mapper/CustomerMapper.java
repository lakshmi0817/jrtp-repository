package in.ashokit.mapper;

import org.modelmapper.ModelMapper;

import in.ashokit.dto.CustomerDto;
import in.ashokit.entity.Customer;

public class CustomerMapper {
	
	public static final ModelMapper modelMapper = new ModelMapper();
	
	public static CustomerDto convertToDto(Customer entity) {
		return modelMapper.map(entity,CustomerDto.class);
	}
	
	public static Customer convertToEntity(CustomerDto dto) {
		return modelMapper.map(dto,Customer.class);
	}

}
