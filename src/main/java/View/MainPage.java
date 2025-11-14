package View;

import Controller.PageController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Locale;

public class MainPage extends Stage {
    private final PageController pageController;

    public MainPage(Double x, Double p) {
        pageController = new PageController(this);
        VBox root = buildPage(x, p);
        Scene scene = new Scene(root, 370, 180);
        this.setScene(scene);
        this.show();
    }

    public VBox buildPage(Double xValue, Double pValue) {
        String xText, pText;
        if (xValue == null) {
            xText = "0";
        } else {
            xValue = Math.round(xValue * 100.0) / 100.0;
            xText = String.format(Locale.US, "%.2f", xValue);
        }
        if (pValue == null) {
            pText = "0";
        } else {
            pValue = Math.round(pValue * 10000.0) / 10000.0;
            pText = String.format(Locale.US, "%.4f", pValue);
        }

        Label title = new Label("Standard Normal Distribution");
        Text xName = new Text("x = ");
        TextField xInput = new TextField(xText);
        xInput.setPromptText("The value of z");
        HBox line1 = new HBox(xName, xInput);
        line1.setAlignment(Pos.CENTER);
        line1.setSpacing(10);

        Text pName = new Text(" P(Z < z) = ");
        TextField pInput = new TextField(pText);
        pInput.setPromptText("The probability of Z < x");
        HBox line2 = new HBox(pName, pInput);
        line2.setAlignment(Pos.CENTER);
        line2.setSpacing(10);

        HBox buttons = getButtons(xInput, pInput);

        VBox root = new VBox(title, line1, line2, buttons);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        return root;
    }

    private HBox getButtons(TextField xInput, TextField pInput) {
        Button calculateP = new Button("Calculate Probability");
        calculateP.setOnAction(e -> pageController.getProbability(xInput.getText()));

        Button calculateX = new Button("Calculate X value");
        calculateX.setOnAction(e -> pageController.getXValue(pInput.getText()));

        Button reset = new Button("Clear");
        reset.setOnAction(e -> pageController.clear());

        HBox buttons = new HBox(calculateP, calculateX, reset);
        buttons.setSpacing(10);
        buttons.setAlignment(Pos.CENTER);
        return buttons;
    }
}
