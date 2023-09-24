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
public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
    }

    @Override
    public List<Position> getPossibleMoves(Position position, Board board) {
        return everyPossibleMove(position, board, Direction.values());
    }

    @Override
    public List<Position> getCapturePositions(Position position, Board board) {
        return getPossibleMoves(position, board);
    }

}
