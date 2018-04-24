package edu.iot.gateway.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.iot.gateway.model.Sensor;

@Service
public class SensorServiceImpl implements SensorService {

	static int seq = 1; // id 발급용(spring의 bean 객체는 싱글톤이므로 static 키워드 필요없음)
	List<Sensor> dao; // db 연결없이 테스트해본다.(dao 생성x)
	
	public SensorServiceImpl() {
		dao = new ArrayList<>();
		
		//테스트 데이터 20건 추가
		for(int i=1; i<=20; i++) {
			Sensor sensor = new Sensor(seq++, 20+i, "온도", "거실", "2018-04-12");
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

}
