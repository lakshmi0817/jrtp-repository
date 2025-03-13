package in.ashokit.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "orders_tbl")
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer order_id;
	private String order_tracking_num;
	private Integer total_quantity;
	private Double total_price;
	private String order_status;
	private String payment_status;
	private String razor_pay_order_id;
	private String razor_pay_payment_id;
	
	@CreationTimestamp
	private LocalDate date_created;
	
	@UpdateTimestamp
	private LocalDate last_updated;
	
	@JoinColumn(name = "customer_id")
	@ManyToOne
	private Integer customer_id;
	
	@JoinColumn(name = "addr_id")
	@OneToMany
	private Integer addr_id;
	
	
	
	
	
	
	
	
	
}
