package courses.microservices.beerservice.service;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;

import courses.microservices.beerservice.model.BeerDto;
import courses.microservices.beerservice.model.BeerPagedList;
import courses.microservices.beerservice.model.BeerStyle;

public interface BeerService {
	BeerDto findById(UUID beerId, boolean showInventoryOnHand);
	BeerPagedList listBeers(String name, BeerStyle style, boolean showInventoryOnHand, Pageable pageable);
	BeerDto save(@Valid BeerDto beerDto);
	BeerDto update(UUID beerId, BeerDto beerDto);
}
