package courses.microservices.beerservice;

import java.util.UUID;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import courses.microservices.beerservice.service.inventory.BeerInventoryService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@Disabled
public class BeerInventoryServiceRestTest {
  
  @Autowired
  BeerInventoryService beerInventoryService;

  @Test
  void getQuentityOnHand() {
    Integer qoh = beerInventoryService.getOnHandInventory(UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb"));
    log.info(qoh.toString());
  }

}
