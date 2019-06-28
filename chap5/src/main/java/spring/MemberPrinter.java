package spring;

import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class MemberPrinter {
    private DateTimeFormatter formatter;

    public MemberPrinter() {
        formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
    }

    public void print(Member m) {
        if (formatter == null) {
            System.out.printf("회원정보 : 아이디=%d, 비밀번호=%s 이메일=%s, 이름=%s, 등록일=%tF\n",
                    m.getId(), m.getPassword(), m.getEmail(), m.getName(), m.getRegisterDateTime());
        } else {
            System.out.printf("format 회원정보 : 아이디=%d, 비밀번호=%s, 이메일=%s, 이름=%s, 등록일=%s\n",
                    m.getId(), m.getPassword(), m.getEmail(), m.getName(), m.getRegisterDateTime(), formatter.format(m.getRegisterDateTime()));
        }
    }
}
