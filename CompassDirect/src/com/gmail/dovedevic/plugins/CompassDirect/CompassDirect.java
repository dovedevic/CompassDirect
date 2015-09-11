package com.gmail.dovedevic.plugins.CompassDirect;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
public class CompassDirect extends JavaPlugin{
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_MAGENTA = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[0m";
	public ArrayList<String> pendingSaves = new ArrayList<String>(); 
	protected UpdateChecker uc;
	public void pt(String msg){
		System.out.println(msg);
	}
	public void onEnable(){
		pt(ANSI_MAGENTA + "CompassDirect Enabled" + ANSI_WHITE);
		this.uc = new UpdateChecker(this, "http://dev.bukkit.org/bukkit-plugins/compassdirect/files.rss");
		this.uc.UpdateNeeded();
		getServer().getPluginManager().registerEvents(new CompassDirectListener(this), this);
		getCommand("cd").setExecutor(this);
		if(this.getConfig().getString("enable_UpdatePrompt").equals("true")){
			if(this.uc.UpdateNeeded()){
				pt(ANSI_CYAN + "A new version is available: " + this.uc.getVersion() + ANSI_WHITE);
				pt(ANSI_CYAN + "Get the new update from: " + this.uc.getLink() + ANSI_WHITE);
		}}
		if(configHasEverything()){this.saveConfig();}else{this.getConfig().options().copyDefaults(true);}
		configLoop();
	}
	public boolean configHasEverything(){
		
		return true;
	}
	public void configLoop(){
		for(String names : this.getConfig().getConfigurationSection("unsafe").getKeys(false)){
			String DirName = names;
			String DirLoc = this.getConfig().getString("unsafe." + names);	
			String DirX = DirLoc.substring(0, DirLoc.indexOf(","));
			DirLoc = DirLoc.replaceFirst(DirX + ",", "");
			String DirY = DirLoc.substring(0, DirLoc.lastIndexOf(","));
			DirLoc = DirLoc.replaceFirst(DirY + ",", "");
			String DirZ = DirLoc;			
			if (this.getConfig().getString("enable_visable_location_registration_in_console").equals("true")){
				pt(ANSI_RED + "CompassDirect registering UNSAFE location name: " + DirName + " @ location : X:" + DirX + " Y:" + DirY + " Z:" + DirZ + "." + ANSI_WHITE);
			}
		}
		for(String names : this.getConfig().getConfigurationSection("safe").getKeys(false)){
			String DirName = names;
			String DirLoc = this.getConfig().getString("safe." + names);	
			String DirX = DirLoc.substring(0, DirLoc.indexOf(","));
			DirLoc = DirLoc.replaceFirst(DirX + ",", "");
			String DirY = DirLoc.substring(0, DirLoc.lastIndexOf(","));
			DirLoc = DirLoc.replaceFirst(DirY + ",", "");
			String DirZ = DirLoc;			
			if (this.getConfig().getString("enable_visable_location_registration_in_console").equals("true")){
				pt(ANSI_GREEN + "CompassDirect registering SAFE location name: " + DirName + " @ location : X:" + DirX + " Y:" + DirY + " Z:" + DirZ + "." + ANSI_WHITE);
			}
		}
		for(String names : this.getConfig().getConfigurationSection("shop").getKeys(false)){
			String DirName = names;
			String DirLoc = this.getConfig().getString("shop." + names);	
			String DirX = DirLoc.substring(0, DirLoc.indexOf(","));
			DirLoc = DirLoc.replaceFirst(DirX + ",", "");
			String DirY = DirLoc.substring(0, DirLoc.lastIndexOf(","));
			DirLoc = DirLoc.replaceFirst(DirY + ",", "");
			String DirZ = DirLoc;			
			if (this.getConfig().getString("enable_visable_location_registration_in_console").equals("true")){
				pt(ANSI_BLUE + "CompassDirect registering SHOP location name: " + DirName + " @ location : X:" + DirX + " Y:" + DirY + " Z:" + DirZ + "." + ANSI_WHITE);
			}
		}
		for(String names : this.getConfig().getConfigurationSection("spawn").getKeys(false)){
			String DirName = names;
			String DirLoc = this.getConfig().getString("spawn." + names);	
			String DirX = DirLoc.substring(0, DirLoc.indexOf(","));
			DirLoc = DirLoc.replaceFirst(DirX + ",", "");
			String DirY = DirLoc.substring(0, DirLoc.lastIndexOf(","));
			DirLoc = DirLoc.replaceFirst(DirY + ",", "");
			String DirZ = DirLoc;			
			if (this.getConfig().getString("enable_visable_location_registration_in_console").equals("true")){
				pt(ANSI_MAGENTA + "CompassDirect registering SPAWN location name: " + DirName + " @ location : X:" + DirX + " Y:" + DirY + " Z:" + DirZ + "." + ANSI_WHITE);
			}
		}
		this.saveConfig();
	}
	public boolean onCommand(CommandSender p, Command cmd, String commandLabel, String[] args){
		if(commandLabel.equalsIgnoreCase("cd") && args.length == 2){
			if(args[0].equalsIgnoreCase("add")){
				Player plyr = (Player)p;
				if(plyr.hasPermission("compassdirect.addremove")){
					String locName = args[1];
					Location loc = plyr.getLocation();
					this.getConfig().set(plyr.getName() + "." + locName, Math.round(loc.getX()) + "," + Math.round(loc.getY()) + "," + Math.round(loc.getZ()));
					this.saveConfig();
					plyr.sendMessage(ChatColor.AQUA + "[CompassDirect]" + ChatColor.WHITE + " - You have successfully " + ChatColor.GREEN + "ADDED" + ChatColor.WHITE + " a custom location @ your current location called " + ChatColor.YELLOW + locName + ChatColor.WHITE + "!");
					return true;
				}  else {plyr.sendMessage(ChatColor.RED + "You do not have the permission to do this!"); }
			}
			else if(args[0].equalsIgnoreCase("remove")){
				Player plyr = (Player)p;
				if(plyr.hasPermission("compassdirect.addremove")){
					String locName = args[1];
					if (this.getConfig().contains(plyr.getName() + "." + locName)){
						this.getConfig().set(plyr.getName() + "." + locName, null);
						this.saveConfig();
						plyr.sendMessage(ChatColor.AQUA + "[CompassDirect]" + ChatColor.WHITE + " - You have successfully" + ChatColor.RED + " REMOVED" + ChatColor.WHITE + " a location called " + ChatColor.YELLOW +  locName +  ChatColor.WHITE + "!");
						return true;
					}
					plyr.sendMessage(ChatColor.AQUA + "[CompassDirect]" + ChatColor.WHITE + " - You do not have a location saved with this name!");
					return true;
				} else {plyr.sendMessage(ChatColor.RED + "You do not have the permission to do this!"); }
			}
			else if(args[0].equalsIgnoreCase("removeglobal")){
				Player plyr = (Player)p;
				if(plyr.hasPermission("compassdirect.op")){
					String tbr = args[1];
					List<String> globalLocs = new ArrayList<String>();
					for(String names : this.getConfig().getConfigurationSection("unsafe").getKeys(false)){
						globalLocs.add(names);
					}
					for(String names : this.getConfig().getConfigurationSection("safe").getKeys(false)){
						globalLocs.add(names);
					}
					for(String names : this.getConfig().getConfigurationSection("shop").getKeys(false)){
						globalLocs.add(names);
					}
					for(String names : this.getConfig().getConfigurationSection("spawn").getKeys(false)){
						globalLocs.add(names);
					}
					if(globalLocs.contains(tbr)){
						for(String names : this.getConfig().getConfigurationSection("unsafe").getKeys(false)){
							if(names.equals(tbr)){
								this.getConfig().set("unsafe." + names, null);
								for(String s : this.getConfig().getKeys(false)){
									if(!(s.equalsIgnoreCase("spawn") || s.equalsIgnoreCase("shop") || s.equalsIgnoreCase("safe") || s.equalsIgnoreCase("unsafe") || s.equalsIgnoreCase("enable_visable_location_registration_in_console") || s.equalsIgnoreCase("enable_player_added_registration_in_console") || s.equalsIgnoreCase("enable_player_fixes_in_console") || s.equalsIgnoreCase("enable_player_custom_add_location_registration_in_console"))){
										this.getConfig().set(s + "." + names, null);
									}
								}
								plyr.sendMessage(ChatColor.AQUA + "[CompassDirect]" + ChatColor.WHITE + " - Successfully " + ChatColor.RED + "REMOVED" + ChatColor.WHITE + " a global location called " + ChatColor.YELLOW + args[1] + ChatColor.WHITE + " from the config and all players!");
								pt(ANSI_RED + plyr.getName() + " issued a command to remove all locations with the name of " + tbr + ANSI_WHITE);
								this.saveConfig();
								return true;
							}
						}
						for(String names : this.getConfig().getConfigurationSection("safe").getKeys(false)){
							if(names.equals(tbr)){
								this.getConfig().set("safe." + names, null);
								for(String s : this.getConfig().getKeys(false)){
									if(!(s.equalsIgnoreCase("spawn") || s.equalsIgnoreCase("shop") || s.equalsIgnoreCase("safe") || s.equalsIgnoreCase("unsafe") || s.equalsIgnoreCase("enable_visable_location_registration_in_console") || s.equalsIgnoreCase("enable_player_added_registration_in_console") || s.equalsIgnoreCase("enable_player_fixes_in_console") || s.equalsIgnoreCase("enable_player_custom_add_location_registration_in_console"))){
										this.getConfig().set(s + "." + names, null);
									}
								}
								plyr.sendMessage(ChatColor.AQUA + "[CompassDirect]" + ChatColor.WHITE + " - Successfully " + ChatColor.RED + "REMOVED" + ChatColor.WHITE + " a global location called " + ChatColor.YELLOW + args[1] + ChatColor.WHITE + " from the config and all players!");
								pt(ANSI_RED + plyr.getName() + " issued a command to remove all locations with the name of " + tbr + ANSI_WHITE);
								this.saveConfig();
								return true;
							}
						}
						for(String names : this.getConfig().getConfigurationSection("shop").getKeys(false)){
							if(names.equals(tbr)){
								this.getConfig().set("shop." + names, null);
								for(String s : this.getConfig().getKeys(false)){
									if(!(s.equalsIgnoreCase("spawn") || s.equalsIgnoreCase("shop") || s.equalsIgnoreCase("safe") || s.equalsIgnoreCase("unsafe") || s.equalsIgnoreCase("enable_visable_location_registration_in_console") || s.equalsIgnoreCase("enable_player_added_registration_in_console") || s.equalsIgnoreCase("enable_player_fixes_in_console") || s.equalsIgnoreCase("enable_player_custom_add_location_registration_in_console"))){
										this.getConfig().set(s + "." + names, null);
									}
								}
								plyr.sendMessage(ChatColor.AQUA + "[CompassDirect]" + ChatColor.WHITE + " - Successfully " + ChatColor.RED + "REMOVED" + ChatColor.WHITE + " a global location called " + ChatColor.YELLOW + args[1] + ChatColor.WHITE + " from the config and all players!");
								pt(ANSI_RED + plyr.getName() + " issued a command to remove all locations with the name of " + tbr + ANSI_WHITE);
								this.saveConfig();
								return true;
							}
						}
						for(String names : this.getConfig().getConfigurationSection("spawn").getKeys(false)){
							if(names.equals(tbr)){
								this.getConfig().set("spawn." + names, null);
								for(String s : this.getConfig().getKeys(false)){
									if(!(s.equalsIgnoreCase("spawn") || s.equalsIgnoreCase("shop") || s.equalsIgnoreCase("safe") || s.equalsIgnoreCase("unsafe") || s.equalsIgnoreCase("enable_visable_location_registration_in_console") || s.equalsIgnoreCase("enable_player_added_registration_in_console") || s.equalsIgnoreCase("enable_player_fixes_in_console") || s.equalsIgnoreCase("enable_player_custom_add_location_registration_in_console"))){
										this.getConfig().set(s + "." + names, null);
									}
								}
								plyr.sendMessage(ChatColor.AQUA + "[CompassDirect]" + ChatColor.WHITE + " - Successfully " + ChatColor.RED + "REMOVED" + ChatColor.WHITE + " a global location called " + ChatColor.YELLOW + args[1] + ChatColor.WHITE + " from the config and all players!");
								pt(ANSI_RED + plyr.getName() + " issued a command to remove all locations with the name of " + tbr + ANSI_WHITE);
								this.saveConfig();
								return true;
							}
						}
						plyr.sendMessage(ChatColor.AQUA + "[CompassDirect]" + ChatColor.WHITE + " - An unknown error occured!");
						pt(ANSI_RED + plyr.getName() + "Fatal error on removing global location! Ticket this incident with what happened! (x167:158) " + ANSI_WHITE);
						return true;
					}
					else{
						plyr.sendMessage(ChatColor.AQUA + "[CompassDirect]" + ChatColor.WHITE + " - Could not find global location " + ChatColor.RED + "'" + tbr + "'" + ChatColor.WHITE + "to remove!");
						return true;
					}
				} else {plyr.sendMessage(ChatColor.RED + "You do not have the permission to do this!"); }
			}
			else if(args[0].equalsIgnoreCase("help")){
				Player plyr = (Player)p;
				if(args[1].equals("1")){
					plyr.sendMessage(ChatColor.RED + "-----------CompassDirect Help [1]-----------");
					plyr.sendMessage(ChatColor.GRAY + "------Use /cd help [n] to get page [n] of help------");
					plyr.sendMessage(ChatColor.GREEN + "/cd add [location name]");
					plyr.sendMessage(ChatColor.YELLOW + "---> Adds your current location to your compass list.");
					plyr.sendMessage(ChatColor.GREEN + "/cd remove [location name]");
					plyr.sendMessage(ChatColor.YELLOW + "---> Removes a location from your compass list by its name.");
					plyr.sendMessage(ChatColor.GREEN + "/cd list");
					plyr.sendMessage(ChatColor.YELLOW + "---> Lists all global and custom saved locations.");
					plyr.sendMessage(ChatColor.GREEN + "/cd tell [player name] [location name]");
					plyr.sendMessage(ChatColor.YELLOW + "---> Tell a player a location for them to save.");
					return true;
				}
				if(args[1].equals("2")){
					plyr.sendMessage(ChatColor.RED + "-----------CompassDirect Help [2]-----------");
					plyr.sendMessage(ChatColor.GRAY + "------Use /cd help [n] to get page [n] of help------");
					plyr.sendMessage(ChatColor.GREEN + "/cd accept");
					plyr.sendMessage(ChatColor.YELLOW + "---> Accepts a player's location share.");
					plyr.sendMessage(ChatColor.GREEN + "/cd decline");
					plyr.sendMessage(ChatColor.YELLOW + "---> Declines a player's location share.");
					plyr.sendMessage(ChatColor.GREEN + "/cd activate");
					plyr.sendMessage(ChatColor.YELLOW + "---> Activates the compass you're holding.");
					plyr.sendMessage(ChatColor.GREEN + "/cd help");
					plyr.sendMessage(ChatColor.YELLOW + "---> Shows this!");
					return true;
				}
				else{
					plyr.sendMessage(ChatColor.AQUA + "[CompassDirect]" + ChatColor.WHITE + " - There are only 2 pages of commands at the moment.");
					return true;
				}
			}
			this.saveConfig();
			return false;
		}
		else if(commandLabel.equalsIgnoreCase("cd") && args.length == 1){
			if(args[0].equalsIgnoreCase("list")){
				Player plyr = (Player)p;
				p.sendMessage(ChatColor.AQUA + "[CompassDirect]" + ChatColor.WHITE + " - Your locations with coordinates...");
				for(String names : this.getConfig().getConfigurationSection(p.getName()).getKeys(false)){
					plyr.sendMessage(ChatColor.GREEN + names + " @ " + this.getConfig().get(plyr.getName() + "." + names));
				}
				return true;
			}
			else if(args[0].equalsIgnoreCase("activate")){
				Player plyr = (Player)p;
				if(plyr.hasPermission("compassdirect.activate")){
					if (plyr.getItemInHand().getType() == Material.COMPASS){
						ItemStack it = plyr.getItemInHand();
						ItemMeta itMeta = it.getItemMeta();
						if(!itMeta.hasDisplayName()){
							for(String names : this.getConfig().getConfigurationSection("spawn").getKeys(false)){
								itMeta.setDisplayName(ChatColor.DARK_PURPLE + names);
								it.setItemMeta(itMeta);
								Location loc = new Location(plyr.getWorld(),0,0,0);
								loc.setZ(Double.valueOf(this.getConfig().get(plyr.getName() + "." + names).toString().substring(this.getConfig().get(plyr.getName() + "." + names).toString().lastIndexOf(",")+1, this.getConfig().get(plyr.getName() + "." + names).toString().length())));
								loc.setX(Double.valueOf(this.getConfig().get(plyr.getName() + "." + names).toString().substring(0,this.getConfig().get(plyr.getName() + "." + names).toString().indexOf(","))));
								plyr.setCompassTarget(loc);
								plyr.sendMessage(ChatColor.AQUA + "[CompassDirect]" + ChatColor.WHITE + " - Your compass has been activated!");
								return true;
							}
						} else{
							plyr.sendMessage(ChatColor.AQUA + "[CompassDirect]" + ChatColor.WHITE + " - This is already an activated compass!");
							return true;
						}
					}
					else{
						plyr.sendMessage(ChatColor.AQUA + "[CompassDirect]" + ChatColor.WHITE + " - You must be holding a compass to activate!");
						return true;
					}
				} else {plyr.sendMessage(ChatColor.RED + "You do not have the permission to do this!"); }
			}
			else if(args[0].equalsIgnoreCase("help")){
				Player plyr = (Player)p;
				plyr.sendMessage(ChatColor.RED + "-----------CompassDirect Help [1]-----------");
				plyr.sendMessage(ChatColor.GRAY + "------Use /cd help [n] to get page [n] of help------");
				plyr.sendMessage(ChatColor.GREEN + "/cd add [location name]");
				plyr.sendMessage(ChatColor.YELLOW + "---> Adds your current location to your compass list.");
				plyr.sendMessage(ChatColor.GREEN + "/cd remove [location name]");
				plyr.sendMessage(ChatColor.YELLOW + "---> Removes a location from your compass list by its name.");
				plyr.sendMessage(ChatColor.GREEN + "/cd list");
				plyr.sendMessage(ChatColor.YELLOW + "---> Lists all global and custom saved locations.");
				plyr.sendMessage(ChatColor.GREEN + "/cd tell [player name] [location name]");
				plyr.sendMessage(ChatColor.YELLOW + "---> Tell a player a location for them to save.");
				return true;
			}
			else if(args[0].equalsIgnoreCase("all")){
				Player plyr = (Player)p;
				if(plyr.hasPermission("compassdirect.op")){
					plyr.sendMessage(pendingSaves.toString());
					return true;
				} else {plyr.sendMessage(ChatColor.RED + "You do not have the permission to do this!"); }
			}
			else if(args[0].equalsIgnoreCase("accept")){
				Player plyr = (Player)p;
				for(int nms = 0; nms < pendingSaves.size(); nms++){
					String name = pendingSaves.get(nms).substring(0,pendingSaves.get(nms).indexOf(":"));
					if(name.equals(plyr.getName())){
						plyr.sendMessage(ChatColor.AQUA + "[CompassDirect]" + ChatColor.WHITE + " - You just " + ChatColor.GREEN + "ACCEPTED" + ChatColor.WHITE + " a location save from " + ChatColor.RED + pendingSaves.get(nms).substring((pendingSaves.get(nms).indexOf(":")+1), pendingSaves.get(nms).lastIndexOf(":")));
						String LocName = pendingSaves.get(nms).substring(pendingSaves.get(nms).lastIndexOf(":") +1, pendingSaves.get(nms).indexOf("*"));
						this.getConfig().set(plyr.getName() + "." + LocName, pendingSaves.get(nms).substring(pendingSaves.get(nms).indexOf("*")+1,pendingSaves.get(nms).indexOf(",")) + "," + pendingSaves.get(nms).substring(pendingSaves.get(nms).indexOf(",")+1,pendingSaves.get(nms).lastIndexOf(",")) + "," + pendingSaves.get(nms).substring(pendingSaves.get(nms).lastIndexOf(",")+1,pendingSaves.get(nms).length()));
						this.saveConfig();
						pendingSaves.remove(pendingSaves.get(nms));
						return true;
					}
				}
				plyr.sendMessage(ChatColor.AQUA + "[CompassDirect]" + ChatColor.WHITE + " - You have no pending location saves to accept!");
			}
			else if(args[0].equalsIgnoreCase("decline")){
				Player plyr = (Player)p;
				for(int nms = 0; nms < pendingSaves.size(); nms++){
					String name = pendingSaves.get(nms).substring(0,pendingSaves.get(nms).indexOf(":"));
					if(name.equals(plyr.getName())){
						plyr.sendMessage(ChatColor.AQUA + "[CompassDirect]" + ChatColor.WHITE + " - You just " + ChatColor.RED + "DECLINED" + ChatColor.WHITE + " a location save from " + ChatColor.RED + pendingSaves.get(nms).substring((pendingSaves.get(nms).indexOf(":")+1), pendingSaves.get(nms).lastIndexOf(":")));
						pendingSaves.remove(pendingSaves.get(nms));
						return true;
					}
				}
				plyr.sendMessage(ChatColor.AQUA + "[CompassDirect]" + ChatColor.WHITE + " - You have no pending location saves to decline!");
				return true;
			}
			return false;
		}
		if(commandLabel.equalsIgnoreCase("cd") && args.length == 3){
			if(args[0].equalsIgnoreCase("tell")){
				Player plyr = (Player)p;
				if(plyr.hasPermission("compassdirect.share")){
					try{
						Player toSend = Bukkit.getPlayer(args[1]);
						if(!(this.getConfig().getString(plyr.getName() + "." + args[2]) == null)){
							toSend.sendMessage(ChatColor.AQUA + "[CompassDirect]" + ChatColor.WHITE + " - " + ChatColor.RED + plyr.getName() + ChatColor.WHITE +  " sent you the coordinates for " + ChatColor.YELLOW  + args[2]  + ChatColor.WHITE + " @ " + ChatColor.YELLOW + this.getConfig().getString(plyr.getName() + "." + args[2]) + ChatColor.WHITE + ". Type in " + ChatColor.GREEN + "/cd accept " + ChatColor.WHITE + "or " + ChatColor.RED + "/cd decline" + ChatColor.WHITE + ".");
							plyr.sendMessage(ChatColor.AQUA + "[CompassDirect]" + ChatColor.WHITE + " - You sent " + ChatColor.RED + toSend.getName() + ChatColor.WHITE + " the coordinates for " + ChatColor.YELLOW + args[2] + ChatColor.WHITE + "!");
							for(int nms = 0; nms < pendingSaves.size(); nms++){
								String name1 = pendingSaves.get(nms).substring(0,pendingSaves.get(nms).indexOf(":"));
								if(name1.equals(toSend.getName())){
									pendingSaves.remove(pendingSaves.get(nms));
									pendingSaves.add(toSend.getName() + ":" + plyr.getName() + ":" + args[2] + "*" + this.getConfig().getString(plyr.getName() + "." + args[2]));
									return true;
								}
							}
							pendingSaves.add(toSend.getName() + ":"  + plyr.getName() + ":" + args[2] + "*" + this.getConfig().getString(plyr.getName() + "." + args[2]));
							return true;
						}
						plyr.sendMessage(ChatColor.AQUA + "[CompassDirect]" + ChatColor.WHITE + " - You do not have a location saved with that name!");
						return true;
					}
					catch (NullPointerException e){
						plyr.sendMessage(ChatColor.AQUA + "[CompassDirect]" + ChatColor.WHITE + " - Invalid player name!");
						return true;
					}
				} else {plyr.sendMessage(ChatColor.RED + "You do not have the permission to do this!"); }
			}
			else if(args[0].equalsIgnoreCase("addglobal")){
				Player plyr = (Player)p;
				if(plyr.hasPermission("compassdirect.op")){
					if(args[1].equalsIgnoreCase("spawn") && !args[2].equals(null)){
						this.getConfig().set("spawn." + args[2], Math.round(plyr.getLocation().getX()) + "," + Math.round(plyr.getLocation().getY()) + "," + Math.round(plyr.getLocation().getZ()));
						for(String s : this.getConfig().getKeys(false)){
							if(!(s.equalsIgnoreCase("spawn") || s.equalsIgnoreCase("shop") || s.equalsIgnoreCase("safe") || s.equalsIgnoreCase("unsafe") || s.equalsIgnoreCase("enable_visable_location_registration_in_console") || s.equalsIgnoreCase("enable_player_added_registration_in_console") || s.equalsIgnoreCase("enable_player_fixes_in_console") || s.equalsIgnoreCase("enable_player_custom_add_location_registration_in_console"))){
								this.getConfig().set(s + "." + args[2], Math.round(plyr.getLocation().getX()) + "," + Math.round(plyr.getLocation().getY()) + "," + Math.round(plyr.getLocation().getZ()));						}
						}
						plyr.sendMessage(ChatColor.AQUA + "[CompassDirect]" + ChatColor.WHITE + " - " + ChatColor.GREEN + "SUCCESSFULLY" + ChatColor.WHITE + " added a global " + ChatColor.DARK_PURPLE + "SPAWN" + ChatColor.WHITE + " location called " + ChatColor.YELLOW + args[2] + ChatColor.WHITE + " at your current location!");
						pt(ANSI_RED + plyr.getName() + " added a new global SPAWN location called " + args[2] + ANSI_WHITE);
						this.saveConfig();
						return true;
					}
					else if(args[1].equalsIgnoreCase("shop") && !args[2].equals(null)){
						this.getConfig().set("shop." + args[2], Math.round(plyr.getLocation().getX()) + "," + Math.round(plyr.getLocation().getY()) + "," + Math.round(plyr.getLocation().getZ()));					for(String s : this.getConfig().getKeys(false)){
							if(!(s.equalsIgnoreCase("spawn") || s.equalsIgnoreCase("shop") || s.equalsIgnoreCase("safe") || s.equalsIgnoreCase("unsafe") || s.equalsIgnoreCase("enable_visable_location_registration_in_console") || s.equalsIgnoreCase("enable_player_added_registration_in_console") || s.equalsIgnoreCase("enable_player_fixes_in_console") || s.equalsIgnoreCase("enable_player_custom_add_location_registration_in_console"))){
								this.getConfig().set(s + "." + args[2], Math.round(plyr.getLocation().getX()) + "," + Math.round(plyr.getLocation().getY()) + "," + Math.round(plyr.getLocation().getZ()));						}
						}
						plyr.sendMessage(ChatColor.AQUA + "[CompassDirect]" + ChatColor.WHITE + " - " + ChatColor.GREEN + "SUCCESSFULLY" + ChatColor.WHITE + " added a global " + ChatColor.BLUE + "SHOP" + ChatColor.WHITE + " location called " + ChatColor.YELLOW + args[2] + ChatColor.WHITE + " at your current location!");
						pt(ANSI_RED + plyr.getName() + " added a new global SHOP location called " + args[2] + ANSI_WHITE);
						this.saveConfig();
						return true;
					}
					else if(args[1].equalsIgnoreCase("safe") && !args[2].equals(null)){
						this.getConfig().set("safe." + args[2], Math.round(plyr.getLocation().getX()) + "," + Math.round(plyr.getLocation().getY()) + "," + Math.round(plyr.getLocation().getZ()));					for(String s : this.getConfig().getKeys(false)){
							if(!(s.equalsIgnoreCase("spawn") || s.equalsIgnoreCase("shop") || s.equalsIgnoreCase("safe") || s.equalsIgnoreCase("unsafe") || s.equalsIgnoreCase("enable_visable_location_registration_in_console") || s.equalsIgnoreCase("enable_player_added_registration_in_console") || s.equalsIgnoreCase("enable_player_fixes_in_console") || s.equalsIgnoreCase("enable_player_custom_add_location_registration_in_console"))){
								this.getConfig().set(s + "." + args[2], Math.round(plyr.getLocation().getX()) + "," + Math.round(plyr.getLocation().getY()) + "," + Math.round(plyr.getLocation().getZ()));						}
						}
						plyr.sendMessage(ChatColor.AQUA + "[CompassDirect]" + ChatColor.WHITE + " - " + ChatColor.GREEN + "SUCCESSFULLY" + ChatColor.WHITE + " added a global " + ChatColor.GREEN + "SAFE" + ChatColor.WHITE + " location called " + ChatColor.YELLOW + args[2] + ChatColor.WHITE + " at your current location!");
						pt(ANSI_RED + plyr.getName() + " added a new global SAFE location called " + args[2] + ANSI_WHITE);
						this.saveConfig();
						return true;
					}
					else if(args[1].equalsIgnoreCase("unsafe") && !args[2].equals(null)){
						this.getConfig().set("unsafe." + args[2], Math.round(plyr.getLocation().getX()) + "," + Math.round(plyr.getLocation().getY()) + "," + Math.round(plyr.getLocation().getZ()));					for(String s : this.getConfig().getKeys(false)){
							if(!(s.equalsIgnoreCase("spawn") || s.equalsIgnoreCase("shop") || s.equalsIgnoreCase("safe") || s.equalsIgnoreCase("unsafe") || s.equalsIgnoreCase("enable_visable_location_registration_in_console") || s.equalsIgnoreCase("enable_player_added_registration_in_console") || s.equalsIgnoreCase("enable_player_fixes_in_console") || s.equalsIgnoreCase("enable_player_custom_add_location_registration_in_console"))){
								this.getConfig().set(s + "." + args[2], Math.round(plyr.getLocation().getX()) + "," + Math.round(plyr.getLocation().getY()) + "," + Math.round(plyr.getLocation().getZ()));						}
						}
						plyr.sendMessage(ChatColor.AQUA + "[CompassDirect]" + ChatColor.WHITE + " - " + ChatColor.GREEN + "SUCCESSFULLY" + ChatColor.WHITE + " added a global " + ChatColor.RED + "UNSAFE" + ChatColor.WHITE + " location called " + ChatColor.YELLOW + args[2] + ChatColor.WHITE + " at your current location!");
						pt(ANSI_RED + plyr.getName() + " added a new global UNSAFE location called " + args[2] + ANSI_WHITE);
						this.saveConfig();
						return true;
					}
					else{
						plyr.sendMessage(ChatColor.AQUA + "[CompassDirect]" + ChatColor.WHITE + " - Usage: /cd globaladd <safe:unsafe:shop:spawn> <name>");
						return true;
					}
				} else {plyr.sendMessage(ChatColor.RED + "You do not have the permission to do this!"); }
			}
		}
		this.saveConfig();
		return false;
	}
}
