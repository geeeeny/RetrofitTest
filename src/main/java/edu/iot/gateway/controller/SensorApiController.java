package edu.iot.gateway.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.iot.gateway.model.Sensor;
import edu.iot.gateway.service.SensorService;

@RestController
@RequestMapping("/api/sensor")
public class SensorApiController {
	//SensorServiceImpl, SensorServiceSqliteImpl은 타입이 같음. 따라서 byName방식의 @Resource로 매핑해야 함
	//@Autowired
	@Resource(name="sensorService") 
	SensorService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Sensor> getList() {
		try {
			return service.getList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Sensor get(@PathVariable int id) {
		try {
			return service.get(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public boolean insert(@RequestBody Sensor sensor) {
		System.out.println(sensor);
		try {
			return service.insert(sensor);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public boolean update(@PathVariable int id, @RequestBody Sensor sensor) {
		try {
			sensor.setSensorId(id);
			return service.update(sensor);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public boolean delete(@PathVariable int id) {
		try {
			return service.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
