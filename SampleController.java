package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class SampleController 
{
		
		@FXML
		static Label score;
		
		@FXML
		public AnchorPane tab;
		
		@FXML
		public AnchorPane tabPrinc;
		
		
		@FXML
		private void initialize()
		{
			Timeline timeline;
			TimeController ctrl = new TimeController();
			timeline=new Timeline();
			KeyFrame kfl=new KeyFrame(Duration.millis(100),ctrl);
			timeline.getKeyFrames().add(kfl);
			timeline.setCycleCount(Timeline.INDEFINITE);
			timeline.play();
			for(int i=0;i<20;i++)
			{
				for(int j=0;j<10;j++)
				{
					Rectangle rectangle=new Rectangle();
					rectangle.setX(j*35);
			        rectangle.setY(i*35);
			        rectangle.setWidth(35);
			        rectangle.setHeight(35);
			        rectangle.setFill(Color.TRANSPARENT);
			        tab.getChildren().add(rectangle);
				}
			}
			Label label=new Label();
			label.setText(Integer.toString(Tetriminos.scoreActu));
			label.setFont(new Font("Arial", 30));
			label.setLayoutX(527);
			label.setLayoutY(130);
			label.setTextFill(Color.web("#fff"));
			tabPrinc.getChildren().add(3,label);
		}		
}
	
	

