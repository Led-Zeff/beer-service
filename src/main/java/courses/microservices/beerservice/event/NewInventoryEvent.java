package courses.microservices.beerservice.event;

import courses.microservices.beerservice.model.BeerDto;

public class NewInventoryEvent extends BeerEvent {
  
  private static final long serialVersionUID = 234567856783456781L;

  public NewInventoryEvent(BeerDto beerDto) {
    super(beerDto);
  }
  
}
