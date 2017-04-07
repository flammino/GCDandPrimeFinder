/**
 * SDEV 350
 * Created by Flammino on 4/4/2017.
 * Finds GCD and primes
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Optional;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        /* Define panes */
        BorderPane bPane = new BorderPane();
        VBox topBox = new VBox();
        GridPane leftGrid = new GridPane();
        GridPane rightGrid = new GridPane(); // Needed for margins
        HBox bottomBox = new HBox(15);

        /* Set VBox attributes */
        topBox.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        topBox.setAlignment(Pos.CENTER);

        /* Set HBox attributes */
        bottomBox.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        bottomBox.setAlignment(Pos.CENTER);

        /* Set GridPane Attributes */
        leftGrid.setAlignment(Pos.CENTER_LEFT);
        leftGrid.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        leftGrid.setHgap(5.5);
        leftGrid.setVgap(5.5);
        // leftGrid.setPrefSize(175,175);
        rightGrid.setAlignment(Pos.CENTER_LEFT);
        rightGrid.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        rightGrid.setHgap(5.5);
        rightGrid.setVgap(20);
        //   rightGrid.setPrefWidth(175);

        /* Add panes to BorderPanes, set borderpane attributes */
        bPane.setTop(topBox);
        bPane.setLeft(leftGrid);
        bPane.setRight(rightGrid);
        bPane.setBottom(bottomBox);

        /* Declare top vBox components */
        Text banner = new Text("Common Divisors and Primes");
        banner.setFont(Font.font("Bodoni MT Black", FontWeight.BOLD,
                FontPosture.ITALIC, 24));
        Text instructions = new Text("Enter an integer in each box, then click Execute");
        instructions.setFont(Font.font("Bodoni MT Black", FontPosture.ITALIC, 14));
        topBox.getChildren().add(banner);
        topBox.getChildren().add(instructions);

        /* Add left GridPane components */
        Label lblNum1 = new Label("1st Number:");
        Label lblNum2 = new Label("2nd number: ");
        TextField txtNum1 = new TextField();
        TextField txtNum2 = new TextField();
        leftGrid.add(lblNum1, 0, 0);
        leftGrid.add(lblNum2, 0, 1);
        leftGrid.add(txtNum1, 1, 0);
        leftGrid.add(txtNum2, 1, 1);

        /* Add right GridPane components */
        TextArea txaResults = new TextArea();
        txaResults.setPrefSize(400, 100);
        txaResults.setEditable(false);
        txaResults.setWrapText(true);
        rightGrid.add(txaResults, 0, 0);

        /* Add bottom hBox components */
        Button btnExecute = new Button("Execute");
        Button btnClear = new Button("Clear");
        Button btnExit = new Button("Exit");
        bottomBox.getChildren().add(btnExecute);
        bottomBox.getChildren().add(btnClear);
        bottomBox.getChildren().add(btnExit);

        /* Creates scene, sets stage */
        Scene scene = new Scene(bPane);
        primaryStage.setTitle("Maps");
        primaryStage.setScene(scene);
        primaryStage.show();

        Validation v = new Validation();
        GCDEuclid g = new GCDEuclid();
        SOE s = new SOE();

        btnExecute.setOnAction((ActionEvent e) -> {
            String num1S = txtNum1.getText().trim();
            String num2S = txtNum2.getText().trim();
            boolean validInt = true;
            int num1 = 0;
            int num2 = 0;
            if (v.checkEmpty(num1S)) {
                noNumber("first");
                txtNum1.requestFocus();
            } else if (v.checkEmpty(num2S)) {
                noNumber("second");
                txtNum2.requestFocus();
            } else {
                try {
                    num2 = Integer.parseInt(num2S);
                    num1 = Integer.parseInt(num1S);
                } catch (NumberFormatException ex) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Not an Integer!");
                    alert.setContentText("Please enter integers in the boxes on the left");
                    alert.showAndWait();
                    txtNum1.requestFocus();
                    validInt = false;
                }
                if (validInt) {
                    int gcd;
                    gcd = g.gcd(num1, num2);
                    txaResults.appendText("The greatest common denominator of " + num1S + " and " + num2S + " is " + gcd + "\n");
                    s.findPrimes(txaResults, num1);
                    txaResults.appendText("\n");
                    s.findPrimes(txaResults, num2);
                    txaResults.appendText("\n");
                }
            }
        });

        btnClear.setOnAction((ActionEvent e) -> {
           txaResults.clear();
           txtNum1.clear();
           txtNum2.clear();
           txtNum1.requestFocus();
        });

        btnExit.setOnAction((ActionEvent e) ->{
            Alert exit = new Alert(Alert.AlertType.CONFIRMATION);
            exit.setTitle("Goodbye!");
            exit.setContentText("Really quit?");
            Optional<ButtonType> result = exit.showAndWait(); /* Confirmation dialog method from 
            https://examples.javacodegeeks.com/desktop-java/javafx/dialog-javafx/javafx-dialog-example/ */
            if ((result.isPresent()) && (result.get() == ButtonType.OK))
            System.exit(0);
        });
    }
    private void noNumber(String thing) {
        String fullAlert = "Please enter a number in the " + thing + " box and try again.";
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("No " + thing + " number found");
        alert.setContentText(fullAlert);
        alert.showAndWait();
    }
}
