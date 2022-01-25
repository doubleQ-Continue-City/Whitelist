package world.qccity.whitelist.command;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;
import world.qccity.whitelist.Whitelist;
import world.qccity.whitelist.utils.ConfigUtils;
import world.qccity.whitelist.utils.WhitelistUtils;

public class ListwhiteCommand extends Command {
    public ListwhiteCommand() {
        super("listwhite");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (ConfigUtils.getAdminList().contains(commandSender.getName())) {
            if (strings.length == 0) {
                commandSender.sendMessage(new TextComponent("Whitelist BungeeCord Plugin " + Whitelist.version));
                commandSender.sendMessage(new TextComponent("Author : buguwu,doubleQ Continue City Team"));
                commandSender.sendMessage(new TextComponent("Can only be used in DoubleQ Continue City Server!"));
            }else if (strings.length == 1) {
                switch (strings[0]) {
                    case "on":
                        ConfigUtils.setEnable(true);
                        commandSender.sendMessage(new TextComponent("Enabled whitelist successfully!"));
                        break;
                    case "off":
                        ConfigUtils.setEnable(false);
                        commandSender.sendMessage(new TextComponent("Disabled whitelist successfully!"));
                        break;
                    case "reload":
                        WhitelistUtils.save();
                        ConfigUtils.load();
                        WhitelistUtils.load();
                        commandSender.sendMessage(new TextComponent("Reloaded successfully!"));
                        break;
                    case "list":
                        for (String key : WhitelistUtils.getWhitelist().keySet()) {
                            commandSender.sendMessage(new TextComponent(key + "  (" + WhitelistUtils.getWhitelist().get(key) + ")"));
                        }
                        break;
                    default:
                        commandSender.sendMessage(new TextComponent("Missing parameter!"));
                        break;
                }
            }else if (strings.length == 2) {
                if (strings[0].equals("add")) {
                    commandSender.sendMessage(new TextComponent(WhitelistUtils.add(strings[1])));
                }else if (strings[0].equals("remove")) {
                    commandSender.sendMessage(new TextComponent(WhitelistUtils.remove(strings[1])));
                }else {
                    commandSender.sendMessage(new TextComponent("Missing parameter!"));
                }
            }else {
                commandSender.sendMessage(new TextComponent("Missing parameter!"));
            }
        }
    }
}
