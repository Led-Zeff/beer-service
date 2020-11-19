package courses.microservices.brewery.event;

import java.io.Serializable;

import courses.microservices.brewery.model.BeerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerEvent implements Serializable {

  private static final long serialVersionUID = 132674832682313L;

  private BeerDto beerDto;

}
