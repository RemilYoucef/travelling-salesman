package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.List;

//cette classe permet de dessiner les graphes
public class ManController {
@FXML private AnchorPane plan;
@FXML private Button b;
@FXML private javafx.scene.control.TextField nombre_sommets;
@FXML private Button nouveau_graphe;
    @FXML
    private TextField depart;
    private int N = 5;

    private Canvas layer; // un canvas represante le dessin d'un sommet
    private int i_gen=0;//compteur de sommet et identifiant du dernier sommet crée a l'instant t
    private List<Canvas> canvas_list=null ;//liste des canvas pour les garder a la fin pour les colorier
    private GraphicsContext gc1;//outils de dessin
    private Color bleu,rouge;//des couleurs :3

    private Graphe g;//instance du model d'un graphe
    private List<Line> lines = new ArrayList<Line>();
    private Line activeLine=null;

    @FXML
    private Text te_1;

    @FXML
    private Text ch1;

    @FXML
    private Text c1;

    @FXML
    private Text te_2;

    @FXML
    private Text ch2;

    @FXML
    private Text c2;

    @FXML
    private Text te_3;

    @FXML
    private Text ch3;

    @FXML
    private Text c3;

    void initText(){
        te_1.setText("TE : ");
        te_2.setText("TE : ");
        te_3.setText("TE : ");

        c1.setText("Cout : ");
        c2.setText("Cout : ");
        c3.setText("Cout : ");

        ch1.setText("Chemin : ");
        ch2.setText("Chemin : ");
        ch3.setText("Chemin : ");
    }



    @FXML
    public void initialize(){
    depart.setText(0+"");
    g = new Graphe(N);
    bleu = Color.web("#3232ff");rouge = Color.web("#990000");
    nombre_sommets.setText("5");
    canvas_list = new ArrayList<Canvas>();
        // cette fonction est appelé lors du clique sur extraction des points d'articulation
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if(canvas_list!= null) {

                        long t0,t1;
                    t0 = System.currentTimeMillis();
                    SolutGlutonVoisin solutGlutonVoisin = new SolutGlutonVoisin(N);
                    solutGlutonVoisin.GlutonVoisin(g,Integer.parseInt(depart.getText())<N ? Integer.parseInt(depart.getText()):0 );
                    t1 = System.currentTimeMillis();
                    te_1.setText(te_1.getText()+String.valueOf(t1-t0)+" ms");
                    c1.setText(c1.getText()+String.valueOf(solutGlutonVoisin.cycle_opt.get_cout()));
                    for(int i = 0 ; i <= N ; i++)
                    {
                        ch1.setText(ch1.getText()+solutGlutonVoisin.cycle_opt.get_sommmetChemin(i)+" ");
                    }
                    /*----------------------------------------------------------------*/

                    t0 = System.currentTimeMillis();
                    SolutGlutonTrie solutGlutonTrie = new SolutGlutonTrie(N);
                    solutGlutonTrie.GlutonTrie(g,Integer.parseInt(depart.getText())<N ? Integer.parseInt(depart.getText()):0);
                    t1 = System.currentTimeMillis();
                    te_2.setText(te_2.getText()+String.valueOf(t1-t0)+" ms");
                    c2.setText(c2.getText()+String.valueOf(solutGlutonTrie.cycle_opt.get_cout()));
                    for(int i = 0 ; i <= N ; i++)
                    {
                        ch2.setText(ch2.getText()+solutGlutonTrie.cycle_opt.get_sommmetChemin(i)+" ");
                    }
                    /*----------------------------------------------------------------*/

                    t0 = System.currentTimeMillis();
                    SolutionExpo solutionExpo = new SolutionExpo(N);
                    solutionExpo.bfs(g,Integer.parseInt(depart.getText())<N ? Integer.parseInt(depart.getText()):0);
                    t1 = System.currentTimeMillis();
                    te_3.setText(te_3.getText()+String.valueOf(t1-t0)+" ms");
                    c3.setText(c3.getText()+String.valueOf(solutionExpo.cycle_opt.get_cout()));
                    for(int i = 0 ; i <= N ; i++)
                    {
                        ch3.setText(ch3.getText()+solutionExpo.cycle_opt.get_sommmetChemin(i)+" ");
                    }




                }

            }
        };

            //cette fonction est appelé lors du clique sur nouveau graphe
            EventHandler<MouseEvent> eventHandler2 = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    try {
                        //introduire le nouveau nombre de sommets
                        N = Integer.valueOf(nombre_sommets.getText());
                        g = new Graphe(N);
                        //remettre a 0 le compteur de sommets crée
                        i_gen =0;
                        //supprimer les canvas sur le plan
                        plan.getChildren().clear();
                        //vider la liste de Canvas
                        canvas_list = new ArrayList<Canvas>();

                        initText();
                    }catch(Exception ex){

                    }

                    }

                };

        //Registering the event filter
        b.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
        nouveau_graphe.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler2);

    }


public void onClick(MouseEvent e){
    //System.out.println("x="+e.getSceneX() + " y="+e.getSceneY());
   if(e.getTarget().getClass().toString().equals("class javafx.scene.layout.AnchorPane") && i_gen<N) {

       layer = new Canvas(26, 26,i_gen++);
       canvas_list.add(layer);

       layer.setLayoutY(e.getY() - 10);
       layer.setLayoutX(e.getX() - 10);

       gc1 = layer.getGraphicsContext2D();
       gc1.setFill(bleu);
       gc1.setStroke(bleu);
       gc1.setLineWidth(3);
       gc1.strokeOval(2, 2, 20, 20);
        gc1.fillOval(2,2,20,20);
       gc1.setTextAlign(TextAlignment.CENTER);
       gc1.setTextBaseline(VPos.CENTER);
       gc1.setFill(Color.WHITE);
       gc1.fillText(
               String.valueOf(layer.getNodeID()),
               Math.round(layer.getWidth() / 2 - 1),
               Math.round(layer.getHeight() / 2 - 2)
       );
       plan.getChildren().add(layer);

    }
    if (i_gen == N){
       //construire le graphe complet aleatoirement
        for(int i1=0;i1<N;i1++){
            for(int i2=i1+1;i2<N;i2++)
            {
                TextField t = new TextField();
                t.toFront();
                t.setMaxWidth(60);
                t.setMaxHeight(25);
                Line line = new Line(canvas_list.get(i1).getLayoutX()+10, canvas_list.get(i1).getLayoutY()+10,canvas_list.get(i2).getLayoutX()+10, canvas_list.get(i2).getLayoutY()+10,i1,i2,canvas_list,t,g,plan);
                plan.getChildren().add(line);
                lines.add(line);

                line.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        activeLine = (Line)event.getTarget();
                        activeLine.textField.requestFocus();
                        activeLine.textField.setLayoutX(event.getX());
                        activeLine.textField.setLayoutY(event.getY());
                        if(!plan.getChildren().contains(activeLine.textField))plan.getChildren().add(activeLine.textField);

                        //System.out.println(activeLine);
                    }
                });
                line.setFill(bleu);
                line.toBack();
                canvas_list.get(i1).toFront();
                canvas_list.get(i2).toFront();
                g.setArc(canvas_list.get(i1).getNodeID(),canvas_list.get(i2).getNodeID(),1);
            }
        }
        i_gen++;
   }else if(i_gen>N)
    {
        activeLine = null;
    }



}
}
