package in.ashokit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "city_master")
@Getter
@Setter
public class CityEntity {
	
	@Id
	private Integer cityId;
	private String cityName;
	
	@ManyToOne
	@JoinColumn(name = "stateId")
	private StateEntity state;
	

}
