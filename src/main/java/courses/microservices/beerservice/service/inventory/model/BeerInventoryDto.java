package courses.microservices.beerservice.service.inventory.model;

import java.time.OffsetDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class BeerInventoryDto {
  private UUID id;
  private OffsetDateTime createdDate;
  private OffsetDateTime lastModifiedDate;
  private UUID beerId;
  private String upc;
  private Integer quantityOnHand;
}
