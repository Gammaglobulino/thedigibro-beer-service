package com.thedigibro.thedigibrobeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thedigibro.thedigibrobeerservice.domain.Beer;
import com.thedigibro.thedigibrobeerservice.repositories.BeerRepository;
import com.thedigibro.thedigibrobeerservice.web.model.BeerDto;
import com.thedigibro.thedigibrobeerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs//(uriScheme = "https",uriHost = "thedigibro.com",uriPort = 80)
@WebMvcTest(BeerController.class)
@ComponentScan(basePackages = "com.thedigibro.thedigibrobeerservice.mapper")
class BeerControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerRepository beerRepository;

    @Test
    void getBeerById() throws Exception {
        given(beerRepository.findById(any())).willReturn(Optional.of(Beer.builder().build()));
        mockMvc.perform(get("/api/v1/beer/{beerId}",UUID.randomUUID().toString())
                .param("isCold","yes")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("v1/beer-get",
                        pathParameters(
                        parameterWithName("beerId").description("UUID of the desired beer to get.")
                ),
                        requestParameters(parameterWithName("isCold").description("Is Beer Cold Query param")
                        ),
                        responseFields(
                                fieldWithPath("id").description("Id of Beer"),
                                fieldWithPath("version").description("Versione Number"),
                                fieldWithPath("createdDate").description("Date Updated"),
                                fieldWithPath("lastModifiedDate").description("Id of Beer"),
                                fieldWithPath("beerName").description("Name of the beer"),
                                fieldWithPath("beerStyle").description("Style of the beer"),
                                fieldWithPath("upc").description("upc number of Beer"),
                                fieldWithPath("price").description("price of Beer"),
                                fieldWithPath("quantityOnHand").description("Quantity On Hand")

                        )));
    }

    @Test
    void saveNewBeer() throws Exception {
        BeerDto beerDto= getValidBeerDto();
        String beerDtoJson=objectMapper.writeValueAsString(beerDto);
        ConstrainedFields fields= new ConstrainedFields(BeerDto.class);
        mockMvc.perform(post("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated())
                .andDo(document("v1/beer-new",
                requestFields(
                        fields.withPath("id").ignored(),
                        fields.withPath("version").ignored(),
                        fields.withPath("createdDate").ignored(),
                        fields.withPath("lastModifiedDate").ignored(),
                        fields.withPath("beerName").description("Name of the beer"),
                        fields.withPath("beerStyle").description("Style of the beer"),
                        fields.withPath("upc").description("upc number of Beer").attributes(),
                        fields.withPath("price").description("price of Beer"),
                        fields.withPath("quantityOnHand").ignored()

                )));
    }

    @Test
    void updateBeerById() throws Exception {
        BeerDto beerDto= getValidBeerDto();
        String beerDtoJson=objectMapper.writeValueAsString(beerDto);
        mockMvc.perform(put("/api/v1/beer/"+UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson)).andExpect(status().isNoContent());
    }
    BeerDto getValidBeerDto(){
        return BeerDto.builder()
                .beerName("Moretti")
                .beerStyle(BeerStyleEnum.ALE)
                .price(new BigDecimal(2.99))
                .upc(12223333444L)
                .build();
    }
    private static class ConstrainedFields{
        private final ConstraintDescriptions constraintDescriptions;
        ConstrainedFields(Class<?> input){
            this.constraintDescriptions=new ConstraintDescriptions(input);
        }
        private FieldDescriptor withPath(String path){
            return fieldWithPath(path).attributes(key("constraints").
                    value(StringUtils.collectionToDelimitedString(this.constraintDescriptions.descriptionsForProperty(path),".  ")));
        }
    }
}

