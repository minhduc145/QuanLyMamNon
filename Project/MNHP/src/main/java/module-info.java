module hp.mnhp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires atlantafx.base;
    requires static lombok;


    opens hp.mnhp to javafx.fxml;
    exports hp.mnhp;
}