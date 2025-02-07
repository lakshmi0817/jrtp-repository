package in.ashokit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "state_master")
@Getter
@Setter
public class StateEntity {
	
	@Id
	private Integer stateId;
	private String stateName;
	
    @ManyToOne
	@JoinColumn(name="countryId")
	private CountryEntity country;

}
