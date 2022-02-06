package com.restTemplateExample.RestTemplate.Controller;

import com.restTemplateExample.RestTemplate.Dao.DataPersistence;
import com.restTemplateExample.RestTemplate.Model.SerivceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import com.restTemplateExample.RestTemplate.Service.FetchValuesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class fetchValuesController {

    RestTemplate restTemplate;

    @Autowired
    DataPersistence  dao;

    public fetchValuesController(RestTemplate restTemplate) {
        this.restTemplate=restTemplate;
    }

    SerivceResponse serivceResponse =new SerivceResponse();

    private static final Logger logger= LoggerFactory.getLogger(fetchValuesController.class);

    @RequestMapping(value = "/")
    public String  helloWorld()
    {
        return "Hello World";
    }
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
