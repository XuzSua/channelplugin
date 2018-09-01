package system;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class gamemode implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
	        
			GameMode[] gamemode = {GameMode.SURVIVAL, GameMode.CREATIVE, GameMode.ADVENTURE, GameMode.SPECTATOR};
			int mode = Integer.parseInt(args[0]);
			String[] gm = {"生存模式", "創造模式", "冒險模式", "觀察模式"};

			if (args.length == 1)
			{
				
				if (mode > 3 || mode < 0)
				{

					player.sendMessage("遊戲模式不存在!(最多0-3)，你輸入了: " + mode);
					return false;
					
				}
				player.setGameMode(gamemode[mode]);
				player.sendMessage(String.format("遊戲模式變更為 ( %s )", gm[mode]));
				return false;
				
				
			}
			
			if (args.length == 2)
			{
				
				Player target = Bukkit.getServer().getPlayer(args[1]);
				target.setGameMode(gamemode[mode]);
				target.sendMessage(String.format("遊戲模式變更為 ( %s )", gm[mode]));
				return false;
				
			}
			
	    }

	        
		return true;	 
	}

}
