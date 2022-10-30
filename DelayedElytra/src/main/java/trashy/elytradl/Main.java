package trashy.elytradl;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;


public final class Main extends JavaPlugin {

    public static Main plugin() {
        return plugin;
    }

    private static Main plugin;



    public static HashMap<Player, Boolean> map() {
        return elytra;
    }
    public static HashMap<Player, Boolean> stel() {
        return started_elytra;
    }




    public static HashMap<Player, Boolean> elytra = new HashMap<>();
    public static HashMap<Player, Boolean> started_elytra = new HashMap<>();
    public static HashMap<Player, Long> cooldowns = new HashMap<>();


    @Override
    public void onEnable() {
        getLogger().info(ChatColor.AQUA + "Delayed Elytra enabled");
        FileConfiguration config = this.getConfig();
        if (!getDataFolder().exists()) getDataFolder().mkdir();
        this.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new POG(), this);
        plugin = this;


        this.getCommand("delayedelytra").setExecutor(new Commands());

    }




    @Override
    public void onDisable() {
        getLogger().info(ChatColor.AQUA + "Delayed Elytra disabled");
    }
}
