/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package g58412.chess.view;

import g58412.chess.model.GameState;
import g58412.chess.model.Position;

/**
 *
 * @author yumst
 */
public interface View {

    /**
     * Permet d'afficher le titre et un message de bienvenue aux joueurs
     */
    public void displayTitle();

    /**
     * Permet d'afficher le tableau de jeu
     */
    public void displayBoard();
    
    /**
     * Permet d'afficher le tableau de jeu
     * @param position la position de la pièce
     */
    public void displayBoard(Position position);

    /**
     * Affiche le joueur qui a gagné il affiche le vainqueur est le joueur blanc
     * si WHITE a gagné et noir si BLACK a gagné
     */
    public void displayWinner();

    /**
     * Affiche un message invitant le joueur courant (WHITE ou BLACK)
     */
    public void displayPlayer();

    /**
     * Demande une position valide sur le plateau de jeu à l'utilisateur
     *
     * @return la position souhaitée, un objet de type Position
     */
    public Position askPosition();

    /**
     * Permet d'afficher un message d'erreur passé en paramètre
     *
     * @param message String de message à afficher
     */
    public void displayError(String message);
    /**
     * Permet d'afficher un message à l'écran
     * @param message le message à afficher un String
     */
    public void displayMessage(String message);
    
    /**
     * Permet d'afficher l'état courant du jeu
     * @param state l'état courant du jeu
     */
    public void displayState(GameState state);
}
