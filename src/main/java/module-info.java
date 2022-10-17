module se233.chapter6_tdd {
    requires javafx.controls;
    requires javafx.swing;
    requires javafx.fxml;
    requires junit;
    requires org.slf4j;
    requires org.apache.logging.log4j;


    opens se233.chapter6_tdd to javafx.fxml;
    exports se233.chapter6_tdd;
}