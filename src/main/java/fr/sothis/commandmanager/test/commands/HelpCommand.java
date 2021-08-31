package fr.sothis.commandmanager.test.commands;

import fr.sothis.commandmanager.commands.Command;

import java.util.List;

public class HelpCommand extends Command {

    public HelpCommand() {
        super("help");

        this.setDescription("Display help menu");
    }

    @Override
    public void execute(String[] args) {
        this.getParent().ifPresent(parent -> {
            System.out.println("Help for command /" + parent.getName() + " :");

            System.out.println(parent.getName() + " " + parent.getUsage() + " : " + parent.getDescription());

            parent.getSubCommands().forEach(subcommand -> {
                System.out.println(subcommand.getName() + " " + subcommand.getUsage() + " : " + subcommand.getDescription());
            });
        });
    }
}
