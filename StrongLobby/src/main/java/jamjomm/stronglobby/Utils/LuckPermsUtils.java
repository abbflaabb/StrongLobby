package jamjomm.stronglobby.Utils;


import jamjomm.stronglobby.StrongLobby;
import net.luckperms.api.model.user.User;
import net.luckperms.api.query.QueryOptions;
import org.bukkit.entity.Player;

public class LuckPermsUtils {
    public static String getPlayerRank(Player player) {
        StrongLobby plugin = StrongLobby.getInstance();
        User user = plugin.getLuckPerms().getUserManager().getUser(player.getUniqueId());

        if (user != null) {
            return user.getCachedData().getMetaData(QueryOptions.defaultContextualOptions()).getPrimaryGroup();
        } else {
            return "Unknown";
        }
    }
}
