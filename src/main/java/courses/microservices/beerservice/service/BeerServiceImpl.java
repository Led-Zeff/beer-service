package courses.microservices.beerservice.service;

import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

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

  @Override
  public BeerDto findById(UUID beerId) {
    return beerMapper.beerToDto(beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
  }

  @Override
  public BeerPagedList listBeers(String name, BeerStyle style, Pageable pageable) {
    Page<Beer> page;

    if (!StringUtils.isEmpty(name) && !StringUtils.isEmpty(style)) {
      page = beerRepository.findAllByNameAndStyle(name, style, pageable);
    } else if (!StringUtils.isEmpty(name)) {
      page = beerRepository.findAllByName(name, pageable);
    } else if (!StringUtils.isEmpty(style)) {
      page = beerRepository.findAllByStyle(style, pageable);
    } else {
      page = beerRepository.findAll(pageable);
    }

    return new BeerPagedList(page.getContent().stream()
      .map(beerMapper::beerToDto)
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

    beer.setName(beerDto.getName());
    beer.setStyle(beerDto.getStyle().name());
    beer.setPrice(beerDto.getPrice());
    beer.setUpc(beerDto.getUpc());

    return beerMapper.beerToDto(beerRepository.save(beer));
  }

}