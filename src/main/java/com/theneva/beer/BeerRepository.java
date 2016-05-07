package com.theneva.beer;

import org.springframework.data.repository.CrudRepository;

public interface BeerRepository extends CrudRepository<Beer, Long>
{
}
