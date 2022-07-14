package com.powerplantsystem.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.powerplantsystem.dto.BatteryDTO;
import com.powerplantsystem.dto.BatteryEntityDTO;
import com.powerplantsystem.entity.BatteryEntity;
import com.powerplantsystem.repository.BatteryRepository;

@ExtendWith(MockitoExtension.class)
public class BatteryServiceImplTest {

	@InjectMocks
	BatteryServiceImpl batteryService;

	@Mock
	BatteryRepository batteryRepository;

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
	
	
	public List<BatteryEntity> list(){
		
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
		
		return bList;
		
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
	public void test_save() {
		Iterable<BatteryEntity> result = batteryService.save(batteryinput());
		assertNotNull(result);
	}
	
	@Test
	public void test_getByPostCode() {
		when(batteryRepository.findAllByPostcode("")).thenReturn(list());
		BatteryDTO result = batteryService.getByPostCode("");
		assertNotNull(result.getList());
	}

}
