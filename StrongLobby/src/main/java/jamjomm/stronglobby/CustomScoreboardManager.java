package jamjomm.stronglobby;

import jamjomm.stronglobby.Utils.LuckPermsUtils;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import static jamjomm.stronglobby.StrongLobby.plugin;

public class CustomScoreboardManager {
    private ScoreboardManager scoreboardManager;

    public CustomScoreboardManager() {
        this.scoreboardManager = Bukkit.getScoreboardManager();
    }

    public void createScoreboard(Player player) {
        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("example", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        // Get the title from config
        String title = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("scoreboard.title", "&aExample Scoreboard"));
        plugin.getLogger().info("Scoreboard title: " + title);  // Debugging line
        objective.setDisplayName(title);

        // Set placeholders for dynamic values
        Team playerNameTeam = scoreboard.registerNewTeam("playerName");
        playerNameTeam.addEntry(ChatColor.GREEN + " ");
        objective.getScore(ChatColor.GREEN + " ").setScore(10);

        // Get prefixes from config
        String playerNamePrefix = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("scoreboard.playerNamePrefix", "&ePlayer Name: "));
        plugin.getLogger().info("Player name prefix: " + playerNamePrefix);  // Debugging line
        playerNameTeam.setPrefix(playerNamePrefix);
        playerNameTeam.setSuffix(player.getName());

        Team playerHealthTeam = scoreboard.registerNewTeam("playerHealth");
        playerHealthTeam.addEntry(ChatColor.RED + " ");
        objective.getScore(ChatColor.RED + " ").setScore(9);

        String playerHealthPrefix = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("scoreboard.playerHealthPrefix", "&ePlayer Health: "));
        plugin.getLogger().info("Player health prefix: " + playerHealthPrefix);  // Debugging line
        playerHealthTeam.setPrefix(playerHealthPrefix);
        playerHealthTeam.setSuffix(String.valueOf((int) player.getHealth()));

        Team playerRankTeam = scoreboard.registerNewTeam("playerRank");
        playerRankTeam.addEntry(ChatColor.BLUE + " ");
        objective.getScore(ChatColor.BLUE + " ").setScore(8);
        playerRankTeam.setPrefix(ChatColor.YELLOW + "Rank: ");
        playerRankTeam.setSuffix(LuckPermsUtils.getPlayerRank(player));

        player.setScoreboard(scoreboard);
    }

    public void updateScoreboard(Player player) {
        Scoreboard scoreboard = player.getScoreboard();
        Team playerHealthTeam = scoreboard.getTeam("playerHealth");

        if (playerHealthTeam != null) {
            playerHealthTeam.setSuffix(String.valueOf((int) player.getHealth()));
        }

        Team playerRankTeam = scoreboard.getTeam("playerRank");

        if (playerRankTeam != null) {
            playerRankTeam.setSuffix(LuckPermsUtils.getPlayerRank(player));
        }
    }


    public void showScoreboard(Player player) {
        createScoreboard(player);
    }

    public void hideScoreboard(Player player) {
        player.setScoreboard(scoreboardManager.getNewScoreboard());
    }
}
