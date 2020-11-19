package courses.microservices.beerservice.service.brewing;

import javax.transaction.Transactional;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import courses.microservices.beerservice.config.JmsConfig;
import courses.microservices.beerservice.domain.Beer;
import courses.microservices.beerservice.repository.BeerRepository;
import courses.microservices.brewery.event.BrewBeerEvent;
import courses.microservices.brewery.event.NewInventoryEvent;
import courses.microservices.brewery.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewBeerListener {

  private final BeerRepository beerRepository;
  private final JmsTemplate jmsTemplate;
  
  @Transactional
  @JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE)
  public void listen(BrewBeerEvent brewBeerEvent) {
    BeerDto beerDto = brewBeerEvent.getBeerDto();
    Beer beer = beerRepository.getOne(beerDto.getId());
    beerDto.setQuantityOnHand(beer.getQuantityToBrew());

    log.info("Requesting {} to brew for beer {}", beer.getQuantityToBrew(), beer.getId());
    NewInventoryEvent toBrew = new NewInventoryEvent(beerDto);
    jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, toBrew);
  }

}
