package com.colonelkai.emergencyalertsystem.commands;

import org.mose.command.BukkitCommandWrapper;

public final class Commands {

    public static final BukkitCommandWrapper COMMANDS =
            new BukkitCommandWrapper(
                    new IssueCommand()
            );

    private Commands() {
        throw new RuntimeException("Called constructor for Utility Class, COMMANDS.JAVA");
    }
}
