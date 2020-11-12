package courses.microservices.beerservice.event;

import java.io.Serializable;

import courses.microservices.beerservice.model.BeerDto;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
public class BeerEvent implements Serializable {

  private static final long serialVersionUID = 132674832682313L;

  private final BeerDto beerDto;

}
