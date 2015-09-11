package com.gmail.dovedevic.plugins.CompassDirect;


import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
public class CompassDirectListener implements Listener{
	private CompassDirect plugin;
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[0";
	public CompassDirectListener(CompassDirect instance){
		plugin = instance;
	}
	@EventHandler
	public void join(PlayerJoinEvent event){
		Player p = event.getPlayer();
		if (plugin.getConfig().contains(p.getName())){
			for (String name : plugin.getConfig().getConfigurationSection("unsafe").getKeys(false)){
				if (!plugin.getConfig().contains(p.getName() + "." +  name)){
					plugin.getConfig().set(p.getName() + "." + name, plugin.getConfig().get("unsafe." + name));
					if (plugin.getConfig().getString("enable_player_fixes_in_console").equals("true")){
						System.out.println(ANSI_CYAN + "Added/Fixed " + p.getName() + "'s default locations!" + ANSI_WHITE);
					}
				}
				if (!plugin.getConfig().get(p.getName() + "." + name).equals(plugin.getConfig().get("unsafe." + name))){
					plugin.getConfig().set(p.getName() + "." + name, plugin.getConfig().get("unsafe." + name));
					if (plugin.getConfig().getString("enable_player_fixes_in_console").equals("true")){
						System.out.println(ANSI_CYAN + "Added/Fixed " + p.getName() + "'s default locations!" + ANSI_WHITE);
					}
				}
			}			
			for (String name : plugin.getConfig().getConfigurationSection("safe").getKeys(false)){
				if (!plugin.getConfig().contains(p.getName() + "." +  name)){
					plugin.getConfig().set(p.getName() + "." + name, plugin.getConfig().get("safe." + name));
					if (plugin.getConfig().getString("enable_player_fixes_in_console").equals("true")){
						System.out.println(ANSI_CYAN + "Added/Fixed " + p.getName() + "'s default locations!" + ANSI_WHITE);
					}
				}
				if (!plugin.getConfig().get(p.getName() + "." + name).equals(plugin.getConfig().get("safe." + name))){
					plugin.getConfig().set(p.getName() + "." + name, plugin.getConfig().get("safe." + name));
					if (plugin.getConfig().getString("enable_player_fixes_in_console").equals("true")){
						System.out.println(ANSI_CYAN + "Added/Fixed " + p.getName() + "'s default locations!" + ANSI_WHITE);
					}
				}
			}	
			for (String name : plugin.getConfig().getConfigurationSection("spawn").getKeys(false)){
				if (!plugin.getConfig().contains(p.getName() + "." +  name)){
					plugin.getConfig().set(p.getName() + "." + name, plugin.getConfig().get("spawn." + name));
					if (plugin.getConfig().getString("enable_player_fixes_in_console").equals("true")){
						System.out.println(ANSI_CYAN + "Added/Fixed " + p.getName() + "'s default locations!" + ANSI_WHITE);
					}
				}
				if (!plugin.getConfig().get(p.getName() + "." + name).equals(plugin.getConfig().get("spawn." + name))){
					plugin.getConfig().set(p.getName() + "." + name, plugin.getConfig().get("spawn." + name));
					if (plugin.getConfig().getString("enable_player_fixes_in_console").equals("true")){
						System.out.println(ANSI_CYAN + "Added/Fixed " + p.getName() + "'s default locations!" + ANSI_WHITE);
					}
				}
			}	
			for (String name : plugin.getConfig().getConfigurationSection("shop").getKeys(false)){
				if (!plugin.getConfig().contains(p.getName() + "." +  name)){
					plugin.getConfig().set(p.getName() + "." + name, plugin.getConfig().get("shop." + name));
					if (plugin.getConfig().getString("enable_player_fixes_in_console").equals("true")){
						System.out.println(ANSI_CYAN + "Added/Fixed " + p.getName() + "'s default locations!" + ANSI_WHITE);
					}
				}
				if (!plugin.getConfig().get(p.getName() + "." + name).equals(plugin.getConfig().get("shop." + name))){
					plugin.getConfig().set(p.getName() + "." + name, plugin.getConfig().get("shop." + name));
					if (plugin.getConfig().getString("enable_player_fixes_in_console").equals("true")){
						System.out.println(ANSI_CYAN + "Added/Fixed " + p.getName() + "'s default locations!" + ANSI_WHITE);
					}
				}
			}	
			plugin.saveConfig();
		}
		
		if (!plugin.getConfig().contains(p.getName())){
			for (String name : plugin.getConfig().getConfigurationSection("shop").getKeys(false)){
				plugin.getConfig().set(p.getName() + "." + name, plugin.getConfig().get("shop." + name));
			}
			for (String name : plugin.getConfig().getConfigurationSection("spawn").getKeys(false)){
				plugin.getConfig().set(p.getName() + "." + name, plugin.getConfig().get("spawn." + name));
			}
			for (String name : plugin.getConfig().getConfigurationSection("safe").getKeys(false)){
				plugin.getConfig().set(p.getName() + "." + name, plugin.getConfig().get("safe." + name));
			}
			for (String name : plugin.getConfig().getConfigurationSection("unsafe").getKeys(false)){
				plugin.getConfig().set(p.getName() + "." + name, plugin.getConfig().get("unsafe." + name));
			}
			plugin.saveConfig();
			if (plugin.getConfig().getString("enable_player_added_registration_in_console").equals("true")){
				System.out.println(ANSI_CYAN + "Registered player: " + p.getName() + " into CompassDirect's config!" + ANSI_WHITE);
			}
		}
	}
	
	@EventHandler
	public void click(PlayerInteractEvent event){
		Player p = event.getPlayer();
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR){
			if (p.getItemInHand().getType() == Material.COMPASS){
				ItemStack it = p.getItemInHand();
				ItemMeta itMeta = it.getItemMeta();
				if(itMeta.hasDisplayName()){
					String ComNm = itMeta.getDisplayName().substring(2, itMeta.getDisplayName().length());
					List<String> rotationCycleNm = new ArrayList<String>();
					List<ChatColor> rotationCycleClr = new ArrayList<ChatColor>();
					for(String names : plugin.getConfig().getConfigurationSection(p.getName()).getKeys(false)){
						rotationCycleNm.add(names);
						if(plugin.getConfig().contains("spawn." + names)){
							rotationCycleClr.add(ChatColor.DARK_PURPLE);
						}
						else if(plugin.getConfig().contains("shop." + names)){
							rotationCycleClr.add(ChatColor.BLUE);
						}
						else if(plugin.getConfig().contains("safe." + names)){
							rotationCycleClr.add(ChatColor.GREEN);
						}
						else if(plugin.getConfig().contains("unsafe." + names)){
							rotationCycleClr.add(ChatColor.RED);
						}
						else{
							rotationCycleClr.add(ChatColor.GOLD);
						}
					}
					int nms = rotationCycleNm.indexOf(ComNm) + 1;
					if(nms >= rotationCycleNm.size()){
						nms = 0;
					}
					itMeta.setDisplayName(rotationCycleClr.get(nms) + rotationCycleNm.get(nms));
					Location loc = new Location(p.getWorld(),0,0,0);
					loc.setX(Double.valueOf(plugin.getConfig().get(p.getName() + "." + rotationCycleNm.get(nms)).toString().substring(0, plugin.getConfig().get(p.getName() + "." + rotationCycleNm.get(nms)).toString().indexOf(","))));
					loc.setZ(Double.valueOf(plugin.getConfig().get(p.getName() + "." + rotationCycleNm.get(nms)).toString().substring(plugin.getConfig().get(p.getName() + "." + rotationCycleNm.get(nms)).toString().lastIndexOf(",") + 1, plugin.getConfig().get(p.getName() + "." + rotationCycleNm.get(nms)).toString().length())));
					p.setCompassTarget(loc);
					it.setItemMeta(itMeta);
				}
			}
		}
		if(event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_AIR){
			if (p.getItemInHand().getType() == Material.COMPASS){
				ItemStack it = p.getItemInHand();
				ItemMeta itMeta = it.getItemMeta();
				if(itMeta.hasDisplayName()){
					String ComNm = itMeta.getDisplayName().substring(2, itMeta.getDisplayName().length());
					List<String> rotationCycleNm = new ArrayList<String>();
					List<ChatColor> rotationCycleClr = new ArrayList<ChatColor>();
					for(String names : plugin.getConfig().getConfigurationSection(p.getName()).getKeys(false)){
						rotationCycleNm.add(names);
						if(plugin.getConfig().contains("spawn." + names)){
							rotationCycleClr.add(ChatColor.DARK_PURPLE);
						}
						else if(plugin.getConfig().contains("shop." + names)){
							rotationCycleClr.add(ChatColor.BLUE);
						}
						else if(plugin.getConfig().contains("safe." + names)){
							rotationCycleClr.add(ChatColor.GREEN);
						}
						else if(plugin.getConfig().contains("unsafe." + names)){
							rotationCycleClr.add(ChatColor.RED);
						}
						else{
							rotationCycleClr.add(ChatColor.GOLD);
						}
					}
					int nms = rotationCycleNm.indexOf(ComNm) - 1;
					if(nms <= 0){
						nms = rotationCycleNm.size() -1;
					}
					itMeta.setDisplayName(rotationCycleClr.get(nms) + rotationCycleNm.get(nms));
					Location loc = new Location(p.getWorld(),0,0,0);
					loc.setX(Double.valueOf(plugin.getConfig().get(p.getName() + "." + rotationCycleNm.get(nms)).toString().substring(0, plugin.getConfig().get(p.getName() + "." + rotationCycleNm.get(nms)).toString().indexOf(","))));
					loc.setZ(Double.valueOf(plugin.getConfig().get(p.getName() + "." + rotationCycleNm.get(nms)).toString().substring(plugin.getConfig().get(p.getName() + "." + rotationCycleNm.get(nms)).toString().lastIndexOf(",") + 1, plugin.getConfig().get(p.getName() + "." + rotationCycleNm.get(nms)).toString().length())));
					p.setCompassTarget(loc);
					it.setItemMeta(itMeta);
				}
			}
		}
	}
}
