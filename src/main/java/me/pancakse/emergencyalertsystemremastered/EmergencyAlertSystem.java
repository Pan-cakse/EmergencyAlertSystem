package me.pancakse.emergencyalertsystemremastered;

import me.pancakse.emergencyalertsystemremastered.commands.Commands;
import me.pancakse.emergencyalertsystemremastered.configloader.ConfigManager;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class EmergencyAlertSystem extends JavaPlugin {
    private static JavaPlugin plugin;
    private static ConfigManager configManager;
    @Override
    public void onEnable() {

        // Plugin startup logic
        EmergencyAlertSystem.plugin = this;
        EmergencyAlertSystem.getPlugin().getLogger().info("Starting EAS...");
        EmergencyAlertSystem.configManager = new ConfigManager();
        EmergencyAlertSystem.configManager.getAllEASTypesFromConfig();
        PluginCommand command = this.getCommand("emergency-alert-system");
        if (null == command) {
            throw new RuntimeException("'emergency-alert-system' command not found in plugin.yml. Has this been modified?");
        }
        command.setExecutor(Commands.COMMANDS);
        command.setTabCompleter(Commands.COMMANDS);
    }

    public static JavaPlugin getPlugin() {
        return EmergencyAlertSystem.plugin;
    }

    public static ConfigManager getConfigManager() {
        return EmergencyAlertSystem.configManager;
    }
}
