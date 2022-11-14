package com.colonelkai.emergencyalertsystem.configloader;

import com.colonelkai.emergencyalertsystem.EmergencyAlertSystem;
import com.colonelkai.emergencyalertsystem.eastype.EASType;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Collectors;

public class ConfigManager {

    private YamlConfiguration config;
    private Collection<EASType> easTypeSet;

    public ConfigManager() {
        this.onEnable();
    }

    private void onEnable() {
        File configFile = new File(EmergencyAlertSystem.getPlugin().getDataFolder(), "config.yml");

        if(!configFile.exists()) { // if config does not exist, thou shall create
            if(!configFile.getParentFile().exists()) {
                if (!configFile.getParentFile().mkdir()) {
                    EmergencyAlertSystem.getPlugin().getLogger().warning("Failed to mkdir default config's parent directory.");
                    throw new RuntimeException("Failed to mkdir default config's parent directory.");
                }
            }

            InputStream defaultConfigInputStream = EmergencyAlertSystem.getPlugin().getResource("config.yml");

            try {
                if (null == defaultConfigInputStream) {
                    throw new RuntimeException("Cannot find config.yml located inside the plugin. Has this been modified?");
                }
                Files.copy(
                        defaultConfigInputStream,
                        configFile.toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException("Failed to create default config.", e);
            }

            try {
                defaultConfigInputStream.close();
            } catch (IOException e) {
                throw new RuntimeException("Failed to close default config InputStream.", e);
            }

        }

        this.setConfig(YamlConfiguration.loadConfiguration(configFile));
        EmergencyAlertSystem.getPlugin().getLogger().info("Loaded Config.");
    }

    public void getAllEASTypesFromConfig() {
        Set<String> keys = this.getConfig().getKeys(false);

        EmergencyAlertSystem.getPlugin().getLogger().info("Attempting to load: " + keys);

        this.easTypeSet = (keys.parallelStream().map(k -> new EASType(
                k,
                this.getConfig().getString(k+".permission"),
                this.getConfig().getString(k+".sound"),
                this.getConfig().getString(k+".short-message"),
                this.getConfig().getStringList(k+".long-message"),
                this.getConfig().getInt(k+".volume"),
                this.getConfig().getInt(k+".pitch"),
                this.getConfig().getInt(k+".sound-length")
        )).collect(Collectors.toSet()));

        EmergencyAlertSystem.getPlugin().getLogger().info("Loaded: " +
                  this.getEasTypeSet().parallelStream().map(EASType::getName).toList()
        );

    }

    public Collection<EASType> getAllEASTypesFromCache() {
        return this.getEasTypeSet();
    }

    public YamlConfiguration getConfig() {
        return config;
    }

    public Collection<EASType> getEasTypeSet() {
        return easTypeSet;
    }
}
