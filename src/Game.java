import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/*TABI
 * (means Journey in Japanese)
 * This is a simple RPG game that uses Javax Swing components (JFrame, JPanel, JLabel, JButton etc.) 
 * There are RPG elements like HP, weapons, damage, and path choices etc.
 * Inspired by RyiSnow
 * By: Samin
 * Date: November 12, 2022
 */
public class Game {

	JFrame frame, gameFrame;
	JPanel titlePanel, buttonPanel, gameDisplayPanel, gameDisplayChoicesPanel, playerInfoPanel;
	JLabel title, playerHPLabel, playerWeaponLabel;
	JTextArea gameDisplayMessage;
	JButton startButton, gameChoice1, gameChoice2, gameChoice3, gameChoice4;
	
	int playerHP, playerDamage, monsterHP, monsterDamage, weaponDamage;
	String playerWeapon, currentPosition, choiceValue;
	boolean rubyNecklace = false;
	
	
	ButtonHandler bHandler = new ButtonHandler();
	ChoiceHandler cHandler = new ChoiceHandler();

	
	public static void main(String[] args) {
		new Game();
	}
	
	public Game() {
		
		//JFRAME FOR MAIN TITLE SCREEN
		frame = new JFrame();
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.black);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		
		//PANEL FOR TITLE ON TITLE SCREEN
		titlePanel = new JPanel();
		titlePanel.setBounds(140,70,500,160);
		titlePanel.setBackground(Color.black);
		
		 
		title = new JLabel("TABI");
		title.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 120));
		title.setForeground(Color.white);
		title.setBackground(Color.black);
		title.setOpaque(true);
		
		//JPANEL FOR BUTTON ON TITLE SCREEN
		buttonPanel = new JPanel();
		buttonPanel.setBounds(290,380,200,100);
		buttonPanel.setBackground(Color.black);
		
		//JBUTTON FOR BUTTON ON TITLE SCREEN
		startButton = new JButton("BEGIN");
		startButton.setFocusPainted(false);
		startButton.setForeground(Color.white);
		startButton.setBackground(Color.black);
		startButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN,30));
		startButton.addActionListener(bHandler);
		
		//ADDING COMPONENTS TO PANELS
		titlePanel.add(title);
		buttonPanel.add(startButton);
		
		
		
		//ADDING PANELS TO MAIN FRAME
		frame.add(titlePanel);
		frame.add(buttonPanel);
		
		
		
		frame.setVisible(true);
	}
	
	public void gameDisplay() {
		
		titlePanel.setVisible(false);
		buttonPanel.setVisible(false);
		
		gameDisplayPanel = new JPanel();
		gameDisplayPanel.setBounds(75, 70, 630, 200);
		gameDisplayPanel.setBackground(Color.black);
		
		gameDisplayMessage = new JTextArea("You are at the gate of the town.\nA guard is standing in front of you.\n\nWhat do you do?");
		gameDisplayMessage.setBounds(75, 70, 630, 200); //SAME BOUNDS AS PANEL gameDisplayPanel
		gameDisplayMessage.setFont(new Font("Cambria", Font.PLAIN, 23));
		gameDisplayMessage.setForeground(Color.white);
		gameDisplayMessage.setBackground(Color.black);
		gameDisplayMessage.setEditable(false);
		gameDisplayMessage.setLineWrap(true);
		gameDisplayMessage.setWrapStyleWord(true);
		
		gameDisplayChoicesPanel = new JPanel();
		gameDisplayChoicesPanel.setBounds(270, 300, 250, 200);
		gameDisplayChoicesPanel.setBackground(Color.black);
		gameDisplayChoicesPanel.setLayout(new GridLayout(4,1));
		
		gameChoice1 = new JButton();
		gameChoice1.setBackground(Color.black);
		gameChoice1.setForeground(Color.white);
		gameChoice1.setFont(new Font("Cambria", Font.PLAIN, 25));
		gameChoice1.setFocusPainted(false);
		gameChoice1.addActionListener(cHandler);
		gameChoice1.setActionCommand("1");
		gameChoice2 = new JButton();
		gameChoice2.setBackground(Color.black);
		gameChoice2.setForeground(Color.white);
		gameChoice2.setFont(new Font("Cambria", Font.PLAIN, 25));
		gameChoice2.setFocusPainted(false);
		gameChoice2.addActionListener(cHandler);
		gameChoice2.setActionCommand("2");
		gameChoice3 = new JButton();
		gameChoice3.setFocusPainted(false);
		gameChoice3.setBackground(Color.black);
		gameChoice3.setForeground(Color.white);
		gameChoice3.setFont(new Font("Cambria", Font.PLAIN, 25));
		gameChoice3.addActionListener(cHandler);
		gameChoice3.setActionCommand("3");
		gameChoice4 = new JButton();
		gameChoice4.setFocusPainted(false);
		gameChoice4.setBackground(Color.black);
		gameChoice4.setForeground(Color.white);
		gameChoice4.setFont(new Font("Cambria", Font.PLAIN, 25));
		gameChoice4.addActionListener(cHandler);
		gameChoice4.setActionCommand("4");
		
		playerInfoPanel = new JPanel();
		playerInfoPanel.setBounds(15, 490, 220, 70);
		playerInfoPanel.setBackground(Color.black);
		playerInfoPanel.setLayout(new GridLayout(2,1));
		
		playerHPLabel = new JLabel();
		playerHPLabel.setFont(new Font("Cambria", Font.PLAIN, 16));
		playerHPLabel.setForeground(Color.white);
		playerHPLabel.setBackground(Color.black);
		
		playerWeaponLabel = new JLabel();
		playerWeaponLabel.setFont(new Font("Cambria", Font.PLAIN, 16));
		playerWeaponLabel.setForeground(Color.white);
		playerWeaponLabel.setBackground(Color.black);
		
		
		
		
		gameDisplayPanel.add(gameDisplayMessage);
		gameDisplayChoicesPanel.add(gameChoice1);
		gameDisplayChoicesPanel.add(gameChoice2);
		gameDisplayChoicesPanel.add(gameChoice3);
		gameDisplayChoicesPanel.add(gameChoice4);
		playerInfoPanel.add(playerHPLabel);
		playerInfoPanel.add(playerWeaponLabel);
		
		
		frame.add(playerInfoPanel);
		frame.add(gameDisplayPanel);
		frame.add(gameDisplayChoicesPanel);
		
		playerCreation();
		
	}
	
	public void playerCreation() {
		monsterHP = 25;
		playerHP = 12;
		playerWeapon = "Rusty Knife";
		
		playerHPLabel.setText("HP: " + playerHP);
		playerWeaponLabel.setText("Weapon: " + playerWeapon);
		
		villageEntrance();
	}
	
	public void villageEntrance() {
		currentPosition = "villageEntrance";
		
		gameDisplayMessage.setText("You are in front of the village\nA guard is protecting the entrance\n\nWhat do you want to do?\n");
		
		gameChoice1.setText("Talk to the Guard");
		gameChoice2.setText("Attack the Guard");
		gameChoice3.setText("Leave");
		gameChoice4.setText("");
	}
	
	public void choiceTalkGuard() {
		currentPosition = "talkGuard";
		
		gameDisplayMessage.setText("Guard:\nHello there wanderer, I haven't seen you around these parts.\nSo you want to enter the village?\n\nSorry but we cannot let a stranger like you enter.\n\n");
		
		gameChoice1.setText("Continue");
		gameChoice2.setText("");
		gameChoice3.setText("");
		gameChoice4.setText("");
	
	}
	
	public void choiceAttackGuard() {
		currentPosition = "attackGuard";
		
		
		
		gameDisplayMessage.setText("Guard:\nWhat do you think you are doing!??\n\nYou underestimated the guard's strength.\n\nThe guard hit you hard and you decided to flee.\n(You received 3 damage)  \n");
		
		playerHP = playerHP - 3;
		
		if(playerHP<=0) {
		playerHP = 0;
		gameOver();
		
		}
		
		else {
		playerHPLabel.setText("HP: " + playerHP);
		}
		
		gameChoice1.setText("Continue");
		gameChoice2.setText("");
		gameChoice3.setText("");
		gameChoice4.setText("");
		
		

	}
	
	public void choiceLeave() {
	    currentPosition = "pathway";
	    
	    gameDisplayMessage.setText("You are at a pathway that splits of to different directions.\nWhich direction would you like to head?\n\n(Walking south will lead you back to the village)\n");
		
		gameChoice1.setText("Go North");
		gameChoice2.setText("Go East");
		gameChoice3.setText("Go West");
		gameChoice4.setText("Go South");
		
	}
	
	public void north() {
		currentPosition = "north";
	    
	    gameDisplayMessage.setText("You have stumbled upon a river.\nYou decided to drink the water and take a rest.\n\nYou recovered some HP  (+2)\n");
	    
	    playerHP = playerHP + 2;
	    playerHPLabel.setText("HP: " + playerHP);
	    
	    gameChoice1.setText("Go South");
		gameChoice2.setText("");
		gameChoice3.setText("");
		gameChoice4.setText("");
	}
	
	public void east() {
		currentPosition = "east";
		
		gameDisplayMessage.setText("You ventured deep into the woods and found an abandoned shack.\nYou search inside the shed and find a razor-sharp axe!\n\n(You obtained a Lumberjack's Axe)\n");
		
		playerWeapon = "Lumberjack's Axe";
		playerWeaponLabel.setText("Weapon: " + playerWeapon);
		
		gameChoice1.setText("Go West");
		gameChoice2.setText("");
		gameChoice3.setText("");
		gameChoice4.setText("");
		
		
		
	}
	
	public void west() {
		currentPosition = "west";
		
		  gameDisplayMessage.setText("A monster ambushes you!\nIt appears to be an Orc.\n\nWhat do you want to do?\n");
		
		  gameChoice1.setText("Fight");
		  gameChoice2.setText("Run");
		  gameChoice3.setText("");
		  gameChoice4.setText("");
	}
	public void fight() {
		currentPosition = "fight";
		
		gameDisplayMessage.setText("Orc's HP: " + monsterHP + "\nOrc's Weapon: Old Club\n\nWhat do you want to do?");
		
		gameChoice1.setText("Attack");
		gameChoice2.setText("Run");
		gameChoice3.setText("");
		gameChoice4.setText("");
	}
	
	
	public void attack() {
		currentPosition = "attack";
		
		if(playerWeapon.equals("Rusty Knife")){
			weaponDamage = 5; //0-4
		}
		if(playerWeapon.equals("Lumberjack's Axe")) {
			weaponDamage = 8; //0-7
		}
		
		playerDamage = new Random().nextInt(weaponDamage);
		
		monsterHP = monsterHP - playerDamage;
		
		if(monsterHP<=0) {
			monsterHP = 0;
			gameDisplayMessage.setText("Orc's HP: " + monsterHP + "\n\nYou attacked the orc and did " + playerDamage + " damage!");
		}
		else {
		
		gameDisplayMessage.setText("Orc's HP: " + monsterHP + "\n\nYou attacked the orc and did " + playerDamage + " damage!");
		
		}
		gameChoice1.setText("Continue");
		gameChoice2.setText("");
		gameChoice3.setText("");
		gameChoice4.setText("");
		
		  
	}
	
	public void monsterDamage() {
		currentPosition = "monsterDamage";
		monsterDamage = new Random().nextInt(5); //0-4
		playerHP = playerHP - monsterDamage;
		
		if(playerHP <= 0) {
		playerHP = 0;
		playerHPLabel.setText("HP: " + playerHP);
		}
		else {
		playerHPLabel.setText("HP: " + playerHP);	
		}
		
		gameDisplayMessage.setText("Orc's HP: " + monsterHP+ "\n\nThe orc attacked you and did " + monsterDamage + " damage!\n\n");
		
		gameChoice1.setText("Continue");
		gameChoice2.setText("");
		gameChoice3.setText("");
		gameChoice4.setText("");
	
	}
	
	
	public void win() {
		currentPosition = "win";
		rubyNecklace = true;
		
		gameDisplayMessage.setText("You killed the orc!\nThe orc dropped a shiny necklace!\n\n(You obtained a ruby necklace)\n\nMaybe you can return to the village now.");
		
		gameChoice1.setText("Go East");
		gameChoice2.setText("");
		gameChoice3.setText("");
		gameChoice4.setText("");
	}
	
	public void gameOver() {
		playerHP = 0;
		gameDisplayMessage.setText("Your HP is: " + playerHP +"\nYou Died. \n\n<GAME OVER>" );
		
		gameChoice1.setVisible(false);
		gameChoice2.setVisible(false);
		gameChoice3.setVisible(false);
		gameChoice4.setVisible(false);
		playerHPLabel.setVisible(false);
		playerWeaponLabel.setVisible(false);
		
	}
	
	public void ending() {
		
		gameDisplayMessage.setText("Guard:\nOh you killed the orc!!? You are so amazing!!!\nIt seems like you are a trustworthy person!\nPlease enter our village!\n\n<THE END>");
		
		
		gameChoice1.setVisible(false);
		gameChoice2.setVisible(false);
		gameChoice3.setVisible(false);
		gameChoice4.setVisible(false);
		playerHPLabel.setVisible(false);
		playerWeaponLabel.setVisible(false);
	}
	
	public class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == startButton) {
				
				gameDisplay();
			}
			
		}
		
	}
	
	public class ChoiceHandler implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			choiceValue = e.getActionCommand();
			
			switch(currentPosition) {
			case "villageEntrance" :
				
				switch(choiceValue) {
				case "1": 
					if(!rubyNecklace) {
					choiceTalkGuard(); 
					}
					else {
						ending();
					}
					
					break;
				case "2": choiceAttackGuard(); break;
				case "3": choiceLeave(); break;
				}
				
				break;
			
			case "talkGuard" : 
			switch(choiceValue) {
			case "1" : villageEntrance(); break;
				
			}	
				break;	
			
			case "attackGuard" :
				
				switch(choiceValue) {
				
				case "1": villageEntrance(); break;
				}
				
				break;
			
			case "pathway" : 
				
				switch(choiceValue) {
				
				case "1" : north(); break;
				case "2" : east(); break;
				case "3" : west(); break;
				case "4" : villageEntrance(); break;
				}
			
				break;
				
			case "north" :
				switch(choiceValue) {
				case "1" : choiceLeave(); break;
				}
				
				break;
				
			case "east" :
				switch(choiceValue) {
				case "1" : choiceLeave(); break;
				}
				
				break;
				
			case "west" :
				switch(choiceValue) {
				case "1": fight(); break;
				case "2": choiceLeave(); break;
				}
				
				break;
				
			case "fight" :
				switch(choiceValue) {
				case "1": attack(); break;
				case "2": choiceLeave(); break;
				}
				
				break;
			
			case "attack" :
				switch(choiceValue) {
				case "1" : 
				if(monsterHP<=0) {
					
					win();
				}
				
				else{
					monsterDamage();
				} 
				break;
			}
				break;
				
			case "monsterDamage" :
				switch(choiceValue) {
				case "1" : 
					if(playerHP<=0){ 
						gameOver();
					} 
					else {
						fight();	
					} 
					break;

				}
				
				break;
				
			case "win" :
				switch(choiceValue) {
				case "1" : choiceLeave(); break;
				}
				break;
			}
		}
	}
	
	

}



