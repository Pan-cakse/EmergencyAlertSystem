package me.pancakse.emergencyalertsystemremastered.commands;

import me.pancakse.emergencyalertsystemremastered.EmergencyAlertSystem;
import me.pancakse.emergencyalertsystemremastered.eastype.EASType;
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

public class IssueCommand implements ArgumentCommand {
    private static final ExactArgument ISSUE_ARGUMENT = new ExactArgument("issue");

    private static final AnyArgument<EASType> EAS_TYPE_ARGUMENT = new AnyArgument<>(
            "argument",
            EASType::getName,
            (c, s) -> c.parallelStream().filter(t -> t.getName().equalsIgnoreCase(s)).findFirst().orElse(null),
            (context, argumentContext) -> EmergencyAlertSystem.getConfigManager().getAllEASTypesFromCache());

    private static final RemainingArgument<String> REMAINING_ARGUMENT = new RemainingArgument<>(new StringArgument("message"));
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
        String message = String.join(" ", list);

        // CHECK PERMISSION
        if(!commandContext.getSource().hasPermission(easType.getPermission())) {
            commandContext.getSource().sendMessage(ChatColor.RED + "You lack the following permission to issue the EAS warning: ");
            return true;
        }

        String broadcastMessage =
                ChatColor.RED +
                String.join("\n", easType.getLongMessages())
                + '\n' + ChatColor.ITALIC +
                message;

        EmergencyAlertSystem.getPlugin().getServer().broadcastMessage(broadcastMessage);

        // SHORT MESSAGE
        String broadcastMessageShort =
                ChatColor.RED +
                        easType.getShortMessage()
                        + ChatColor.ITALIC + ' ' +
                        message;

        // SOUNDS TO PLAYERS
        EmergencyAlertSystem.getPlugin().getServer().getOnlinePlayers()
                .parallelStream()
                .forEach(p -> p.playSound(p.getLocation(), easType.getSound(), easType.getVolume(), easType.getPitch()));

        // HOT-BAR MESSAGE

        EmergencyAlertSystem.getPlugin().getServer().getOnlinePlayers()
        .parallelStream()
        .forEach(p -> p.sendTitle(" ", broadcastMessageShort, 10, (easType.getSoundLength() * 20) ,20));



        return true;
    }
}
