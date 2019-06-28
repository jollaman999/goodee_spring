package chap5;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

// 3.0 이후 버전에서만 사용 가능
@Configuration     //환경 설정 클래스. xml 기능을 대체 할 수 있는 클래스
public class SpringConfig {
    /*
     *	 xml로 표현
     *	<bean id="executor" class="chap5.chap5.Executor" p:worker-ref="worker" />
     *  <bean id="worker" class="chap5.chap5.Worker"... />
     */

    @Bean    // 리턴 객체를 컨테이너에 저장. 메서드 이름이 Bean의 이름.
    public Executor executor() {
        Executor exec = new Executor();
        exec.setWorker(worker());
        return exec;
    }

    @Bean
    @Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)  //일회성 객체
    //chap5.chap5.Worker@6f3187b0: work :chap5.chap5.WorkUnit@2663e964
    //chap5.chap5.Worker@48b67364: work :chap5.chap5.WorkUnit@189cbd7c
    public Worker worker() {
        return new Worker();
    }

    @Bean
    public Camera camera1() {
        Camera c = new Camera();
        c.setNumber(1);
        return c;
    }

    @Bean
    public Camera camera2() {
        Camera c = new Camera();
        c.setNumber(2);
        return c;
    }

    @Bean
    public Camera camera3() {
        Camera c = new Camera();
        c.setNumber(3);
        return c;
    }

    @Bean
    public Camera camera4() {
        Camera c = new Camera();
        c.setNumber(4);
        return c;
    }

    @Bean
    public InfraredRaySensor windowSensor() {
        InfraredRaySensor s = new InfraredRaySensor("창센서");
        return s;
    }

    @Bean
    public InfraredRaySensor doorSensor() {
        InfraredRaySensor s = new InfraredRaySensor("현관센서");
        return s;
    }

    @Bean
    public InfraredRaySensor lampSensor() {
        InfraredRaySensor s = new InfraredRaySensor("전등센서");
        return s;
    }

    @Bean
    public AlarmDevice alarmDevice() {
        return new SmsAlarmDevice();
    }

    @Bean
    public Viewer viewer() {
        MonitorViewer v = new MonitorViewer();
        v.setDisplayMode(displayMode());
        return v;
    }

    @Bean
    public DisplayMode displayMode() {
        DisplayMode d = new DisplayMode();
        d.setType("GRID");
        return d;
    }

    //initMethod="init" : 객체 초기화 이후에 HomeController의 init  메서드 호출
    @Bean(initMethod = "init")  //빈으로 init를 호출
    public HomeController homeController() {
        HomeController h = new HomeController();
        List<InfraredRaySensor> sensors =
                new ArrayList<InfraredRaySensor>();
        sensors.add(windowSensor());
        sensors.add(doorSensor());
        sensors.add(lampSensor());
        h.setSensors(sensors);
        h.prepare(alarmDevice(), viewer());
        h.setCamera1(camera1());
        h.setCamera2(camera2());
        h.setCamera3(camera3());
        h.setCamera4(camera4());
        return h;  //리턴하기전에 init를 먼저 호출고 와야됨.

    }


}
