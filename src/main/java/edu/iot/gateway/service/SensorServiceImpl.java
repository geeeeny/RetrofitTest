package edu.iot.gateway.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.iot.gateway.handler.MonitoringHandler;
import edu.iot.gateway.model.LocationStatus;
import edu.iot.gateway.model.Sensor;

@Service
public class SensorServiceImpl implements SensorService {

	static int seq = 1; // id 발급용(spring의 bean 객체는 싱글톤이므로 static 키워드 필요없음)
	List<Sensor> dao; // db 연결없이 테스트해본다.(dao 생성x)
	Map<String, LocationStatus> locationMap;
	
	@Autowired
	MonitoringHandler handler;
	
	public SensorServiceImpl() {
		dao = new ArrayList<>();
		locationMap = new HashMap<>();
		
		//테스트 데이터 20건 추가
		for(int i=1; i<=20; i++) {
			Sensor sensor = new Sensor(seq++,"조도", 20+i, "온도", "거실", "2018-04-12");
			dao.add(sensor);
		}
	}
	
	@Override
	public List<Sensor> getList() throws Exception {
		return dao;
	}

	@Override
	public Sensor get(int id) throws Exception {
		for(Sensor s : dao){
			if(s.getSensorId()==id)
				return s;
		}
		return null;
	}

	@Override
	public boolean insert(Sensor sensor) throws Exception {
		sensor.setSensorId(seq++);
		
		LocationStatus location = locationMap.get(sensor.getLocation());
		if(location == null) {
			location = new LocationStatus(sensor.getLocation());
		}
		location.put(sensor);
		locationMap.put(location.getLocation(), location);
		//값 전달
		handler.send(locationMap);
		
		dao.add(sensor);
		
		return true;
	}

	@Override
	public boolean update(Sensor sensor) throws Exception {
		for(int i=0; i<dao.size(); i++) {
			Sensor s = dao.get(i);
			if(s.getSensorId()==sensor.getSensorId()) {
				dao.set(i, sensor);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean delete(int id) throws Exception {
		for(int i=0; i<dao.size(); i++) {
			Sensor s = dao.get(i);
			if(s.getSensorId()==id) {
				dao.remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public Map<String, LocationStatus> getLocationStatus() {
		// TODO Auto-generated method stub
		return null;
	}



}
