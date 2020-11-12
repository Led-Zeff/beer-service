package courses.microservices.beerservice.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import courses.microservices.beerservice.domain.Beer;
import courses.microservices.beerservice.model.BeerStyle;

public interface BeerRepository extends JpaRepository<Beer, UUID> {
  Page<Beer> findAllByName(String name, Pageable pageable);
  Page<Beer> findAllByStyle(BeerStyle style, Pageable pageable);
  Page<Beer> findAllByNameAndStyle(String name, BeerStyle style, Pageable pageable);
  Optional<Beer> findByUpc(String upc);
}
