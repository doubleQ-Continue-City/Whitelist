package world.qccity.whitelist;

import net.md_5.bungee.api.plugin.Plugin;
import world.qccity.whitelist.command.ListwhiteCommand;
import world.qccity.whitelist.listener.LoginEvent;
import world.qccity.whitelist.utils.ConfigUtils;
import world.qccity.whitelist.utils.WhitelistUtils;

import java.io.File;
import java.io.InputStream;

public final class Whitelist extends Plugin {
    public static final String version = "1.0";
    public static File DataFolder;
    public static InputStream configResourceAsStream;

    @Override
    public void onEnable() {
        // load
        DataFolder = getDataFolder();
        configResourceAsStream = getResourceAsStream("config.yml");
        // Plugin startup logic
        ConfigUtils.load();
        WhitelistUtils.load();
        getProxy().getPluginManager().registerListener(this, new LoginEvent());
        getProxy().getPluginManager().registerCommand(this, new ListwhiteCommand());
        getLogger().info("Enabled WhitelistPlugin version " + version);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Disabled WhitelistPlugin version " + version);
    }
}
