package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberDeleteService {
    @Autowired
    private MemberDao memberDao;

    public void delete(String[] args) {
        Member member = memberDao.selectByEmail(args[1]);
        if (member == null) {
            throw new MemberNotFoundException("Email not found : " + args[1]);
        }

        memberDao.delete(args[1]);
    }
}
