package dao;

import logic.Item;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemDao {
    private NamedParameterJdbcTemplate template;
    public void setDataSource(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Item> list() {
        String sql = "select * from item";
        RowMapper<Item> mapper = new BeanPropertyRowMapper<Item>(Item.class);
        return template.query(sql, mapper);
    }

    public Item selectOne(Integer id) {
        String sql = "select * from item where id=:id";
        RowMapper<Item> mapper = new BeanPropertyRowMapper<>(Item.class);
        Map<String, Integer> param = new HashMap<>();
        param.put("id", id);
        return template.queryForObject(sql, param, mapper);
    }
}
