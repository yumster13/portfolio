/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g58412.chess.main;

import g58412.chess.controller.Controller;
import g58412.chess.model.Game;
import g58412.chess.model.Model;
import g58412.chess.view.TextView;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author yumst
 */
/**
 * Méthode principale qui permet de créer la partie et d'y jouer grâce à la
 * Classe Controller2
 */
public class Chess {

    public static void main(String[] args) throws UnsupportedEncodingException {
        Model game = new Game();
        Controller controller = new Controller(game, new TextView(game));
        controller.play();
    }
}
