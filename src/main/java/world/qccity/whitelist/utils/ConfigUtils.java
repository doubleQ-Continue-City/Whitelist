package world.qccity.whitelist.utils;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import world.qccity.whitelist.Whitelist;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigUtils {
    private static Configuration configuration;
    private static Boolean enable;
    private static List<String> adminList;
    private static String noWhitelistMessage;

    public static void load() {
        if (!Whitelist.DataFolder.exists())
            Whitelist.DataFolder.mkdir();

        File file = new File(Whitelist.DataFolder, "config.yml");

        if (!file.exists()) {
            try (InputStream in = Whitelist.configResourceAsStream) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(Whitelist.DataFolder, "config.yml"));
            enable = configuration.getBoolean("enable");
            noWhitelistMessage = configuration.getString("noWhitelistMessage");
            adminList = Arrays.asList(configuration.getString("adminList").split(","));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, new File(Whitelist.DataFolder, "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setEnable(Boolean value) {
        enable = value;
        configuration.set("enable",enable);
        save();
    }

    public static Configuration getConfiguration() {
        return configuration;
    }

    public static Boolean isEnabled() {
        return enable;
    }

    public static List<String> getAdminList() {
        return adminList;
    }

    public static String getNoWhitelistMessage() {
        return noWhitelistMessage;
    }
}
