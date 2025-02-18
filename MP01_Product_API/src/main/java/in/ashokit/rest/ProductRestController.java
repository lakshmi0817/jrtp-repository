package in.ashokit.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.dto.ProductCategoryDto;
import in.ashokit.dto.ProductDto;
import in.ashokit.response.ApiResponse;
import in.ashokit.service.ProductService;

@RestController
public class ProductRestController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/categories")
	public ResponseEntity<ApiResponse<List<ProductCategoryDto>>> productCategories(){
		
		ApiResponse<List<ProductCategoryDto>> response = new ApiResponse<>();
		
		List<ProductCategoryDto> allCategories = productService.findAllCategories();
		
		if(!allCategories.isEmpty()){
		response.setStatus(200);
		response.setMessage("Fetched Categories Successfully");
		response.setData(allCategories);
		return new ResponseEntity<>(response,HttpStatus.OK);
		
		}else {
			response.setStatus(500);
			response.setMessage("Failed to Fetch ");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	
	@GetMapping("/products{categoryById}")
	public ResponseEntity<ApiResponse<List<ProductDto>>> products(@PathVariable Long categoryId){
		
		ApiResponse<List<ProductDto>> response = new ApiResponse<>();
		List<ProductDto> products = productService.findProductByCategoryId(categoryId);
		
		if(!products.isEmpty()){
			response.setStatus(200);
			response.setMessage("Fetched Categories Successfully");
			response.setData(products);
			return new ResponseEntity<>(response,HttpStatus.OK);
			
			}else {
				response.setStatus(500);
				response.setMessage("Failed to Fetch ");
				response.setData(null);
				return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
				
			}
	}
		
		@GetMapping("/productsByName{productName}")
		public ResponseEntity<ApiResponse<List<ProductDto>>> products(@PathVariable String productName){
			
			ApiResponse<List<ProductDto>> response = new ApiResponse<>();
			List<ProductDto> products = productService.findByProductName(productName);
			
			if(!products.isEmpty()){
				response.setStatus(200);
				response.setMessage("Fetched Products Successfully");
				response.setData(products);
				return new ResponseEntity<>(response,HttpStatus.OK);
				
				}else {
					response.setStatus(500);
					response.setMessage("Failed to Fetch ");
					response.setData(null);
					return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
					
				}
		
		
	}
		
		@GetMapping("/product{id}")
		public ResponseEntity<ApiResponse<ProductDto>> product(@PathVariable Long productId){
			
			ApiResponse<ProductDto> response = new ApiResponse<>();
			ProductDto product = productService.findByProductId(productId);
			
			if(product!=null){
				response.setStatus(200);
				response.setMessage("Fetched product Successfully");
				response.setData(product);
				return new ResponseEntity<>(response,HttpStatus.OK);
				
				}else {
					response.setStatus(500);
					response.setMessage("Failed to Fetch the product");
					response.setData(null);
					return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
					
				}
		
		
	}
	
	
	
	

}
