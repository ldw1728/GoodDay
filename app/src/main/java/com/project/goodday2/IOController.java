package com.project.goodday2;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class IOController {

    private File data;

    public IOController(File fileDir){
        this.data = new File(fileDir+ "/data.json");
        Log.d("asdasdddd", fileDir.toString());
    }

    public void writeJSONfile(JSONObject jsonObject){
        try {
            FileWriter fw = new FileWriter(data);
            fw.write(jsonObject.toString());
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public org.json.simple.JSONObject readJSONFile(){
        JSONParser parser = new JSONParser();
        org.json.simple.JSONObject items = null;
        try {
            FileReader fr = new FileReader(data);
            items = (org.json.simple.JSONObject)parser.parse(fr);
            fr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }
}
