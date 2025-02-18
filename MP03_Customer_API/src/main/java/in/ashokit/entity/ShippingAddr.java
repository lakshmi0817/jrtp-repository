package in.ashokit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class ShippingAddr {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer addr_id;
	private String house_num;
	private String street;
	private String city;
	private String state;
	private Long zipcode;
	private String country;
}
