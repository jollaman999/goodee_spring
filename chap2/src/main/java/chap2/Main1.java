package chap2;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main1 {
    public static void main(String[] args) {
        /*
        컨테이너 : 객체의 집합
        BeanFactory : 가장 간단한 컨테이너. 컨테이너의 최상단 인터페이스
                      DI만 처리 가능. AOP 기능 사용 불가.
                      4버전 이후 deprecated 됨.
        ApplicationContext : BeanFactory 의 하위 인터페이스.
                             AOP 기능 사용.
        WebApplicationContext : ApplicationContext 의 하위 인터페이스
                                Web 환경에서 사용되는 컨테이너.
         */
        GenericXmlApplicationContext ctx =
                new GenericXmlApplicationContext("classpath:applicationContext.xml");
        Greeter g = ctx.getBean("greeter", Greeter.class);
        System.out.println(g.greet("스프링"));
        Greeter g2 = ctx.getBean("greeter", Greeter.class);
        System.out.println(g == g2);
    }
}
