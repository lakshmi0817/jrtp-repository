package in.ashokit.dto;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CustomerDto {

	private Integer id;
	
	private String name;
	
	private String email;
	
	private String pwd;
	
	private String pwdUpdated;
	
	private String resetPwd;
	
	private Long phno;
	
	private Date dateCreated;
	
	private Date lastUpdated;
}
