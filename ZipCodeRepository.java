package com.tts.weatherapp;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZipCodeRepository extends CrudRepository<ZipCode, Long>
{
	List<ZipCode> findAllByOrderByIdAsc(Pageable pagelimit);
}
