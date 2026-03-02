package game;

/*
CLASS: YourGameNameoids
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;
import java.awt.event.*;
import java.awt.Point;

class DeathRun extends Game {

  private enum GameState {
    MENU, PLAYING, GAME_OVER
  }

  private enum Difficulty {
    EASY, MEDIUM, HARD
  }

  private static final double GRAVITY = 0.5;
  //did some experimenting to find the best jump strength
  private static final double JUMP_STRENGTH = -12; 
  private static final double MOVE_SPEED = 5;

  private GameState currentState = GameState.MENU;
  private Difficulty selectedDifficulty;

  private int lives;

  private DifficultyButton easyButton, mediumButton, hardButton;
  private MenuButton playButton;

  private Player player;
  private double floorY;

  private boolean leftPressed = false;
  private boolean rightPressed = false;
  private boolean jumpPressed = false;

  public DeathRun() {
    super("Death Run!",800,600);
    this.setFocusable(true);
    this.requestFocus();

    floorY = height - 100;

    int menuCenterX = width / 2;
    easyButton = new DifficultyButton(new Rectangle(menuCenterX - 220, 250, 140, 44), "Easy", Difficulty.EASY);
    mediumButton = new DifficultyButton(new Rectangle(menuCenterX - 70, 250, 140, 44), "Medium", Difficulty.MEDIUM);
    hardButton = new DifficultyButton(new Rectangle(menuCenterX + 80, 250, 140, 44), "Hard", Difficulty.HARD);
    playButton = new MenuButton(new Rectangle(menuCenterX - 95, 340, 190, 54), "Play");

    initPlayer();
    initInput();
  }

  private void initPlayer() {
    int playerWidth = 40;
    int playerHeight = 60;
    int startX = width / 4;
    int startY = (int) (floorY - playerHeight);
    player = new Player(startX, startY, playerWidth, playerHeight);
  }

  private void initInput() {
    addKeyListener(new KeyAdapter() { // Anonymous class
    	
      public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
          leftPressed = true;
        } else if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
          rightPressed = true;
        } else if (code == KeyEvent.VK_UP || code == KeyEvent.VK_SPACE) {
          jumpPressed = true;
        }
      }
      
      public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
          leftPressed = false;
        } else if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
          rightPressed = false;
        } else if (code == KeyEvent.VK_UP || code == KeyEvent.VK_SPACE) {
          jumpPressed = false;
        }
      }
    });

    addMouseListener(new MouseAdapter() {
    	
      public void mouseClicked(MouseEvent e) {
        if (currentState != GameState.MENU) {
          return;
        }
        Point p = e.getPoint();

        if (easyButton.contains(p)) {
          selectedDifficulty = Difficulty.EASY;
        } else if (mediumButton.contains(p)) {
          selectedDifficulty = Difficulty.MEDIUM;
        } else if (hardButton.contains(p)) {
          selectedDifficulty = Difficulty.HARD;
        } else if (playButton.contains(p)) {
          startGameForDifficulty();
        }
      }
    });
  }

  private void startGameForDifficulty() {
    switch (selectedDifficulty) {
      case EASY:
        lives = 3;
        break;
      case MEDIUM:
        lives = 2;
        break;
      case HARD:
        lives = 1;
        break;
    }
    initPlayer();
    currentState = GameState.PLAYING;
  }

  public Rectangle getPlayerBounds() {
    if (player == null) {
      return new Rectangle(0, 0, 0, 0);
    }
    return player.getBounds();
  }

  public void loseLife() {
    if (currentState != GameState.PLAYING) {
      return;
    }
    lives--;
    if (lives <= 0) {
      currentState = GameState.GAME_OVER;
    } else {
      initPlayer();
    }
  }
  
  public int getLives() {
	  return lives;
  }

	public void paint(Graphics brush) {
    switch (currentState) {
      case MENU:
        paintMenu(brush);
        break;
      case PLAYING:
        paintPlaying(brush);
        break;
      case GAME_OVER:
        paintGameOver(brush);
        break;
    }
  }

  private void paintMenu(Graphics brush) {
    brush.setColor(Color.black);
    brush.fillRect(0, 0, width, height);
    // cleaner, more readable fonts
    brush.setColor(Color.white);
    brush.setFont(new Font("Monospace", Font.BOLD, 40));
    brush.drawString("Death Run", width / 2 - 110, 150);

    // text to choose difficulty
    brush.setFont(new Font("Monospace", Font.PLAIN, 18));
    easyButton.draw(brush, selectedDifficulty == Difficulty.EASY);
    mediumButton.draw(brush, selectedDifficulty == Difficulty.MEDIUM);
    hardButton.draw(brush, selectedDifficulty == Difficulty.HARD);

    brush.setFont(new Font("Monospace", Font.PLAIN, 16));
    brush.drawString("Easy: 3 lives   Medium: 2 lives   Hard: 1 life", width / 2 - 170, 315);

    playButton.draw(brush, true);
  }

  private void paintPlaying(Graphics brush) {
    brush.setColor(Color.black);
    brush.fillRect(0, 0, width, height);

    brush.setColor(Color.darkGray);
    brush.fillRect(0, (int) floorY, width, height - (int) floorY);

    if (player != null) {
      double vx = 0;
      if (leftPressed) {
        vx -= MOVE_SPEED;
      }
      if (rightPressed) {
        vx += MOVE_SPEED;
      }
      if (jumpPressed && player.isOnGround()) {
        player.setVy(JUMP_STRENGTH);
      }

      player.setVx(vx);
      player.update(GRAVITY, floorY, width);
      player.draw(brush);
    }

    brush.setColor(Color.white);
    brush.setFont(new Font("Monospace", Font.PLAIN, 16));
    brush.drawString("Lives: " + lives, 10, 20);
    brush.drawString("Difficulty: " + selectedDifficulty.name(), 10, 40);
  }
  
  // display game over screen with text
  private void paintGameOver(Graphics brush) {
    brush.setColor(Color.black);
    brush.fillRect(0, 0, width, height);

    brush.setColor(Color.red);
    brush.setFont(new Font("Monospace", Font.BOLD, 32));
    brush.drawString("Game Over", width / 2 - 90, 200);

    brush.setColor(Color.white);
    brush.setFont(new Font("Monospace", Font.PLAIN, 18));
    brush.drawString("Difficulty: " + selectedDifficulty.name(), width / 2 - 80, 240);
    brush.drawString("Lives remaining: 0", width / 2 - 80, 270);
    brush.drawString("Your partner will add play-again and high score here.", width / 2 - 200, 310);
  }

	public static void main (String[] args) {
		// lambda function
	    EventQueue.invokeLater(() -> {
	      DeathRun a = new DeathRun();
	      a.repaint();
	    });
	}

  // Inner class #1: generic menu button
  private class MenuButton {
    protected Rectangle box;
    protected String label;

    MenuButton(Rectangle box, String label) {
    	this.box = box;
        this.label = label;
    }

    boolean contains(Point p) {
    	return box.contains(p);
    }

    void draw(Graphics g, boolean enabled) {
    	g.setColor(enabled ? Color.darkGray : Color.gray);
    	g.fillRect(box.x, box.y, box.width, box.height);
    	g.setColor(Color.white);
    	g.drawRect(box.x, box.y, box.width, box.height);
    	g.setFont(new Font("Monospace", Font.BOLD, 20));
    	int textX = box.x + (box.width / 2) - (label.length() * 6);
  	  	g.drawString(label, textX, box.y + 34);
    }
  }

  // Inner class #2: difficulty button
  private class DifficultyButton extends MenuButton {
    private Difficulty difficulty;

    DifficultyButton(Rectangle box, String label, Difficulty difficulty) {
      super(box, label);
      this.difficulty = difficulty;
    }

    void draw(Graphics g, boolean selected) {
      if (selected) {
    	  g.setColor(Color.green.darker());
      } else {
    	  g.setColor(Color.gray);
      }
      
	  g.fillRect(box.x, box.y, box.width, box.height);
	  g.setColor(Color.white);
	  g.drawRect(box.x, box.y, box.width, box.height);
	  g.setFont(new Font("Monospace", Font.BOLD, 18));
	  g.drawString(label, box.x + 18, box.y + 28);
    }

    Difficulty getDifficulty() {
    	return difficulty;
    }
  }
}