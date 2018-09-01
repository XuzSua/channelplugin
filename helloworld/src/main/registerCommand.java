package main;

import system.channel;

public class registerCommand
{

	main plugin = main.plugin;
	
	public registerCommand()
	{

		plugin.getCommand("channel").setExecutor(new channel());
	}
	
}
