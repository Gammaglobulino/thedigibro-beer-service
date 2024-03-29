package com.thedigibro.thedigibrobeerservice.repositories;

import com.thedigibro.thedigibrobeerservice.domain.Beer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface BeerRepository extends PagingAndSortingRepository <Beer, UUID> {
}
