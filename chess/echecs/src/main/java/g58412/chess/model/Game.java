/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g58412.chess.model;

import g58412.chess.model.pieces.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yumst
 */
public class Game implements Model {

    private Board board;
    private Player white;
    private Player black;
    private Player currentPlayer;
    private King whiteKing;
    private King blackKing;
    private GameState state;

    /**
     * Constructeur pour la classe Game
     *
     * board un objet de type Board white un objet de type Player, le joueur de
     * couleur WHITE black un objet de type Player, le joueur de couleur BLACK
     */
    public Game() {
        this.board = new Board();
        this.white = new Player(Color.WHITE);
        this.black = new Player(Color.BLACK);
    }

    /**
     * Méthode pour récupérer le board pour les tests
     *
     * @return le tableau de pieces
     */
    Board getBoard() {
        return board;
    }

    /**
     * Méthode pour récupérer le joueur courrant pour les tests
     *
     * @param currentPlayer un objet de type player pour le joueur courant
     */
    protected void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    @Override
    /**
     * Créer la partie, place les pions sur le plateau Initialise le joueur
     * courrant à WHITE les lignes du board vont de 0 à 7 donc 8 les pions
     * blancs à la ligne 1 du board(8 pions, un dans chaque colonne de la ligne
     * 1) les pions noirs à la ligne 6 du board(8 pions, un dans chaque colonne
     * de la ligne 6) initialisation du roi de couleur WHITE et le roi de
     * couleur BLACK
     */
    public void start() {
        currentPlayer = white;
        blackKing = new King(Color.BLACK);
        whiteKing = new King(Color.WHITE);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board.setPiece(new Pawn(Color.BLACK), new Position(6, j));
                board.setPiece(new Pawn(Color.WHITE), new Position(1, j));
            }
        }
        for (int i = 0; i < 8; i++) {
            board.setPiece(SwitchPiece(i, Color.WHITE), new Position(0, i));
        }
        for (int i = 0; i < 8; i++) {
            board.setPiece(SwitchPiece(i, Color.BLACK), new Position(7, i));
        }
        board.setPiece(blackKing, new Position(7, 4));
        board.setPiece(whiteKing, new Position(0, 4));
        state = GameState.PLAY;
    }

    /**
     * Accesseur pour l'attribut state
     *
     * @return l'état actuel du jeu PLAY,CHECK,CHECK_MATE,STALE_MATE;
     */
    @Override
    public GameState getState() {
        return state;
    }

    /**
     * Permet de retourner la pièce adéquate en fonction de la position
     *
     * @param i la colonne
     * @param color la couleur de la pièce
     * @return la pièce correcte en fonction de la position
     */
    private Piece SwitchPiece(int i, Color color) {
        switch (i) {
            case 0,7:
                return new Rook(color);
            case 1,6:
                return new Knight(color);
            case 2,5:
                return new Bishop(color);
            case 3:
                return new Queen(color);
        }
        return null;
    }

    /**
     * Récupère la pièce à une position donnée
     *
     * @param pos la position de la pièce
     * @return la pièce à la position pos
     * @throws IllegalArgumentException si la position n'est pas sur le plateau
     */
    @Override
    public Piece getPiece(Position pos) {
        if (board.contains(pos)) {
            return board.getPiece(pos);
        } else {
            throw new IllegalArgumentException("la position ne se trouve pas sur le plateau");
        }
    }

    /**
     * Accesseur pour le joueur courant
     *
     * @return currentPlayer
     */
    @Override
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    /**
     * Accesseur pour le second joueur
     *
     * @return l'autre joueur , celui qui ne joue pas
     */
    @Override
    public Player getOppositePlayer() {
        if (this.currentPlayer.getColor() == Color.BLACK) {
            return this.white;
        } else {
            return this.black;
        }
    }

    /**
     * Vérifie si la case de la position donnée est occupée par une piece du
     * joueur courant
     *
     * @param pos un objet de type Position
     * @return vrai si la case est occupée par une piece du joueur courrant
     * @throws IllegalArgumentException si la position n'est pas sur le plateau
     */
    @Override
    public boolean isCurrentPlayerPosition(Position pos) {
        if (board.contains(pos) && board.getPiece(pos) != null) {
            return board.getPiece(pos).getColor() == this.currentPlayer.getColor();
        } else {
            throw new IllegalArgumentException("la position ne se trouve pas sur le plateau");
        }
    }

    /**
     * Permet de bouger une piece du plateau à une autre Si la partie n'est pas
     * finie on change de joueur
     *
     * Le joueur courant soit en échec (son roi est sur une des positions de
     * capture du joueur adverse). Le mouvement est donc non valide selon les
     * règles des échecs, et doit être refusé. Un message d’erreur doit être
     * affiché à l’écran, et on demande au joueur courant de rentrer un autre
     * coup.
     *
     * @param oldPos la position actuelle
     * @param newPos la nouvelle position pour la piece de l'ancienne position
     * @throws IllegalArgumentException 1. oldPos et new Pos ne sont pas sur le
     * plateau 2. il n'y a pas de pièce sur oldPos 3. la pièce n'appartient pas
     * au joueur courant 4. le déplacement n'est pas valide pour la piece sur
     * oldPos
     */
    @Override
    public void movePiecePosition(Position oldPos, Position newPos) {
        if (isValidMove(oldPos, newPos)) {
            if(board.getPiece(oldPos) instanceof Pawn && newPos.getRow() == 7 || newPos.getRow() == 0){
                if(newPos.getRow() == 7 && board.getPiece(oldPos).getColor() == Color.WHITE){
                   board.setPiece(new Queen(Color.WHITE), newPos);
                   board.dropPiece(oldPos); 
                }else if(newPos.getRow() == 0 && board.getPiece(oldPos).getColor() == Color.BLACK){
                   board.setPiece(new Queen(Color.BLACK), newPos);
                   board.dropPiece(oldPos); 
                }
                
            }else{
            board.setPiece(board.getPiece(oldPos), newPos);
            board.dropPiece(oldPos);
            }
            if (state != GameState.STALE_MATE) {
                state = check();
            }
            if (state == GameState.CHECK) {
                state = checkMate();
            }
            if (state == GameState.PLAY) {
                state = staleMate();
            }
            if (state == GameState.CHECK || state == GameState.PLAY) {
                this.currentPlayer = this.getOppositePlayer();
            }

        } else {
            throw new IllegalArgumentException("les valeurs données sont incorrectes");
        }

    }

    /**
     * Vérifie que le roi sort d'échec avec le joueur Opposé
     *
     * @param oldPos l'ancienne position du pion
     * @param newPos la nouvelle position du pion
     * @return true si il sort d'échec et false sinon
     */
    private boolean movePieceOppPlayer(Position oldPos, Position newPos, King king) {
        Piece piece = board.getPiece(newPos);
        board.setPiece(board.getPiece(oldPos), newPos);
        board.dropPiece(oldPos);
            if (getCapturePositions(getOppositePlayer()).contains(board.getPiecePosition(king))) {
                state = GameState.CHECK;
                board.setPiece(board.getPiece(newPos), oldPos);
                board.dropPiece(newPos);
                board.setPiece(piece, newPos);
                return false;
            }
        
        board.setPiece(board.getPiece(newPos), oldPos);
        board.dropPiece(newPos);
        board.setPiece(piece, newPos);
        return true;

    }

    /**
     * Le joueur adverse n’est pas en échec, mais ne possède aucun coup valide
     * (appelons cet état du jeu STALEMATE). Dans ce cas la partie est nulle,
     * elle se termine, et aucun joueur ne gagne.
     *
     * @return
     */
    private GameState staleMate() {
        List<Position> posOcc = board.getPositionsOccupiedBy(getOppositePlayer());
        for (int i = 0; i < posOcc.size(); i++) {
            List<Position> posCap2 = getPossibleMoves(posOcc.get(i));
            for (int j = 0; j < posCap2.size(); j++) {
                movePieceSamePlayer(posOcc.get(i), posCap2.get(j));
                if (state != GameState.CHECK) {
                    return GameState.PLAY;
                }
            }

        }
        return GameState.STALE_MATE;
    }

    /**
     *
     * Permet de vérifier qu'un coup du joueur courant ne le met pas en
     * staleMate
     *
     * @param oldPos l'ancienne position
     * @param newPos la nouvelle position
     */
    private void movePieceSamePlayer(Position oldPos, Position newPos) {
        Piece piece = board.getPiece(newPos);
        board.setPiece(board.getPiece(oldPos), newPos);
        board.dropPiece(oldPos);
        state = check();
        board.setPiece(board.getPiece(newPos), oldPos);
        board.dropPiece(newPos);
        board.setPiece(piece, newPos);
    }

    /**
     * Soit le joueur adverse peut jouer au moins un coup valide, ce qu’il est
     * alors obligé de faire (appelons cet état du jeu CHECK). Il faut avertir
     * ce joueur au moyen d’un message approprié.
     *
     * @return
     */
    public GameState check() {
        if (currentPlayer.getColor() == Color.BLACK) {
            return getCapturePositions(getCurrentPlayer()).contains(board.getPiecePosition(whiteKing)) ? GameState.CHECK : GameState.PLAY;
        } else {

            return getCapturePositions(getCurrentPlayer()).contains(board.getPiecePosition(blackKing)) ? GameState.CHECK : GameState.PLAY;
        }
    }

    /**
     * Soit le joueur adverse ne peut jouer aucun coup valide (appelons cet état
     * du jeu CHECKMATE ). Le jeu se termine, le joueur courant est déclaré
     * gagnant, et la partie se termine.
     *
     * @return
     */
    private GameState checkMate() {
        List<Position> posCap = getCapturePositions(getCurrentPlayer());
        posCap.addAll(board.getPositionsOccupiedBy(getCurrentPlayer()));
        if (currentPlayer.getColor() == Color.BLACK) {
            return posCap.containsAll(getPossibleMoves(board.getPiecePosition(whiteKing))) ? GameState.CHECK_MATE : GameState.CHECK;
        } else {
            return posCap.containsAll(getPossibleMoves(board.getPiecePosition(blackKing))) ? GameState.CHECK_MATE : GameState.CHECK;
        }
    }

    /**
     * Donne les mouvements possible pour la pièce à une position donnée
     *
     * @param position un objet de type Position
     * @return une liste des positions possibles
     */
    @Override
    public List<Position> getPossibleMoves(Position position) {
        return board.getPiece(position).getPossibleMoves(position, board);
    }

    /**
     * Permet de vérifier que le déplacement d’une pièce de la position oldPos
     * vers la position newPos est bien valide.
     *
     * @param oldPos l'ancienne position
     * @param newPos la nouvelle position
     * @return true si le déplacement est valide et false sinon
     * @throws new IllegalArgumentException: 1.la position de départ oldPos ne
     * contient aucune pièce 2.le déplacement de la pièce de oldPos vers newPos
     * n’est pas un mouvement possible pour la pièce en question.
     */
    @Override
    public boolean isValidMove(Position oldPos, Position newPos) {
        if (board.contains(oldPos) && board.getPositionsOccupiedBy(currentPlayer).contains(oldPos)) {
            if (currentPlayer.getColor() == Color.BLACK) {
                return getPossibleMoves(oldPos).contains(newPos) && movePieceOppPlayer(oldPos, newPos, blackKing);
            } else {
                return getPossibleMoves(oldPos).contains(newPos) && movePieceOppPlayer(oldPos, newPos, whiteKing);
            }
        } else {
            throw new IllegalArgumentException("les positions sont incorrectes");
        }
    }

    /**
     * Permet de déterminer toutes les positions en lesquelles un joueur peut
     * capturer une autre pièce.
     *
     * @param player un objet de type Player
     * @return les positions en lesquelles un joueur peut capturer une autre
     * pièce
     */
    private List<Position> getCapturePositions(Player player) {
        List<Position> posocc = board.getPositionsOccupiedBy(player);
        List<Position> posCap = new ArrayList();
        for (int i = 0; i < posocc.size(); i++) {
            posCap.addAll(getPiece(posocc.get(i)).getCapturePositions(posocc.get(i), board));
        }
        return posCap;
    }

}
