package trashy.elytradl;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Objects;

import static trashy.elytradl.Main.*;

public class POG implements Listener {




    public String prefix() {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin().getConfig().getString("prefix")));
    }

    public String message() {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin().getConfig().getString("message")));
    }


    public static long SL(Player p) {
        int cooldownTime = plugin().getConfig().getInt("Delay");
        return ((cooldowns.get(p) / 1000) + cooldownTime) - (System.currentTimeMillis() / 1000);
    }


    @EventHandler
    public void PlayerOG(PlayerMoveEvent e) {


        Player p = e.getPlayer();

        if (!p.hasPermission("dle.bypass")) {


            if (map().get(p) != null) {
                if (stel().get(p) != null) {

                    if (p.getInventory().getChestplate() != null) {
                        if (p.getInventory().getChestplate().getType().equals(Material.ELYTRA)) {


                            if (map().get(p).equals(false)) {
                                if (p.isGliding()) {
                                    stel().replace(p, true);

                                }
                            }

                            if (p.isOnGround()) {
                                if (stel().get(p).equals(true)) {
                                    if (cooldowns.containsKey(p)) {
                                        return;
                                    }
                                    cooldowns.put(p, System.currentTimeMillis());


                                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin(), new Runnable() {
                                        @Override
                                        public void run() {
                                            map().replace(p, true);
                                        }
                                    }, 5L);


                                }
                            }


                            if (map().get(p).equals(true)) {
                                if (SL(p) > 0) {
                                    if (map().get(p).equals(true)) {
                                        if (p.isGliding()) {


                                            HashMap<Player, ItemStack> Elytra = new HashMap<>();

                                            Elytra.put(p, p.getInventory().getChestplate());

                                            p.getInventory().setChestplate(null);

                                            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin(), new Runnable() {
                                                @Override
                                                public void run() {
                                                    p.getInventory().setChestplate(Elytra.get(p));
                                                }
                                            }, 5L);


                                            p.sendMessage(prefix() + " " + message().replaceAll("%time%", String.valueOf(SL(p))));
                                            return;

                                        }
                                    }
                                }

                                if (SL(p) == 0) {
                                    map().remove(p);
                                    stel().remove(p);
                                    cooldowns.remove(p);


                                }
                            }


                        }


                    }
                } else {
                    stel().put(p, false);
                }
            } else {
                map().put(p, false);

            }
        }
    }
}







