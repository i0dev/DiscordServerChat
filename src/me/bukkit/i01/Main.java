package me.bukkit.i01;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static FileConfiguration config;
    public static String ServerChatChannelID;
    public static String GuildID;
    public static String Message_Embed_Footer;
    public static String ColorHexCode;


    public static String generalConfigPrefix = "GeneralConfig.";
    public static String messagingConfigPrefix = "Messaging.";

    public void loadConfig() {
        reloadConfig();
        GuildID = config.getString(generalConfigPrefix + "GuildID");
        ColorHexCode = config.getString(generalConfigPrefix + "ColorHexCode");
        Message_Embed_Footer = config.getString(messagingConfigPrefix + "Discord_Embed_Footer");
        ServerChatChannelID = config.getString(generalConfigPrefix + "ServerChatChannelID");
        String BotToken = config.getString(generalConfigPrefix + "Token");
        Bot.init(BotToken);
        saveConfig();
    }

    @Override
    public void onEnable() {
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_RESET = "\u001B[0m";

        getLogger().info(color(ANSI_GREEN + "Enabling Discord Server Chat Bot!" + ANSI_RESET));
        config = getConfig();

        new ServerChatListener(this);

        getConfig().addDefault(generalConfigPrefix + "Token", "ENTER TOKEN HERE");
        getConfig().addDefault(generalConfigPrefix + "ServerChatChannelID", "765021985301594122");
        getConfig().addDefault(generalConfigPrefix + "GuildID", "743277096327053316");
        getConfig().addDefault(generalConfigPrefix + "ColorHexCode", "#800080");
        getConfig().addDefault(messagingConfigPrefix + "Reload_Successful", "&aYou successfully reloaded the plugin!");
        getConfig().addDefault(messagingConfigPrefix + "Reload_Usage", "&3Please use &9/dsc reload &3 to reload the plugin!");
        getConfig().addDefault(messagingConfigPrefix + "Discord_Embed_Footer", "Message Time");
        getConfig().options().copyDefaults(true);
        saveConfig();
        loadConfig();

    }

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    @Override
    public void onDisable() {
        String ANSI_RED = "\u001B[31m";
        String ANSI_RESET = "\u001B[0m";

        getLogger().info(color(ANSI_RED + "Disabling Discord Server Chat Bot!" + ANSI_RESET));
        saveConfig();
    }


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("dsc")) {
            Player player = (Player) sender;

            if (args.length == 0) {
                player.sendMessage(color(getConfig().getString(messagingConfigPrefix + "Reload_Usage")));
            }

            if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
                if (player.hasPermission("dsc.reload")) {
                    player.sendMessage(color(config.getString(messagingConfigPrefix + "Reload_Successful")));
                    loadConfig();
                }
            }
            return true;
        }
        return false;
    }

}
