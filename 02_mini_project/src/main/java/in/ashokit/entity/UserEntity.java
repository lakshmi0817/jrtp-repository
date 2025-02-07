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
@Table(name = "user_tbl")
@Getter
@Setter
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userId;
	private String userName;
	private String email;
	private String pwd;
	private String updatedPwd;
	private Long phno;
	
	@ManyToOne
	@JoinColumn(name="countryId")
	private CountryEntity country;
	
	@ManyToOne
	@JoinColumn(name="stateId")
	private StateEntity state;
	
	@ManyToOne
	@JoinColumn(name="cityId")
	private CityEntity city;

}
