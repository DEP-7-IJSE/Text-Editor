package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class MainFormController {
    public TextField txtIntegersOnly;
    public TextField txtNumbersOnly;
    public TextField txtNIC;

    public void btnValidateIntegersOnly_OnAction(ActionEvent actionEvent) {
//        try {
//            Integer.parseInt(txtIntegersOnly.getText());
//            new Alert(Alert.AlertType.INFORMATION, "Valid").show();
//        }catch (NumberFormatException e){
//            new Alert(Alert.AlertType.ERROR, "Invalid").show();
//        }

        String input = txtIntegersOnly.getText();
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) {
                new Alert(Alert.AlertType.ERROR, "Invalid").show();
                return;
            }
        }
        new Alert(Alert.AlertType.INFORMATION, "Valid").show();
    }

    public void btnValidateNumbersOnly_OnAction(ActionEvent actionEvent) {
//        try {
//            Double.parseDouble(txtNumbersOnly.getText());
//            if (txtNumbersOnly.getText().endsWith("d") || txtNumbersOnly.getText().endsWith("f")
//                    || txtNumbersOnly.getText().contains("e")) {
//                throw new NumberFormatException();
//            }
//            new Alert(Alert.AlertType.INFORMATION, "Valid").show();
//        } catch (NumberFormatException e) {
//            new Alert(Alert.AlertType.ERROR, "Invalid").show();
//        }

        char[] chars = txtNumbersOnly.getText().toCharArray();

        if (!(chars[0] == '+' || chars[0] == '-' || chars[0] == '.' || Character.isDigit(chars[0]))){
            new Alert(Alert.AlertType.ERROR, "Invalid").show();
            return;
        }
        int i = 0;
        int periodCount = 0;

        for (char aChar : chars) {

            if (i++ == 0) continue;

            if (aChar == '.'){
                periodCount++;
            }

            if (periodCount > 1){
                new Alert(Alert.AlertType.ERROR, "Invalid").show();
                return;
            }

            if (!(Character.isDigit(aChar) || aChar == '.')){
                new Alert(Alert.AlertType.ERROR, "Invalid").show();
                return;
            }
        }
        new Alert(Alert.AlertType.INFORMATION, "Valid").show();
    }

    public void btnValidateNIC_OnAction(ActionEvent actionEvent) {
        String input = txtNIC.getText();

        if (!(input.length() == 10 || input.length() == 12)){
            new Alert(Alert.AlertType.ERROR, "Invalid NIC").show();
            return;
        }
        
        if (input.startsWith("+") || input.startsWith("-")){
            new Alert(Alert.AlertType.ERROR, "Invalid NIC").show();
            return;
        }

        /* Validating old nic */
        if (input.length() == 10){
            if (isInteger(input.substring(0,9)) || input.endsWith("V") || input.endsWith("v")){
                new Alert(Alert.AlertType.INFORMATION, "Valid Old NIC", ButtonType.OK).show();
                return;
            }
        }else if (isInteger(input)){
            new Alert(Alert.AlertType.INFORMATION, "Valid New NIC", ButtonType.OK).show();
            return;
        }

        new Alert(Alert.AlertType.ERROR, "Invalid NIC").show();
    }

    private boolean isInteger(String input){
        try {
            Long.parseLong(input);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}
