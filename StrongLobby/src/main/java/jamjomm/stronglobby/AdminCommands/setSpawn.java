package jamjomm.stronglobby.AdminCommands;

import jamjomm.stronglobby.StrongLobby;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class setSpawn implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length == 0){
                if(sender.isOp() || sender.hasPermission("StrongLobby.admin")){
                    StrongLobby.getInstance().reloadConfig();
                    StrongLobby.getInstance().getConfig().set("spawn.world", p.getWorld().getName());
                    StrongLobby.getInstance().getConfig().set("spawn.x", p.getLocation().getX());
                    StrongLobby.getInstance().getConfig().set("spawn.y", p.getLocation().getY());
                    StrongLobby.getInstance().getConfig().set("spawn.z", p.getLocation().getZ());
                    StrongLobby.getInstance().getConfig().set("spawn.yaw", p.getLocation().getYaw());
                    StrongLobby.getInstance().getConfig().set("spawn.pitch", p.getLocation().getPitch());
                    StrongLobby.getInstance().saveConfig();
                    // Add this line after saving the spawn point in the config
                    p.sendMessage(ChatColor.GREEN + "Spawn point has been set successfully.");
                }else{
                    p.sendMessage("§cYou do not have permission to execute this command!");
                }
            }else{
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull
                (StrongLobby.getInstance().getConfig().getString("messages.cmd-setspawn-usage"))));
            }
        }else{
            sender.sendMessage("This Command can only executed by a player, sorry!");
        }

        return false;
    }
}

