package courses.microservices.beerservice.controller;

import java.util.UUID;

import javax.validation.Valid;

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
import courses.microservices.beerservice.service.BeerService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/beer")
@RequiredArgsConstructor
public class BeerController {

  private final BeerService beerService;
  
  @GetMapping("{beerId}")
  public BeerDto getById(@PathVariable UUID beerId) {
    return beerService.findById(beerId);
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public BeerDto createBeer(@RequestBody @Valid BeerDto beerDto) {
    return beerService.save(beerDto);
  }

  @PutMapping("{beerId}")
  public BeerDto updateBeer(@PathVariable UUID beerId, @RequestBody BeerDto beerDto) {
    return beerService.update(beerId, beerDto);
  }

}