package jamjomm.stronglobby.commands;

import jamjomm.stronglobby.StrongLobby;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Help implements CommandExecutor {
    private final StrongLobby plugin;

    public Help(StrongLobby plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            displayGeneralHelp(sender);
        } else {
            displayCommandHelp(sender, args[0]);
        }
        return true;
    }

    private void displayGeneralHelp(CommandSender sender) {
        List<String> generalHelp = plugin.getConfig().getStringList("help.general");
        for (String line : generalHelp) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', line));
        }
    }

    private void displayCommandHelp(CommandSender sender, String command) {
        List<String> commandHelp = plugin.getConfig().getStringList("help.commands." + command.toLowerCase());
        if (commandHelp.isEmpty()) {
            sender.sendMessage(ChatColor.RED + "Unknown command: " + command);
        } else {
            for (String line : commandHelp) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', line));
            }
        }
    }
}