package product.micro.application.dto;

import product.micro.domain.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {
    private String uuid;
    private String fantasyName;
    private Category category;
    private String description;
    private float price;
    private boolean available;
}
