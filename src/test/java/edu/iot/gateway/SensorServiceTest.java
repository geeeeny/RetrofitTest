package edu.iot.gateway;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.iot.gateway.service.SensorService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
	"classpath:servlet-context.xml"
})
@WebAppConfiguration
public class SensorServiceTest {
	@Autowired
	SensorService service;
	
	@Test
	public void testSensorServiceBind() {
		assert service != null : "service is null";
	}
	
	@Test
	public void testList() throws Exception{
		assert service.getList() != null : "list is null";
	}
}
