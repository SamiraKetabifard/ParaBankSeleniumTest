package com.example.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class JsonUtils {

    private List<Map<String,Object>> registerData;

    public JsonUtils(String path) {

        try {
            FileReader reader = new FileReader(path);

            Type type = new TypeToken<Map<String,List<Map<String,Object>>>>(){}.getType();

            Map<String,List<Map<String,Object>>> data =
                    new Gson().fromJson(reader,type);

            registerData = data.get("registerCases");

        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    public List<Map<String,Object>> getRegisterData(){
        return registerData;
    }
}