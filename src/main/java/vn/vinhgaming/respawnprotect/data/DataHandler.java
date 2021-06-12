package vn.vinhgaming.respawnprotect.data;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import vn.vinhgaming.respawnprotect.RespawnProtect;

import java.util.HashSet;

public class DataHandler {
    private static HashSet<Player> protecting;

    /**
     * Init
     */
    public static void init() {
        protecting = new HashSet<>();
    }

    /**
     * Kiểm tra xem người chơi có nằm trong danh sách người chơi đang được bảo vệ hay không?
     *
     * @param player Người chơi cần được kiểm tra
     * @return true nếu có trong danh sách, false nếu không.
     */
    public static boolean isProtect(Player player) {
        return protecting.contains(player);
    }

    /**
     * Thêm người chơi vào danh sách bảo vệ, và schedule để gỡ bỏ khỏi danh sách sau X tick
     *
     * @param player Người chơi cần thêm vào danh sách
     */
    public static void addProtect(Player player) {
        protecting.add(player);
        player.sendMessage(ConfigHandler.getMessage("PlayerReceiveProtection"));
        ConfigHandler.sendTitle(player);
        Bukkit.getScheduler().scheduleSyncDelayedTask(RespawnProtect.main, () -> {
            if (!protecting.contains(player)) return;
            removeProtect(player);
            player.sendMessage(ConfigHandler.getMessage("PlayerLostProtection"));
        }, ConfigHandler.protectionTime);
    }

    public static void removeProtect(Player player) {
        protecting.remove(player);
        player.sendTitle(" ", " ", 0, 5, 0); // clear title
    }
}
