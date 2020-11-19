package courses.microservices.brewery.event;

import courses.microservices.brewery.model.BeerDto;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NewInventoryEvent extends BeerEvent {
  
  private static final long serialVersionUID = 234567856783456781L;

  public NewInventoryEvent(BeerDto beerDto) {
    super(beerDto);
  }
  
}
