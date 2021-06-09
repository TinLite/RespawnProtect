package vn.vinhgaming.respawnprotect;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import vn.vinhgaming.respawnprotect.data.ConfigHandler;
import vn.vinhgaming.respawnprotect.data.DataHandler;

public class EventListener implements Listener {
    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        // Nếu người chơi vừa hồi sinh, thêm vào danh sách bảo vệ
        DataHandler.addProtect(event.getPlayer());
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        if ((event.getDamager() instanceof Player) && event.getDamager().hasPermission("rp.byass")) return;
        if (!(DataHandler.isProtect((((Player) event.getEntity()).getPlayer())))) return;
        event.setCancelled(true);
        if (event.getDamager() instanceof Player)
            event.getDamager().sendMessage(ConfigHandler.getMessage("UnableToCauseDamage"));
    }
}
