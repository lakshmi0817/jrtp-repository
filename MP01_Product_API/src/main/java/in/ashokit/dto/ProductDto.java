package in.ashokit.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

	private Long productId;
	private String productName;
	private String desc;
	private String title;
	private BigDecimal unitPrice;
	private String imageUrl;
	private boolean active;
	private int unitStock;
	private Date dateCreated;
	private Date lastUpdated;
}
