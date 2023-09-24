/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package g58412.chess.model;

/**
 *
 * @author yumst
 */
public enum Direction {
    /**
     * Initialisation des valeurs de l'énumération
     */
    NW(1,-1),N(1,0),NE(1,1),W(0,-1),E(0,1),SW(-1,-1),S(-1,0),SE(-1,1);
    
    private Integer deltaRow;
    private Integer deltaColumn;
    /**
     * Constructeur pour la Direction permet d'initaliser les valeurs de l'énum
     * @param deltaR la valeur de la ligne de la Direction
     * @param deltaC la valeur de la colonne de la Direction
     */
    private Direction(Integer deltaR, Integer deltaC) {
        this.deltaRow = deltaR;
        this.deltaColumn = deltaC;
    }
    /**
     * Accesseur pour la ligne de la Direction
     * @return deltaRow 
     */
    public Integer getDeltaRow() {
        return deltaRow;
    }
    /**
     * Accesseur pour la colonne de la Direction
     * @return deltaColumn
     */
    public Integer getDeltaColumn() {
        return deltaColumn;
    }
    
    
    
    
}
