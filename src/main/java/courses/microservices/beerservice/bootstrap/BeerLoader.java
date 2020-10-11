package courses.microservices.beerservice.bootstrap;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import courses.microservices.beerservice.domain.Beer;
import courses.microservices.beerservice.repository.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class BeerLoader implements CommandLineRunner {

  private final BeerRepository beerRepository;

  @Override
  public void run(String... args) throws Exception {
    loadBeers();
  }

  private void loadBeers() {
    if (beerRepository.count() == 0) {
      beerRepository.save(Beer.builder().name("Mango Bobs").style("IPA").quantityToBrew(200).upc(43248236748L).price(new BigDecimal("12.32")).minOnHand(32).build());
      beerRepository.save(Beer.builder().name("Galaxy Cat").style("PALE_ALE").quantityToBrew(200).upc(43248234748L).price(new BigDecimal("23.76")).minOnHand(32).build());

      log.info("Loaded beers");
    }
  }
}
