package com.project.goodday2;

import android.util.Log;

import com.google.gson.Gson;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Iterator;

import static java.nio.file.Paths.get;

public class JSONController {

    private JSONObject allData=null ;
    private Gson gson;

    public JSONController(){
        gson = new Gson();
    }

    public void JSON_addItem(ToDoItem item){
        JSONArray yearMonth;
        if(allData == null) {
            allData = new JSONObject();
            yearMonth = new JSONArray();
        }
        else{
            yearMonth = (JSONArray) allData.get(item.getTime());
            if(yearMonth == null)
                yearMonth = new JSONArray();
        }
        yearMonth.add(gson.toJson(item));
        allData.put(item.getTime(), yearMonth);


        //전체 JSON데이터에 객체추가
    }

    public void JSON_deleteItem(ToDoItem item){
        JSONArray yearMonth;
        yearMonth = (JSONArray) allData.get(item.getTime());
        for(int i =0; i <yearMonth.size(); i++){
            ToDoItem temp = (ToDoItem) gson.fromJson(String.valueOf(yearMonth.get(i)), ToDoItem.class);
            if(temp.getTitle().equals(item.getTitle())){
                yearMonth.remove(i);
            }
        }

        allData.put(item.getTime(), yearMonth);
    }

    public JSONArray JSON_getSelectedData(String selectedDate){
        if(allData != null) {

            JSONArray seletedArray = (JSONArray) allData.get(selectedDate);
            return seletedArray;
        }
        else return null;

    }

    public JSONArray getCycleItem() { //allData의 키값들을 이용하여 순회
        if(allData != null){
            JSONParser parser = new JSONParser();
            JSONArray ja = new JSONArray();
            Iterator iter = allData.keySet().iterator();

            while(iter.hasNext()){
                String key = (String) iter.next();
                JSONArray seletedArray = (JSONArray) allData.get(key);
                for(int i = 0 ; i<seletedArray.size(); i++){
                    JSONObject jo = null;
                    try {
                        jo = (JSONObject) parser.parse(String.valueOf(seletedArray.get(i)));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Log.d("cycle-----", String.valueOf(jo.get("cycle")));
                    String cycle = String.valueOf(jo.get("cycle"));
                    if(!cycle.equals("-1")){
                        ja.add(jo);
                    }
                }
            }
            return ja;
        }
        else return null;
    }


    public JSONObject getAllData() {
        return allData;
    }

    public void setAllData(JSONObject allData) {
        this.allData = allData;
    }

    public Gson getGson(){
        return this.gson;
    }
}
