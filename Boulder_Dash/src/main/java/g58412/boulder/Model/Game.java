package g58412.boulder.model;

import g58412.boulder.model.Command.CommandManager;
import g58412.boulder.model.Command.CommandMove;
import g58412.boulder.model.Objects.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class Game {

    private int level;
    Levels levels = new Levels();
    Stack<Board> boards;
    private Board board;
    boolean GameState;
    private CommandManager manager;
    private ArrayList<Position> movingObjects;


    /**
     * Constructor for the Game object
     */
    public Game() {
        manager = new CommandManager();
        boards = new Stack();
        this.GameState = true;
        this.movingObjects = new ArrayList<>();
    }

    /**
     * Starts the level: sets up the whole level to be able to play
     *  -level => n
     *  -reads the level from textfile
     *  -creates the board and sets all the pieces on the board -> initializeBoard
     *  -checks if any stones have to move
     * @param n the level to start and load
     */
    public void startLevel(int n){
        if(n <= levels.getLevels().size()){
            this.level = n;
            LireLevel(levels.getLevel(n));
            board = new Board(levels.getLevel(n));
            initializeBoard(levels.getLevel(n));
        }else throw new IllegalArgumentException("The level doesn't exist");
        checkStones(board.getMovingObjects());
    }

    /**
     * Sets all the pieces on the board
     * @param level the level to create
     */
    public void initializeBoard(Level level){
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int k = 0; k < board.getBoard()[i].length; k++) {
                switch (level.getMap()[i][k]) {
                    case 0:
                        board.setSquare(new Sand(new Position(k, i)));break;
                    case 1:
                        board.setSquare(new Wall(new Position(k, i)));break;
                    case 2:
                        board.setSquare(new Diamond(new Position(k, i)));
                        movingObjects.add(new Position(k, i));
                        break;
                    case 3:
                        board.setSquare(new Empty(new Position(k, i)));break;
                    case 4:{
                        board.setSquare(new Stone(new Position(k, i)));
                        movingObjects.add(new Position(k, i));
                        break;}
                    case 5:
                        board.setSquare(new Player(new Position(k, i)));break;
                    case 6:
                        board.setSquare(new Sortie(new Position(k, i)));break;
                    case 7:
                        board.setSquare(new DiamondNoMove(new Position(k, i)));break;
                }
            }

        }
    }

    /**
     * All the movements the player:
     *  -UP,RIGHT,LEFT,RIGHT
     *  -UNDO
     *  -REDO
     * @param direction the direction you want to move to -> UP,LEFT,RIGHT,DOWN
     */
    public void moveExecute(String direction){
        switch(direction){
            case "UNDO": {
                manager.undo();
            break;}
            case "REDO":{
                Position oldPlayerPos = board.getPlayer().getPos();
                Board copy = copyBoard(getBoard());
                manager.redo();
                if(!(oldPlayerPos.equals(board.getPlayer().getPos()))) {
                    boards.push(copyBoard(copy));
                }
                break;}
            case "EXIT" : board.setGamestate(Gamestate.STOPPED);break;
            default: {
                boards.push(copyBoard(getBoard()));
                CommandMove move = new CommandMove(boards, board, direction);
                manager.doCommand(move);
                board.OpenExit();
                break;
            }
        }
        checkStones(board.getMovingObjects());
    }

    public void nextLevel(){
        System.out.println("Vous avez gagné");
        if(level+1 < levels.getLevels().size()){
            startLevel(level+1);
            System.out.println("hello");
        }else System.out.println("You have finished the game");
    }
    /**
     * Enables to do a copy of the board without copying the references
     * @param toCopy the board you want to copy
     * @return the new board, the copy of the old one
     */
    public Board copyBoard(Board toCopy){
        Board copyBoard = new Board(levels.getLevel(level));
        for (int i = 0; i < toCopy.getBoard().length; i++) {
            for (int j = 0; j < toCopy.getBoard()[i].length; j++) {
                copyBoard.getBoard()[i][j].setPiece(toCopy.getBoard()[i][j].getPiece());
            }
        }
        return copyBoard;
    }

    /**
     * Returns the current game state
     * @return the current game state
     */
    public Gamestate isGameState() {
        return board.getGamestate();
    }

    /**
     * Checks if any stones have to move on the board
     * @param stones the list of Stones to move on the board
     */
    public void checkStones(ArrayList<Position> stones){
        for (int i = stones.size()-1; i >=0; i--) {
            Position oldPos;
            Position newPos;
            do {
                oldPos = new Position(stones.get(i).getX(),stones.get(i).getY());
                newPos = board.checkUnderStone(stones, i, stones.get(i));
                newPos = board.checkRollStone(stones, i,stones.get(i), Direction.RIGHT);
                newPos = board.checkRollStone(stones, i,stones.get(i), Direction.LEFT);
            }while(!newPos.equals(oldPos));
        }

    }

    /**
     * Getter for the attribute board, the board of the game
     * @return the object board of the game
     */
    public Board getBoard() {
        return board;
    }

    public int getLevel() {
        return level;
    }

    public Levels getLevels() {
        return levels;
    }

    /**
     * Enables you to read a level from a file
     * @param level the board of the level you want to read
     */
    public void LireLevel(Level level){
        int[][] map = new int[level.getHeight()][level.getWidth()];
        try {
            File file = new File("src/ressources/level"+level.getLevelNumber()+".txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            int i = 0;
            while((line = br.readLine()) != null)
            {
                for (int k = 0; k < line.length(); k++) {
                    map[i][k] = (int)line.charAt(k)-48;
                }
                i++;
            }
            level.setMap(map);
            fr.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
