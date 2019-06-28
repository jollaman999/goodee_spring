package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordChangeService {
    @Autowired
    private MemberDao memberDao;

    public void change(String[] args) {
        Member member = memberDao.selectByEmail(args[1]);
        if (member == null) {
            throw new MemberNotFoundException("Email not found : " + args[1]);
        }

        if (!member.getPassword().equals(args[2])) {
            throw new RuntimeException("Wrong Password : " + args[2]);
        }

        memberDao.chgPassword(args);
    }
}
