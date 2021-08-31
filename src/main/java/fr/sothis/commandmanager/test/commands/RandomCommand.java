package fr.sothis.commandmanager.test.commands;

import fr.sothis.commandmanager.commands.Command;

public class RandomCommand extends Command {

    public RandomCommand() {
        super("random");

        this.setDescription("Display some numbers");

        this.addRequiredArgs("number1");
        this.addOptionalArgs("number2");
    }

    @Override
    public void execute(String[] args) {
        try {
            int number1 = Integer.parseInt(args[0]);
            int number2 = 0;
            if (args.length > 1) {
                number2 = Integer.parseInt(args[1]);
            }

            System.out.println(number1 + " " + number2);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number!");
        }
    }
}
