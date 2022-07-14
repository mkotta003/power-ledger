package com.powerplantsystem.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powerplantsystem.dto.BatteryDTO;
import com.powerplantsystem.dto.BatteryEntityDTO;
import com.powerplantsystem.entity.BatteryEntity;
import com.powerplantsystem.repository.BatteryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BatteryServiceImpl implements BatteryService {

	@Autowired
	private BatteryRepository batteryRepository;

	public List<BatteryEntity> convertToEntity(List<BatteryEntityDTO> plantCapacity) {
		ModelMapper mapper = new ModelMapper();
		List<BatteryEntity> entityToDto = mapper.map(plantCapacity, new TypeToken<List<BatteryEntity>>() {
		}.getType());
		log.info("payload {} " + entityToDto);

		return entityToDto;

	}

	@Override
	public Iterable<BatteryEntity> save(List<BatteryEntityDTO> plantCapacity) {
		List<BatteryEntity> batteries = null;
		try {
			batteries = convertToEntity(plantCapacity);
		} catch (Exception e) {
			log.error("faild converting Entity to DTO : {}");
		}

		return batteryRepository.saveAll(batteries);
	}

	@Override
	public BatteryDTO getByPostCode(String postcode) {

		List<BatteryEntity> list = batteryRepository.findAllByPostcode(postcode);
		BatteryDTO bDTO = new BatteryDTO();

		if (!list.isEmpty()) {
			Comparator<BatteryEntity> sorting 
			= (o1, o2) -> o1.getName().compareTo(o2.getName());
			
			Collections.sort(list, sorting);

			OptionalDouble avg = list.stream().mapToInt(i -> i.getWattCapacity()).average();

			bDTO.setList(list);
			bDTO.setTotal(list.size());
			bDTO.setAvgWattCapacity(avg.getAsDouble());
		}

		return bDTO;

	}

}
