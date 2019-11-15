package com.project.goodday2;

import android.util.Log;

import com.google.gson.JsonElement;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.util.ArrayList;

public class CRUD_Method {

    private IOController ioController;
    private JSONController jsonController;

    public CRUD_Method(File getFIleDir){
        ioController = new IOController(getFIleDir);
        jsonController = new JSONController();
        jsonController.setAllData(ioController.readJSONFile());
    }


    public void addItem(ToDoItem item){
        jsonController.JSON_addItem(item);
        ioController.writeJSONfile(jsonController.getAllData());
    }

    public void deleteItem(ToDoItem item){
        jsonController.JSON_deleteItem(item);
        ioController.writeJSONfile(jsonController.getAllData());
    }

    public ArrayList<ToDoItem> loadSelectedData(String selectedDate) {
        ArrayList<ToDoItem> selectedItems = JSONArrayToArrayList(jsonController.JSON_getSelectedData(selectedDate));
        ArrayList<ToDoItem> cycleItems =  JSONArrayToArrayList(jsonController.getCycleItem());

        for(ToDoItem item : cycleItems){
            if(item.getCycle() == R.id.radio_day){
                selectedItems.add(0,item);
            }
            else if(item.getCycle() == R.id.radio_month){
                if(MainActivity.calController.getCurrentTime("dd").equals(selectedDate.substring(0,5))){
                    selectedItems.add(0,item);
                }
            }
            else if(item.getCycle() == R.id.radio_year){
                if(item.getDay().charAt(5) == selectedDate.charAt(5) && MainActivity.calController.getCurrentTime("dd").equals(selectedDate.substring(0,5))){
                    selectedItems.add(0,item);
                }
            }
        }

        return selectedItems;
    }

    public ArrayList<ToDoItem> JSONArrayToArrayList(JSONArray jsonArray){
        ArrayList<ToDoItem> items = new ArrayList<>();
        if(jsonArray != null) {
            Log.d("items not null---------", jsonArray.size()+"");
            for (int i = 0; i < jsonArray.size(); i++) {
                ToDoItem temp = jsonController.getGson().fromJson( jsonArray.get(i).toString(), ToDoItem.class);
                items.add(temp);
            }
        }
        return items;
    }
}
