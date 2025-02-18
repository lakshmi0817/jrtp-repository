package in.ashokit.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class Order_items {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer oItems_id;
	private Integer quantity;
	private Double price;
	private byte[] image_url;
	
	@JoinColumn
	@OneToMany
	private Integer order_id;
	
	@JoinColumn
	@OneToOne
	private Integer product_id;

}
