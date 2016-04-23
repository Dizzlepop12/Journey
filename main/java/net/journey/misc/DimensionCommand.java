package net.journey.misc;

import java.util.List;

import net.journey.JourneyBlocks;
import net.journey.client.server.DarkEnergyBar;
import net.journey.client.server.EssenceBar;
import net.journey.dimension.ModTeleporter;
import net.journey.dimension.corba.TeleporterCorba;
import net.journey.util.Config;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.Teleporter;

public class DimensionCommand extends CommandBase {

	@Override
	public String getCommandName() {
		return "dimension";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/dimension";
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) throws CommandException {
		EntityPlayerMP playerMP = getCommandSenderAsPlayer(var1);
		if(!playerMP.worldObj.isRemote) {
			if(var2[0].equalsIgnoreCase("Overworld")) {
				if(playerMP.dimension != 0) {
					playerMP.dimension = 0;
				}
			}

			if(var2[0].equalsIgnoreCase("Nether")) {
				if(playerMP.dimension != -1) {
					playerMP.dimension = -1;
				}
			}

			if(var2[0].equalsIgnoreCase("End")) {
				if(playerMP.dimension != 1) {
					playerMP.dimension = 1;
				}
			}

			if(var2[0].equalsIgnoreCase("Euca")) {
				if(playerMP.dimension != Config.euca) {
					playerMP.dimension = Config.euca;
				}
			}

			if(var2[0].equalsIgnoreCase("BoilingPoint")) {
				if(playerMP.dimension != Config.boil) {
					playerMP.dimension = Config.boil;
				}
			}

			if(var2[0].equalsIgnoreCase("Depths")) {
				if(playerMP.dimension != Config.depths) {
					playerMP.dimension = Config.depths;
				}
			}

			if(var2[0].equalsIgnoreCase("FrozenLands")) {
				if(playerMP.dimension != Config.frozen) {
					playerMP.dimension = Config.frozen;
				}
			}

			if(var2[0].equalsIgnoreCase("Corba")) {
				if(playerMP.dimension != Config.corba) {
					playerMP.dimension = Config.corba;
				}
			}

			/*if(var2[0].equalsIgnoreCase("Wastelands")) {
				if(playerMP.dimension != Config.wastelands) {
					playerMP.mcServer.getConfigurationManager().transferPlayerToDimension(playerMP, Config.wastelands, new ModTeleporter(playerMP.mcServer.worldServerForDimension(Config.wastelands), Config.wastelands, EssenceBlocks.wastelandsPortal, EssenceBlocks.wastelandsPortalFrame));
				}
			}*/

			if(var2[0].equalsIgnoreCase("Cloudia")) {
				if(playerMP.dimension != Config.cloudia) {
					playerMP.dimension = Config.cloudia;
				}
			}
		}

	}

	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2, BlockPos pos) {
		return par2.length == 1 ? getListOfStringsMatchingLastWord(par2, new String[] {"overworld", "nether", "end", "euca", "boilingpoint", "depths", "frozenlands", "corba", "wastelands", "cloudia"}) : null;
	}
}