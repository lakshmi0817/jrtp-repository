package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.dto.ProductCategoryDto;
import in.ashokit.entity.Product;

import java.util.Collection;
import java.util.List;


public interface ProductRepo extends JpaRepository<Product,Long> {
	
	public List<Product>  findByCategoryId(Long categoryId);
	
	public List<Product> findByNameContaining(String productName);

	

}
