package fr.mleduc.kmf.adaptation.poc.business;

import fr.mleduc.adaptation.PocModel;
import fr.mleduc.kmf.adaptation.poc.validator.InputValidator;
import gol.Cell;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mleduc on 12/04/16.
 */
public class AddBusiness {
    private final ClientBusiness clientBusiness;
    private final InputValidator inputValidator = new InputValidator();
    private final PocModel model;

    public AddBusiness(final ClientBusiness clientBusiness, final PocModel model) {
        this.clientBusiness = clientBusiness;
        this.model = model;
    }

    public void add(String[] nextLine) {
        final List<String> errors = validate(nextLine);

        if (errors.isEmpty()) {
            try {
                proceedAdd(Integer.parseInt(nextLine[1]), Integer.parseInt(nextLine[2]));
            } catch (InterruptedException e) {
                System.out.println("Operation failed");
            }
        } else {
            errors.forEach(System.out::println);
            clientBusiness.help();
        }
    }

    private List<String> validate(String[] nextLine) {
        final List<String> arrayList = new ArrayList<>();
        if (nextLine.length < 3) {
            arrayList.add("wrong command format");
        } else {
            inputValidator.validateInteger(arrayList, nextLine[1], "x");
            inputValidator.validateInteger(arrayList, nextLine[2], "y");
        }
        return arrayList;
    }

    private void proceedAdd(final int x, final int y) throws InterruptedException {
        final Cell cell = model.createCell(0, System.currentTimeMillis());
        cell.setX(x);
        cell.setY(y);
        System.out.println(cell);
        model.save(o -> System.out.println("Saved"));
    }
}
