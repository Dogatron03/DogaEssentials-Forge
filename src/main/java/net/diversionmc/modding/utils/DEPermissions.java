package net.diversionmc.modding.utils;

import net.minecraft.entity.player.EntityPlayerMP;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class DEPermissions {

    private static HashMap<UUID, List<String>> perms;

    public static boolean checkPermission(EntityPlayerMP p, String permission) {
        if (hasPermission(p, permission)) return true;
        p.sendMessage(new DEStringParser("noPermission", "&cYou do not have enough permissions!", DEStringParser.DETranslationTypes.LANG_COMMANDS).toMinecraft());
        return false;
    }

    private static boolean hasPermission(EntityPlayerMP p, String permission) {
        if (!perms.containsKey(p.getUniqueID())) return false;
        if(perms.get(p.getUniqueID()).contains("*")) return true;
        return perms.get(p.getUniqueID()).contains(permission.toLowerCase());
    }

    public static void setOp(EntityPlayerMP p){
        if(!perms.containsKey(p.getUniqueID())) return;
        if(perms.get(p.getUniqueID()).contains("*")) return;
        perms.get(p.getUniqueID()).add("*");
    }

    public static void initPermissions(EntityPlayerMP p){

    }

}
