package jamjomm.stronglobby.AdminCommands;

import jamjomm.stronglobby.StrongLobby;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {
    private final StrongLobby plugin;

    public ReloadCommand(StrongLobby plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("stronglobby.reload")) {
            plugin.reloadConfig();
            sender.sendMessage(ChatColor.GREEN + "StrongLobby configuration reloaded.");
            return true;
        }
        sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
        return false;
    }
}
