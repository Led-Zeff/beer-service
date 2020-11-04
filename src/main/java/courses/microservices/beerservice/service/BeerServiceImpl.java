package courses.microservices.beerservice.service;

import java.util.UUID;

import javax.validation.Valid;

import courses.microservices.beerservice.domain.Beer;
import courses.microservices.beerservice.exception.NotFoundException;
import courses.microservices.beerservice.mapper.BeerMapper;
import courses.microservices.beerservice.model.BeerDto;
import courses.microservices.beerservice.repository.BeerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

  private final BeerRepository beerRepository;
  private final BeerMapper beerMapper;

  @Override
  public BeerDto findById(UUID beerId) {
    return beerMapper.beerToDto(beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
  }

  @Override
  public BeerDto save(@Valid BeerDto beerDto) {
    return beerMapper.beerToDto(beerRepository.save(beerMapper.dtoToBeer(beerDto)));
  }

  @Override
  public BeerDto update(UUID beerId, BeerDto beerDto) {
    Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);

    beer.setName(beerDto.getName());
    beer.setStyle(beerDto.getStyle().name());
    beer.setPrice(beerDto.getPrice());
    beer.setUpc(beerDto.getUpc());

    return beerMapper.beerToDto(beerRepository.save(beer));
  }

}