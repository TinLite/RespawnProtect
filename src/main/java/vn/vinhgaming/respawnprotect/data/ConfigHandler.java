package vn.vinhgaming.respawnprotect.data;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import static vn.vinhgaming.respawnprotect.RespawnProtect.main;

public class ConfigHandler {
    public static int protectionTime;
    private static FileConfiguration config;

    public static void init() {
        main.saveDefaultConfig();
        main.reloadConfig();
        config = main.getConfig();
        protectionTime = config.getInt("ProtectionTime");
    }

    /**
     * Get tin nhắn đặt trong Config
     *
     * @param path Tin nhắn cần gửi
     * @return String tin nhắn đã được translate color code.
     */
    public static String getMessage(String path) {
        return ChatColor.translateAlternateColorCodes('&', config.getString("Message." + path));
    }
}
