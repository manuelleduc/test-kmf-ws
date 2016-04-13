package fr.mleduc.kmf.adaptation.poc.validator;

import java.util.List;

/**
 * Created by mleduc on 12/04/16.
 */
public class InputValidator {
    public void validateInteger(List<String> arrayList, String s, String x) {
        try {
            Long.parseLong(s);
        } catch (NumberFormatException e) {
            arrayList.add(x + " is not an integer");
        }
    }
}
