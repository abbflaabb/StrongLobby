package jamjomm.stronglobby.events;

import jamjomm.stronglobby.CustomScoreboardManager;
import jamjomm.stronglobby.StrongLobby;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final CustomScoreboardManager scoreboardManager;
    private final LuckPerms luckPerms;

    public PlayerJoinListener(CustomScoreboardManager scoreboardManager) {
        this.scoreboardManager = scoreboardManager;
        this.luckPerms = StrongLobby.getInstance().getLuckPerms();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null); // Remove the default "joined the game" message
        event.getPlayer().sendMessage(ChatColor.GREEN + "Welcome to The Server Storing");

        // Set up the player's scoreboard
        scoreboardManager.createScoreboard(event.getPlayer());

        // Check and set rank
        User user = luckPerms.getUserManager().getUser(event.getPlayer().getUniqueId());
        if (user == null || user.getPrimaryGroup().isEmpty()) {
            if (user != null) {
                user.data().add(Node.builder("group.unknown").build());
                luckPerms.getUserManager().saveUser(user);
            }
        }
    }
}
