package fr.sothis.commandmanager.test.commands;

import fr.sothis.commandmanager.commands.Command;

public class TestCommand extends Command {

    public TestCommand() {
        super("test");

        this.setDescription("This is the main command");
        this.addRequiredArgs("number");
        this.addSubCommand(new RandomCommand(), new HelpCommand());
    }

    @Override
    public void execute(String[] args) {
        try {
            int number = Integer.parseInt(args[0]);

            System.out.println(number);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number!");
        }
    }
}
