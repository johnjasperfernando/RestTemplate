package com.restTemplateExample.RestTemplate.Controller;

import com.restTemplateExample.RestTemplate.Dao.DataPersistence;
import com.restTemplateExample.RestTemplate.Model.Post;
import com.restTemplateExample.RestTemplate.Model.SerivceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class fetchValuesController {

    RestTemplate restTemplate;

    @Autowired
    DataPersistence  dao;

    public fetchValuesController(RestTemplate restTemplate) {
        this.restTemplate=restTemplate;
    }

    @RequestMapping(value = "/")
    public String  helloWorld()
    {
        return "Hello World";
    }

    @RequestMapping(value = "/posts")
    public Post[] getProductList() throws Exception {
        try{
        HttpHeaders httpHeaders = new HttpHeaders();
        List<Post> userList = new ArrayList<>();
        SerivceResponse serivceResponse = new SerivceResponse();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        String url = "https://jsonplaceholder.typicode.com/posts";
        Post[] response = restTemplate.exchange(url, HttpMethod.GET, entity, Post[].class).getBody();
        if (null != response) {
            for (Post res : response) {
                userList.add(res);
                res.getBody();
                boolean query= dao.insertPeople(userList);
//                if(!query) throw new Exception("error  whille inserting");
            }

        }
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        return  new Post[0];
    }
}
