package courses.microservices.beerservice.service.brewing;

import java.util.List;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import courses.microservices.beerservice.config.JmsConfig;
import courses.microservices.beerservice.domain.Beer;
import courses.microservices.beerservice.event.BrewBeerEvent;
import courses.microservices.beerservice.mapper.BeerMapper;
import courses.microservices.beerservice.repository.BeerRepository;
import courses.microservices.beerservice.service.inventory.BeerInventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {
  
  private final BeerRepository beerRepository;
  private final BeerInventoryService beerInventoryService;
  private final JmsTemplate jmsTemplate;
  private final BeerMapper beerMapper;

  @Scheduled(fixedRate = 5000)
  public void checkForLowInventory() {
    List<Beer> beers = beerRepository.findAll();
    beers.forEach(beer -> {
      Integer inv = beerInventoryService.getOnHandInventory(beer.getId());
      log.info("Beer {} with inventory {}", beer.getId(), inv);

      if (beer.getMinOnHand() >= inv) {
        jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent(beerMapper.beerToDto(beer)));
      }
    });
  }

}
