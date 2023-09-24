/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g58412.chess.model;

/**
 *
 * @author g58412
 */
public class Player {
    private Color color;
    /**
     * Permet de créer l'objet joueur avec une couleur WHITE ou BLACK
     * @param color une couleur de l'énumération Color : WHITE ou BLACK
     */
    public Player(Color color){
        this.color = color;
    }
    /**
     * Accesseur pour recevoir la couleur de l'objet Player
     * @return this.color la couleur de l'objet Player courant (WHITE ou BLACK)
     */
    public Color getColor(){
        return this.color;
    }
}
