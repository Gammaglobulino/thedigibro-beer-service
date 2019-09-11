package com.thedigibro.thedigibrobeerservice.web.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.io.IOException;

@JsonTest
class BeerDtoTest extends BaseTest{
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testSerializedDto() throws JsonProcessingException {
        BeerDto beerDto=getDto();
        String jsonString=objectMapper.writeValueAsString(beerDto);
        System.out.println(jsonString);

    }
    @Test
    void testDeserializedDto() throws IOException {
        String json="{\"id\":\"1732d84e-63ec-4434-aec3-27ab7f510a44\",\"version\":null,\"createdDate\":\"2019-09-11T19:19:56.4125296+02:00\",\"lastModifiedDate\":\"2019-09-11T19:19:56.4125296+02:00\",\"beerName\":\"Beername\",\"beerStyle\":\"PALE_ALE\",\"upc\":12233345546,\"price\":12.99,\"quantityOnHand\":null}";
        BeerDto dto=objectMapper.readValue(json,BeerDto.class);
        System.out.println(dto);
    }

}