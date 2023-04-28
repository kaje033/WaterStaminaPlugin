package com.qm.mainsystem.Json;

import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class Write {
    public static void jPut(int Thirst, UUID uuid){
        String path = "plugins/MainSystem"; //폴더 경로
        File Folder = new File(path);

        // 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
        if (!Folder.exists()) {
            try{
                Folder.mkdir(); //폴더 생성합니다.
            }
            catch(Exception e){
                e.getStackTrace();
            }
        }

        String path1 = "plugins/MainSystem/WaterSystem"; //폴더 경로
        File Folder1 = new File(path1);

        // 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
        if (!Folder1.exists()) {
            try{
                Folder1.mkdir(); //폴더 생성합니다.
            }
            catch(Exception e){
                e.getStackTrace();
            }
        }

        JSONObject obj = new JSONObject();

        obj.put("Thirst",Thirst);

        try{
            FileWriter file = new FileWriter("plugins/MainSystem/WaterSystem/"+uuid+".json");
            file.write(obj.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
