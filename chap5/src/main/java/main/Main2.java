package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.*;

public class Main2 {
    private static ApplicationContext ctx = null;

    public static void main(String[] args) throws IOException {
        ctx = new AnnotationConfigApplicationContext(AppCtx.class);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("명령어를 입력하세요");
            String command = reader.readLine();
            if (command.equalsIgnoreCase("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }
            // command 문자열의 시작이 "new " 인 경우 true 리턴
            if (command.startsWith("new ")) {
                processNewCommand(command.split(" "));
                continue;
            } else if (command.startsWith("passchg ")) {
                passwordChangeCommand(command.split(" "));
                continue;
            } else if (command.startsWith("change ")) {
                processNewCommand(command.split(" "));
                continue;
            } else if (command.startsWith("delete ")) {
                deleteCommand(command.split(" "));
                continue;
            } else if (command.startsWith("list")) {
                processListCommand();
                continue;
            }
            printHelp();
        }

    }

    private static void processNewCommand(String[] args) {
        if (args.length != 5) {   // args : new 이메일 이름 암호 암호확인
            printHelp();
            return;
        }

        MemberRegisterService regSvc = ctx.getBean(MemberRegisterService.class);

        RegisterRequest req = new RegisterRequest();
        req.setEmail(args[1]);
        req.setName(args[2]);
        req.setPassword(args[3]);
        req.setConfirmPassword(args[4]);
        if (!req.isPasswordEqualToConfirmPassword()) {
            System.out.println("암호화 확인 불일치 \n");
            return;
        }
        try {
            regSvc.regist(req);
            System.out.println("등록했습니다.\n");
        } catch (DuplicateMemberException e) {
            System.out.println("이미 존재하는 이메일 입니다.");
        }
    }

    private static void processListCommand() {
        MemberListPrinter listprinter = ctx.getBean("listPrinter", MemberListPrinter.class);
        listprinter.printAll();
    }

    // passchg 이메일 변경전비밀번호 변경후비밀번호
    // 이메일이 존재하지 않으면 MemberNotFoundException 예외발생
    // 비밀번호를 변경 후 비밀밀번호로 변경 하기.
    // 비밀번호 변경 완료. 메세지 출력하기.
    private static void passwordChangeCommand(String[] args) {
        if (args.length != 4) {   // args : passchg 이메일 변경전비밀번호 변경후비밀번호
            printHelp();
            return;
        }

        PasswordChangeService passSvc = ctx.getBean(PasswordChangeService.class);

        try {
            passSvc.change(args);
            System.out.println("비밀번호가 변경되었습니다.");
        } catch (MemberNotFoundException e) {
            System.out.println("입력하신 이메일이 존재하지 않습니다!");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deleteCommand(String[] args) {
        if (args.length != 2) {
            printHelp();
            return;
        }

        MemberDeleteService delSvc = ctx.getBean(MemberDeleteService.class);

        try {
            delSvc.delete(args);
            System.out.println("회원 정보가 삭제 되었습니다.");
        } catch (MemberNotFoundException e) {
            System.out.println("입력하신 이메일이 존재하지 않습니다!");
        }
    }

    private static void printHelp() {
        System.out.println("----------------------------");
        System.out.println("\n잘못된 명령입니다.");
        System.out.println("명령어 사용법.");
        System.out.println("new 이메일 이름 암호 암호확인");
        System.out.println("passchg 이메일 변경전비밀번호 변경후비밀번호");
        System.out.println("delete 이메일");
        System.out.println("----------------------------");
    }


}
