package edu.iot.gateway.dao;

import java.util.List;

import edu.iot.gateway.model.Sensor;

public interface SensorDao {
	List<Sensor> list() throws Exception;
	
	Sensor get(int sensorId) throws Exception;
	
	int insert(Sensor sensor) throws Exception;
	
	int update(Sensor sensor) throws Exception;
	
	int delete(int sensorId) throws Exception;
}
