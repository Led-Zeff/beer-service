package courses.microservices.beerservice.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import courses.microservices.beerservice.model.BeerDto;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {
  
  @GetMapping("{beerId}")
  public BeerDto getById(@PathVariable UUID beerId) {
    return BeerDto.builder()
            .build();
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public void createBeer() {

  }

  @PutMapping("{beerId}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void updateBeer(@PathVariable UUID beerId, @RequestBody BeerDto beerDto) {

  }

}