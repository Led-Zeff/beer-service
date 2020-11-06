package courses.microservices.beerservice.service.inventory;

import java.util.UUID;

public interface BeerInventoryService {
  Integer getOnHandInventory(UUID beerId);
}
