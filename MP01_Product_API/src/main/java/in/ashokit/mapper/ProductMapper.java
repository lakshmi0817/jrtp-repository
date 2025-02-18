package in.ashokit.mapper;

import org.modelmapper.ModelMapper;

import in.ashokit.dto.ProductDto;
import in.ashokit.entity.Product;

public class ProductMapper {
	
	public static final ModelMapper modelMapper = new ModelMapper();
	
	public static ProductDto convertToDto(Product entity) {
		
		return modelMapper.map(entity,ProductDto.class);
		
	}
	
	public static Product convertToEntity(ProductDto dto) {
		
		
		return modelMapper.map(dto,Product.class);
	}

}
