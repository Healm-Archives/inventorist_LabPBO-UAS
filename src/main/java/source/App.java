package source;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * JavaFX App
 * aplikasi inventaris barang
 */

//  SET GLOBAL max_allowed_packet=1073741824;

public class App extends Application {
    public static ObservableList<Barang> barangList = FXCollections.observableArrayList();
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Db.dbConn();
        scene = new Scene(getLoader("menu").load(), 640, 480);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Inventory");
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(getLoader(fxml).load());
    }
    
    static void setRootWithParent(Parent parent) throws IOException {
        scene.setRoot(parent);
    }

    private static FXMLLoader getLoader(String fxml) throws IOException {
        return new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    }
    public static void main(String[] args) {
        launch();
    }

}