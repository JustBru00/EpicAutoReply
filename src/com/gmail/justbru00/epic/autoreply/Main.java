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
public class Main extends JavaPlugin implements Listener{
	
	private ConsoleCommandSender clogger = getServer().getConsoleSender();
	public String prefix = color("&8[&bEpic&fAutoReply&8] &f");
	public PluginDescriptionFile pdffile = this.getDescription();

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
	    	 clogger.sendMessage(prefix + color(uncoloredmsg));
	    }
	    
	    public void msgPlayer(String uncoloredmsg, Player p){
	    	p.sendMessage(prefix + color(uncoloredmsg));
	    }
	    
	    public String color(String uncolored) {
	    	return ChatColor.translateAlternateColorCodes('&', uncolored);
	    }
	    
	    @SuppressWarnings("deprecation")
		@EventHandler
	    public void Player(PlayerChatEvent e){
	    	Player p = e.getPlayer();
	    	
	    	if (e.getMessage().contains("op me")) {
	    		Bukkit.broadcastMessage(prefix + color("Sorry I can not do that."));
	    	}
	    
	    }
}
