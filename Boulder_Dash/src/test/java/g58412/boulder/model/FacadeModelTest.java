package g58412.boulder.model;

import g58412.boulder.model.Objects.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FacadeModelTest {

    FacadeGame facadeGame = new FacadeGame();
    @BeforeEach
    void SetUp(){
        facadeGame.startLevel(0);
    }

    @Test
    public void isFinishedFalse(){
        assertFalse(facadeGame.isFinished());
    }
    @Test
    public void isFinishedTrue(){
        facadeGame.action("R");
        facadeGame.action("R");
        facadeGame.action("R");
        facadeGame.action("R");
        facadeGame.action("U");
        facadeGame.action("D");
        assertTrue(facadeGame.isFinished());
    }

    @Test
    void actionRIGHT(){
        facadeGame.action("R");
        assertEquals(new Position(3,3),facadeGame.getGame().getBoard().getPlayer().getPos());
    }
    @Test
    void actionUP(){
        facadeGame.action("U");
        assertEquals(new Position(2,2),facadeGame.getGame().getBoard().getPlayer().getPos());
    }
    @Test
    void actionLEFT(){
        facadeGame.action("L");
        assertEquals(new Position(1,3),facadeGame.getGame().getBoard().getPlayer().getPos());
    }

    @Test
    void actionInvalid(){
        assertThrows(IllegalArgumentException.class,
                ()->{
                    facadeGame.action("yuio");
                });
    }
    @Test
    void actionCaseNotSensitive(){
        facadeGame.action("r");
        assertEquals(new Position(3,3),facadeGame.getGame().getBoard().getPlayer().getPos());
    }

    @Test
    void nbDiamondsCollected0(){
        assertEquals(facadeGame.nbDiamondsCollected(),0);
    }
    @Test
    void nbDiamondsToCollect1(){
        assertEquals(facadeGame.nbDiamondsToCollect(),1);
    }
    @Test
    void nbDiamondsCollected1(){
        facadeGame.action("U");
        facadeGame.action("U");
        assertEquals(facadeGame.nbDiamondsCollected(),1);
    }
    @Test
    void nbDiamondsToCollect0(){
        facadeGame.action("U");
        facadeGame.action("U");
        assertEquals(facadeGame.nbDiamondsToCollect(),0);
    }



}