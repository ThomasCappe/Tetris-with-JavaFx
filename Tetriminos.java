package application;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;



public class Tetriminos {
	
	public static byte [][] Game= new byte[20][10];							//Création du Game
	public static byte [][] GameMem= new byte[20][10];
	public static byte [][] GameMem2= new byte[20][10];
	public static byte [][] I= {{1,0,0,0},{1,0,0,0},{1,0,0,0},{1,0,0,0}};	
	public static byte [][] O= {{0,0,0,0},{0,0,0,0},{1,1,0,0},{1,1,0,0}};
	public static byte [][] T= {{0,0,0,0},{1,0,0,0},{1,1,0,0},{1,0,0,0}};
	public static byte [][] L= {{0,0,0,0},{1,1,0,0},{1,0,0,0},{1,0,0,0}};
	public static byte [][] J= {{0,0,0,0},{1,0,0,0},{1,0,0,0},{1,1,0,0}};
	public static byte [][] Z= {{0,0,0,0},{1,0,0,0},{1,1,0,0},{0,1,0,0}};
	public static byte [][] S= {{0,0,0,0},{0,1,0,0},{1,1,0,0},{1,0,0,0}};
	public static byte [][] tetriminosingame;								//Etat du tetriminos 
	public static byte [][] tetriminosMem=new byte[4][4];;
	public static int AxeX,AxeY,Nb_points=0;											//Positionnement du tetriminos dans le Game (HautGauche)
	public static int scoreActu=0;
	public static boolean collision,Etat=false,EtatTetriC4=false,EtatTetriC3=false,EtatTetriL3=false,EtatTetriL4=false,fini=false;
	
	
	
	public static void main(String[] args)
	{}

