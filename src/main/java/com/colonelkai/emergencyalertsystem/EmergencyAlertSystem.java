package com.colonelkai.emergencyalertsystem;

import com.colonelkai.emergencyalertsystem.commands.Commands;
import com.colonelkai.emergencyalertsystem.configloader.ConfigManager;
import com.colonelkai.emergencyalertsystem.eas_type.EASType;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;

public final class EmergencyAlertSystem extends JavaPlugin {
    private static JavaPlugin plugin;
    private static ConfigManager configManager;
    @Override
    public void onEnable() {

        // Plugin startup logic
        EmergencyAlertSystem.plugin = this;
        EmergencyAlertSystem.getPlugin().getLogger().info("Starting EAS...");
        EmergencyAlertSystem.configManager = new ConfigManager();
        Set<EASType> easTypeSet = EmergencyAlertSystem.configManager.getAllEASTypesFromConfig();
        PluginCommand command = this.getCommand("emergency-alert-system");
        assert command != null;
        command.setExecutor(Commands.COMMANDS);
        command.setTabCompleter(Commands.COMMANDS);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static JavaPlugin getPlugin() {
        return EmergencyAlertSystem.plugin;
    }

    public static ConfigManager getConfigManager() {
        return EmergencyAlertSystem.configManager;
    }
}
