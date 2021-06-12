package vn.vinhgaming.respawnprotect.data;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;

import static vn.vinhgaming.respawnprotect.RespawnProtect.main;

public class ConfigHandler {
    public static boolean removeProtectionOnAttack;

    public static int protectionTime;
    private static FileConfiguration config;
    private static boolean titleEnable;

    public static void init() {
        main.saveDefaultConfig();
        main.reloadConfig();
        config = main.getConfig();
        protectionTime = config.getInt("ProtectionTime");
        removeProtectionOnAttack = config.getBoolean("RemoveProtectionOnAttack");
        titleEnable = config.getBoolean("Title.Protecting.Enable");
        if (config.getInt("Config-Version") == 2) return;
        try {
            Files.move(
                    Paths.get(main.getDataFolder() + "\\config.yml"),
                    Paths.get(main.getDataFolder() + "\\config.old.yml"),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bukkit.getLogger().log(Level.WARNING, "Phát hiện phiên bản config không khớp với phiên bản plugin. Vì thế, config cũ đã được save thành file config.old.yml và config mới đã được update vào. Xin hãy update config mới càng sớm càng tốt.");
        init(); //re-init
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

    public static void sendTitle(Player player) {
        if (!titleEnable) return;
        player.sendTitle(
                ChatColor.translateAlternateColorCodes('&', config.getString("Title.Protecting.Title")),
                ChatColor.translateAlternateColorCodes('&', config.getString("Title.Protecting.Subtitle")),
                0, protectionTime, 10
        );
    }
}
