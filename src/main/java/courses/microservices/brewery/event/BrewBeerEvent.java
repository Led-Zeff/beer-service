package courses.microservices.brewery.event;

import courses.microservices.brewery.model.BeerDto;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent {

  private static final long serialVersionUID = 143567825679349L;
  
  public BrewBeerEvent(BeerDto beerDto) {
    super(beerDto);
  }

}
