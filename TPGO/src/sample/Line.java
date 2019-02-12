package sample;

import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.effect.Effect;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KARIM on 12/10/2018.
 */
public class Line extends javafx.scene.shape.Line {

    public int n1,n2;
    public int val;
    public TextField textField;
    private Color bleu,rouge;//des couleurs :3
    private GraphicsContext gc1;//outils de dessin
    public Line(double startx, double starty, double endx, double endy, int n1, int n2, List<Canvas> canvas, TextField t, Graphe g, AnchorPane plan){
        super(startx,starty,endx,endy);
        setStrokeWidth(3);
        this.n1 = n1;
        this.n2 = n2;
        textField = t;
        t.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                try{
                if(event.isControlDown()){
                    g.setArc(n1,n2,Integer.parseInt(t.getText()));
                    if(plan.getChildren().contains(textField))plan.getChildren().remove(textField);
                    setStrokeWidth(1);
                }
                }catch (Exception e){

                }
            }
        });
        bleu = Color.web("#3232ff");rouge = Color.web("#990000");
        this.val = val;
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                for(Canvas c: canvas){
                    gc1 = c.getGraphicsContext2D();
                    gc1.setFill(bleu);
                    gc1.setStroke(bleu);
                    gc1.setLineWidth(3);
                    gc1.strokeOval(2, 2, 20, 20);
                    gc1.fillOval(2,2,20,20);
                    gc1.setTextAlign(TextAlignment.CENTER);
                    gc1.setTextBaseline(VPos.CENTER);
                    gc1.setFill(Color.WHITE);
                    gc1.fillText(
                            String.valueOf(c.getNodeID()),
                            Math.round(c.getWidth() / 2 - 1),
                            Math.round(c.getHeight() / 2 - 2)
                    );


                }
                Canvas c = canvas.get(n1);
                gc1 = c.getGraphicsContext2D();
                gc1.setFill(rouge);
                gc1.setStroke(rouge);
                gc1.setLineWidth(3);
                gc1.strokeOval(2, 2, 20, 20);
                gc1.fillOval(2,2,20,20);
                gc1.setTextAlign(TextAlignment.CENTER);
                gc1.setTextBaseline(VPos.CENTER);
                gc1.setFill(Color.WHITE);
                gc1.fillText(
                        String.valueOf(c.getNodeID()),
                        Math.round(c.getWidth() / 2 - 1),
                        Math.round(c.getHeight() / 2 - 2)
                );
                c = canvas.get(n2);
                gc1 = c.getGraphicsContext2D();
                gc1.setFill(rouge);
                gc1.setStroke(rouge);
                gc1.setLineWidth(3);
                gc1.strokeOval(2, 2, 20, 20);
                gc1.fillOval(2,2,20,20);
                gc1.setTextAlign(TextAlignment.CENTER);
                gc1.setTextBaseline(VPos.CENTER);
                gc1.setFill(Color.WHITE);
                gc1.fillText(
                        String.valueOf(c.getNodeID()),
                        Math.round(c.getWidth() / 2 - 1),
                        Math.round(c.getHeight() / 2 - 2)
                );

            }

        };
        addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);

    }
}
