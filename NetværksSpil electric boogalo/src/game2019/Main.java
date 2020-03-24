package game2019;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.*;

public class Main extends Application {

	public static final int size = 20;
	public static final int scene_height = size * 20 + 100;
	public static final int scene_width = size * 20 + 200;

	public static Image image_floor;
	public static Image image_wall;
	public static Image hero_right, hero_left, hero_up, hero_down;

	public static Player me;
	public static List<Player> players = new ArrayList<Player>();
	public String playerIP[] = { "10.24.67.230", "10.24.68.182", "10.24.4.189" };

	private static Label[][] fields;
	private static TextArea scoreList;

	private static Socket clientSocket;
	private static boolean isStarted = false;

	private static String[] board = { // 20x20
			"wwwwwwwwwwwwwwwwwwww", "w        ww        w", "w w  w  www w  w  ww", "w w  w   ww w  w  ww",
			"w  w               w", "w w w w w w w  w  ww", "w w     www w  w  ww", "w w     w w w  w  ww",
			"w   w w  w  w  w   w", "w     w  w  w  w   w", "w ww ww        w  ww", "w  w w    w    w  ww",
			"w        ww w  w  ww", "w         w w  w  ww", "w        w     w  ww", "w  w              ww",
			"w  w www  w w  ww ww", "w w      ww w     ww", "w   w   ww  w      w", "wwwwwwwwwwwwwwwwwwww" };
	
	private static boolean[][] legalpositions;
	
	

	// -------------------------------------------
	// | Maze: (0,0) | Score: (1,0) |
	// |-----------------------------------------|
	// | boardGrid (0,1) | scorelist |
	// | | (1,1) |
	// -------------------------------------------

