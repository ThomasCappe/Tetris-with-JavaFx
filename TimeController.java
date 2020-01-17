package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class TimeController implements EventHandler <ActionEvent>
{
	public void handle(ActionEvent event)
	{
		Tetriminos.Jeu();
		
	}
}
