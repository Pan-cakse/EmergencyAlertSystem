package com.colonelkai.emergencyalertsystem.commands;

import com.colonelkai.emergencyalertsystem.EmergencyAlertSystem;
import com.colonelkai.emergencyalertsystem.eas_type.EASType;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;
import org.mose.command.ArgumentCommand;
import org.mose.command.CommandArgument;
import org.mose.command.arguments.operation.AnyArgument;
import org.mose.command.arguments.operation.ExactArgument;
import org.mose.command.arguments.operation.RemainingArgument;
import org.mose.command.arguments.simple.text.StringArgument;
import org.mose.command.context.CommandContext;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class IssueCommand implements ArgumentCommand {
    private final ExactArgument ISSUE_ARGUMENT = new ExactArgument("issue");

    private final AnyArgument<EASType> EAS_TYPE_ARGUMENT = new AnyArgument<EASType>(
            "argument",
            EASType::getName,
            (c, s) -> c.parallelStream().filter(t -> t.getName() == s).findFirst().get(),
            EmergencyAlertSystem.getConfigManager().getAllEASTypesFromCache());

    private final RemainingArgument<String> REMAINING_ARGUMENT = new RemainingArgument<>(new StringArgument("message"));
    @Override
    public @NotNull List<CommandArgument<?>> getArguments() {
        return Arrays.asList(
                ISSUE_ARGUMENT,
                EAS_TYPE_ARGUMENT,
                REMAINING_ARGUMENT
        );
    }

    @Override
    public @NotNull String getDescription() {
        return "Issue an EAS warning.";
    }

    @Override
    public @NotNull Optional<String> getPermissionNode() {
        return Optional.empty();
    }

    @Override
    public boolean run(CommandContext commandContext, String... args) {
        EASType easType = commandContext.getArgument(this, EAS_TYPE_ARGUMENT);
        List<String> list = commandContext.getArgument(this, REMAINING_ARGUMENT);
        String string = String.join(" ", list);

        // TODO LONG MESSAGE
        String broadcastMessage =
                ChatColor.RED +
                String.join("\n", easType.getLongMessages())
                + "\n" + ChatColor.ITALIC +
                string
                ;

        // SHORT MESSAGE
        String broadcastMessageShort =
                ChatColor.RED +
                        easType.getShortMessage()
                        + ChatColor.ITALIC +
                        string;

        // SOUNDS TO PLAYERS
        EmergencyAlertSystem.getPlugin().getServer().getOnlinePlayers()
                .parallelStream()
                .forEach(p -> {
                    p.playSound(p.getLocation(), easType.getSound(), easType.getVolume(), easType.getPitch());
                });

        // HOTBAR MESSAGE
        int task2 = EmergencyAlertSystem.getPlugin().getServer().getScheduler().scheduleSyncRepeatingTask(EmergencyAlertSystem.getPlugin(), new Runnable(){
            public void run(){
                EmergencyAlertSystem.getPlugin().getServer().getOnlinePlayers()
                        .parallelStream()
                        .forEach(p -> {
                            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(broadcastMessageShort));
                        });
            }
        }, 0, 20);

        EmergencyAlertSystem.getPlugin().getServer().getScheduler().scheduleSyncDelayedTask(EmergencyAlertSystem.getPlugin(), new Runnable() {
            @Override
            public void run() {
                EmergencyAlertSystem.getPlugin().getServer().getScheduler().cancelTask(task2);
            }
        }, easType.getSoundLength());

        return true;
    }
}
