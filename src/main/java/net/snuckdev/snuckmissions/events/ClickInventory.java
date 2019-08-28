package net.snuckdev.snuckmissions.events;

import net.snuckdev.snuckmissions.Main;
import net.snuckdev.snuckmissions.enums.MissionType;
import net.snuckdev.snuckmissions.items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClickInventory implements Listener {

    @EventHandler
    public void on(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        String title = e.getInventory().getTitle();
        ItemStack item = e.getCurrentItem();
        String itemName = item.getItemMeta().getDisplayName();

        if(title.equals("Menu de missões")) {
            if(e.getCurrentItem() == null) return;
            if(e.getCurrentItem().getType() == Material.AIR) return;

            switch (itemName) {
                case "§aBlocos quebrados":
                    e.setCancelled(true);
                    Inventory brokenBlocks = Bukkit.createInventory(null, 3 * 9, "Missões - blocos quebrados");
                    p.openInventory(brokenBlocks);
                    break;
                case "§aBlocos colocados":
                    e.setCancelled(true);
                    Inventory placedBlocks = Bukkit.createInventory(null, 3 * 9, "Missões - blocos colocados");
                    p.openInventory(placedBlocks);
                    break;
                case "§aJogadores mortos":
                    e.setCancelled(true);
                    Inventory killedPlayers = Bukkit.createInventory(null, 3 * 9, "Missões - jogadores mortos");
                    if(Main.data.get(p.getName() + ".players_killed").getAsInt() >= 1000) {
                        List<String> lore = new ArrayList<>();
                        lore.add("§7Status: §acompleta");
                        lore.add("");
                        lore.add("§7Progresso: §f1000§7/§f1000");
                        ItemStack itemS = new ItemStack(Material.STORAGE_MINECART);
                        ItemMeta meta = itemS.getItemMeta();
                        meta.setDisplayName("§bAssassino de Jogadores I");
                        meta.setLore(lore);
                        itemS.setItemMeta(meta);
                        killedPlayers.setItem(10, itemS);
                    } else if(Main.data.get(p.getName() + ".players_killed").getAsInt() < 1000) {
                        List<String> lore2 = new ArrayList<>();
                        lore2.add("§7Status: §cincompleta");
                        lore2.add("");
                        lore2.add("§7Progresso: §f" + Main.data.get(p.getName() + ".players_killed") + "§7/§f1000");
                        ItemStack itemS = new ItemStack(Material.MINECART);
                        ItemMeta meta = itemS.getItemMeta();
                        meta.setDisplayName("§bAssassino de Jogadores I");
                        meta.setLore(lore2);
                        itemS.setItemMeta(meta);
                        killedPlayers.setItem(10, itemS);
                    }
                    p.openInventory(killedPlayers);
                    break;
                case "§aMonstros mortos":
                    e.setCancelled(true);
                    Inventory killedMobs = Bukkit.createInventory(null, 3 * 9, "Missões - monstros mortos");
                    p.openInventory(killedMobs);
                    break;
            }

        }
    }

}
