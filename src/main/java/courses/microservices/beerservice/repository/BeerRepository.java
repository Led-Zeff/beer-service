package courses.microservices.beerservice.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import courses.microservices.beerservice.domain.Beer;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
  
}