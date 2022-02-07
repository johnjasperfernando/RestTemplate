package com.restTemplateExample.RestTemplate.Dao;

import com.restTemplateExample.RestTemplate.Model.Post;
import com.restTemplateExample.RestTemplate.Controller.fetchValuesController;
import com.restTemplateExample.RestTemplate.Model.PostMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DataPersistence {

//    @Qualifier("jdbcTemplate")
    private NamedParameterJdbcTemplate jdbcTemplate;

//
//    @Qualifier("jdbcNamedTemplate")
    private JdbcTemplate jdbNamedTemplate;

    public DataPersistence(NamedParameterJdbcTemplate jdbcTemplate){this.jdbcTemplate=jdbcTemplate;}

    private static final Logger logger= LoggerFactory.getLogger(fetchValuesController.class);

    public boolean insertPeople(List<Post> list) throws Exception {

        boolean queryExecuted=false;
//        String  sql="Insert into Posts.Persons (id,userId,title,body) values (?,?,?,?);";
        String  sql="Insert into Posts.Persons (id,userId,title,body) values (:userId,:id,:title,:body);";
        int result=0;
        try
        {
//            for(int i=0;i< list.size();i++)
                for(Post li:list){
                MapSqlParameterSource parameterSource = new MapSqlParameterSource();
                parameterSource.addValue("userId",li.getUserId());
                parameterSource.addValue("id",li.getId());
                parameterSource.addValue("title",li.getTitle());
                parameterSource.addValue("body",li.getBody());
//                result = jdbcTemplate.update(sql, new Object[]{list.get(i).getUserId(),list.get(i).getId(),list.get(i).getTitle(),list.get(i).getBody()});
                result = jdbcTemplate.update(sql,parameterSource );
                    logger.info("insert the userId :"+li.getUserId());
            }

        }
        catch (Exception  e)
        {
            throw new Exception(e);
        }
        return true;
    }

    public List<Post> searchComment(int userId) throws SQLException {
    try{


        String sql = "select *  from  Posts.Persons where userId=:userId";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("userId",userId);
        return jdbcTemplate.query(sql,parameterSource,new PostMapper());
        }
        catch(Exception e)
        {
            throw new SQLException(e);
        }
    }
}
