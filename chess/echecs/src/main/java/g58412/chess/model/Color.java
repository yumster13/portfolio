/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package g58412.chess.model;

/**
 *
 * @author g58412
 */
public enum Color {
    WHITE, BLACK;
    /**
     * Retourne la couleur opposée d'une couleur donnée
     * @return WHITE si couleur donnée est BLACK et inversément
     */
    public Color opposite(){
        return this.equals(WHITE)? BLACK : WHITE;
    }
}


