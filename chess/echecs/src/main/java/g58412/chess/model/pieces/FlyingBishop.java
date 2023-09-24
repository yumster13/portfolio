/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g58412.chess.model.pieces;

import g58412.chess.model.Board;
import g58412.chess.model.Color;
import g58412.chess.model.Direction;
import g58412.chess.model.Position;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yumst
 */
public class FlyingBishop extends Piece {
    
    public FlyingBishop(Color color){
        super(color);
    }
    @Override
    public List<Position> getPossibleMoves(Position position, Board board) {
        Direction[] dir = {Direction.NE, Direction.SE, Direction.NW, Direction.SW};
        return Moves(position, board, dir);
    }

    @Override
    public List<Position> getCapturePositions(Position position, Board board) {
        return getPossibleMoves(position, board);
    }

    protected List<Position> Moves(Position position, Board board, Direction[] dir) {
        List<Position> possibleMoves = new ArrayList();
        for (int i = 0; i < dir.length; i++) {
            Position nextPos = position.next(dir[i]);
            while (board.contains(nextPos)) {
                if (!board.isFree(nextPos) && board.containsOppositeColor(nextPos, board.getPiece(position).getColor())) {
                    possibleMoves.add(nextPos);
                } else if (board.isFree(nextPos)) {
                    possibleMoves.add(nextPos);
                }
                nextPos = nextPos.next(dir[i]);
            }
            

        }
        return possibleMoves;
    }
}
