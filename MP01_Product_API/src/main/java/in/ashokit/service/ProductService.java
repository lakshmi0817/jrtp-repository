package in.ashokit.service;

import java.util.List;

import in.ashokit.dto.ProductCategoryDto;
import in.ashokit.dto.ProductDto;
import in.ashokit.entity.Product;


public interface ProductService {
	
	public List<ProductCategoryDto> findAllCategories();
	
	public List<ProductDto> findProductByCategoryId(Long categoryid);
	
	public ProductDto findByProductId(Long productId);
	
	public List<ProductDto> findByProductName(String name);
	
	

}
