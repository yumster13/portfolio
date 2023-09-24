/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g58412.chess.model;

import g58412.chess.model.pieces.Piece;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author g58412
 */
public class Board {

    private Square squares[][];

    /**
     * Initialise un talbeau de Square de taille 8x8
     */
    public Board() {
        this.squares = new Square[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.squares[i][j] = new Square(null);
            }
        }
    }

    /**
     * Permet de voir si la position est contenue dans Board (lignes et colonnes
     * allant de 0 à 7)
     *
     * @param pos la position souhaitée
     * @return true si la position est contenue entre 0 et 7 et false sinon
     */
    public boolean contains(Position pos) {
        if (pos.getRow() >= 0 && pos.getRow() <= 7) {
            if (pos.getColumn() >= 0 && pos.getColumn() <= 7) {
                return true;
            }
        }
        return false;
    }

    /**
     * Place la pièce passée en paramètre sur la case correspondante du plateau
     *
     * @param piece un objet de type Piece
     * @param position un objet de type Position
     * @throws IllegalArgumentException si la position donnée n'est pas sur le
     * plateau
     */
    public void setPiece(Piece piece, Position position) {
        if (this.contains(position)) {
            squares[position.getRow()][position.getColumn()].setPiece(piece);
        } else {
            throw new IllegalArgumentException("la position donnée n'est pas sur le plateau");
        }
    }

    /**
     * Permet d'accéder à une case
     *
     * @return une des cases (de type Square) du plateau de jeu
     */
    protected Square[][] getSquare() {
        return squares;
    }

    /**
     * Permet de déterminer si un pion est toujours sur sa position initiale ou
     * non
     *
     * @param color de l'énum Color, soit WHITE soit BLACK
     * @return 6 pour la couleur BLACK et 1 pour la couleur WHITE
     */
    public Integer getInitialPawnRow(Color color) {
        return (color == Color.BLACK)? 6 : 1 ;
    }

    /**
     * Permet d'obtenir l'objet de type piece sur une case pour une position
     * donnée
     *
     * @param pos un objet de type Position
     * @return la pièce si il y en a une
     * @throws IllegalArgumentException si la position donnée n'est pas sur le
     * plateau
     */
    public Piece getPiece(Position pos) {
        if (this.contains(pos)) {
            return squares[pos.getRow()][pos.getColumn()].getPiece();
        } else {
            throw new IllegalArgumentException("la position donnée n'est pas sur le plateau");
        }
    }

    /**
     * Supprime la pièce de la case dont la position est passée en paramètre
     *
     * @param pos un bojet de type Position
     * @throws IllegalArgumentException si la position donnée n'est pas sur le
     * plateau
     */
    public void dropPiece(Position pos) {
        if (this.contains(pos)) {
            squares[pos.getRow()][pos.getColumn()].setPiece(null);
        } else {
            throw new IllegalArgumentException("la position donnée n'est pas sur le plateau");
        }
    }

    /**
     * Vérifie si la case à la positon donnée est libre ou non
     *
     * @param pos un bojet de type Position
     * @return true si la case donnée est libre et false sinon
     * @throws IllegalArgumentException si la position donnée n'est pas sur le
     * plateau
     */
    public boolean isFree(Position pos) {
        if (this.contains(pos)) {
            return squares[pos.getRow()][pos.getColumn()].isFree();
        } else {
            throw new IllegalArgumentException("la position donnée n'est pas sur le plateau");
        }
    }

    /**
     * Vérifie si sur la case à la position donnée contient une pièce d'une
     * couleur opposée à celle passée en paramètre
     *
     * @param pos un bojet de type Position
     * @param col un objet de l'énum Color soit BLACK soit WHITE
     * @return renvoie true si la case dont la position passée en paramètre
     * contient une pièce de la couleur opposée à celle passée en paramètre, et
     * false sinon
     * @throws IllegalArgumentException si la position donnée n'est pas sur le
     * plateau
     */
    public boolean containsOppositeColor(Position pos, Color col) {
        if (this.contains(pos)) {
            if (getPiece(pos) != null) {
                return getPiece(pos).getColor().opposite() == col;
            }
        } else {
            throw new IllegalArgumentException("la position donnée n'est pas sur le plateau");
        }
        return false;
    }

    /**
     * Crée une liste de toutes les positions occupées par le joueur donné
     *
     * @param player un bojet de type Player
     * @return la liste de toutes les positions occupées par le joueur donné
     */
    public List<Position> getPositionsOccupiedBy(Player player) {
        List<Position> pos = new ArrayList();
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                if (squares[i][j].getPiece() != null) {
                    if (squares[i][j].getPiece().getColor() == player.getColor()) {
                        pos.add(new Position(i, j));
                    }
                }

            }
        }
        return pos;
    }
    /**
     * Retourne la position d'une piece passée en paramètres
     * @param piece un objet de type Piece
     * @return un position si il y en a une sinon null
     */
    public Position getPiecePosition(Piece piece){
      for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                if (squares[i][j].getPiece() != null) {
                    if (squares[i][j].getPiece().equals(piece)) {
                        return new Position(i,j);
                    }
                }

            }
        }
      return null;
    }
}
