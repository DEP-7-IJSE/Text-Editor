package controller;

import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import util.FXUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class EditorFormController {
    private final List<Index> searchList = new ArrayList<>();
    public TextArea txtEditor;
    public AnchorPane pneFind;
    public TextField txtSearch;
    public AnchorPane pneReplace;
    public TextField txtSearch1;        // This is inside the pneReplace
    public TextField txtReplace;
    private int findOffset = -1;

    public void initialize() {
        pneFind.setOpacity(0);
        pneReplace.setOpacity(0);

        ChangeListener textListener = (ChangeListener<String>) (observable, oldValue, newValue) -> {
            searchMatches(newValue);
        };

        txtSearch.textProperty().addListener(textListener);
        txtSearch1.textProperty().addListener(textListener);
    }

    private void searchMatches(String query) {
        FXUtil.highlightOnTextArea(txtEditor, query, Color.web("yellow", 0.8));

        try {
            Pattern regExp = Pattern.compile(query);
            Matcher matcher = regExp.matcher(txtEditor.getText());

            searchList.clear();

            while (matcher.find()) {
                searchList.add(new Index(matcher.start(), matcher.end()));
            }

            if (searchList.isEmpty()) {
                findOffset = -1;
            }
        } catch (PatternSyntaxException e) {

        }
    }

    public void mnuItemNew_OnAction(ActionEvent actionEvent) {
        txtEditor.clear();
        txtEditor.requestFocus();
    }

    public void mnuItemExit_OnAction(ActionEvent actionEvent) {
    }

    public void mnuItemFind_OnAction(ActionEvent actionEvent) {
        findOffset = -1;
        if (pneReplace.getOpacity()==1) {
            pneReplace.setOpacity(0);
        }
        FadeTransition fadeTransition= new FadeTransition(Duration.millis(250),pneFind);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
        txtSearch.requestFocus();
    }

    public void mnuItemReplace_OnAction(ActionEvent actionEvent) {
        findOffset = -1;
        if (pneFind.getOpacity()==1) {
            pneFind.setOpacity(0);
        }
        FadeTransition fadeTransition= new FadeTransition(Duration.millis(250),pneReplace);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
        txtSearch1.requestFocus();
    }

    public void mnuItemSelectAll_OnAction(ActionEvent actionEvent) {
        txtEditor.selectAll();
    }

    public void btnFindNext_OnAction(ActionEvent actionEvent) {
        if (!searchList.isEmpty()) {
            findOffset++;
            if (findOffset >= searchList.size()) {
                findOffset = 0;
            }
            txtEditor.selectRange(searchList.get(findOffset).startingIndex, searchList.get(findOffset).endIndex);
        }
    }

    public void btnFindPrevious_OnAction(ActionEvent actionEvent) {
        if (!searchList.isEmpty()) {
            findOffset--;
            if (findOffset < 0) {
                findOffset = searchList.size() - 1;
            }
            txtEditor.selectRange(searchList.get(findOffset).startingIndex, searchList.get(findOffset).endIndex);
        }
    }

    public void btnReplaceAll_OnAction(ActionEvent actionEvent) {
/*        try {
            String replacedText = Pattern.compile(txtSearch1.getText()).matcher(txtEditor.getText()).replaceAll(txtReplace.getText());
            txtEditor.setText(replacedText);
        }catch (PatternSyntaxException e){
            // There is nothing to do here
        }

        for (int i = 0; i< searchList.size(); i++) {
            txtEditor.replaceText(searchList.get(i).startingIndex, searchList.get(i).endIndex, txtReplace.getText());
            searchMatches(txtSearch1.getText());
            i=-1;
        }*/

        while (!searchList.isEmpty()) {
            txtEditor.replaceText(searchList.get(0).startingIndex, searchList.get(0).endIndex, txtReplace.getText());
            searchMatches(txtSearch1.getText());
        }
    }

    public void btnReplace_OnAction(ActionEvent actionEvent) {
        if (findOffset == -1) return;
        txtEditor.replaceText(searchList.get(findOffset).startingIndex, searchList.get(findOffset).endIndex, txtReplace.getText());
        searchMatches(txtSearch1.getText());
    }

}

class Index {
    int startingIndex;
    int endIndex;

    public Index(int startingIndex, int endIndex) {
        this.startingIndex = startingIndex;
        this.endIndex = endIndex;
    }
}
