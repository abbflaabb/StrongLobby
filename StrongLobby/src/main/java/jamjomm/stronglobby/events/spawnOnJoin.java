package jamjomm.stronglobby.events;

import jamjomm.stronglobby.StrongLobby;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

public class spawnOnJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        StrongLobby.getInstance().reloadConfig();

        if(!e.getPlayer().hasPlayedBefore()){
            if (StrongLobby.getInstance().getConfig().getString("spawn.world")
                    != null && StrongLobby.getInstance().getConfig().getString("spawn.x")
                    != null && StrongLobby.getInstance().getConfig().getString("spawn.y")
                    != null && StrongLobby.getInstance().getConfig().getString("spawn.z")
                    != null && StrongLobby.getInstance().getConfig().getString("spawn.yaw")
                    != null && StrongLobby.getInstance().getConfig().getString("spawn.pitch") != null){
                World world = Bukkit.getWorld(Objects.requireNonNull(StrongLobby.getInstance().getConfig().getString
                ("spawn.world")));
                double x = StrongLobby.getInstance().getConfig().getDouble("spawn.x");
                double y = StrongLobby.getInstance().getConfig().getDouble("spawn.y");
                double z = StrongLobby.getInstance().getConfig().getDouble("spawn.z");
                float yaw = (float) StrongLobby.getInstance().getConfig().getDouble("spawn.yaw");
                float pitch = (float) StrongLobby.getInstance().getConfig().getDouble("spawn.pitch");

                Location loc = new Location(world, x, y, z, yaw, pitch);
                e.getPlayer().teleport(loc);
            }
        }else{
            if(Boolean.parseBoolean(StrongLobby.getInstance().getConfig().getString(
                    "settings.spawn-on-join"))){
                if (StrongLobby.getInstance().getConfig().getString("spawn.world")
                        != null && StrongLobby.getInstance().getConfig().getString("spawn.x")
                        != null && StrongLobby.getInstance().getConfig().getString("spawn.y")
                        != null && StrongLobby.getInstance().getConfig().getString("spawn.z")
                        != null && StrongLobby.getInstance().getConfig().getString("spawn.yaw")
                        != null && StrongLobby.getInstance().getConfig().getString("spawn.pitch") != null){
                    World world = Bukkit.getWorld(Objects.requireNonNull
                    (StrongLobby.getInstance().getConfig().getString("spawn.world")));
                    double x = StrongLobby.getInstance().getConfig().getDouble("spawn.x");
                    double y = StrongLobby.getInstance().getConfig().getDouble("spawn.y");
                    double z = StrongLobby.getInstance().getConfig().getDouble("spawn.z");
                    float yaw = (float) StrongLobby.getInstance().getConfig().getDouble("spawn.yaw");
                    float pitch = (float) StrongLobby.getInstance().getConfig().getDouble("spawn.pitch");

                    Location loc = new Location(world, x, y, z, yaw, pitch);
                    e.getPlayer().teleport(loc);
                }
            }
        }
    }


}
