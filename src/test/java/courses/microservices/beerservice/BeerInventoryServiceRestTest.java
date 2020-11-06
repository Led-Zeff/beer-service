package courses.microservices.beerservice;

import java.util.UUID;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import courses.microservices.beerservice.service.inventory.BeerInventoryService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Disabled
@SpringBootTest
public class BeerInventoryServiceRestTest {
  
  @Autowired
  BeerInventoryService beerInventoryService;

  @Test
  void getQuentityOnHand() {
    Integer qoh = beerInventoryService.getOnHandInventory(UUID.randomUUID());
    log.info(qoh.toString());
  }

}
