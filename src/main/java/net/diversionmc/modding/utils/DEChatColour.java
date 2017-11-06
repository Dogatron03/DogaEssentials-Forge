package net.diversionmc.modding.utils;

public enum DEChatColour {
    BLACK("§0"),
    DARK_BLUE("§1"),
    DARK_GREEN("§2"),
    CYAN("§3"),
    DARK_RED("§4"),
    DARK_PURPLE("§5"),
    GOLD("§6"),
    LIGHT_GREY("§7"),
    DARK_GREY("§8"),
    BLUE("§9"),
    GREEN("§a"),
    AQUA("§b"),
    RED("§c"),
    PINK("§d"),
    YELLOW("§e"),
    WHITE("§f"),
    OBFUSCATED("§k"),
    BOLD("§l"),
    STRIKETHROUGH("§m"),
    UNDERLINE("§n"),
    ITALIC("§o"),
    RESET("§r");


    private String code;

    private DEChatColour(String code) {
        this.code = code;
    }

    public static String translate(String c, String message) {
        return message.replaceAll(c, "§");
    }

    public static String translate(String message){
        return message.replaceAll("&", "§");
    }

    public String toString() {
        return code;
    }



}
