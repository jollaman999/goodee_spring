package dao;

import logic.Board;
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
public class BoardDao {
    private NamedParameterJdbcTemplate template;
    private RowMapper<Board> mapper = new BeanPropertyRowMapper<>(Board.class);
    private Map<String, Object> param = new HashMap<>();
    private String boardcol = "select num, name, pass, subject, content, file1 fileurl, regdate, readcnt, ref, "+
                                "reflevel, refstep from board ";

    @Autowired
    public void setDataSource(DataSource dataSource) {
        template = new NamedParameterJdbcTemplate(dataSource);
    }

    public int count() {
        param.clear();
        return template.queryForObject("select count(*) from board", param, Integer.class);
    }

    public int maxnum() {
        param.clear();
        return template.queryForObject("select ifnull(max(num), 0) from board", param, Integer.class);
    }

    public List<Board> list(int pageNum, int limit) {
        String sql = boardcol + "order by ref desc, refstep asc limit :startrow, :limit";
        param.clear();
        param.put("startrow", (pageNum - 1) * limit);
        param.put("limit", limit);

        return template.query(sql, param, mapper);
    }

    public Board selectOne(int num) {
        String sql = boardcol + "where num = :num";
        param.clear();
        param.put("num", num);

        return template.queryForObject(sql, param, mapper);
    }

    public void readcntadd(int num) {
        String sql = "update board set readcnt = readcnt + 1 where num = :num";
        param.clear();
        param.put("num", num);

        template.update(sql, param);
    }

    public int insert(Board board) {
        String sql = "insert into board (num, name, pass, subject, content, file1, regdate, readcnt, ref, reflevel, refstep) " +
                "values (:num, :name, :pass, :subject, :content, :fileurl, now(), 0, :ref, :reflevel, :refstep)";
        SqlParameterSource paramClass = new BeanPropertySqlParameterSource(board);

        return template.update(sql, paramClass);
    }

    public void updateRefStep(int ref, int refstep) {
        String sql = "update board set refstep = refstep + 1 where ref = :ref and refstep > :refstep";
        param.clear();
        param.put("ref", ref);
        param.put("refstep", refstep);

        template.update(sql, param);
    }

    public int update(Board board) {
        String sql = "update board set name = :name, subject = :subject, content = :content, file1 = :fileurl where num = :num";
        SqlParameterSource paramClass = new BeanPropertySqlParameterSource(board);

        return template.update(sql, paramClass);
    }

    public int delete(Integer num) {
        String sql = "delete from board where num = :num";
        param.clear();
        param.put("num", num);

        return template.update(sql, param);
    }
}
