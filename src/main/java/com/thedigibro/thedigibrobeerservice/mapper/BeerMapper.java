package com.thedigibro.thedigibrobeerservice.mapper;

import com.thedigibro.thedigibrobeerservice.domain.Beer;
import com.thedigibro.thedigibrobeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface BeerMapper {
    BeerDto beerToBeerDto(Beer beer);
    Beer beerDtoToBeer(BeerDto beerDto);
}

