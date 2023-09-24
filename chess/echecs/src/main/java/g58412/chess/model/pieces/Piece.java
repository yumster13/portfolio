/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author g58412
 */
public abstract class Piece {

    Color color;

    /**
     * Constructeur pour la pièce permet de créer une pièce
     *
     * @param color soit WHITE ou BLACK de enum Color
     */
    public Piece(Color color) {
        this.color = color;
    }

    /**
     * Accesseur de la couleur de la pièce
     *
     * @return color qui a pour valeur WHITE ou BLACK
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Tous les déplacements possibles pour un pion
     *
     * @param position objet de type Position
     * @param board objet de type Board
     * @return tous les déplacements possibles
     */
    public abstract List<Position> getPossibleMoves(Position position, Board board);

    /**
     * Les positions en elsquelles la pièce peut capturer une autre pièce
     *
     * @param position objet de type Position
     * @param board objet de type board
     * @return tous les positions de capture possible
     */
    public abstract List<Position> getCapturePositions(Position position, Board board);

    /**
     * Permet de trouver les coups possibles pour un tableau de direction donné
     *
     * @param position la position du joueur courant
     * @param board un objet de type board
     * @param dir un tableau des directions à controler
     * @return une liste de toutes les positions possibles pour la position, le
     * type de pion, le tableau de direction donné et le plateau de jeu board
     */
    protected List<Position> everyPossibleMove(Position position, Board board, Direction[] dir) {
        List<Position> possibleMoves = new ArrayList();
        for (int i = 0; i < dir.length; i++) {
            Position nextPos = position.next(dir[i]);
            while (board.contains(nextPos) && board.isFree(nextPos)) {
                possibleMoves.add(nextPos);
                nextPos = nextPos.next(dir[i]);
            }
            if (board.contains(nextPos) && !board.isFree(nextPos) && board.containsOppositeColor(nextPos, board.getPiece(position).getColor())) {
                possibleMoves.add(nextPos);

            }

        }

        return possibleMoves;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Piece other = (Piece) obj;
        return this.color == other.color;
    }
}