	@Override
	public void start(Stage primaryStage) {
		try {
			GridPane grid = new GridPane();
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(0, 10, 0, 10));

			Text mazeLabel = new Text("Maze:");
			mazeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));

			Text scoreLabel = new Text("Score:");
			scoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));

			scoreList = new TextArea();

			GridPane boardGrid = new GridPane();

			image_wall = new Image(getClass().getResourceAsStream("Image/wall4.png"), size, size, false, false);
			image_floor = new Image(getClass().getResourceAsStream("Image/floor1.png"), size, size, false, false);

			hero_right = new Image(getClass().getResourceAsStream("Image/heroRight.png"), size, size, false, false);
			hero_left = new Image(getClass().getResourceAsStream("Image/heroLeft.png"), size, size, false, false);
			hero_up = new Image(getClass().getResourceAsStream("Image/heroUp.png"), size, size, false, false);
			hero_down = new Image(getClass().getResourceAsStream("Image/heroDown.png"), size, size, false, false);

			fields = new Label[20][20];
			legalpositions = new boolean[20][20];
			for (int j = 0; j < 20; j++) {
				for (int i = 0; i < 20; i++) {
					switch (board[j].charAt(i)) {
					case 'w':
						legalpositions[i][j] = false;
						fields[i][j] = new Label("", new ImageView(image_wall));
						break;
					case ' ':
						legalpositions[i][j] = true;
						fields[i][j] = new Label("", new ImageView(image_floor));
						break;
					default:
						throw new Exception("Illegal field value: " + board[j].charAt(i));
					}
					boardGrid.add(fields[i][j], i, j);
				}
			}
					
			scoreList.setEditable(false);

			grid.add(mazeLabel, 0, 0);
			grid.add(scoreLabel, 1, 0);
			grid.add(boardGrid, 0, 1);
			grid.add(scoreList, 1, 1);
			
			Player me = new Player("Simon", 1, 1, "up");
			players.add(me);
			setrandom(me);

			Scene scene = new Scene(grid, scene_width, scene_height);
			primaryStage.setScene(scene);
			primaryStage.show();

			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

			scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
				switch (event.getCode()) {

				case UP:
					try {
						if (isStarted) {
							String sentence = me.getName() + " " + me.getXpos() + " " + (me.getYpos() - 1) + " " + "up";
							outToServer.writeBytes(sentence + "\n");
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case DOWN:
					try {
						if (isStarted) {
							String sentence = me.getName() + " " + me.getXpos() + " " + (me.getYpos() + 1) + " "
									+ "down";
							outToServer.writeBytes(sentence + "\n");
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case LEFT:
					try {
						if (isStarted) {
							String sentence = me.getName() + " " + (me.getXpos() - 1) + " " + me.getYpos() + " "
									+ "left";
							outToServer.writeBytes(sentence + "\n");
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case RIGHT:
					try {
						if (isStarted) {
							String sentence = me.getName() + " " + (me.getXpos() + 1) + " " + me.getYpos() + " "
									+ "right";
							outToServer.writeBytes(sentence + "\n");
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					break;
				case SPACE:
					try {
						if (!isStarted) {
							String sentence = me.getName() + " " + (me.getXpos()) + " " + me.getYpos() + " "
									+ me.getDirection();
							outToServer.writeBytes(sentence + "\n");
							isStarted = true;
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				default:
					break;
				}
			});

			// Setting up standard players

			

			scoreList.setText(getScoreList());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void playerMoved(Player player, int delta_y, int delta_x) {
		Platform.runLater(() -> {
			int x = player.getXpos(), y = player.getYpos();
			if (board[y + delta_y].charAt(x + delta_x) == 'w') {
				player.addPoints(-1);
			} else {
				Player p = getPlayerAt(x + delta_x, y + delta_y);
				if (p != null) {
					player.addPoints(10);
					p.addPoints(-10);
				} else {
					player.addPoints(1);

					fields[x][y].setGraphic(new ImageView(image_floor));

					x += delta_x;
					y += delta_y;

					if (player.getDirection().equals("right")) {
						fields[x][y].setGraphic(new ImageView(hero_right));
					}
					;
					if (player.getDirection().equals("left")) {
						fields[x][y].setGraphic(new ImageView(hero_left));
					}
					;
					if (player.getDirection().equals("up")) {
						fields[x][y].setGraphic(new ImageView(hero_up));
					}
					;
					if (player.getDirection().equals("down")) {
						fields[x][y].setGraphic(new ImageView(hero_down));
					}
					player.setXpos(x);
					player.setYpos(y);
				}
			}
		scoreList.setText(getScoreList());
		});
	}

	public static String getScoreList() {
		StringBuffer b = new StringBuffer(100);
		for (Player p : players) {
			b.append(p + "\r\n");
		}
		return b.toString();
	}

	public static Player getPlayerAt(int x, int y) {
		for (Player p : players) {
			if (p.getXpos() == x && p.getYpos() == y) {
				return p;
			}
		}
		return null;
	}

	public static void main(String[] args) throws Exception, IOException {

		clientSocket = new Socket("10.24.", 9999);
		Main main = new Main();

		ReadThread readThread = main.new ReadThread(clientSocket);

		readThread.start();
		launch(args);

	}
	
	public static void setrandom(Player player) {
		Random r = new Random();
		boolean found = false;
		int x = r.nextInt(18 + 1);
		int y = r.nextInt(18 + 1);
		while (!found) {
		if (legalpositions[x][y] && getPlayerAt(x, y) == null) {
			player.setXpos(x);
			player.setYpos(y);
			fields[x][y].setGraphic(new ImageView(hero_up));
			found = true;
		}
		else {
			x = r.nextInt(18 + 1);
			x = r.nextInt(18 + 1);
		}
		}
	}

	public static void newPlayer(Player player) {
		Platform.runLater(() -> {
			
			if (player.getDirection().equals("right")) {
				fields[player.getXpos()][player.getYpos()].setGraphic(new ImageView(hero_right));
			}
			;
			if (player.getDirection().equals("left")) {
				fields[player.getXpos()][player.getYpos()].setGraphic(new ImageView(hero_left));
			}
			;
			if (player.getDirection().equals("up")) {
				fields[player.getXpos()][player.getYpos()].setGraphic(new ImageView(hero_up));
			}
			;
			if (player.getDirection().equals("down")) {
				fields[player.getXpos()][player.getYpos()].setGraphic(new ImageView(hero_down));
			}
		});
	}

	class ReadThread extends Thread {

		private Socket clientSocket;

		public ReadThread(Socket clientSocket) {
			this.clientSocket = clientSocket;
		}

		public synchronized void run() {
			while (true) {
				try {
					BufferedReader inFromServer = new BufferedReader(
							new InputStreamReader(clientSocket.getInputStream()));
					String sentence = inFromServer.readLine();
					String[] playerInfo = sentence.split(" ");
					System.out.println(sentence);
					boolean newPlayer = true;
					for (Player p : players) {
						if (p.getName().equals(playerInfo[0])) {
							playerMoved(p, Integer.parseInt(playerInfo[2]) - p.getYpos(),
									Integer.parseInt(playerInfo[1]) - p.getXpos());
							p.setDirection(playerInfo[3]);
							newPlayer = false;
						}
					}
					if (newPlayer) {
						Player p = new Player(playerInfo[0], Integer.parseInt(playerInfo[1]),
								Integer.parseInt(playerInfo[2]), playerInfo[3]);
						players.add(p);
						newPlayer(p);
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
