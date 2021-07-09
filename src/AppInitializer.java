import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.Properties;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        AnchorPane root = FXMLLoader.load(this.getClass().getResource("/view/EditorForm.fxml"));
        Scene mainScene = new Scene(root);
        primaryStage.setScene(mainScene);

        Properties properties= new Properties();
        File file = new File("textEditor.properties");

        if (file.exists()){
            try (FileReader fileReader = new FileReader(file);
                 BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                properties.load(bufferedReader);

                primaryStage.setWidth(Double.parseDouble(properties.getProperty("width")));
                primaryStage.setHeight(Double.parseDouble(properties.getProperty("height")));
                primaryStage.setX(Double.parseDouble(properties.getProperty("x")));
                primaryStage.setY(Double.parseDouble(properties.getProperty("y")));
            } catch (NumberFormatException e){
                primaryStage.setMaximized(true);
            }
        }else {
            primaryStage.setMaximized(true);
        }

        primaryStage.setOnCloseRequest(event -> {
            properties.setProperty("width", String.valueOf(primaryStage.getWidth()));
            properties.setProperty("height", String.valueOf(primaryStage.getHeight()));
            properties.setProperty("x", String.valueOf(primaryStage.getX()));
            properties.setProperty("y", String.valueOf(primaryStage.getY()));

            try (FileWriter fileWriter = new FileWriter(file);
                 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                properties.store(bufferedWriter, "Window Properties");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        primaryStage.setTitle("Text Editor");
        primaryStage.show();
    }
}
