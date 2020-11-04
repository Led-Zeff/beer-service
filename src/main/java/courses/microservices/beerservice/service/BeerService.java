package courses.microservices.beerservice.service;

import java.util.UUID;

import javax.validation.Valid;

import courses.microservices.beerservice.model.BeerDto;

public interface BeerService {
	BeerDto findById(UUID beerId);
	BeerDto save(@Valid BeerDto beerDto);
	BeerDto update(UUID beerId, BeerDto beerDto);
}
