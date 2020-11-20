package courses.microservices.beerservice.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import courses.microservices.beerservice.config.JmsConfig;
import courses.microservices.beerservice.repository.BeerRepository;
import courses.microservices.brewery.event.ValidateBeerOrderRequestEvent;
import courses.microservices.brewery.model.ValidateBeerOrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValidateBeerOrderListener {
  
  private final BeerRepository beerRepository;
  private final JmsTemplate jmsTemplate;

  @Transactional
  @JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
  public void listen(ValidateBeerOrderRequestEvent event) {
    boolean isValid = event.getBeerOrderDto().getBeerOrderLines().stream()
      .allMatch(orderLine -> beerRepository.existsByUpc(orderLine.getUpc()));

    log.info("Order validated with ID {}, result: {}", event.getBeerOrderDto().getId(), isValid);
    
    jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESULT_QUEUE, new ValidateBeerOrderResponse(event.getBeerOrderDto().getId(), isValid));
  }

}
