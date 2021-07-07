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

                String width = properties.getProperty("width");
                String height = properties.getProperty("height");
                String x = properties.getProperty("x");
                String y = properties.getProperty("y");

                primaryStage.setWidth(Double.parseDouble(width));
                primaryStage.setHeight(Double.parseDouble(height));
                primaryStage.setX(Double.parseDouble(x));
                primaryStage.setY(Double.parseDouble(y));
            }
        }else {
            primaryStage.setMaximized(true);
        }

        primaryStage.setTitle("Text Editor");
        primaryStage.show();
    }
}
