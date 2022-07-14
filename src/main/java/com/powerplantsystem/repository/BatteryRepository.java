package com.powerplantsystem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.powerplantsystem.entity.BatteryEntity;

@Repository
public interface BatteryRepository extends CrudRepository<BatteryEntity, Long> {

	List<BatteryEntity> findAllByPostcode(String postcode);

 	

}
