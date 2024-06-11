package jamjomm.stronglobby.AdminCommands;

import jamjomm.stronglobby.Utils.LuckPermsUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RankCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String rank = LuckPermsUtils.getPlayerRank(player);
            player.sendMessage(ChatColor.GREEN + "Your rank is: " + ChatColor.YELLOW + rank);
            return true;
        } else {
            sender.sendMessage("This command can only be executed by a player.");
            return true;
        }
    }
}