	public static void init_tetriminos(byte[][]tab)
	{
		for (int i=0;i<tab[0].length;i++)
		{
			for(int j=0;j<tab.length;j++)
			{
				tab[j][i]=0;
			}
		}
	}
	public static void affichage(byte[][]tab)
	{		 	
		int n=0;
		for (int i=0;i<tab.length;i++)
		{
			for(int j=0;j<tab[0].length;j++)
			{
				n++;
				
				if (n==tab[0].length)
				{
					n=0;
				}
			}
		}
	}
	public static void affichageGame()
	{		 	
		int n=0;
		for (int i=0;i<Game.length;i++)
		{
			for(int j=0;j<Game[0].length;j++)
			{
				n++;
				
				if (n==Game[0].length)
				{					
					n=0;
				}
			}
		}
	}
	public static void rotatG()
	{
		init_tetriminos(tetriminosMem);
		CopieMat(tetriminosingame,tetriminosMem);
		for (int i=0;i<4;i++)
		{
			for (int j=0;j<4;j++)
			{
				tetriminosMem[i][3-j]=tetriminosingame[j][i];					
			}
		}
		CopieMat(tetriminosMem,tetriminosingame);
		Refresh();
		VerifC4();
		VerifC3();	
	}
	public static void rotatD()
	{
		init_tetriminos(tetriminosMem);
		CopieMat(tetriminosingame,tetriminosMem);
		for (int i=0;i<4;i++)
		{
			for (int j=0;j<4;j++)
			{
				tetriminosMem[3-i][j]=tetriminosingame[j][i];	 				
			}
		}
		CopieMat(tetriminosMem,tetriminosingame);
		Refresh();
		VerifC4();
		VerifC3();
	}		
		public static void AnimationChute()
		{
			Enregistrement();
			Collisions();
			if(collision==false)
			{
				if(AxeX==-1)
				{
					for(int i=AxeX+1;i<AxeX+5;i++)
					{
						for (int j=AxeY;j<AxeY+4;j++)
						{
							if(GameMem2[j][i]==1)
							{
							Game[j+1][i]=GameMem2[j][i];
							}
						}
					}
					
				}else{
					if(AxeX<7)
					{
						for(int i=AxeX;i<AxeX+4;i++)
						{
							for (int j=AxeY;j<AxeY+4;j++)
							{
								if(tetriminosingame[j-AxeY][i-AxeX]==1)
								{
								Game[j+1][i]=GameMem2[j][i];
								}
							}
						}
					}else{
						
						if(EtatTetriC4==true)
						{
							for(int i=AxeX;i<AxeX+2;i++)
							{
								for (int j=AxeY;j<AxeY+4;j++)
								{
									if(GameMem2[j][i]==1)
									{
									Game[j+1][i]=GameMem2[j][i];
									}
								}
							}
						}else{
							if(EtatTetriC3==true)
							{
								for(int i=AxeX;i<AxeX+3;i++)
								{
									for (int j=AxeY;j<AxeY+4;j++)
									{
										if(GameMem2[j][i]==1)
										{
										Game[j+1][i]=GameMem2[j][i];
										}
									}
								}	
							}else{
								for(int i=AxeX;i<AxeX+2;i++)
								{
									for (int j=AxeY;j<AxeY+4;j++)
									{
										if(GameMem2[j][i]==1)
										{
										Game[j+1][i]=GameMem2[j][i];
										}
									}
								}	
							}
						}
					}
				}
				AxeY++;
			}
			else
			{
				CopieMat(GameMem2,Game);
				ApparitionRandom();
			}
			if(AxeY==16)
			{
				ApparitionRandom();
			}
		}
	public static void AnimationDroite()
	{
		Enregistrement();
		if(AxeX<7)
		{
			if(AxeX==-1)
			{
				for(int i=AxeX+1;i<AxeX+5;i++)
				{
					for (int j=AxeY;j<AxeY+4;j++)
					{
						if(GameMem2[j][i]==1)
						{
						Game[j][i+1]=1;								//GameMem2[j][i]
						}
					}
				}
			}else{
				for(int i=AxeX;i<AxeX+4;i++)
				{
					for (int j=AxeY;j<AxeY+4;j++)
					{
						if(GameMem2[j][i]==1)
						{
						Game[j][i+1]=1;								//GameMem2[j][i]
						}
					}
				}
			}
		}else{
			if(EtatTetriC4==true)
			{
			}else{
				if(EtatTetriC3==true)
				{
					for(int i=AxeX;i<AxeX+3;i++)
					{
						for (int j=AxeY;j<AxeY+4;j++)
						{
							if(GameMem2[j][i]==1)
							{
							Game[j][i+1]=1;								//GameMem2[j][i]
							}
						}
					}
				}else{
					for(int i=AxeX;i<AxeX+2;i++)
					{
						for (int j=AxeY;j<AxeY+4;j++)
						{
							if(GameMem2[j][i]==1)
							{
							Game[j][i+1]=1;								//GameMem2[j][i]
							}
						}
					}
				}
			}
		}
		AxeX++;	
	}
	public static void AnimationGauche()
	{
		Enregistrement();
		if(AxeX==-1)
		{
			for(int i=AxeX;i<AxeX+4;i++)
			{
				for (int j=AxeY;j<AxeY+4;j++)
				{
					if(GameMem2[j][i]==1)
					{
					Game[j][i-1]=1;								//GameMem2[j][i]
					}
				}
			}
		}else{
			
			if(AxeX<7)
			{
				for(int i=AxeX;i<AxeX+4;i++)
				{
					for (int j=AxeY;j<AxeY+4;j++)
					{
						if(GameMem2[j][i]==1)
						{
						Game[j][i-1]=1;								//GameMem2[j][i]
						}
					}
				}
			}else{
				if(EtatTetriC4==true)
				{
					for(int i=AxeX;i<AxeX+4;i++)
					{
						for (int j=AxeY;j<AxeY+4;j++)
						{
							if(GameMem2[j][i]==1)
							{
							Game[j][i-1]=1;								//GameMem2[j][i]
							}
						}
					}
				}else{
					if(EtatTetriC3==true)
					{
						for(int i=AxeX;i<AxeX+3;i++)
						{
							for (int j=AxeY;j<AxeY+4;j++)
							{
								if(GameMem2[j][i]==1)
								{
								Game[j][i-1]=1;								//GameMem2[j][i]
								}
							}
						}
					}
					if(EtatTetriC3==false)
					{
						for(int i=AxeX;i<AxeX+2;i++)
						{
							for (int j=AxeY;j<AxeY+4;j++)
							{
								if(GameMem2[j][i]==1)
								{
								Game[j][i-1]=1;								//GameMem2[j][i]
								}
							}
						}
					}
				}
			}
		}
		AxeX--;		
	}
	public static void ApparitionRandom()
	{
		scoreActu=scoreActu+75;
		VerificationLigne();
		HashMap<String,ArrayList<byte[][]>> map=new HashMap<String,ArrayList<byte[][]>>();
		ArrayList<byte[][]> tmp=new ArrayList<byte[][]>();
		
		tmp.add(0,I);
		tmp.add(1,O);
		tmp.add(2,T);
		tmp.add(3,L);
		tmp.add(4,J);
		tmp.add(5,Z);
		tmp.add(6,S);
		map.put("ALLTetriminos",tmp);
		int max=0,min=6;
		int nbAlea = (int)(Math.random()*(max-min))+min;
		
		collision=false;
		CopieMat(Game,GameMem);
		tetriminosingame=tmp.get(nbAlea); 
		AxeX=3;AxeY=0;
		for(int i=0;i<4;i++)
		{
			for (int j=3;j<7;j++)
			{
				Game[i][j]=tetriminosingame[i][j-3];
			}
		}
	}
	public static void Refresh()
	{
		Enregistrement();
		for(int i=AxeX;i<AxeX+4;i++)
		{
			for (int j=AxeY;j<AxeY+4;j++)
			{
				if(tetriminosingame[j-AxeY][i-AxeX]==1)
				{
				Game[j][i]=tetriminosingame[j-AxeY][i-AxeX];
				}
			}
		}
	}
	public static void Enregistrement()
	{
		CopieMat(Game,GameMem2);
		CopieMat(GameMem,Game);
	}
	public static byte[][] CopieMat(byte[][]GameIn,byte[][]GameOut)
	{
		for (int i=0;i<GameIn[0].length;i++)
		{
			for(int j=0;j<GameIn.length;j++)
			{
				GameOut[j][i]=GameIn[j][i];
			}
		}
		return(GameOut);
	}
	public static void VerificationLigne()
	{
		int numLigne;
		for(numLigne=0;numLigne<20;numLigne++)
		{
			if (Game[numLigne][0]==1) 						
			{
				int n=0;
				Tetriminos.affichage(Tetriminos.Game);
				for(int j=0;j<10;j++) 
				{
					if(Game[numLigne][j]==1)
					{
						n++;
					}
					if(n==10)
					{
						for(int k=0;k<10;k++)
						{
							Game[numLigne][k]=0;
						}
						for(int l=numLigne;l>0;l--)
						{
							for(int k=0;k<10;k++)
							{
								Game[l][k]=Game[l-1][k];
							}
						}
						scoreActu=scoreActu+10;
						CopieMat(Game,GameMem);
						CopieMat(Game,GameMem2);
					}
				}
			}
		}
	}
	public static boolean Collisions()
	{
		for(int i=3;i>=0;i--)
		{
			for(int j=3;j>=0;j--)
			{
				if(collision==false)
				{
					if(tetriminosingame[i][j]==1)
					{
						if(GameMem[AxeY+i+1][AxeX+j]==1)
						{
							collision=true;
							
						}
					}else{
						collision=false;
					}
				}else if(collision==true) {
					
				}
			}
		}

		return(collision);
	}
	public static boolean GameOver()
	{
		for(int i=0;i<10;i++)
		{
			if (GameMem[4][i]==1)
			{
				Etat=true;
				break;
			}
		}
		return(Etat);
	}
	public static boolean VerifC4()	
	{
		for(int i=0;i<3;i++)
		{
				if(tetriminosingame[i][3]==1)
				{
					EtatTetriC4=true;
				}else{
					EtatTetriC4=false;
				}
		}
		return(EtatTetriC4);
	}
	public static boolean VerifC3()	
	{
		for(int i=0;i<3;i++)
		{
				if(tetriminosingame[i][2]==1)
				{
					EtatTetriC3=true;
				}else{
					EtatTetriC3=false;
				}
		}
		return(EtatTetriC3);
	}
	public static boolean VerifL3()	
	{
		for(int i=0;i<3;i++)
		{
				if(tetriminosingame[2][i]==1)
				{
					EtatTetriL3=true;
				}else{
					EtatTetriL3=false;
				}
		}
		return(EtatTetriL3);
	}
	public static boolean VerifL4()	
	{
		for(int i=0;i<3;i++)
		{
				if(tetriminosingame[3][i]==1)
				{
					EtatTetriL4=true;
				}else{
					EtatTetriL4=false;
				}
		}
		return(EtatTetriL4);
	}
	public static void AffichageTableau()
	{
		ReiniTableau();
		for(int i=0;i<20;i++)
		{
			for(int j=0;j<10;j++)
			{
				if(Game[i][j]==1)
				{
					Rectangle rec= (Rectangle) Main.ctrl.tab.getChildren().get(i*10+j);
					rec.setFill(Color.BLACK);
				}
			}
		}
	}
	public static void ReiniTableau()
	{
		for(int i=0;i<20;i++)
		{
			for(int j=0;j<10;j++)
			{
					Rectangle rec= (Rectangle) Main.ctrl.tab.getChildren().get(i*10+j);
					rec.setFill(Color.TRANSPARENT);
			}
		}
	}
	public static void Jeu() 
	{
		Tetriminos.GameOver();
    	if(Tetriminos.Etat==false)
    	{
    		Tetriminos.AffichageTableau();
        	Tetriminos.AnimationChute();
        	
        	Label label=(Label) Main.ctrl.tabPrinc.getChildren().get(3);
			label.setText(Integer.toString(Tetriminos.scoreActu)); 
			fini=false;
        
    	}else if (fini==false && Etat==true){
    		Alert alert=new Alert(AlertType.WARNING);
    		alert.setTitle("GameOver");
    		alert.setContentText("Dommage vous avez perdu !\n\nScore: "+scoreActu);

    		alert.setHeaderText(null);
    		ButtonType ok = new ButtonType("Quitter");
            alert.getButtonTypes().clear();
            alert.getButtonTypes().addAll(ok);
    		alert.show();
    		fini=true;
    	}     
		
	}
	
	
	
}







