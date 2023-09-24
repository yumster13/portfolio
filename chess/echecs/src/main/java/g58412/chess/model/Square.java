/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g58412.chess.model;

import g58412.chess.model.pieces.Piece;

/**
 *
 * @author g58412
 */
public class Square {
    
    private Piece piece;
    /**
     * Permet de construire un objet de type Square
     * @param piece objet de type Piece, il faut passer une couleur en paramètre
     */
    public Square(Piece piece){
        this.piece = piece;
    }
    /**
     * Permet d'avoir l'objet Piece de l'objet Square
     * @return la piece de l'objet Square courant
     */
    public Piece getPiece(){
        return this.piece;
    }
    /**
     * Permet d'instancier un objet de type Piece dans l'objet Square
     * @param piece l'objet de type Piece que l'on veut mettre dans l'objet Square
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    /**
     * Permet de voir si un objet de type Piece est présent dans l'objet de type Square
     * @return true si il n'y a pas d'objet de type Piece dans l'objet Square et false inversément
     */
    public boolean isFree(){
        return this.getPiece() == null;
    }
    
}
