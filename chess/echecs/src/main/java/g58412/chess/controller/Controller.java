/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g58412.chess.controller;

import g58412.chess.model.Game;
import g58412.chess.model.GameState;
import g58412.chess.model.Model;
import g58412.chess.model.Position;
import g58412.chess.view.TextView;
import g58412.chess.view.View;

/**
 *
 * @author yumst
 */
public class Controller {

    private View view;
    private Model model;

    /**
     * Constructeur pour initialiser le contrôler avec la vue et le modèle
     *
     * @param model le modèle
     * @param view la vue
     */
    public Controller(Model model, View view) {
        this.view = view;
        this.model = model;
    }

    /**
     * Elle permet de piloter le jeu: Affiche le plateau et invite le joueur
     * Demande une position de départ si elle est mauvaise elle est redemandée
     * Demande une position d'arrivée si elle est mauvaise elle est redemandée
     * On joue le coup (bouger de la position de départ à la position d'arrivée)
     * Vérifie que le jeu est terminé Mettre gameIsOver à jour
     */
    public void play() {
        Model game = new Game();
        View view = new TextView(game);
        view.displayTitle();
        game.start();
        Position posold;
        Position posnew;
        do {
            view.displayBoard();
            view.displayPlayer();
            try {
                if (game.getState() == GameState.PLAY) {
                    view.displayMessage("Quelle piece voulez-vous bouger?");
                    posold = view.askPosition();
                    view.displayBoard(posold);
                    view.displayMessage("Le mouvement souhaite?");
                    posnew = view.askPosition();
                    game.movePiecePosition(posold, posnew);

                } else {
                    do {
                        view.displayMessage("Quelle piece voulez-vous bouger?");
                        posold = view.askPosition();
                        view.displayBoard(posold);
                        view.displayMessage("Le mouvement souhaite?");
                        posnew = view.askPosition();
                        game.movePiecePosition(posold, posnew);
                    } while (game.getState() != GameState.PLAY);
                }
                view.displayState(game.getState());
            } catch (Exception e) {
                view.displayError(e.getMessage());
            }
        } while ((game.getState() != GameState.CHECK_MATE) && (game.getState() != GameState.STALE_MATE));
        view.displayBoard();
        view.displayWinner();
    }

}
