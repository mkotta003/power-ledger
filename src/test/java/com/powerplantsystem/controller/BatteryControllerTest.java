package com.powerplantsystem.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.powerplantsystem.dto.BatteryEntityDTO;
import com.powerplantsystem.entity.BatteryEntity;
import com.powerplantsystem.exception.InvalidInputException;
import com.powerplantsystem.service.BatteryService;

@ExtendWith(MockitoExtension.class)
public class BatteryControllerTest {

	@Mock
	private BatteryService batteryService;

	@InjectMocks
	BatteryController batteryController;

	public Iterable<BatteryEntity> cannedData() {
		List<BatteryEntity> bList = new ArrayList<>();

		BatteryEntity b1 = new BatteryEntity();
		b1.setId(1L);
		b1.setName("unit1");
		b1.setPostcode("1234");
		b1.setWattCapacity(12);

		BatteryEntity b2 = new BatteryEntity();
		b2.setId(2L);
		b2.setName("unit2");
		b2.setPostcode("1234");
		b2.setWattCapacity(24);
		bList.add(b1);
		bList.add(b2);

		return (Iterable<BatteryEntity>) bList;
	}

	public List<BatteryEntityDTO> batteryinput() {

		List<BatteryEntityDTO> bList = new ArrayList<>();

		BatteryEntityDTO b1 = new BatteryEntityDTO();
		b1.setName("unit1");
		b1.setPostcode("1234");
		b1.setWattCapacity(12);

		BatteryEntityDTO b2 = new BatteryEntityDTO();
		b2.setName("unit2");
		b2.setPostcode("1234");
		b2.setWattCapacity(24);
		bList.add(b1);
		bList.add(b2);

		return bList;

	}

	@Test
	public void successCase() {
		when(batteryService.save(batteryinput())).thenReturn(cannedData());
		ResponseEntity<Iterable<BatteryEntity>> result = batteryController.save(batteryinput());
		assertEquals(cannedData(), result.getBody());
		assertEquals(201, result.getStatusCodeValue());

	}

	@Test
	public void invalidInputcase() {
		assertThrows(InvalidInputException.class, () -> batteryController.save(new ArrayList<>()));

	}

}
