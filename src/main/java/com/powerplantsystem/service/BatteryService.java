package com.powerplantsystem.service;

import java.util.List;

import javax.validation.Valid;

import com.powerplantsystem.dto.BatteryDTO;
import com.powerplantsystem.dto.BatteryEntityDTO;
import com.powerplantsystem.entity.BatteryEntity;

public interface BatteryService {

	Iterable<BatteryEntity> save(@Valid List<BatteryEntityDTO> batteries);

	BatteryDTO getByPostCode(String postcode);

}
