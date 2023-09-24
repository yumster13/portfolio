package g58412.boulder.model;

import g58412.boulder.model.Objects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Game game;
    private Levels levels;

    @BeforeEach
    void setUp() {
        this.levels = new Levels();
        this.game = new Game();
        game.startLevel(0);
    }

    @Test
    void move(){
        game.moveExecute("R");
        assertEquals(game.isGameState(), Gamestate.PLAY);
    }
    @Test
    void moveDeadStone(){
        game.moveExecute("R");
        game.moveExecute("R");
        game.moveExecute("R");
        game.moveExecute("R");
        game.moveExecute("U");
        game.moveExecute("D");
        assertEquals(game.isGameState(), Gamestate.STOPPED);
    }
    @Test
    void moveRight(){
        game.moveExecute("R");
        assertEquals(game.getBoard().getPlayer().getPos(), new Position(3,3));
    }
    @Test
    void moveLeft(){
        game.moveExecute("L");
        assertEquals(game.getBoard().getPlayer().getPos(), new Position(1,3));
    }
    @Test
    void moveUp(){
        game.moveExecute("U");
        assertEquals(game.getBoard().getPlayer().getPos(), new Position(2,2));
    }

    @Test
    void moveDeadDiamond(){
        game.moveExecute("U");
        game.moveExecute("D");
        assertEquals(game.isGameState(), Gamestate.STOPPED);
    }
    @Test
    void moveDiamondDown(){
        game.moveExecute("U");
        game.moveExecute("L");
        assertTrue(game.getBoard().getSquare(new Position(2,3))instanceof Diamond);
    }

    @Test
    void getPlayer(){
        assertEquals(game.getBoard().getPlayer().getPos(), new Position(2, 3));
    }

    @Test
    void exit(){
        game.moveExecute("U");
        game.moveExecute("U");
        game.getBoard().moveDirection(Direction.LEFT);
        assertEquals(game.isGameState(), Gamestate.EXIT);
    }
    @Test
    void DiamondCollected(){
        game.moveExecute("U");
        game.moveExecute("U");
        assertEquals(game.getBoard().nbDiamondsCollected(), 1);
    }
    @Test
    void DiamondToCollect(){
        assertEquals(game.getBoard().nbDiamondstoCollect(), 1);
    }

    @Test
    void OpenExit(){
        game.moveExecute("U");
        game.moveExecute("U");
        assertTrue(game.getBoard().getSortie().isOpen());
    }
    @Test
    void ClosedExit(){
        assertFalse(game.getBoard().getSortie().isOpen());
    }

    @Test
    void undo(){
        Board copy = game.copyBoard(game.getBoard());
        game.moveExecute("R");
        game.moveExecute("UNDO");
        for (int i = 0; i < game.getBoard().getBoard().length; i++) {
            for (int j = 0; j < game.getBoard().getBoard()[0].length; j++) {
                assertEquals(copy.getBoard()[i][j], game.getBoard().getBoard()[i][j]);
            }
        }
    }

    @Test
    void redo(){
        game.moveExecute("U");
        Board copy = game.copyBoard(game.getBoard());
        game.moveExecute("UNDO");
        game.moveExecute("REDO");
        for (int i = 0; i < game.getBoard().getBoard().length; i++) {
            for (int j = 0; j < game.getBoard().getBoard()[0].length; j++) {
                assertEquals(copy.getBoard()[i][j].getPiece().getColor(), game.getBoard().getBoard()[i][j].getPiece().getColor());
            }
        }
    }

    @Test
    void getMovingObjects(){
        ArrayList<Position> expected = new ArrayList();
        //Stones-----------------------------
        expected.add(new Position(3,2));
        expected.add(new Position(4,2));
        expected.add(new Position(6,1));
        //Diamond----------------------------
        expected.add(new Position(2,1));
        assertTrue(game.getBoard().getMovingObjects().containsAll(expected));
    }
    @Test
    void rollStones(){
        assertTrue(game.getBoard().getSquare(new Position(4,2))instanceof Stone);
    }

    @Test
    void getSortiePos(){
        assertEquals(game.getBoard().getSortie().getPos(), new Position(1,1));
    }

    @Test
    void noLevel(){
        assertThrows(IllegalArgumentException.class,
                ()->{
                    game.startLevel(10);
                });
    }

    @Test
    void moveStone(){
        game.moveExecute("R");
        game.moveExecute("R");
        game.moveExecute("R");
        game.moveExecute("U");
        game.moveExecute("R");
        game.moveExecute("R");
        game.moveExecute("L");
        assertTrue(game.getBoard().getSquare(new Position(5,3))instanceof Stone);
    }

    @Test
    void moveSand(){
        assertTrue(game.getBoard().getSquare(new Position(3,3))instanceof Sand);
    }
    @Test
    void moveEmpty(){
        game.moveExecute("R");
        assertTrue(game.getBoard().getSquare(new Position(2,3))instanceof Empty);
    }

    @Test
    void moveWallError(){
        game.moveExecute("L");
        assertThrows(IllegalArgumentException.class,
                ()->{
                    game.moveExecute("L");
                });
    }

}