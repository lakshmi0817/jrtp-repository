package in.ashokit.mapper;

import org.modelmapper.ModelMapper;

import in.ashokit.dto.ProductCategoryDto;
import in.ashokit.entity.ProductCategory;

public class ProductCategoryMapper {
	
	public static final ModelMapper modelMapper = new ModelMapper();
		
		public static ProductCategoryDto convertToDto(ProductCategory entity) {
			
			return modelMapper.map(entity,ProductCategoryDto.class);
			
		}
		
		public static ProductCategory convertToEntity(ProductCategoryDto dto) {
			
			return modelMapper.map(dto,ProductCategory.class);
			
		}
	

}
