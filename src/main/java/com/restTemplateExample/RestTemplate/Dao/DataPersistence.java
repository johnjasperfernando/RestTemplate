package com.restTemplateExample.RestTemplate.Dao;

import com.restTemplateExample.RestTemplate.Model.Post;
import com.restTemplateExample.RestTemplate.Controller.fetchValuesController;
import com.restTemplateExample.RestTemplate.Model.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
>>>>>>> 546ff7a88519a2dbcb6d6e80d96f6b6200b10b65
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

    private static final Logger logger= LoggerFactory.getLogger(fetchValuesController.class);
>>>>>>> 546ff7a88519a2dbcb6d6e80d96f6b6200b10b65

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

                logger.info("insert the userId :"+li.getUserId());
            }
        }
        catch (Exception  e)
        {
            throw new Exception(e);
        }
        return true;
    }

}
