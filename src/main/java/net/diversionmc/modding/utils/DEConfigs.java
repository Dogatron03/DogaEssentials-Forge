package net.diversionmc.modding.utils;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class DEConfigs {

    public static final String CATEGORY_LANG_COMMANDS = "commands";
    public static final String CATEGORY_CONFIG_MAIN = "main";
    public static Configuration language;
    public static Configuration config;
    public static Configuration permissions;

    public static void setup() {

        //Lang Config File
        File langFile = new File(Loader.instance().getConfigDir(), "language.yml");
        if (!langFile.exists()) {
            try {
                langFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            langFile = firstTimeSetupLang();
        }
        language = new Configuration(langFile);
        language.load();

        //Main Config File
        File cfgFile = new File(Loader.instance().getConfigDir(), "config.yml");
        if (!cfgFile.exists()) {
            try {
                cfgFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            cfgFile = firstTimeSetupConfig();
        }
        config = new Configuration(cfgFile);
        config.load();
        
        //Permissions Config File
        File permFile = new File(Loader.instance().getConfigDir(), "permissions.yml");
        if (!permFile.exists()) {
            try {
                permFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            permFile = firstTimeSetupPermissions();
        }
        permissions = new Configuration(permFile);
        permissions.load();
    }

    public static File firstTimeSetupLang() {
        String url = "https://raw.githubusercontent.com/Dogatron03/DogaEssentials-Forge/master/language.yml";
        try {
            URL u = new URL(url);
            return new File(u.getFile());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static File firstTimeSetupPermissions() {
        String url = "https://raw.githubusercontent.com/Dogatron03/DogaEssentials-Forge/master/permissions.yml";
        try {
            URL u = new URL(url);
            return new File(u.getFile());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static File firstTimeSetupConfig() {
        String url = "https://raw.githubusercontent.com/Dogatron03/DogaEssentials-Forge/master/config.yml";
        try {
            URL u = new URL(url);
            return new File(u.getFile());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
