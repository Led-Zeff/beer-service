package courses.microservices.beerservice.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import courses.microservices.beerservice.domain.Beer;
import courses.microservices.beerservice.model.BeerDto;
import courses.microservices.beerservice.service.inventory.BeerInventoryService;

public abstract class BeerMapperDecorator implements BeerMapper {

  private BeerInventoryService beerInventoryService;
  private BeerMapper beerMapper;

  @Autowired
  public void setBeerInventoryService(BeerInventoryService beerInventoryService) {
    this.beerInventoryService = beerInventoryService;
  }

  @Autowired
  public void setBeerMapper(BeerMapper beerMapper) {
    this.beerMapper = beerMapper;
  }
  
  @Override
  public BeerDto beerToDto(Beer beer) {
    BeerDto dto = beerMapper.beerToDto(beer);
    dto.setQuantityOnHand(beerInventoryService.getOnHandInventory(beer.getId()));
    return dto;
  }

  @Override
  public Beer dtoToBeer(BeerDto beerDto) {
    return beerMapper.dtoToBeer(beerDto);
  }

}
