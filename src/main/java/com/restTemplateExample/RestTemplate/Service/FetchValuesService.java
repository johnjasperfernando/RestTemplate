package com.restTemplateExample.RestTemplate.Service;

import com.restTemplateExample.RestTemplate.Controller.fetchValuesController;
import com.restTemplateExample.RestTemplate.Dao.DataPersistence;
import com.restTemplateExample.RestTemplate.Model.Post;
//import com.restTemplateExample.RestTemplate.Model.SerivceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class FetchValuesService {

    RestTemplate restTemplate;

    public FetchValuesService(RestTemplate restTemplate) throws Exception {
        this.restTemplate=restTemplate;
    }
    @Autowired
    DataPersistence dao;

    private static final Logger logger= LoggerFactory.getLogger(fetchValuesController.class);

    public boolean getproductlist() throws Exception {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            List<Post> userList = new ArrayList<>();
            boolean query = false;
            httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
            String url = "https://jsonplaceholder.typicode.com/posts";
            Post[] response = restTemplate.exchange(url, HttpMethod.GET, entity, Post[].class).getBody();
            if (null != response) {
                for (Post res : response) {
                    userList.add(res);
                    res.getBody();
                    query = dao.insertPeople(userList);
                }
            }
            logger.info("Data has been successfully inserted");

        } catch (Exception e) {
            logger.info("innsertion failed");
            throw new Exception(e);
        }
        return true;
    }
}
