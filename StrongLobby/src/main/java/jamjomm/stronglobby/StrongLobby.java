package jamjomm.stronglobby;

import jamjomm.stronglobby.AdminCommands.RankCommand;
import jamjomm.stronglobby.AdminCommands.ReloadCommand;
import jamjomm.stronglobby.AdminCommands.setSpawn;
import jamjomm.stronglobby.DataBase.DatabaseHandler;
import jamjomm.stronglobby.commands.*;
import jamjomm.stronglobby.events.PlayerJoinListener;
import jamjomm.stronglobby.events.PlayerLeave;
import jamjomm.stronglobby.events.PlayerRespawnListener;
import jamjomm.stronglobby.events.spawnOnJoin;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;





public final class StrongLobby extends JavaPlugin {
    public static StrongLobby plugin;
    private CustomScoreboardManager scoreboardManager;
    private LuckPerms luckPerms;
    private DatabaseHandler databaseHandler;




    @Override
    public void onEnable() {
        plugin = this;
        scoreboardManager = new CustomScoreboardManager();
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        loadConfigValues();
        getLogger().info("Config loaded: " + plugin.getConfig().getString("scoreboard.title"));

        // Initialize LuckPerms API
        luckPerms = LuckPermsProvider.get();


        databaseHandler = new DatabaseHandler();
        databaseHandler.connect();

        String scoreboardTitle = plugin.getConfig().getString("scoreboard.title");
        if (scoreboardTitle == null) {
            getLogger().severe("scoreboard.title is null. Please check your config.yml file.");
        } else {
            getLogger().info("Config title: " + scoreboardTitle);
        }


        System.out.print("[SpawnPlugin] Plugin Abilitato Correttamente!");
        getCommand("spawn").setExecutor(new spawn());
        getCommand("setspawn").setExecutor(new setSpawn());
        getCommand("Fly").setExecutor(new Fly());
        getCommand("StrongLobbyReload").setExecutor(new ReloadCommand(this));
        getCommand("showscoreboard").setExecutor(new ShowScoreboardCommand());
        getCommand("hidescoreboard").setExecutor(new HideScoreboardCommand());
        getCommand("Help").setExecutor(new Help(this));
        getCommand("rank").setExecutor(new RankCommand());




        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerJoinListener(scoreboardManager), this);
        pluginManager.registerEvents(new PlayerLeave(), this);
        pluginManager.registerEvents(new spawnOnJoin(), this);
        pluginManager.registerEvents(new PlayerRespawnListener(), this);



        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    scoreboardManager.updateScoreboard(player);
                }
            }
        }.runTaskTimer(this, 0, 20); // Update every second (20 ticks)
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Server is Stopped");
    }

    public static StrongLobby getInstance() {
        return plugin;
    }


    public CustomScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }

    public void loadConfigValues() {
        // Load and cache config values here
        String scoreboardTitle = plugin.getConfig().getString("scoreboard.title");
        // other config values...
    }
    public LuckPerms getLuckPerms() {
        return luckPerms;
    }


    public DatabaseHandler getDatabaseHandler() {
        return databaseHandler;
    }


}
