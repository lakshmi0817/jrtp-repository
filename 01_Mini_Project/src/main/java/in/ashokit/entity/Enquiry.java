package in.ashokit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Enquiry_tbl")
@Setter
@Getter

public class Enquiry {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer enquiryId;
	private String name;
	private Long phno;
	private String classMode;
	private String course;
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "counsellor_id")
	private Counsellor counsellor;

}
