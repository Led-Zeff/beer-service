package courses.microservices.beerservice.service;

import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import courses.microservices.beerservice.domain.Beer;
import courses.microservices.beerservice.exception.NotFoundException;
import courses.microservices.beerservice.mapper.BeerMapper;
import courses.microservices.beerservice.model.BeerDto;
import courses.microservices.beerservice.model.BeerPagedList;
import courses.microservices.beerservice.model.BeerStyle;
import courses.microservices.beerservice.repository.BeerRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

  private final BeerRepository beerRepository;
  private final BeerMapper beerMapper;

  @Cacheable(cacheNames = "beerCache", key = "#beerId", condition = "#showInventoryOnHand == false")
  @Override
  public BeerDto findById(UUID beerId, boolean showInventoryOnHand) {
    Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
    return showInventoryOnHand ? beerMapper.beerToDtoWithInventory(beer) : beerMapper.beerToDto(beer);
  }
  
  @Cacheable(cacheNames = "beerListCache", condition = "#showInventoryOnHand == false")
  @Override
  public BeerPagedList listBeers(String name, BeerStyle style, boolean showInventoryOnHand, Pageable pageable) {
    Page<Beer> page;

    if (!StringUtils.isEmpty(name) && !StringUtils.isEmpty(style)) {
      page = beerRepository.findAllByBeerNameAndBeerStyle(name, style, pageable);
    } else if (!StringUtils.isEmpty(name)) {
      page = beerRepository.findAllByBeerName(name, pageable);
    } else if (!StringUtils.isEmpty(style)) {
      page = beerRepository.findAllByBeerStyle(style, pageable);
    } else {
      page = beerRepository.findAll(pageable);
    }

    return new BeerPagedList(page.getContent().stream()
      .map(b -> showInventoryOnHand ? beerMapper.beerToDtoWithInventory(b) : beerMapper.beerToDto(b))
      .collect(Collectors.toList()),
      pageable,
      page.getTotalElements()
    );
  }

  @Override
  public BeerDto save(@Valid BeerDto beerDto) {
    return beerMapper.beerToDto(beerRepository.save(beerMapper.dtoToBeer(beerDto)));
  }

  @Override
  public BeerDto update(UUID beerId, BeerDto beerDto) {
    Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);

    beer.setBeerName(beerDto.getBeerName());
    beer.setBeerStyle(beerDto.getBeerStyle().name());
    beer.setPrice(beerDto.getPrice());
    beer.setUpc(beerDto.getUpc());

    return beerMapper.beerToDto(beerRepository.save(beer));
  }

  @Cacheable(cacheNames = "beerUpcCache", key = "#upc", condition = "#showInventoryOnHand == false")
  @Override
  public BeerDto findByUpc(String upc, boolean showInventoryOnHand) {
    Beer beer = beerRepository.findByUpc(upc).orElseThrow(NotFoundException::new);
    return showInventoryOnHand ? beerMapper.beerToDtoWithInventory(beer) : beerMapper.beerToDto(beer);
  }

}