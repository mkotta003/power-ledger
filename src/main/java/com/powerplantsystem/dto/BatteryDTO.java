package com.powerplantsystem.dto;

import java.util.List;

import com.powerplantsystem.entity.BatteryEntity;

import lombok.Data;

@Data
public class BatteryDTO {

	private List<BatteryEntity> list;
	private int total;
	private double avgWattCapacity;

}
