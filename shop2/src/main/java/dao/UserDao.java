package dao;

import logic.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class UserDao {
    private NamedParameterJdbcTemplate template;

    public void setDataSource(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    public void insert(User user) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(user);
        String sql = "insert into useraccount " +
                "(userid, username, password, birthday, phoneno, " +
                "postcode, address, email) " +
                "values (:userId, :userName, :password, :birthDay, :phoneNo, :postcode, :address, :email)";
        template.update(sql, param);
    }

    public User selectOne(String userId) {
        String sql = "select * from useraccount where userid = :userId";
        RowMapper<User> mapper = new BeanPropertyRowMapper<>(User.class);
        Map<String, String> param = new HashMap<>();
        param.put("userId", userId);
        return template.queryForObject(sql, param, mapper);
    }
}
