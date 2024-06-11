package jamjomm.stronglobby.events;

import jamjomm.stronglobby.StrongLobby;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.Objects;

public class PlayerRespawnListener implements Listener {

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        StrongLobby plugin = StrongLobby.getInstance();
        if (isSpawnConfigured(plugin)) {
            World world = Bukkit.getWorld(plugin.getConfig().getString("spawn.world"));
            double x = plugin.getConfig().getDouble("spawn.x");
            double y = plugin.getConfig().getDouble("spawn.y");
            double z = plugin.getConfig().getDouble("spawn.z");
            float yaw = (float) plugin.getConfig().getDouble("spawn.yaw");
            float pitch = (float) plugin.getConfig().getDouble("spawn.pitch");

            Location spawnLocation = new Location(world, x, y, z, yaw, pitch);
            event.setRespawnLocation(spawnLocation);
        } else {
            event.getPlayer().sendMessage(ChatColor.RED + "Spawn point is not set!");
        }
    }

    private boolean isSpawnConfigured(StrongLobby plugin) {
        return plugin.getConfig().getString("spawn.world") != null &&
                plugin.getConfig().getString("spawn.x") != null &&
                plugin.getConfig().getString("spawn.y") != null &&
                plugin.getConfig().getString("spawn.z") != null &&
                plugin.getConfig().getString("spawn.yaw") != null &&
                plugin.getConfig().getString("spawn.pitch") != null;
    }
}
