package fr.sothis.commandmanager.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CommandManager {

    private final List<Command> commandsRegistry = new ArrayList<>();

    public void addCommand(Command command) {
        this.commandsRegistry.add(command);
    }

    public void listenForInput() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String nextLine = scanner.nextLine();

            String[] input = nextLine.split(" ");

            if (input.length > 0) {
                String commandName = input[0];

                this.commandsRegistry.stream()
                        .filter(command -> command.getAliases().contains(commandName) || command.getName().equalsIgnoreCase(commandName))
                        .findFirst()
                        .ifPresentOrElse(command -> command.internallyExecute(input.length > 1 ? Arrays.copyOfRange(input, 1, input.length) : new String[0]),
                                () -> System.out.println("Unknown command!"));
            } else {
                System.out.println("Unknown command!");
            }
        }
    }
}
