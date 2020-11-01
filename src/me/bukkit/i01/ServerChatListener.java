package me.bukkit.i01;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

public class ServerChatListener implements Listener {
    Main plugin;

    public ServerChatListener(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin = plugin;
    }

    @EventHandler
    public void ServerChat(AsyncPlayerChatEvent e) {
        Bot.sendServerChat("**" + e.getPlayer().getDisplayName() + "** » `" + e.getMessage() + "`" , e.getPlayer().getUniqueId().toString());

    }
}
