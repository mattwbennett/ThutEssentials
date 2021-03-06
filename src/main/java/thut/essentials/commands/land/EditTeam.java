package thut.essentials.commands.land;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import thut.essentials.ThutEssentials;
import thut.essentials.land.LandManager;
import thut.essentials.land.LandManager.LandTeam;
import thut.essentials.util.BaseCommand;
import thut.essentials.util.RuleManager;

public class EditTeam extends BaseCommand
{

    public EditTeam()
    {
        super("editteam", 0, "editTeam");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        EntityPlayer player = getCommandSenderAsPlayer(sender);
        String team = LandManager.getTeam(player).teamName;
        if (!LandManager.getInstance().isAdmin(
                player.getUniqueID())) { throw new CommandException("you need to be a team admin to do that"); }
        LandTeam landTeam = LandManager.getInstance().getTeam(team, false);
        String arg = args[0];
        String message = "";
        if (args.length > 1) message = args[1];
        for (int i = 2; i < args.length; i++)
        {
            message = message + " " + args[i];
        }
        message = RuleManager.format(message);
        if (arg.equalsIgnoreCase("exit"))
        {
            landTeam.exitMessage = message;
            sender.addChatMessage(new TextComponentString(TextFormatting.GREEN + "Set Exit Message to " + message));
            return;
        }
        if (arg.equalsIgnoreCase("enter"))
        {
            landTeam.enterMessage = message;
            sender.addChatMessage(new TextComponentString(TextFormatting.GREEN + "Set Enter Message to " + message));
            return;
        }
        if (arg.equalsIgnoreCase("deny"))
        {
            landTeam.denyMessage = message;
            sender.addChatMessage(new TextComponentString(TextFormatting.GREEN + "Set Deny Message to " + message));
            return;
        }
        if (arg.equalsIgnoreCase("reserve") && ThutEssentials.perms.hasPermission(player, "land.team.reserve"))
        {
            landTeam.reserved = Boolean.parseBoolean(message);
            sender.addChatMessage(
                    new TextComponentString(TextFormatting.GREEN + "reserved set to " + landTeam.reserved));
            return;
        }
        if (arg.equalsIgnoreCase("noPlayerDamage")
                && ThutEssentials.perms.hasPermission(player, "land.team.noplayerdamage"))
        {
            landTeam.noPlayerDamage = Boolean.parseBoolean(message);
            sender.addChatMessage(
                    new TextComponentString(TextFormatting.GREEN + "noPlayerDamage set to " + landTeam.noPlayerDamage));
            return;
        }
        if (arg.equalsIgnoreCase("noMobSpawn") && ThutEssentials.perms.hasPermission(player, "land.team.nomobspawn"))
        {
            landTeam.noMobSpawn = Boolean.parseBoolean(message);
            sender.addChatMessage(
                    new TextComponentString(TextFormatting.GREEN + "noMobSpawn set to " + landTeam.noMobSpawn));
            return;
        }
        if (arg.equalsIgnoreCase("noExplosions")
                && ThutEssentials.perms.hasPermission(player, "land.team.noexplosions"))
        {
            landTeam.noExplosions = Boolean.parseBoolean(message);
            sender.addChatMessage(
                    new TextComponentString(TextFormatting.GREEN + "noExplosions set to " + landTeam.noExplosions));
            return;
        }
    }
}
