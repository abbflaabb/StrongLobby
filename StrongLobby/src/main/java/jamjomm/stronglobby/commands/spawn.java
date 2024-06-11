package jamjomm.stronglobby.commands;

import jamjomm.stronglobby.StrongLobby;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class spawn implements CommandExecutor {

        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if(sender instanceof Player){
                Player p = (Player) sender;
                if(args.length == 0){
                    if(sender.isOp() || sender.hasPermission("StrongLobby.admin") || sender.hasPermission(
                            "StrongLobby.spawn")){
                        StrongLobby plugin = StrongLobby.getInstance();
                        if (plugin != null) {
                            plugin.reloadConfig();
                            if (plugin.getConfig().getString("spawn.world")
                                    != null && plugin.getConfig().getString("spawn.x")
                                    != null && plugin.getConfig().getString("spawn.y")
                                    != null && plugin.getConfig().getString("spawn.z")
                                    != null && plugin.getConfig().getString("spawn.yaw")
                                    != null && plugin.getConfig().getString("spawn.pitch") != null) {
                                World world = Bukkit.getWorld(plugin.getConfig().getString("spawn.world"));
                                double x = plugin.getConfig().getDouble("spawn.x");
                                double y = plugin.getConfig().getDouble("spawn.y");
                                double z = plugin.getConfig().getDouble("spawn.z");
                                float yaw = (float) plugin.getConfig().getDouble("spawn.yaw");
                                float pitch = (float) plugin.getConfig().getDouble("spawn.pitch");

                                Location loc = new Location(world, x, y, z, yaw, pitch);
                                p.teleport(loc);
                                // Add this line after the player has been teleported
                                p.sendMessage(ChatColor.GREEN + "You have been teleported to the spawn point.");
                                if(Boolean.parseBoolean(plugin.getConfig().getString("settings.tpmessage-enable"))){
                                    p.sendMessage(ChatColor.translateAlternateColorCodes
                                    ('&', Objects.requireNonNull(plugin.getConfig().getString
                                    ("messages.tpmessage"))));
                                }
                            } else {
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull
                                (plugin.getConfig().getString("messages.error-nospawnpoint"))));
                            }
                        } else {
                            sender.sendMessage(ChatColor.RED + "Error: StrongLobby plugin instance is null.");
                        }
                    }else{
                        p.sendMessage("§cYou do not have permission to execute this command!");
                    }
                }else{
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull
                    (StrongLobby.getInstance().getConfig().getString("messages.cmd-spawn-usage"))));
                }
            }else{
                sender.sendMessage("This Command can only executed by a player, sorry!");
            }

            return false;
        }
    }
