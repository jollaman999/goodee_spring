package chap5;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main1 {

	public static void main(String[] args) {
//		GenericXmlApplicationContext ctx = 
//				new GenericXmlApplicationContext("classpath:annotation.xml");
		
		ApplicationContext ctx = 
				new AnnotationConfigApplicationContext(SpringConfig.class);		
		Executor exec = ctx.getBean("executor", Executor.class);
		exec.addUnit(new WorkUnit());
		exec.addUnit(new WorkUnit());
		
		HomeController home = ctx.getBean("homeController", HomeController.class);
		home.checkSensorAndAlarm();
		System.out.println("===침입 없음 ====");
		InfraredRaySensor sensor = 
				ctx.getBean("windowSensor",InfraredRaySensor.class);
		sensor.foundObject();
		home.checkSensorAndAlarm();
		System.out.println("===침입 없음 ====");
//		sensor = new chap5.InfraredRaySensor("현관센서"); // 내 컨테이너에 있는 객체가 아니라서 안나옴.
												 // 새로운 객체 생성했음.
		
		sensor = 
				ctx.getBean("doorSensor",InfraredRaySensor.class);
		sensor.foundObject();
		home.checkSensorAndAlarm();
	}

}
