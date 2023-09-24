package g58412.boulder.model;

import g58412.boulder.model.Objects.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Board {
    private Square[][] Board;
    private int diamonds;
    private Level level;
    private Sortie sortie;
    private boolean finished;
    private Gamestate gamestate;



    /**
     * Constructor for the board:
     *      -diamonds = 0
     *      -level = the number of the level
     *      -sortie = the position of the level exit
     *      -finished = to check if the level is finished or not
     * @param level the current level game
     */
    public Board(Level level){
        Board = new Square[level.getMap().length][level.getMap()[0].length];
        for (int i = 0; i < level.getMap().length; i++) {
            for (int j = 0; j < level.getMap()[i].length; j++) {
                this.Board[i][j] = new Square(new EmptySquare(new Position(j, i)));
            }

        }
        this.level = level;
        this.diamonds = 0;
        this.sortie = level.getSortie();
        this.finished = false;
        this.gamestate = Gamestate.PLAY;
    }

    /**
     * Sets the board for the game
     * @param board the board of the current level
     */
    public void setBoard(Square[][] board) {
        Board = board;
    }

    /**
     * Gets the board of the level
     * @return the array[][] of squares
     */
    public Square[][] getBoard() {
        return Board;
    }

    /**
     * Set a piece on the square
     * @param square a square of the board
     */
    public void setSquare(EmptySquare square){
        Board[square.getPos().getY()][square.getPos().getX()].setPiece(square);
    }

    /**
     * Sets the piece on the square
     * @param pos the position of the square to check
     */
    public void setSquare(EmptySquare square, Position pos){
        Board[pos.getY()][pos.getX()].setPiece(square);
    }

    /**
     * Returns the piece on that square
     * @param pos the position of the square on the board
     * @return the square at that position
     */
    public EmptySquare getSquare(Position pos){
        return Board[pos.getY()][pos.getX()].getPiece();
    }
    /**
     * Remove a piece on a square and set it to an Empty piece
     * @param pos the position of the piece to remove
     */
    public void setEmpty(Position pos){
        Board[pos.getY()][pos.getX()].setPiece(new Empty(pos));
    }

    /**
     * Returns the position of the player at any time, Position(-1,-1) if the player is dead
     * @return the position of the player at any time
     */
    public Player getPlayer(){
        for (int i = 0; i < Board.length; i++) {
            for (int j = 0; j < Board[i].length; j++) {
                if(Board[i][j].getPiece().getClass() == Player.class){
                    return new Player(new Position(j, i));
                }
            }
        }
        return new Player(new Position(-1, -1));
    }

    /**
     * Getter for the List of stones and diamonds on the board
     * The only objects that can move on the board
     * @return the list of stones on the board
     */
    public ArrayList<Position> getMovingObjects(){
        ArrayList<Position> stones= new ArrayList<>();
        for (int i = 0; i < Board.length; i++) {
            for (int j = 0; j < Board[i].length; j++) {
                if(Board[i][j].getPiece().getClass() == Stone.class
                    || Board[i][j].getPiece().getClass() == Diamond.class){
                    stones.add(new Position(j, i));
                }
            }
        }
        return stones;
    }

    /**
     * Enables the player to move in a given direction
     * @param move the direction you wand to move to
     */
    public void move(String move){
        switch(move){
            case "U": moveDirection(Direction.UP);break;
            case "D": moveDirection(Direction.DOWN);break;
            case "L": moveDirection(Direction.LEFT);break;
            case "R": moveDirection(Direction.RIGHT);break;
        }
    }

    /**
     * All the conditions to check if you can move in the given direction
     * @param dir the direction you want to move in
     */
    public void moveDirection(Direction dir){
        Position newPos = new Position(getPlayer().getPos().getX()+dir.getDeltaX(), getPlayer().getPos().getY()+dir.getDeltaY());
        if(!(getSquare(newPos) instanceof Wall)){
            Position oldPos = getPlayer().getPos();
            //Checks if the next square is a diamond
            if(getSquare(oldPos.next(dir)) instanceof Diamond || getSquare(oldPos.next(dir)) instanceof DiamondNoMove){
                setSquare(getPlayer(),(newPos));
                setEmpty(oldPos);
                diamonds++;
            }
            //Checks if you can move the stone
            if(getSquare(newPos) instanceof Stone && (dir == Direction.RIGHT || dir == Direction.LEFT)) {
                if(getSquare(newPos) instanceof Stone && getSquare(newPos.next(dir)) instanceof Empty){
                    moveStone(newPos,oldPos,dir);
                }
            }
            //Checks if you can exit the level
            if(getSquare(newPos) instanceof Sortie && sortie.isOpen()) {
                setEmpty(oldPos);
                setSquare(getPlayer(),(newPos));
                setGamestate(Gamestate.EXIT);
            }
            //Checks if the next direction is sand or empty if yes you can move
            if(getSquare(newPos) instanceof Sand || getSquare(newPos) instanceof Empty){
                setSquare(getPlayer(),newPos);
                setEmpty(oldPos);
            }
        }
        if(getSquare(newPos) instanceof Wall || getSquare(newPos) instanceof Stone){
            throw new IllegalArgumentException("You cant move in this direction");
        }
    }

    /**
     * Checks if the stone can move down
     * @param squares the list of squares to move down
     * @param index the index you want to remove the old stones of
     * @param pos the position of the stone
     * @return true if you can move in that direction
     */
    public Position checkUnderStone(ArrayList<Position> squares, int index, Position pos){
        if(getSquare(pos.next(Direction.DOWN))instanceof Empty) {
            if (getSquare(pos.next(Direction.DOWN).next(Direction.DOWN)) instanceof Player) {
                setSquare(getSquare(pos), pos.next(Direction.DOWN).next(Direction.DOWN));
                setEmpty(pos);
                squares.remove(index);
                squares.add(index, pos.next(Direction.DOWN).next(Direction.DOWN));
                setGamestate(Gamestate.STOPPED);
            } else {
                squares.set(index, pos.next(Direction.DOWN));
                setSquare(getSquare(pos), pos.next(Direction.DOWN));
                setEmpty(pos);
                return pos.next(Direction.DOWN);
            }
        }return pos;
    }
    /**
     * Checks if the stone can roll down
     * @param squares the list of squares to move down
     * @param pos the position of the stone
     * @return true if you can move in that direction
     */
    public Position checkRollStone(ArrayList<Position> squares, int index, Position pos,Direction dir){
        if(getSquare(pos.next(dir))instanceof Empty &&
                (getSquare(pos.next(Direction.DOWN)) instanceof Stone||
                        getSquare(pos.next(Direction.DOWN)) instanceof Diamond||
                        getSquare(pos.next(Direction.DOWN)) instanceof Wall)
                && getSquare((pos.next(Direction.DOWN).next(dir))) instanceof Empty){
            squares.set(index,pos.next(Direction.DOWN).next(dir));
            setSquare(getSquare(pos),pos.next(Direction.DOWN).next(dir));
            setEmpty(pos);
            if(getSquare(pos.next(Direction.DOWN).next(dir).next(Direction.DOWN)) instanceof Player){
                squares.remove(index);
                squares.add(index, pos.next(Direction.DOWN).next(Direction.DOWN));
                setGamestate(Gamestate.STOPPED);
            }
            return pos.next(Direction.DOWN).next(dir);
        }else return pos;
    }

    /**
     * Enables the player to push the rock
     * @param newPos the newPosition for the player
     * @param oldPos the oldPosition of the player
     * @param dir the direction you want to push to
     */
    public void moveStone(Position newPos, Position oldPos,Direction dir){
        setSquare(getSquare(newPos),newPos.next(dir));
        setSquare(getPlayer(),(newPos));
        setEmpty(oldPos);
    }

    /**
     * Checks the amount of diamonds still to collect
     * @return the amount of diamonds to collect
     */
    public int nbDiamondstoCollect(){
        int diamonds = 0;
        for (int i = 0; i < level.getMap().length; i++) {
            for (int j = 0; j < level.getMap()[i].length; j++) {
                if(getSquare(new Position(j,i))instanceof Diamond)diamonds++;
            }
        }
        return diamonds;
    }

    /**
     * The amount of diamonds collected by the player
     * @return
     */
    public int nbDiamondsCollected(){
        return level.getNbDiamonds()-nbDiamondstoCollect();
    }

    /**
     * Checks if you have to open the exit
     *  - the amount of diamonds needed is collected
     */
    public void OpenExit(){
        if(diamonds == level.getDiamondsOpenExit()) {
            this.sortie.setOpen(true);
            System.out.println("The exit is open");
            this.sortie.setColor('E');
            getSquare(sortie.getPos()).setColor('E');
        }
    }

    /**
     * Setter for a gameState
     * @param gamestate a value of the enumeration you want to set
     */
    public void setGamestate(Gamestate gamestate) {
        this.gamestate = gamestate;
    }

    /**
     * Getter for the gameState
     * @return a value of the enumeration you want to get
     */
    public Gamestate getGamestate() {
        return gamestate;
    }

    /**
     * Getter for the exit of the level
     * @return an object Sortie which is the exit of the game
     */
    public Sortie getSortie() {
        return sortie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Board board)) return false;
        return diamonds == board.diamonds && Arrays.equals(getBoard(), board.getBoard()) && Objects.equals(level, board.level) && Objects.equals(getSortie(), board.getSortie()) && getGamestate() == board.getGamestate();
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(diamonds, level, getSortie(), getGamestate());
        result = 31 * result + Arrays.hashCode(getBoard());
        return result;
    }
}
