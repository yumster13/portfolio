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
public class Pawn extends Piece {

    /**
     * Constructeur pour la pièce permet de créer une pièce
     *
     * @param color soit WHITE ou BLACK de enum Color
     */
    public Pawn(Color color) {
        super(color);
    }

    /**
     * Tous les déplacements possibles pour un pion
     *
     * @param position objet de type Position
     * @param board objet de type Board
     * @return tous les déplacements possibles
     */
    @Override
    public List<Position> getPossibleMoves(Position position, Board board) {
        List<Position> possibleMoves = new ArrayList();
        possibleMoves.addAll(déplacement(position, board));
        possibleMoves.addAll(DéplacementDiagonale(position, board));
        return possibleMoves;
    }
    /**
     * Déplacement de deux cases
     *
     * @param position objet de type Position
     * @param board objet de type Board
     * @return tous les déplacements de deux cases possibles
     */
    private List<Position> déplacement(Position position, Board board) {
        List<Position> positions = new ArrayList();
        Integer colorNumber = board.getInitialPawnRow(board.getPiece(position).getColor());
        //SI le joueur est de couleur noir
        switch (colorNumber) {
            case 6: {
                if (colorNumber == 6 && position.getRow().equals(colorNumber)) {
                    isFree(position, Direction.S, board, positions);
                    //SI le joueur est de couleur blanche
                }
            }
            case 1: {
                if (colorNumber == 1 && position.getRow().equals(colorNumber)) {
                    isFree(position, Direction.N, board, positions);
                }
            }
            default: {
                if (board.getPiece(position).getColor() == Color.BLACK) {//joueur noir
                    //Si position libre déplacement vers Direction.S
                    if (board.contains(position.next(Direction.S)) && board.isFree(position.next(Direction.S))) {
                        positions.add(position.next(Direction.S));
                    }
                    //joueur blanc
                    //Si position libre déplacement vers Direction.N
                } else if (board.contains(position.next(Direction.N)) && board.isFree(position.next(Direction.N))) {
                    positions.add(position.next(Direction.N));
                }
            }

        }
        return positions;
    }
    private void isFree(Position position, Direction dir, Board board, List<Position> positions) {
        //direction vers le haut Direction.N
        Position nextpos = position.next(dir);
        //Vérifier que la position est libre si oui Direction.N
        if (board.isFree(nextpos) && board.isFree(nextpos.next(dir))) {
            positions.add(nextpos.next(dir));
        }
    }

    /**
     * Déplacement en diagonale
     *
     * @param position objet de type Position
     * @param board objet de type Board
     * @return tous les déplacements en diagonale possibles
     */
    private List<Position> DéplacementDiagonale(Position position, Board board) {
        List<Position> positions = new ArrayList();
        //joueur de couleur noir
        if (board.getPiece(position).getColor() == Color.BLACK) {
            //Si il y a un pion de couleur opposée qui occupe la case diagonale SW
            if (Diagonale(Direction.SW, position, board)) {
                positions.add(position.next(Direction.SW));
            }
            //Si il y a un pion de couleur opposée qui occupe la case diagonale SE
            if (Diagonale(Direction.SE, position, board)) {
                positions.add(position.next(Direction.SE));
            }
        } else {//jouer de couleur blanc
            //Si il y a un pion de couleur opposée qui occupe la case diagonale NW

            if (Diagonale(Direction.NW, position, board)) {
                positions.add(position.next(Direction.NW));
            }

            //Si il y a un pion de couleur opposée qui occupe la case diagonale NE
            if (Diagonale(Direction.NE, position, board)) {
                positions.add(position.next(Direction.NE));
            }

        }
        return positions;
    }

    /**
     * Vérification de la diagonale, peut prendre la pièce en diagonale
     *
     * @param dir la direction voulue, une des valeurs de l'enumération
     * Direction
     * @param pos la position du pionqui va se déplacer
     * @param board un objet de type board, le plateu de jeu
     * @return true si il peut prendre la position false sinon
     */
    private boolean Diagonale(Direction dir, Position pos, Board board) {
        if (board.contains(pos.next(dir))) {
            return board.containsOppositeColor(pos.next(dir), board.getPiece(pos).getColor());
        }
        return false;
    }

    /**
     * Les positions en elsquelles la pièce peut capturer une autre pièce
     *
     * @param position objet de type Position
     * @param board objet de type board
     * @return tous les positions de capture possible
     */
    @Override
    public List<Position> getCapturePositions(Position position, Board board) {
        return DéplacementDiagonale(position, board);
    }
}
