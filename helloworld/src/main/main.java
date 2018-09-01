package main;

import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin
{
	
	public static main plugin;
	
	public void onEnable()
	{
	
		plugin = this;
		
		new registerCommand();
		new registerListener(this);
		
	}
	
	public void onDisable()
	{
		
	}


}
