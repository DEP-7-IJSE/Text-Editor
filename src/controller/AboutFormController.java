/*
 * Copyright (c) 2021 - present Pethum Jeewantha. All rights reserved.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 */

package controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AboutFormController {
    public AnchorPane pneAbout;

    public void OnClick_btnOK(ActionEvent actionEvent) {
        Stage stage = (Stage) pneAbout.getScene().getWindow();
        stage.close();
    }
}
