package courses.microservices.beerservice.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import courses.microservices.beerservice.service.BeerService;
import courses.microservices.brewery.model.BeerDto;
import courses.microservices.brewery.model.BeerPagedList;
import courses.microservices.brewery.model.BeerStyle;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/beer")
@RequiredArgsConstructor
public class BeerController {

  private final BeerService beerService;

  @GetMapping
  public BeerPagedList listBeers(@RequestParam int page, @RequestParam int size, @RequestParam(required = false) String beerName, @RequestParam(required = false) BeerStyle beerStyle,
    @RequestParam(defaultValue = "false") boolean showInventoryOnHand
  ) {
    return beerService.listBeers(beerName, beerStyle, showInventoryOnHand, PageRequest.of(page, size));
  }
  
  @GetMapping("/{beerId}")
  public BeerDto getById(@PathVariable UUID beerId, @RequestParam(defaultValue = "false") boolean showInventoryOnHand) {
    return beerService.findById(beerId, showInventoryOnHand);
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public BeerDto createBeer(@RequestBody @Valid BeerDto beerDto) {
    return beerService.save(beerDto);
  }

  @PutMapping("/{beerId}")
  public BeerDto updateBeer(@PathVariable UUID beerId, @RequestBody BeerDto beerDto) {
    return beerService.update(beerId, beerDto);
  }

  @GetMapping("/beerUpc/{upc}")
  public BeerDto getByUpc(@PathVariable String upc, @RequestParam(defaultValue = "false") boolean showInventoryOnHand) {
    return beerService.findByUpc(upc, showInventoryOnHand);
  }

}