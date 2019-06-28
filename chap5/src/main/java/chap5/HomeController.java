package chap5;

import java.util.List;

public class HomeController {
	
	private AlarmDevice alarmDevice;
	private Viewer viewer;	
	
	private Camera camera1;	
	private Camera camera2;	
	private Camera camera3;	
	private Camera camera4;
	private List<InfraredRaySensor> sensors;
	private Recorder recorder;	

	public void prepare(AlarmDevice alarmDevice, Viewer viewer ) {
		this.alarmDevice = alarmDevice;
		this.viewer = viewer;
	}
		
	public void init() {
		viewer.add(camera1);
		viewer.add(camera2);
		viewer.add(camera3);
		viewer.add(camera4);
		viewer.draw();
	}	
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
	//setter만
/*
 *  1. chap4의 모든 chap4패키지의 자바 소스를 chap5의 chap5 패키지에 복사하기
 *  2. 스프링 관련 어노테이션을 전부 삭제
 *  3. chap5.HomeController 클래스의 camera1 ~ camera4 setter 메서드 구현
 */
	
	public void setCamera1(Camera camera1) {
		this.camera1 = camera1;
	}

	public void setCamera2(Camera camera2) {
		this.camera2 = camera2;
	}

	public void setCamera3(Camera camera3) {
		this.camera3 = camera3;
	}

	public void setCamera4(Camera camera4) {
		this.camera4 = camera4;
	}

	public void setRecorder(Recorder recorder) {
		this.recorder = recorder;
	}
	
}
