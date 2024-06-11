package jamjomm.stronglobby.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerLeave implements Listener {


    @EventHandler
    public void onLeave(org.bukkit.event.player.PlayerQuitEvent e) {

        String PlayerName = e.getPlayer().getName();


        e.setQuitMessage(ChatColor.DARK_AQUA + PlayerName + " has left the lobby");
    }
}