package net.diversionmc.modding;

import com.mojang.authlib.yggdrasil.response.HasJoinedMinecraftServerResponse;
import net.diversionmc.modding.commands.Gamemode;
import net.diversionmc.modding.utils.DEPermissions;
import net.diversionmc.modding.utils.DEStringParser;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static net.diversionmc.modding.DogaEssentials.*;

@Mod.EventBusSubscriber
@Mod(modid = MODID, version = VERSION, name = NAME, acceptedMinecraftVersions = MCVersion)
public class DogaEssentials {

    public static final String MODID = "dogaessentials";
    public static final String VERSION = "1.0";
    public static final String NAME = "DogaEssentials";
    public static final String MCVersion = "[1.12.1]";

    @Mod.Instance(MODID)
    public static DogaEssentials instance;

    @Mod.EventHandler
    public static void onPreInit(FMLPreInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(new DogaEssentials());
    }

    @SubscribeEvent
    public static void onServerStart(FMLServerStartingEvent e){
        e.registerServerCommand(new Gamemode());
    }

    @SubscribeEvent
    public static void onWorldJoin(EntityJoinWorldEvent e){
        if(!(e.getEntity() instanceof EntityPlayerMP)) return;
        EntityPlayerMP p = (EntityPlayerMP) e.getEntity();
        p.sendMessage(new DEStringParser("joinMessage", DEStringParser.DETranslationTypes.CONFIG_MAIN).toMinecraft());
        if(1 < p.getServer().getPlayerList().getOppedPlayers().getPermissionLevel(p.getGameProfile())) DEPermissions.setOp(p);
    }


}
