package org.vaatu.pluginski;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;

public final class Pluginski extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("Pluginski activated! Let the aids begin...");
        getServer().getPluginManager().registerEvents(new Listeners(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("The aids is gone!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("test")) {
            sender.sendMessage("Test funktioniert du Schwuchtel");
            return true;
        } else if (label.equalsIgnoreCase("killaids")) {
            sender.getServer().dispatchCommand(sender, "kill @e[type=chicken]");
            sender.getServer().dispatchCommand(sender, "kill @e[type=egg]");
            sender.getServer().dispatchCommand(sender, "kill @e[type=snowball]");
            sender.getServer().dispatchCommand(sender, "kill @e[type=item]");
            sender.getServer().dispatchCommand(sender, "kill @e[type=falling_block]");
            sender.getServer().dispatchCommand(sender, "kill @e[type=arrow]");
            return true;
        } else if (label.equalsIgnoreCase("allahuakbar")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                @SuppressWarnings("unchecked")
                Collection<Chicken> chickens = (Collection<Chicken>) p.getWorld()
                        .getEntitiesByClass(EntityType.CHICKEN.getEntityClass());
                for (Chicken c : chickens) {
                    c.damage(30);
                    c.getWorld().spawnEntity(c.getLocation(), EntityType.LIGHTNING);
                }
            }
            return true;
        }
        return false;
    }
}
