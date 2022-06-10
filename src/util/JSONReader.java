package util;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONReader {

    private static JSONParser jsonParser = new JSONParser();
    private static JSONObject skins;
    private static JSONArray messages;

    public static void read() {
        try {
            FileReader reader = new FileReader("resources/skins.json");
            skins = (JSONObject) jsonParser.parse(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            FileReader reader = new FileReader("resources/messages.json");
            messages = (JSONArray) jsonParser.parse(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Color> getSkin(String name) {
        ArrayList<Color> colors = new ArrayList<>();
        JSONObject skin = (JSONObject) skins.get(name);
        for(Object object : skin.values().toArray()) {
            JSONArray color = (JSONArray) object;
            colors.add(new Color(((Long) color.get(0)).intValue(), ((Long) color.get(1)).intValue(), ((Long) color.get(2)).intValue()));
        }
        return colors;
    }

    public static String[] getMessages() {
        String[] msgs = new String[messages.size()];
        for(int i = 0; i < msgs.length; i++) {
            msgs[i] = (String) messages.get(i);
        }
        return msgs;
    }
}
