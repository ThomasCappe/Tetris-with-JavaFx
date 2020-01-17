package application;
import javafx.application.*;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.ImageView;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

public class Main extends Application {

	public static SampleController ctrl;
	
	public void start(Stage primaryStage) throws Exception 
	{		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("Sample.fxml"));
		VBox rootLayout=(VBox) loader.load();
		ctrl = (SampleController) loader.getController();
		Scene scene=new Scene(rootLayout);
		primaryStage.setTitle("THEITRIS");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Lancement de THEITRIS");
		dialog.setHeaderText("Bienvenue sur THEITRIS \nAppuyez sur 'Jouer' pour commencer ");
		dialog.setContentText("Une création originale de : Cappe Thomas\nDesign by Druon Constant");
		dialog.setGraphic(new ImageView(this.getClass().getResource("icons8-manette-64.png").toString()));
		ButtonType loginButtonType = new ButtonType("Jouer", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType);         
		dialog.showAndWait();		
		Tetriminos.ApparitionRandom();
			
		scene.setOnKeyPressed((EventHandler<? super KeyEvent>) new EventHandler<KeyEvent>() 
		{
			@SuppressWarnings("incomplete-switch")
			public void handle(KeyEvent keyEvent) 
	        {
	            switch (keyEvent.getCode()) 
	            {
	                case ENTER:System.out.println("ENTRER");
	                break;    
	                case A:
	                Tetriminos.AnimationGauche();
	                break;   
	                case D:
	                Tetriminos.AnimationDroite();
	                break;	                
	                case W:
	                Tetriminos.rotatD();
	                break;  
	                case S:
	                Tetriminos.rotatG();
	                break; 
	            }
	        }
		});
	}	
	public static void main(String[] args)
	{
		launch(args);
	}	
}
