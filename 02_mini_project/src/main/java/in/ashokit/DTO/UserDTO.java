package in.ashokit.DTO;

import lombok.Data;

@Data
public class UserDTO {
	
	private Integer userId;
	private String userName;
	private String email;
	private String pwd;
	private String updatedPwd;
	private Long phno;
	
	private Integer countryId;
	private Integer stateId;
	private Integer cityId;

}
