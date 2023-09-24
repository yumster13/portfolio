/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g58412.chess.model;

import java.util.Objects;

/**
 *
 * @author yumst
 */
public class Position {
    private Integer row;
    private Integer column;
    /**
     * Constructeur pour une position
     * @param row la ligne
     * @param column la colonne
     */
    public Position(Integer row, Integer column){
        this.row = row;
        this.column = column;   
    }
    /**
     * Accesseur pour l'attribut row (ligne)
     * @return la ligne recherchée
     */
    public Integer getRow() {
        return row;
    }
    /**
     * Accesseur pour l'attribut column (colonne)
     * @return la colonne recherchée
     */
    public Integer getColumn() {
        return column;
    }
    /**
     * Permet d'avoir la prochaine position après avoir appliqué la direction voulue
     * @param dir une des directions de l'énumération Direction
     * @return new Position la nouvelle position
     */
    public Position next(Direction dir){
        return new Position(this.getRow()+dir.getDeltaRow(),this.getColumn()+dir.getDeltaColumn());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }
    /**
     * Permet de vérifier l'égalité entre 2 positions
     * @param obj un objet de type Position
     * @return true si les deux positions sont les mêmes false sinon
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        if (!Objects.equals(this.row, other.row)) {
            return false;
        }
        return Objects.equals(this.column, other.column);
    }

    @Override
    public String toString() {
        return "Position{" + "row=" + row + ", column=" + column + '}';
    }
    
    
    
    
}
