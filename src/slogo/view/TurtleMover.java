package slogo.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class TurtleMover extends VBox {


  public TurtleMover() {
    setAlignment(Pos.CENTER);
    GridPane box = new GridPane();
    box.setPrefSize(10,10);

    Button top = createButton("top","handleTop");
    box.add(top, 1,0);

    Button bottom = createButton("bottom","handleBottom");
    box.add(bottom, 1,2);

    Button left = createButton("left","handleLeft");
    box.add(left, 0,1);

    Button right = createButton("right","handleRight");
    box.add(right, 2,1);

    getChildren().add(box);
  }

  private Button createButton(String prompt, String methodName){
    Button button = new Button();
    button.setOnAction(value -> System.out.println(methodName));
    button.setText(prompt);
    return button;
  }

}
