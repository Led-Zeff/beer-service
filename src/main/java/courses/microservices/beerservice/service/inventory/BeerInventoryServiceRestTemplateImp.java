package courses.microservices.beerservice.service.inventory;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import courses.microservices.beerservice.service.inventory.model.BeerInventoryDto;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ConfigurationProperties(prefix = "sfg.brewery", ignoreUnknownFields = false)
@Component
public class BeerInventoryServiceRestTemplateImp implements BeerInventoryService {
  
  private final RestTemplate restTemplate;

  @Setter
  private String inventoryPath;
  @Setter
  private String beerInventoryServiceHost;

  public BeerInventoryServiceRestTemplateImp(RestTemplateBuilder restTemplateBuilder) {
    restTemplate = restTemplateBuilder.build();
  }

  @Override
  public Integer getOnHandInventory(UUID beerId) {
    log.debug("Calling inventory service");

    ResponseEntity<List<BeerInventoryDto>> responseEntity = restTemplate
      .exchange(beerInventoryServiceHost + inventoryPath, HttpMethod.GET, null,
        new ParameterizedTypeReference<List<BeerInventoryDto>>(){}, beerId);
    
    Integer onHand = Objects.requireNonNull(responseEntity.getBody())
      .stream()
      .mapToInt(BeerInventoryDto::getQuantityOnHand)
      .sum();
    
    return onHand;
  }

}
