package com.yangsc.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: JsonUtils</p>
 *
 * <p>Copyright: © 2018-至今 .All rights reserved.</p>
 *
 * @author yangsanchao
 * *
 * @date 2019-09-20 11:13:13
 **/
@Log4j2
public class JsonUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String getJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <E> E readValue(String json, Class<? extends E> cls) {
        try {
            return objectMapper.readValue(json, cls);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Person person = new Person();
        person.setName("");
        person.setAge(0);
        person.setTimestamp(new Timestamp(System.currentTimeMillis()));
        person.setDate(new Date(System.currentTimeMillis()));
        String json = JsonUtils.getJson(person);
        Person jsonPerson = JsonUtils.readValue(json,Person.class);
        log.info("json:{}",json);
        log.info("jsonPerson:{}",jsonPerson);

        List<Person> personList = new ArrayList<Person>();
        personList.add(person);
        personList.add(person);
        personList.add(person);
        String personListJson = JsonUtils.getJson(personList);
        List<Person> personListJsonList = JsonUtils.readValue(personListJson,List.class);

        log.info("personListJson:{}",personListJson);
        log.info("personListJsonList:{}",personListJsonList);


    }



    @Data
    private static class Person{
        @JsonIgnore
        private String name;
        private Integer age;
        private Timestamp timestamp;
        private Date date;
    }
}