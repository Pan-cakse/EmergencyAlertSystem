package com.colonelkai.emergencyalertsystem.commands;

import org.jetbrains.annotations.NotNull;
import org.mose.command.ArgumentCommand;
import org.mose.command.CommandArgument;
import org.mose.command.context.CommandContext;

import java.util.List;
import java.util.Optional;

public class IssueCommand implements ArgumentCommand {
    @Override
    public @NotNull List<CommandArgument<?>> getArguments() {
        return null;
    }

    @Override
    public @NotNull String getDescription() {
        return null;
    }

    @Override
    public @NotNull Optional<String> getPermissionNode() {
        return Optional.empty();
    }

    @Override
    public boolean run(CommandContext commandContext, String... args) {
        return false;
    }
}
