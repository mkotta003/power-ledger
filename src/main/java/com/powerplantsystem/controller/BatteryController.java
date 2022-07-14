package com.powerplantsystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.powerplantsystem.dto.BatteryDTO;
import com.powerplantsystem.dto.BatteryEntityDTO;
import com.powerplantsystem.entity.BatteryEntity;
import com.powerplantsystem.exception.InvalidInputException;
import com.powerplantsystem.exception.NoDataFoundException;
import com.powerplantsystem.service.BatteryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/battery")
@Slf4j
public class BatteryController {

	@Autowired
	private BatteryService batteryService;

	@PostMapping("/")
	public ResponseEntity<Iterable<BatteryEntity>> save(@Valid @RequestBody List<BatteryEntityDTO> batteries) {
		log.info("save method called :{}");
		Iterable<BatteryEntity> savedData = null;
		if (!batteries.isEmpty()) {
			savedData = batteryService.save(batteries);
		} else {
			log.error("invalid data" + batteries);
			throw new InvalidInputException("please provide valid Input." + batteries);
		}
		log.info("successful" + savedData);
		return new ResponseEntity<>(savedData, HttpStatus.CREATED);

	}

	@GetMapping("/{postcode}")
	public ResponseEntity<BatteryDTO> get(@PathVariable String postcode) {
		log.info("get method called :{}");
		BatteryDTO batteryDTO = batteryService.getByPostCode(postcode);
		if (batteryDTO.getList() == null || batteryDTO.getList().isEmpty()) {
			log.error("no data found" + batteryDTO);
			throw new NoDataFoundException("no data found for the postCode :" + postcode);
		}

		return new ResponseEntity<>(batteryDTO, HttpStatus.OK);

	}

}
