package courses.microservices.beerservice.service.inventory.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BeerInventoryDto {
  private Integer quantityOnHand;
}
