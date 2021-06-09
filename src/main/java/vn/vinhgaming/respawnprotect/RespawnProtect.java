package vn.vinhgaming.respawnprotect;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import vn.vinhgaming.respawnprotect.data.ConfigHandler;
import vn.vinhgaming.respawnprotect.data.DataHandler;

public final class RespawnProtect extends JavaPlugin {
    public static RespawnProtect main;

    @Override
    public void onEnable() {
        // Register event listener class
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);

        // Init data
        main = this;
        ConfigHandler.init();
        DataHandler.init();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("rp.admin")) {
            ConfigHandler.init();
            sender.sendMessage(ChatColor.GREEN + "Configuration reloaded.");
        } else sender.sendMessage(ConfigHandler.getMessage("NoPermission"));
        return true;
    }
}
