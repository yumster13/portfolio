"use strict";

class State {
    /**
     * Constructeur pour la classe state
     * @param {Position} playerPosition la position du joueur
     * @param {Position|undefined} boxPosition la position de la boite déplacée
     */
    constructor(playerPosition, boxPosition = undefined) {
        this.playerPosition = playerPosition;
        this.boxPosition = boxPosition;
    }

    /**
     * Accesseur pour la position du joueur
     * @returns la position du joueur
     */
    getplayerPosition() {
        return this.playerPosition;
    }

    /**
     * Accesseur pour la position du déplacée
     * @returns la position de la boite déplacée
     */
    getboxPosition() {
        return this.boxPosition;
    }
}
