package courses.microservices.beerservice.event;

import courses.microservices.beerservice.model.BeerDto;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent {

  private static final long serialVersionUID = 143567825679349L;
  
  public BrewBeerEvent(BeerDto beerDto) {
    super(beerDto);
  }

}
