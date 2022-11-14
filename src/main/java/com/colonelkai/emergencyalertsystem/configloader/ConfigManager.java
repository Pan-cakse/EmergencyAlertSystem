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

    YamlConfiguration config;
    Collection<EASType> easTypeSet;

    public ConfigManager() {
        this.onEnable();
    }

    private void onEnable() {
        File configFile = new File(EmergencyAlertSystem.getPlugin().getDataFolder(), "config.yml");

        if(!configFile.exists()) { // if config does not exist, thou shall create
            if(!configFile.getParentFile().mkdir()) {
                EmergencyAlertSystem.getPlugin().getLogger().warning("Failed to mkdir default config's parent directory.");
                throw new RuntimeException("Failed to mkdir default config's parent directory.");
            }

            InputStream defaultConfigInputStream = EmergencyAlertSystem.getPlugin().getResource("config.yml");

            try {
                if (null == defaultConfigInputStream) {
                    throw new AssertionError();
                }
                Files.copy(
                        defaultConfigInputStream,
                        configFile.toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                EmergencyAlertSystem.getPlugin().getLogger().warning("Failed to create default config.");
                throw new RuntimeException(e);
            }

            try {
                defaultConfigInputStream.close();
            } catch (IOException e) {
                EmergencyAlertSystem.getPlugin().getLogger().warning("Failed to close default config InputStream.");
                throw new RuntimeException(e);
            }

        }

        this.config = YamlConfiguration.loadConfiguration(configFile);
        EmergencyAlertSystem.getPlugin().getLogger().info("Loaded Config.");
    }

    public void getAllEASTypesFromConfig() {
        // Set<EASType> easTypeSet = new HashSet<>();

        Set<String> keys = this.config.getKeys(false);

        EmergencyAlertSystem.getPlugin().getLogger().info("Attempting to load: " + keys);

        Collection<EASType> easTypeSet = keys.parallelStream().map(k -> new EASType(
                k,
                this.config.getString(k+".permission"),
                this.config.getString(k+".sound"),
                this.config.getString(k+".short-message"),
                this.config.getStringList(k+".long-message"),
                this.config.getInt(k+".volume"),
                this.config.getInt(k+".pitch"),
                this.config.getInt(k+".sound-length")
        )).collect(Collectors.toSet());

        EmergencyAlertSystem.getPlugin().getLogger().info("Loaded: " +
                        easTypeSet.parallelStream().map(EASType::getName).toList()
                );

        this.easTypeSet = easTypeSet;

    }

    public Collection<EASType> getAllEASTypesFromCache() {
        return this.easTypeSet;
    }

}
