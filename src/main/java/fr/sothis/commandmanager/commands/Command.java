package fr.sothis.commandmanager.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Command {

    private final List<Command> subCommands = new ArrayList<>();
    private final List<String> aliases = new ArrayList<>();
    private final List<String> requiredArgsNames = new ArrayList<>();
    private final List<String> optionalArgsNames = new ArrayList<>();
    private final String description = "";
    private Command parent;

    public String getUsage() {
        StringBuilder builder = new StringBuilder();

        this.requiredArgsNames.forEach(argName -> builder.append("<").append(argName).append(">").append(" "));
        this.optionalArgsNames.forEach(argName -> builder.append("<").append(argName).append(">").append(" "));

        return builder.toString();
    }

    public void internallyExecute(String[] args) {
        if (this.subCommands.size() > 0) {
            // Check for subcommands
            if (args.length > 0) {
                String firstArg = args[0];

                Optional<Command> subCommand = this.subCommands.stream()
                        .filter(command -> command.getAliases().contains(firstArg))
                        .findFirst();

                if (subCommand.isPresent()) {    // Can a subcommand be executed
                    // Yes
                    subCommand.get().internallyExecute(args.length > 1 ? Arrays.copyOfRange(args, 1, args.length) : new String[0]);
                    return;
                }

                // No, so we execute this command
            }
        }

        if (args.length < this.requiredArgsNames.size()) {
            return;
        }

        this.execute(args);
    }
    
    public void execute(String[] args) {
        
    }

    public void addSubCommand(Command... subCommands) {
        for (Command subCommand : subCommands) {
            this.subCommands.add(subCommand);
            subCommand.setParent(this);
        }
    }

    public void addAliases(String... aliases) {
        this.aliases.addAll(Arrays.asList(aliases));
    }

    public void addRequiredArgs(String... requiredArgs) {
        this.requiredArgsNames.addAll(Arrays.asList(requiredArgs));
    }

    public void addOptionalArgs(String... optionalArgs) {
        this.optionalArgsNames.addAll(Arrays.asList(optionalArgs));
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setParent(Command parent) {
        this.parent = parent;
    }

    public List<Command> getSubCommands() {
        return subCommands;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public List<String> getOptionalArgsNames() {
        return optionalArgsNames;
    }

    public List<String> getRequiredArgsNames() {
        return requiredArgsNames;
    }

    public String getDescription() {
        return description;
    }

    public Optional<Command> getParent() {
        return this.parent == null ? Optional.empty() : Optional.of(this.parent);
    }
}
