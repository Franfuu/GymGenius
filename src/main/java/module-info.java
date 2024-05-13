module com.github.Franfuu {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.xml.bind;

    opens com.github.Franfuu to javafx.fxml;
    opens com.github.Franfuu.model.connection to java.xml.bind;

    exports com.github.Franfuu;
    exports com.github.Franfuu.utils;
    opens com.github.Franfuu.utils to javafx.fxml;
    exports com.github.Franfuu.view;
    opens com.github.Franfuu.view to javafx.fxml;

}
