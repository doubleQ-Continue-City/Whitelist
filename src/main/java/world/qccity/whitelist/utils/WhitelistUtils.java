package world.qccity.whitelist.utils;

import com.alibaba.fastjson.JSONObject;
import world.qccity.whitelist.Whitelist;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class WhitelistUtils {
    private static Map<String, String> whitelist = new HashMap<>();
    private static File whitelistFile;

    public static void load() {
        whitelistFile = new File(Whitelist.DataFolder,"whitelist.json");
        if (!whitelistFile.exists()) {
            try {
                whitelistFile.createNewFile();
                JSONObject jsonObject= JSONObject.parseObject(whitelist.toString());
                FileWriter fileWriter = new FileWriter(whitelistFile);
                fileWriter.write(jsonObject.toJSONString());
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            JSONObject jsonObject = JSONObject.parseObject(JsonUtils.readJsonFile(whitelistFile));
            whitelist = (Map<String, String>) JSONObject.parse(jsonObject.toJSONString());
        }
    }

    public static String add(String id) {
        String data = NetworkUtils.httpRequestUtils("https://api.mojang.com/users/profiles/minecraft/" + id);
        if (!data.equals("")) {
            String uuid = new JSONObject(JSONObject.parseObject(data)).getString("id");
            whitelist.put(id,uuid);
            save();
            return "Added successfully!";
        }else return "Add failed! Failed to get UUID";
    }

    public static String remove(String id) {
        if (whitelist.containsKey(id)) {
            whitelist.remove(id);
            save();
            return "Removed successfully";
        }
        return "Remove failed! This ID does not exist";
    }

    public static void save() {
        try {
            FileWriter fileWriter = new FileWriter(whitelistFile);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.write(JSONObject.parseObject(whitelist.toString()).toJSONString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean verify(String uuid) {
        return whitelist.containsValue(uuid);
    }

    public static Map<String,String> getWhitelist() {
        return whitelist;
    }

    public static File getWhitelistFile() {
        return whitelistFile;
    }
}
