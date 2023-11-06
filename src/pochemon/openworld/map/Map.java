package pochemon.openworld.map;

import java.awt.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.BufferedImage;
import java.util.Scanner;

import pochemon.GamePanel;
import pochemon.openworld.entities.*;
import pochemon.battle.objects.entities.*;

public class Map {
    public GamePanel gamePanel;
    private static final String BASE_PATH = "../res/maps/";
    private String path;
    public static final Tile[] tiles = new Tile[6];
    public final Tile[][] map = new Tile[69][69]; // A modifier en fonction
                                                  // de la taille par defaut
                                                  // des maps.
    public final Trainer[] trainers = new Trainer[4];
    private static final TrainerList trainerList = new TrainerList();

    public Map(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        path = BASE_PATH+"map1.txt";
        loadTiles();
        loadMap();
        loadTrainers(trainerList.list);
    }

    public Map(GamePanel gamePanel, String mapName) {
        this.gamePanel = gamePanel;
        path = BASE_PATH+mapName+".txt";
        loadTiles();
        loadMap();
        loadTrainers(trainerList.list);
    }

    private void loadTrainers(Trainer[] trainerList) {
        for(int i=0; i<trainerList.length; i++) {
            trainers[i] = trainerList[i];
            trainers[i].addMap(this);
            map[trainers[i].getY()][trainers[i].getX()] = tiles[5];
        }
    }

    private void loadMap() {
        Scanner scanner = null;

        try {
            int i=0;
            String line;
            String[] line1;
            scanner = new Scanner(new File(path));

            while(scanner.hasNextLine()) {
                line = scanner.nextLine();
                line1 = line.split(" ");

                for(int j=0; j<line1.length; j++) {
                    map[i][j] = tiles[Integer.parseInt(line1[j])];
                }

                i++;
            }
        } catch(IOException e) {
            System.out.println("res folder was altered.");
            System.exit(1);
        } catch(NumberFormatException e) {
            scanner.close();
            System.out.println("Map file was altered.");
            System.exit(1);
        } finally {
            scanner.close();
        }
    }

    private static void loadTiles() {
        try {
            BufferedImage grass = ImageIO.read(new FileInputStream("../res/sprites/grass.png"));
            tiles[0] = new Tile(true, grass);

            BufferedImage tree = ImageIO.read(new FileInputStream("../res/sprites/tree.png"));
            tiles[1] = new Tile(false, tree);

            BufferedImage sand = ImageIO.read(new FileInputStream("../res/sprites/sand.png"));
            tiles[2] = new Tile(true, sand);

            BufferedImage water = ImageIO.read(new FileInputStream("../res/sprites/water.png"));
            tiles[3] = new Tile(false, water);

            // Blocs non-marchables pour dresseurs.
            tiles[4] = new Tile(false, grass);
            tiles[5] = new Tile(false, sand);
        } catch(IOException e) {
            System.out.println("res folder was altered.");
            System.exit(1);
        }
    }

    public void draw(Graphics2D g) {
        int middleX = gamePanel.maxScreenCol/2;
        int middleY = gamePanel.maxScreenRow/2;
        BufferedImage sprite;
        int mapRow, mapCol;

        for(int i=0; i<gamePanel.screenWidth; i+=gamePanel.tileSize) {
            for(int j=0; j<gamePanel.screenHeight; j+=gamePanel.tileSize) {
                try {
                    mapCol = gamePanel.player.getX() - middleX + i/gamePanel.tileSize;
                    mapRow = gamePanel.player.getY() - middleY + j/gamePanel.tileSize;
                    sprite = map[mapRow][mapCol].sprite;
                } catch(ArrayIndexOutOfBoundsException e) {
                    continue;
                }

                // Si c'est un arbre, dessiner de l'herbe en-dessous.

                if(map[mapRow][mapCol]==tiles[1]) {
                    g.drawImage(tiles[0].sprite, i, j, gamePanel.tileSize, gamePanel.tileSize, null);
                }

                g.drawImage(sprite, i, j, gamePanel.tileSize, gamePanel.tileSize, null);

                for(int k=0; k<trainers.length; k++) {
                    if(trainers[k].getX()==mapCol && trainers[k].getY()==mapRow) {
                        trainers[k].draw(g);
                    }
                }
            }
        }
    }
}
