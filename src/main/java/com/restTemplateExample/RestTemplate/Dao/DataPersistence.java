package com.restTemplateExample.RestTemplate.Dao;

import com.restTemplateExample.RestTemplate.Model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataPersistence {

//    @Qualifier("jdbcTemplate")
    private NamedParameterJdbcTemplate jdbcTemplate;
//
//    @Qualifier("jdbcNamedTemplate")
//    private NamedParameterJdbcTemplate jdbNamedTemplate;

    public DataPersistence(NamedParameterJdbcTemplate jdbcTemplate)
    {this.jdbcTemplate=jdbcTemplate;}


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

            }

        }
        catch (Exception  e)
        {
            throw new Exception(e);
        }
        return true;
    }

}
