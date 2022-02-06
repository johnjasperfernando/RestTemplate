package com.restTemplateExample.RestTemplate.Controller;

import com.restTemplateExample.RestTemplate.Dao.DataPersistence;
import com.restTemplateExample.RestTemplate.Model.Post;
import com.restTemplateExample.RestTemplate.Model.SerivceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import com.restTemplateExample.RestTemplate.Service.FetchValuesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
>>>>>>> 546ff7a88519a2dbcb6d6e80d96f6b6200b10b65
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
>>>>>>> 546ff7a88519a2dbcb6d6e80d96f6b6200b10b65
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

public class fetchValuesController implements Serializable {


    SerivceResponse serivceResponse =new SerivceResponse();

    private static final Logger logger= LoggerFactory.getLogger(fetchValuesController.class);
>>>>>>> 546ff7a88519a2dbcb6d6e80d96f6b6200b10b65
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
    @Autowired
    FetchValuesService fetchValuesService;
    @RequestMapping(value = "/posts")
    public ResponseEntity getProductList() throws Exception {
        try{
        boolean query=false;
        query=fetchValuesService.getproductlist();
        serivceResponse.setResponseMessage("Success");
        serivceResponse.setResposeCode(4001);
        serivceResponse.setResponse("Data has been inserted successfully");
        if(!query) return new ResponseEntity(serivceResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e)
        {
           logger.info(e.toString());
        }
        return  new ResponseEntity(serivceResponse,HttpStatus.OK) ;
    }
}
