package com.qm.mainsystem.Json;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.UUID;

public class Read {
    public static int Read(UUID uuid) throws IOException, ParseException {
        JSONParser parser = new JSONParser();

        Reader reader = new FileReader("plugins/MainSystem/WaterSystem/"+uuid+".json");
        JSONObject jsonObject = (JSONObject) parser.parse(reader);

        long Thirst = (Long) jsonObject.get("Thirst");

        return (int) Thirst;
    }
}
