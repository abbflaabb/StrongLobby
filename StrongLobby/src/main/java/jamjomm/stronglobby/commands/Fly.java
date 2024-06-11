package jamjomm.stronglobby.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("StrongLobby.Fly")) {
            player.sendMessage(ChatColor.RED + "You do not have permission to fly!");
            return true;
        }

        if (args.length != 0) {
            player.sendMessage(ChatColor.RED + "Usage: /fly");
            return true;
        }

        if (!player.isFlying()) {
            player.setAllowFlight(true);
            player.setFlying(true);
            player.sendMessage(ChatColor.GREEN + "Flying mode enabled!");
        } else {
            player.setAllowFlight(false);
            player.setFlying(false);
            player.sendMessage(ChatColor.RED + "Flying mode disabled!");
        }

        return true;
    }
}
