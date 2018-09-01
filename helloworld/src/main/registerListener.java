package main;

import org.bukkit.plugin.PluginManager;

import system.channel;

public class registerListener
{

	main plugin = main.plugin;
	
	PluginManager pm = plugin.getServer().getPluginManager();
	
	public registerListener(main plugin)
	{
		
		pm.registerEvents(new channel(), plugin);
		
	}
	
}
