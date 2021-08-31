package fr.sothis.commandmanager;

public class Main {

    public static void main(String[] args) {
        CommandManager commandManager = new CommandManager();

        // Register commands
        commandManager.addCommand(new TestCommand());

        commandManager.listenForInput();
    }

}
