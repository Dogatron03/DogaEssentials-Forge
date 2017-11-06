package net.diversionmc.modding.utils;

import net.minecraft.util.text.TextComponentString;

public class DEStringParser {

    private String translated;
    private String backup;

    public DEStringParser(String s, DETranslationTypes type) {
        backup = "&cDefault Message!";
        if (type == DETranslationTypes.LANG_COMMANDS) {
            translated = getCommandsFromLang(s);
        }
        if (type == DETranslationTypes.CONFIG_MAIN) {
            translated = getConfigObject(s);
        }
    }

    public DEStringParser(String s, String backup, DETranslationTypes type) {
        this.backup = backup;
        if (type == DETranslationTypes.LANG_COMMANDS) {
            translated = getCommandsFromLang(s);
        }
    }

    public String getCommandsFromLang(String s) {
        String ss = DEConfigs.language.get(DEConfigs.CATEGORY_LANG_COMMANDS, s, backup).getString();
        String sss = DEChatColour.translate(ss);
        return sss;
    }

    public String getConfigObject(String s) {
        String ss = DEConfigs.config.get(DEConfigs.CATEGORY_CONFIG_MAIN, s, backup).getString();
        String sss = DEChatColour.translate(ss);
        return sss;
    }

    public TextComponentString toMinecraft() {
        return new TextComponentString(translated);
    }

    public String get() {
        return translated;
    }

    public enum DETranslationTypes {
        LANG_COMMANDS(),
        CONFIG_MAIN();

        DETranslationTypes() {

        }

    }

}
