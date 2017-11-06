package net.diversionmc.modding.commands;

import net.diversionmc.modding.utils.DEPermissions;
import net.diversionmc.modding.utils.DEStringParser;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameType;

public class Gamemode extends CommandBase {
    @Override
    public String getName() {
        return "gamemode";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return new DEStringParser("gamemode.usage", DEStringParser.DETranslationTypes.LANG_COMMANDS).get();
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        EntityPlayerMP p = getCommandSenderAsPlayer(sender);
        if(args.length <= 0) throw new WrongUsageException(getUsage(sender));
        if(args.length == 1) {
            if(!DEPermissions.checkPermission(p, "dogaessentials.commands.self")) return;
            p.setGameType(GameType.parseGameTypeWithDefault(args[0], GameType.NOT_SET));
            p.sendMessage(new DEStringParser("gamemode.success." + GameType.parseGameTypeWithDefault(args[0], GameType.NOT_SET).getID(), DEStringParser.DETranslationTypes.LANG_COMMANDS).toMinecraft());
        } else {
            if(server.isSinglePlayer()) {
                p.sendMessage(new DEStringParser("singlePlayer", DEStringParser.DETranslationTypes.LANG_COMMANDS).toMinecraft());
                return;
            }
            if(!DEPermissions.checkPermission(p, "dogaessentials.commands.self")) return;
            EntityPlayerMP target = server.getPlayerList().getPlayerByUsername(args[1]);
            if(target == null) {
                p.sendMessage(new DEStringParser("invalidPlayer", DEStringParser.DETranslationTypes.LANG_COMMANDS).toMinecraft());
                return;
            }
            target.setGameType(GameType.parseGameTypeWithDefault(args[0], GameType.NOT_SET));
            p.sendMessage(new DEStringParser("gamemode.success.other." + GameType.parseGameTypeWithDefault(args[0], GameType.NOT_SET).getID(), DEStringParser.DETranslationTypes.LANG_COMMANDS).toMinecraft());
        }
    }
}
