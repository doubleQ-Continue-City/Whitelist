package world.qccity.whitelist.listener;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import world.qccity.whitelist.utils.ConfigUtils;
import world.qccity.whitelist.utils.WhitelistUtils;

public class LoginEvent implements Listener {
    @EventHandler
    public void onPostLogin(PostLoginEvent event) {
        if (ConfigUtils.isEnabled()) {
            if (!WhitelistUtils.verify(event.getPlayer().getUniqueId().toString().replaceAll("-",""))) {
                event.getPlayer().disconnect(new TextComponent(ConfigUtils.getNoWhitelistMessage()));
            }
        }
    }

}
