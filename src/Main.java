import java.util.LinkedList;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Rectangle2D screenRec = Screen.getPrimary().getVisualBounds();
			Canvas canvas = new Canvas(screenRec.getMaxX(),screenRec.getMaxY());
			BorderPane root = new BorderPane(canvas);
			Scene scene = new Scene(root,screenRec.getMaxX(),screenRec.getMaxY());

			GraphicsContext gc = canvas.getGraphicsContext2D();
			
	        gc.setFill(Color.GREEN);
	        gc.setStroke(Color.BLUE);
	        gc.setLineWidth(5);
	        
	        double R = 200;
	        double r = 42;
	        double O = 40;
	        double max_t = 2 * Math.PI * r / GCD(R, r);
	        
	        LinkedList<Double> xListPoints = new LinkedList<Double>();
	        LinkedList<Double> yListPoints = new LinkedList<Double>();
	        
	        for(double t = 0; t < max_t; t += Math.PI/128) {
	        	xListPoints.add((R+r)*Math.cos(t) - (r+O)*Math.cos(((R+r)/r)*t) + (scene.getWidth()/2));
	        	yListPoints.add((R+r)*Math.sin(t) - (r+O)*Math.sin(((R+r)/r)*t) + (scene.getHeight()/2));
	        }
	   	        
	        double[] xPoints = new double[xListPoints.size()];
	        
	        for(int i = 0; i < xListPoints.size(); i++) {
	        	xPoints[i] = xListPoints.get(i).doubleValue();
	        }
	        
	        double[] yPoints = new double[yListPoints.size()];
	        
	        for(int i = 0; i < yListPoints.size(); i++) {
	        	yPoints[i] = yListPoints.get(i).doubleValue();
	        }
	        
	        gc.strokePolyline(xPoints, yPoints, xPoints.length);
	        
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private double GCD(double r, double r2) { return r2==0 ? r : GCD(r2, r%r2); }
	
	public static void main(String[] args) {
		launch(args);
	}
}
