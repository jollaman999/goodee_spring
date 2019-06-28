package main;

import annotation.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import xml.*;


public class Main3 {
    public static void main(String[] args) {
        String[] config = {"annotation.xml"};
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        // readArticleService : ReadArticleServiceImpl 객체
        // ReadArticleServiceImpl 클래스는 ReadArticleService 인터페이스의 구현 클래스여야 한다.
        ReadArticleService service = ctx.getBean("readArticleService", ReadArticleService.class);

        try {
            Article a1 = service.getArticleAndReadCnt(1);
            Article a2 = service.getArticleAndReadCnt(1);
            System.out.println("[main] a1 == a2 : " + (a1 == a2));
            service.getArticleAndReadCnt(0);
        } catch (Exception e) {
            System.out.println("[main] " + e.getMessage());
        }

        System.out.println("\nUpdateMemberInfoTrace Aspect 연습");
        MemberService msvc = ctx.getBean("memberService", MemberService.class);
        msvc.regist(new Member());
        msvc.update("hong", new UpdateInfo());
        msvc.delete("hong2", "test");
        MemberService2 msvc2 = ctx.getBean("memberService2", MemberService2.class);
        msvc2.test("test", new UpdateInfo());
    }
}
