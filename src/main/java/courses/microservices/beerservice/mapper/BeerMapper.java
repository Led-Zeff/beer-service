package courses.microservices.beerservice.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import courses.microservices.beerservice.domain.Beer;
import courses.microservices.beerservice.model.BeerDto;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {
  BeerDto beerToDto(Beer beer);
  BeerDto beerToDtoWithInventory(Beer beer);
  Beer dtoToBeer(BeerDto beerDto);
}
