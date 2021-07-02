package main;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class MainWindowController {

    @FXML
    private Pane titlePane, actionLogPane;
    @FXML
    private ImageView btnMinimize, btnClose, actionLogButtonClose;
    @FXML
    private Label lblResult, historyResult;

    private double x, y, prevValue = 0, currValue;
    private String operator = "+";
    private boolean flag = false;

    Stack<String> operatorsStack = new Stack<>();

    public void init(Stage stage) {
        actionLogPane.setVisible(false);
        titlePane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        titlePane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });

        btnClose.setOnMouseClicked(mouseEvent -> stage.close());
        btnMinimize.setOnMouseClicked(mouseEvent -> stage.setIconified(true));
        actionLogButtonClose.setOnMouseClicked(mouseEvent -> actionLogPane.setVisible(false));
    }

    @FXML
    void onNumberClicked(MouseEvent event) {
        int value = Integer.parseInt(((Pane) event.getSource()).getId().replace("btn", ""));
        currValue = currValue * 10 + value;
        lblResult.setText(Double.toString(currValue));
        //operatorsStack.push(Integer.toString(value));
        //flag = false;
    }

    @FXML
    void onSymbolClicked(MouseEvent event) {
        operatorsStack.push(Double.toString(currValue));
        //if (!operatorsStack.isEmpty()){
        if (operatorsStack.isEmpty() == true){
            operatorsStack.push(Double.toString(0.0));
        }
            switch (operator) {
                case "+" -> prevValue += currValue;
                case "-" -> prevValue -= currValue;
                case "*" -> prevValue *= currValue;
                case "/" -> prevValue /= currValue;
            }
            currValue = 0;
            String symbol = ((Pane) event.getSource()).getId().replace("btn", "");
            switch (symbol) {
                case "Plus" -> {
                    operator = "+";
                }
                case "Minus" -> {
                    operator = "-";
                }
                case "Multiply" -> {
                    operator = "*";
                }
                case "Divide" -> {
                    operator = "/";
                }
                case "Equals" -> {
                    operator = "=";
                }
            }
            if ((operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/") || operator.equals("=")) && !operatorsStack.isEmpty()){
                if (operatorsStack.peek().equals("+") || operatorsStack.peek().equals("-") || operatorsStack.peek().equals("*") || operatorsStack.peek().equals("/") || operatorsStack.peek().equals("=")){
                    operatorsStack.pop();
                }
            }
            if (/*!operatorsStack.isEmpty() && */(!operator.equals("="))){
                operatorsStack.pop();
                if((!operatorsStack.isEmpty())&&(operatorsStack.peek().equals("="))){
                    operatorsStack.push(Double.toString(prevValue) + "\n\n");
                }
                operatorsStack.push(Double.toString(prevValue));
            }
            operatorsStack.push(operator);
            lblResult.setText(Double.toString(prevValue));
            if (operator.equals("=")) {
                operator = "+";
                lblResult.setText(Double.toString(prevValue));
                operatorsStack.push(Double.toString(prevValue) + "\n\n");
                //operatorsStack.push(Double.toString(prevValue));
            }
        //}
    }

    @FXML
    void onButtonClearClicked(MouseEvent event){
        String symbol = ((Pane) event.getSource()).getId().replace("btn", "");
        if (symbol.equals("Clear")) {
            lblResult.setText(String.valueOf(0.0));
            prevValue = 0;
            currValue = 0;
        }
    }

    @FXML
    void onButtonHistoryClicked(MouseEvent event) {
        String symbol = ((Pane) event.getSource()).getId().replace("btn", "");
        if (symbol.equals("OpenHistory")) {
            actionLogPane.setVisible(true);
            while (!operatorsStack.isEmpty()) {
                historyResult.setText(operatorsStack.pop() + historyResult.getText());
            }
            //if (prevValue != 0.0){
               // operatorsStack.push(Double.toString(prevValue));
            //}
        }
        if (symbol.equals("ClearHistory")) {
            historyResult.setText("");
        }
    }
}
