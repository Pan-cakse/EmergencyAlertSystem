package com.colonelkai.emergencyalertsystem;

import com.colonelkai.emergencyalertsystem.commands.Commands;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class EmergencyAlertSystem extends JavaPlugin {
    private static JavaPlugin plugin;
    @Override
    public void onEnable() {
        // Plugin startup logic
        EmergencyAlertSystem.plugin = this;
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
}
