package com.gmail.justbru00.epic.autoreply;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

/** 
 * @author justb_000
 *
 *    EpicAutoReply by Justin Brubaker is a plugin that autoreplys to chat.
 *   Copyright (C) 2015  Justin Brubaker
 *
 *   This program is free software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation; either version 2 of the License, or
 *   (at your option) any later version.
 * 
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License along
 *   with this program; if not, write to the Free Software Foundation, Inc.,
 *   51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 *   Contact me at justbru00@gmail.com
 *
 */
@SuppressWarnings("deprecation")
public class Main extends JavaPlugin implements Listener{
	
	private ConsoleCommandSender console = getServer().getConsoleSender();
	public String prefix = color("&8[&bEpic&fAutoReply&8] &f");
	public PluginDescriptionFile pdffile = this.getDescription();
	public long waitTime = 500;
	

	@Override
	public boolean onCommand(CommandSender sender, Command command,	String label, String[] args) {
	
		if (command.getName().equalsIgnoreCase("epicautoreply")) {			
			if (args.length == 0) {
				sender.sendMessage(prefix + color("&bThis is EpicAutoReply version : " + pdffile.getVersion()));
				return true;
			} else {
				sender.sendMessage(prefix + color("&cPlease don't put anything after /epicautoreply"));
				return true;
			}
		}
		
		return false;
	}

	@Override
	public void onDisable() {
		msgConsole(prefix + "version: " + pdffile.getVersion() + " has been disabled.");	
		
	}

	@Override
	public void onEnable() {
	    msgConsole(prefix + "version: " + pdffile.getVersion() + " has been enabled.");
		getServer().getPluginManager().registerEvents(this, this);
		
	}

	    public void msgConsole(String uncoloredmsg) {
	    	 console.sendMessage(prefix + color(uncoloredmsg));
	    }
	    
	    public void msgPlayer(String uncoloredmsg, Player p){
	    	p.sendMessage(prefix + color(uncoloredmsg));
	    }
	    
	    public String color(String uncolored) {
	    	return ChatColor.translateAlternateColorCodes('&', uncolored);
	    }
	    
	    @EventHandler
	    public void Player(PlayerChatEvent e){
	    	Player p = e.getPlayer();
	    	
	    	if (e.getMessage().contains("op me")) {	    		
	    		if (waitTimer(waitTime)) {
	    	      Bukkit.broadcastMessage(prefix + color("Sorry I can not do that."));
	    		} else {
	    			error("Error in waitTimer method. In check: op me.", p);
	    		}	    		
	    	}
	    	if (e.getMessage().contains("ban me")) {	    		
	    		if (waitTimer(waitTime)) {
	    	      Bukkit.broadcastMessage(prefix + color("Ok I will."));
	    	      waitTimer(waitTime);
	    	      Bukkit.dispatchCommand(console, "tempban " + p.getName() + " 1d You asked...");
	    		} else {
	    			error("Error in waitTimer method. In check: ban me.", p);
	    		}	    		
	    	}
	    	if (e.getMessage().contains("i hate this server")) {	    		
	    		if (waitTimer(waitTime)) {
	    	      Bukkit.broadcastMessage(prefix + color("Really that is too bad..."));	    	     
	    		} else {
	    			error("Error in waitTimer method. In check: ban me.", p);
	    		}	    		
	    	}
	    	if (e.getMessage().contains("how do i go to my plot")) {	    		
	    		if (waitTimer(waitTime)) {
	    	      Bukkit.broadcastMessage(prefix + color("Do /plot auto to get a plot. Then do /plot home"));
	    	      waitTimer(waitTime);
	    	      Bukkit.broadcastMessage(prefix + color("I will help you this time. :D"));
	    	      Bukkit.dispatchCommand(console, "sudo " + p.getName() + "plot home");
	    		} else {
	    			error("Error in waitTimer method. In check: how do i get to my plot.", p);
	    		}	    		
	    	}
	    
	    }
	    
	    public boolean waitTimer(Long waitMills){
	    	try {
    			Thread.sleep(waitMills); 
    			return true;
    			} catch(InterruptedException ex) {
    			Thread.currentThread().interrupt();
    			return false;
    			}
	    }
	    
	    public void error(String msg, Player player){
	    	msgConsole(color("&cError: " + msg));
	    	msgPlayer(color("&cError: " + msg), player);
	    }
}
