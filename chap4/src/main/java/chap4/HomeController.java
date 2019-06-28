package chap4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.rowset.serial.SerialArray;

//@Component : 객체화 되어서 컨테이너에 저장될 클래스.
//			      이름은 homeController 형태로 저장.
// xml 구조로 표현
// <bean id="homeController" class="chap4.HomeController">
//...
//</bean>

@Component
public class HomeController {
	private AlarmDevice alarmDevice;
	private Viewer viewer;

	//@Resource : 컨테이너에서 이름이 camera1인 객체를 camera1 참조변수에 저장.
	@Resource(name="camera1")
	private Camera camera1;
	@Resource(name="camera2")
	private Camera camera2;
	@Resource(name="camera3")
	private Camera camera3;
	@Resource(name="camera4")
	private Camera camera4;
	private List<InfraredRaySensor> sensors;
	
	//@Autowired : 자료형 해당하는 객체를 찾아서 주입.
	//				단, 객체가 없어도 가능함.
	//				객체가 없는 경우 null
	@Autowired(required=false)
	private Recorder recorder;
	
	//@alarmDevice :  SmsAlarmDevice 객체가 주입.
	//viewer : MonitorViewer 객체가 주입.
	// MonitorViewer가 viewer를 구현해서 . (implements) 	
	@Autowired
	public void prepare(AlarmDevice alarmDevice, Viewer viewer ) {
		this.alarmDevice = alarmDevice;
		this.viewer = viewer;
	}
	
	//@PostConstruct : HomeController 객체가 생성 완료 후 호출되는 메서드
	@PostConstruct
	public void init() {
		viewer.add(camera1);
		viewer.add(camera2);
		viewer.add(camera3);
		viewer.add(camera4);
		viewer.draw();
	}
	//@Autowired : 자료형으로 객체를 찾아서 주입. 자료형에 맞는 개체가 없으면 오류 발생.
	@Autowired   // InfraredRaySensor 객체 들이 주입.
	@Qualifier("intrusionDetection")    // 그 중 별명이 intrusionDetection인 객체만.   >> windeow센서/ door센서; lamp센서고 별명이  Qualifier
	public void setSensors(List<InfraredRaySensor> sensors) {
		this.sensors = sensors;
		for(InfraredRaySensor s : sensors) {
			System.out.println("센서 등록:" + s);
		}
	}
	public void checkSensorAndAlarm() {
		for(InfraredRaySensor s : sensors) {
			if(s.isobjectRounded()) {
				alarmDevice.alarm(s.getName());
			}
		}
	}
}
