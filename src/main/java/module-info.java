module source {
    requires javafx.base;
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive javafx.graphics;

    requires java.sql;

    opens source to javafx.fxml;
    exports source;
}
