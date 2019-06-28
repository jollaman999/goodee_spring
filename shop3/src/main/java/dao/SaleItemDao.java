package dao;

import logic.SaleItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SaleItemDao {
    private NamedParameterJdbcTemplate template;
    private RowMapper<SaleItem> mapper = new BeanPropertyRowMapper<>(SaleItem.class);
    private Map<String, Object> param = new HashMap<>();

    @Autowired
    private void setDataSource(DataSource dataSource) {
        template = new NamedParameterJdbcTemplate(dataSource);
    }

    public void insert(SaleItem si) {
        String sql = "insert into saleitem (saleid, saleitemid, itemid, quantity) values (:saleId, :saleItemId, :item.id, :quantity)";
        SqlParameterSource param = new BeanPropertySqlParameterSource(si);
        template.update(sql, param);
    }

    public List<SaleItem> list(int saleId) {
        String sql = "select * from saleitem where saleid = :saleId";
        param.clear();
        param.put("saleId", saleId);
        return template.query(sql, param, mapper);
    }
}
