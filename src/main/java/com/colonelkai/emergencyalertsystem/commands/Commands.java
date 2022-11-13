package com.colonelkai.emergencyalertsystem.commands;

import org.mose.command.BukkitCommandWrapper;

public class Commands {

    public static final BukkitCommandWrapper COMMANDS =
            new BukkitCommandWrapper(
                    new IssueCommand()
            );
}
