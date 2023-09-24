/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g58412.chess.view;

import g58412.chess.model.Color;
import g58412.chess.model.GameState;
import g58412.chess.model.Model;
import g58412.chess.model.Position;
import g58412.chess.model.pieces.*;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.regex.*;

/**
 *
 * @author yumst
 */
public class TextView implements View {

    private Model model;
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED_BACKGROUND = "\033[0;41m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";

    /**
     * Construceur pour la classe TextView
     *
     * @param model objet de l'interface Model
     */
    public TextView(Model model) {
        this.model = model;
    }

    /**
     * Permet d'afficher le titre et un message de bienvenue aux joueurs
     */
    @Override
    public void displayTitle() {
        System.out.println("CHESS-58412");
        System.out.println("Bienvenue au jeu d'Echecs");
    }
    /**
     * Permet d'afficher le plateau
     */
    @Override
    public void displayBoard() {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
            String lettres = "   a   b  c   d   e   f   g   h ";
            int num = 8;
            for (int i = 7; i >= 0; i--) {
                System.out.print(num + "\u2001");
                num--;

                for (int j = 0; j < 8; j++) {
                    if (i % 2 == 1) {
                        if (j % 2 == 0) {
                            afficherCase(ANSI_WHITE_BACKGROUND, i, j);
                        } else {
                            afficherCase("", i, j);
                        }

                    } else {
                        if (j % 2 == 1) {
                            afficherCase(ANSI_WHITE_BACKGROUND, i, j);
                        } else {
                            afficherCase("", i, j);
                        }
                    }
                }

                System.out.println("");
            }
            System.out.println(lettres);
        } catch (UnsupportedEncodingException e) {
            new UnsupportedEncodingException();
        }

    }
    /**
     * Affiche le plateau avec les positions possible de la pièce en paramètre
     * @param position la position de la pièce
     */
    public void displayBoard(Position position){
       try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
            String lettres = "   a   b  c   d   e   f   g   h ";
            int num = 8;
            for (int i = 7; i >= 0; i--) {
                System.out.print(num + "\u2001");
                num--;

                for (int j = 0; j < 8; j++) {
                    // affiche les pions, PB pour les pions blancs et PN pour les pions noirs et 
                    // si aucun pion alors deux espaces blanc "  "
                    if (i % 2 == 1) {
                        if (j % 2 == 0) {
                            if(model.getPossibleMoves(position).contains(new Position(i,j))){
                                afficherCase(ANSI_RED_BACKGROUND, i, j);
                            }else afficherCase(ANSI_WHITE_BACKGROUND, i, j);
                        } else {
                            if(model.getPossibleMoves(position).contains(new Position(i,j))){
                                afficherCase(ANSI_RED_BACKGROUND, i, j);
                            }else afficherCase("", i, j);
                        }

                    } else {
                        if (j % 2 == 1) {
                             if(model.getPossibleMoves(position).contains(new Position(i,j))){
                                afficherCase(ANSI_RED_BACKGROUND, i, j);
                            }else afficherCase(ANSI_WHITE_BACKGROUND, i, j);
                        } else {
                            if(model.getPossibleMoves(position).contains(new Position(i,j))){
                                afficherCase(ANSI_RED_BACKGROUND, i, j);
                            }else afficherCase("", i, j);
                        }
                    }
                }

                System.out.println("");
            }
            System.out.println(lettres);
        } catch (UnsupportedEncodingException e) {
            new UnsupportedEncodingException();
        } 
    }

    /**
     * Permet de transformer l'instance de pièce en caractère unicode d'échec
     * correspondant
     *
     * @param piece un objet de type Piece
     * @param color un objet de type color, BLACK ou WHITE
     * @return le caractère unicode correspondant à la piece
     */
    private String SwitchPiece(Piece piece, Color color) {
        switch (color) {
            case BLACK: {
                if (piece instanceof Pawn) {
                    return "♟";
                }
                if (piece instanceof Rook) {
                    return "♜";
                }
                if (piece instanceof Bishop) {
                    return "♝";
                }
                if (piece instanceof Knight) {
                    return "♞";
                }
                if (piece instanceof King) {
                    return "♚";
                }
                if (piece instanceof Queen) {
                    return "♛";
                }
            }
            ;
            case WHITE: {
                if (piece instanceof King) {
                    return "♔";
                }
                if (piece instanceof Queen) {
                    return "♕";
                }
                if (piece instanceof Rook) {
                    return "♖";
                }
                if (piece instanceof Bishop) {
                    return "♗";
                }
                if (piece instanceof Knight) {
                    return "♘";
                }
                if (piece instanceof Pawn) {
                    return "♙";
                }
            }
            ;
        }
        return "";
    }

    /**
     * Affiche un message invitant le joueur courant (WHITE ou BLACK)
     */
    @Override
    public void displayPlayer() {
        if (model.getCurrentPlayer().getColor() == Color.BLACK) {
            System.out.println("Joueur Noir:");
        } else {
            System.out.println("Joueur Blanc:");
        }
    }

    /**
     * Affiche le joueur qui a gagné il affiche le vainqueur est le joueur blanc
     * si WHITE a gagné et noir si BLACK a gagné
     */
    @Override
    public void displayWinner() {
        System.out.println(model.getState());
        if (model.getState() == GameState.CHECK_MATE) {
            if (model.getCurrentPlayer().getColor() == Color.WHITE) {
                System.out.println("le vainqueur est le joueur de couleur blanc");
            } else {
                System.out.println("le vainqueur est le joueur de couleur noir");
            }
        }
    }

    /**
     * Demande une position valide sur le plateau de jeu à l'utilisateur
     *
     * @return la position souhaitée, un objet de type Position
     */
    @Override
    public Position askPosition() {
        Scanner kb = new Scanner(System.in);
        String pos;
        do {
            System.out.println("Entrez une position de forme ligne(1 - 8)colonne(a - h)");
            pos = kb.next();
            //regex pour demander une position de forme ligne(1 à 8) et de colonne (a à h)
        } while (!Pattern.matches("[1-8]{1}[a-h]{1}", pos));
        return new Position(Character.getNumericValue(pos.charAt(0)) - 1, switchCol(pos.charAt(1)));
    }

    /**
     * modifier une colonne donnée en char en chiffre
     *
     * @param colonne char de valeur de a-h
     * @return un entier correcpondant au caractère passé
     */
    private static int switchCol(char colonne) {
        int col = -1;
        switch (colonne) {
            case 'a' ->
                col = 0;
            case 'b' ->
                col = 1;
            case 'c' ->
                col = 2;
            case 'd' ->
                col = 3;
            case 'e' ->
                col = 4;
            case 'f' ->
                col = 5;
            case 'g' ->
                col = 6;
            case 'h' ->
                col = 7;
        }
        return col;
    }

    /**
     * Permet d'afficher un message d'erreur passé en paramètre
     *
     * @param message String de message à afficher
     */
    @Override
    public void displayError(String message) {
        System.out.println(message);
    }

    /**
     * Permet d'afficher un message
     *
     * @param message un paramètre de type String, le message à afficher
     */
    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    private void afficherCase(String ansi, int i, int j) {
        if (model.getPiece(new Position(i, j)) != null) {
            System.out.print(ansi + "\u2001" + SwitchPiece(model.getPiece(new Position(i, j)), model.getPiece(new Position(i, j)).getColor()) + "\u2001" + ANSI_RESET);

        } else {
            System.out.print(ansi + "\u2001\u2001\u2001" + ANSI_RESET);
        }
    }
    @Override
    /**
     * Petmet d'afficer l'état du jeu
     * @param state l'état courant du jeu
     */
    public void displayState(GameState state){
        switch (state){
            case STALE_MATE: System.out.println("Vous êtes en état d'égalité");break;
            case CHECK:System.out.println("Vous êtes en échec \nVous devez sortir votre roi d'échec");break;
            case CHECK_MATE:System.out.println("Vous êtes en échec et mat");break;
            default: System.out.println(state);break;
        }
    }

}
