package fr.sothis.commandmanager;

import fr.sothis.commandmanager.commands.CommandManager;
import fr.sothis.commandmanager.test.commands.TestCommand;

public class Main {

    public static void main(String[] args) {
        CommandManager commandManager = new CommandManager();

        // Register commands
        commandManager.addCommand(new TestCommand());

        commandManager.listenForInput();
    }

}
