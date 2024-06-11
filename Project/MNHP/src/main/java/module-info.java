module hp.mnhp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires atlantafx.base;
    requires static lombok;
    requires java.prefs;
    requires org.kordamp.ikonli.javafx;
    requires java.desktop;
    requires org.apache.commons.io;


    opens hp.mnhp to javafx.fxml;
    opens Model to javafx.base;


    exports hp.mnhp;
}