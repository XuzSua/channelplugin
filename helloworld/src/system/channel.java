package system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class channel implements CommandExecutor, Listener
{

	public Map<String, ArrayList<Player>> channels = new HashMap<String, ArrayList<Player>>();
	//頻道, 頻道內玩家
	
	public Map<Player, String> playerChannel = new HashMap<Player, String>();	
	//玩家, 所在頻道
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player)
		{
			
			Player player = (Player) sender;
			if (args.length == 0)
			{
				
				if (playerChannel.get(player) != null)
				{
					
					player.sendMessage(String.format("您所在頻道: %s", playerChannel.get(player)));
					
				}
				
			}
			if (args.length == 1)
			{
				
				switch(args[1])
				{
				
					case "聊天頻道":
						
						joinChannel(player, "聊天頻道");
						
						break;
					case "交易頻道":
						
						joinChannel(player, "交易頻道");
						
						break;
					default:
						
						player.sendMessage("請輸入正確指令: /channel 聊天頻道/交易頻道");
						
						break;
				}
				
			}
			
		}
		
		return false;
	}
	
	public void joinChannel(Player player, String channelName)
	{
		
		if (playerChannel.get(player) != null)
		{
			
			String changeChannel = playerChannel.get(player);
			leaveChannel(player, changeChannel);
			
		}
		
		ArrayList<Player> players = channels.get(channelName);
		if (players == null) players = new ArrayList<Player>();
		
		players.add(player);
		channels.put(channelName, players);
		playerChannel.put(player, channelName);
		player.sendMessage(String.format("您加入了: %s", channelName));
		
	}
	public void leaveChannel(Player player, String channelName)
	{
		
		ArrayList<Player> players = channels.get(channelName);
		players.remove(player);
		channels.put(channelName, players);
		playerChannel.remove(player);
		
		
	}
	
	public ArrayList<Player> getChannel(Player player)
	{
		
		String channelName = playerChannel.get(player);
		return channels.get(channelName);
		
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event)
	{
		
		joinChannel(event.getPlayer(), "頻道大廳");
		
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event)
	{
		
		Player p = event.getPlayer();
		
		event.getRecipients().clear();
		
		getChannel(p).stream().forEach(player -> event.getRecipients().add(p));
		
	}
	
	
	
	
	
	
	

}
