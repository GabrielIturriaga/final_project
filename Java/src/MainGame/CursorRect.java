package MainGame;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;

public class CursorRect extends Rectangle {
    public CursorRect(){
        this.setWidth(32);
        this.setHeight(32);
        this.setFill(Color.DARKRED);
        this.setStroke(Color.BLACK);
    }
}
