package courses.microservices.beerservice;

import java.math.BigDecimal;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import courses.microservices.beerservice.controller.BeerController;
import courses.microservices.beerservice.model.BeerDto;
import courses.microservices.beerservice.model.BeerStyle;
import courses.microservices.beerservice.service.BeerService;

@WebMvcTest(BeerController.class)
public class BeerControllerTests {

  @Autowired MockMvc mockMvc;
  @Autowired ObjectMapper objectMapper;
  @MockBean BeerService beerService;

  @Test
  void getBeerById() throws Exception {
    BDDMockito.given(beerService.findById(ArgumentMatchers.any(), ArgumentMatchers.eq(true))).willReturn(new BeerDto());

    mockMvc.perform(
        MockMvcRequestBuilders.get("/api/v1/beer/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  void createBeer() throws Exception {
    BeerDto beerDto = new BeerDto(null, null, null, null, "name", BeerStyle.API, "435678543658", new BigDecimal("23.4"), 23);
    String json = objectMapper.writeValueAsString(beerDto);

    mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/beer")
          .contentType(MediaType.APPLICATION_JSON)
          .content(json))
          .andExpect(MockMvcResultMatchers.status().isCreated());
  }

  @Test
  void updateBeer() throws Exception {
    BeerDto beerDto = new BeerDto();
    String json = objectMapper.writeValueAsString(beerDto);
    
    mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/beer/" + UUID.randomUUID().toString())
          .contentType(MediaType.APPLICATION_JSON)
          .content(json))
          .andExpect(MockMvcResultMatchers.status().isOk());
  }

}