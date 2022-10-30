package trashy.elytradl;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static trashy.elytradl.Main.*;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {


        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("delayedelytra")) {
            if (p.hasPermission("dle.reload")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("reload")) {
                        plugin().reloadConfig();
                        map().clear();
                        stel().clear();
                        cooldowns.clear();
                        p.sendMessage(ChatColor.GREEN + "Config successfully reloaded!");
                    } else {
                        p.sendMessage(ChatColor.AQUA + "Correct usage: /delayedelytra reload");
                    }
                } else {
                    p.sendMessage(ChatColor.AQUA + "Correct usage: /delayedelytra reload");
                }
            }
        }

        return false;
    }
}
