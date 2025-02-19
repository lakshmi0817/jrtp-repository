package in.ashokit.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "customer_tbl")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	private String pwd;
	private String pwdUpdated;
	private String resetPwd;
	private Long phno;
	
	@Column(name = "date_created")
	@CreationTimestamp
	private Date dateCreated;
	
	@Column(name = "last_updated")
	@UpdateTimestamp
	private Date lastUpdated;

	

}
