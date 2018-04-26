package edu.iot.gateway;

import java.util.List;

import edu.iot.gateway.api.SensorApi;
import edu.iot.gateway.model.Sensor;
import retrofit2.Response;

public class SensorPostTest {

	public static void main(String[] args) throws Exception {
		//Sensor s = new Sensor(0, 15, "온도", "화장실", "2019-04-24");
		
		//Response<Boolean> res1 = SensorApi.service.post(s).execute();
		//Response<Boolean> res1 = SensorApi.service.put(2, s).execute();
		//Response<Boolean> res1 = SensorApi.service.delete(1).execute();
		Response<List<Sensor>> res1= SensorApi.service.list().execute();
		
		if(res1.code()==200) {
			for(Sensor sensor:res1.body()) {
				System.out.println(sensor);
			}
			/*boolean result = res1.body();
			if(result) {
				System.out.println("등록 성공");
			}else {
				System.out.println("등록 실패");
			}*/
		}else {
			System.out.println("에러 코드: "+res1.code());
		}
	}

}
