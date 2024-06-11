package jamjomm.stronglobby.commands;

import jamjomm.stronglobby.StrongLobby;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShowScoreboardCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            StrongLobby.getInstance().getScoreboardManager().showScoreboard(player);
            player.sendMessage(ChatColor.GREEN + "Scoreboard shown.");
            return true;
        } else {
            sender.sendMessage(ChatColor.RED + "This command can only be executed by a player.");
            return false;
        }
    }
}
