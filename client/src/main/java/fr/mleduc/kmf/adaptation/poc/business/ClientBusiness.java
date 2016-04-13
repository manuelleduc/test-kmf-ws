package fr.mleduc.kmf.adaptation.poc.business;

import fr.mleduc.adaptation.PocModel;

import java.util.Scanner;

/**
 * Created by mleduc on 12/04/16.
 */
public class ClientBusiness {
    private final AddBusiness addBusiness;
    final Scanner scanner = new Scanner(System.in);

    public ClientBusiness(final PocModel model) {
        addBusiness = new AddBusiness(this, model);
    }

    public boolean read() {
        final String inputLine = prompt();
        final String[] splittedLine = inputLine.split("\\s+");
        final boolean stop;
        if (splittedLine.length == 0) {
            help();
            stop = false;
        } else {
            final String command = splittedLine[0].toLowerCase();
            stop = readCommand(splittedLine, command);
        }
        return stop;
    }

    private String prompt() {
        System.out.println("Operation : ");
        return scanner.nextLine();
    }

    private boolean readCommand(final String[] nextLine, final String command) {
        boolean stop = false;
        switch (command) {
            case "help":
                help();
                break;
            case "exit":
                stop = true;
                break;
            case "add":
                addBusiness.add(nextLine);
                break;
            default:
                System.out.println("Unknown command. Type \"help\" to display the help.");
        }
        return stop;
    }

    void help() {
        System.out.println("help    : Show this help.");
        System.out.println("add x y : Add a cell to the model. x and y are integers.");
        System.out.println("exit    : Stop the client.");
    }
}
