package spring;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

//@Component
@Repository // Component + model
public class MemberDao {
    private static long nextId = 0;
    // a@aaa.bbb=1, a@aaa.bbb, 홍길동, 1234, 현재 일자를 저장하고 있는 dao
    private Map<String, Member> map = new HashMap<>();

    public Member selectByEmail(String email) {
        return map.get(email);
    }

    public void insert(Member member) {
        member.setId(++nextId);
        map.put(member.getEmail(), member);
    }

    public void chgPassword(String[] args) {
        Member member = map.get(args[1]);
        member.setPassword(args[3]);
    }

    public void delete(String email) {
        map.remove(email);
    }

    public Collection<Member> selectAll() {
        return map.values();
    }
}