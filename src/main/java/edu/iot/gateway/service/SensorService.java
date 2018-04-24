package edu.iot.gateway.service;

import java.util.List;

import edu.iot.gateway.model.Sensor;

public interface SensorService {
	List<Sensor> getList() throws Exception;
	
	Sensor get(int id) throws Exception;
	
	boolean insert(Sensor sensor) throws Exception;
	
	boolean update(Sensor sensor) throws Exception;
	
	boolean delete(int id) throws Exception;
}
