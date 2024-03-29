package com.thedigibro.thedigibrobeerservice.bootstrap;

import com.thedigibro.thedigibrobeerservice.domain.Beer;
import com.thedigibro.thedigibrobeerservice.repositories.BeerRepository;
import com.thedigibro.thedigibrobeerservice.web.model.BeerStyleEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }

    private void loadBeerObjects() {
        if(beerRepository.count()==0){
            beerRepository.save(Beer.builder()
                    .beerName("Mago Bobs")
                    .beerStyle(BeerStyleEnum.PALE_ALE)
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(377010000001L)
                    .price(new BigDecimal(12.95))
                    .build());
            beerRepository.save(Beer.builder()
                    .beerName("Moretti Baffo")
                    .beerStyle(BeerStyleEnum.ALE)
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(377010000002L)
                    .price(new BigDecimal(11.95))
                    .build());
            System.out.println("Loaded Beers"+beerRepository.count());



        }

    }
}
