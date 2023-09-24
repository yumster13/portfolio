/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g58412.chess.model.pieces;

import g58412.chess.model.Board;
import g58412.chess.model.Color;
import g58412.chess.model.Position;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yumst
 */
public class Knight extends Piece{
    public Knight(Color color){
        super(color);
    }

    @Override
    public List<Position> getPossibleMoves(Position position, Board board) {
       return Move(position,board);
    }
    private List<Position> Move(Position position,Board board){
        List<Position> possibleMoves = new ArrayList();
        for (int i = 1; i < 9; i++) {
            if(board.contains(SwitchPos(position,i))){
            if(board.isFree(SwitchPos(position,i))){
                possibleMoves.add(SwitchPos(position,i));
            }else{
                if(board.containsOppositeColor(SwitchPos(position,i), board.getPiece(position).getColor())){
                    
                possibleMoves.add(SwitchPos(position,i));
                }
            }
            
            }
                
            
            
        }
        return possibleMoves;
    }
    private Position SwitchPos(Position pos, int i){
        switch(i){
            case 1: return newPosition(pos,1,-2);
            case 2: return newPosition(pos,2,-1);
            case 3: return newPosition(pos,2,1);
            case 4: return newPosition(pos,1,2);
            case 5: return newPosition(pos,-1,2);
            case 6: return newPosition(pos,-2,1);
            case 7: return newPosition(pos,-2,-1);
            case 8: return newPosition(pos,-1,-2);
        }
        return null;
    }
    private Position newPosition(Position pos,int lg, int col){
        return new Position(pos.getRow()+lg,pos.getColumn()+col);
    }

    @Override
    public List<Position> getCapturePositions(Position position, Board board) {
        return getPossibleMoves(position, board);
    }


}
