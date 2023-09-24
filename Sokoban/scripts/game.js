"use strict";

let niveau = 0;
let compteur = 0;
/**
 * @type {String[]} tableau de State
 */
let direction = [];
let dir = "Down";
/**
 * @type {State[]} tableau de State
 */
let states = [];
console.log("hello world");
$(document).ready(() => {
    compteur = initLevel(niveau);
    $("#restart").click(restart);
    $("#aide").click(help);
    $(".close").click(closeModal);
    $("#annulation").click(annulation);
    $("#charger").click(ChargerNiv);
    $(document).keydown(function (event) {
        if (allOnTarget(niveau) === false) {
            switch (event.key) {
            case "ArrowLeft":
                getPlayerPosition().moveSquare(-1, 0);
                move(-1, 0, "Left");
                break;
            case "ArrowRight":
                getPlayerPosition().moveSquare(1, 0);
                move(1, 0, "Right");
                break;
            case "ArrowUp":
                getPlayerPosition().moveSquare(0, -1);
                move(0, -1, "Up");
                break;
            case "ArrowDown":
                getPlayerPosition().moveSquare(0, 1);
                move(0, 1, "Down");
                break;
            }
        } else if (event.keyCode === 32) {
            finishLevel();
        }
    });
});

class Position {
    /**
     *
     * @param {*} x valeur x
     * @param {*} y
     */
    constructor(x, y) {
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @param {*} x
     * @param {*} y
     */
    moveSquare(x, y) {
        return new Position(this.x + x, this.y + y);
    }

}

/**
 * Permet d'afficher le niveau caractère par caractère
 * @param {number} level le numéro du niveau passé en paramètre
 */
function buildLevel(level) {
    for (let i = 0; i < levels[level].map.length; i++) {
        $("#world").append("<div></div>");
        for (let j = 0; j < levels[level].map[i].length; j++) {
            if (j === 0) {
                $("#world div").last()
                    .append("<div></div>");
            } else {
                $("#world div").parent()
                    .last()
                    .append("<div></div>");
            }

            SwitchClass(levels[level].map[i][j].toString());

            if (levels[level].map[i][j].toString() === "\uD83E") {
                j += 1;
            }
        }
    }
    $("#compteur").text(`Compteur: ${compteur}`);
    $("#niveau").text(`Niveau: ${niveau}`);
}
/**
 * Retourne la classe nécessaire
 * @param {String} i l'élément du niveau
 * @returns un div avec les bonnes classes
 */
function SwitchClass(i) {
    switch (i) {
    case "\uD83E": return $("#world div").last()
        .addClass("square espace joueur joueurBas");
    case "x": return $("#world div").last()
        .addClass("square espace cible");
    case "#": return $("#world div").last()
        .addClass("square espace boite");
    case "@": return $("#world div").last()
        .addClass("square boiteSurCible");
    case " ": return $("#world div").last()
        .addClass("square espace");
    default: return $("#world div").last()
        .addClass("square mur");
    }
}
/**
 * Renvoie la position du joueur dans le jeu
 * @returns la position du joueur
 */
function getPlayerPosition() {
    return new Position($(".joueur").index(), $("div .joueur").parent()
        .index());
}
/**
 * Retourne la case du jeu qui se trouve à la position donnée
 * @param {Position} position un objet de type position
 * @returns le div à la position donnée
 */
function getSquareAt(position) {
    return $("#world").children()
        .eq(position.y)
        .children()
        .eq(position.x);
}
/**
 * Déplace le joueur
 * @param {number} x positionx
 * @param {number} y positiony
 * @param {String} directionJoueur la direction du joueur
 */
function move(x, y, directionJoueur) {
    const position = getPlayerPosition();
    const nextPos = nextPosition(x, y, position);
    const posBoite = pousserBoiteTarget(x, y, nextPos);
    simpleDeplacement(nextPos, directionJoueur, posBoite, position);
}
/**
 * Permet de reagerder si un déplacement simple est possible
 * @param {Position} nextPos la prochaine position
 * @param {String} directionJoueur la direction du joueur
 * @param {Position|undefined} posBoite la position de la boite
 * @param {Position} position la position du joueur
 */
function simpleDeplacement(nextPos, directionJoueur, posBoite, position) {
    if (getSquareAt(nextPos).attr("class") === "square espace" || getSquareAt(nextPos).attr("class") === "square espace cible") {
        removeAllClass();
        getSquareAt(getPlayerPosition()).removeClass("joueur");
        getSquareAt(nextPos).addClass(switchJoueur(directionJoueur));
        direction.push(dir);
        dir = directionJoueur;
        states.push(new State(position, posBoite));
        incrMove();
    }
    $("#compteur").text(`Compteur: ${compteur}`);
    return getPlayerPosition();
}
/**
 * Permet de retirer toutes les classes du Joueur
 */
function removeAllClass() {
    getSquareAt(getPlayerPosition()).removeClass("joueurBas");
    getSquareAt(getPlayerPosition()).removeClass("joueurRight");
    getSquareAt(getPlayerPosition()).removeClass("joueurLeft");
    getSquareAt(getPlayerPosition()).removeClass("joueurUp");
}
/**
 * retourne la bonne classe pour le joueur en fonction de la direction
 * @param {String} dirPoint la direction
 * @returns la classe en fonction de la direction
 */
function switchJoueur(dirPoint) {
    switch (dirPoint) {
    case "Up": return "joueur joueurUp";
    case "Right": return "joueur joueurRight";
    case "Left": return "joueur joueurLeft";
    case "Down": return "joueur joueurBas";
    }
    return "";
}
/**
 * Incrémente le mouvement;
 */
function incrMove() {
    if(finNiveau()){

    }else{
    compteur += 1;
    }
}
/**
 * Permet de regarder si il pousse une boite sur une cible ou une boite d'une cible
 * @param {number} x la valeur de x de move
 * @param {number} y la valeur de y de move
 * @param {Position} nextPos
 * @returns {Position|undefined} la position de la boite
 */
function pousserBoiteTarget(x, y, nextPos) {
    const nextPos2 = nextPosition(x, y, nextPos);
    if (getSquareAt(nextPos).attr("class") === "square espace cible boiteSurCible" || getSquareAt(nextPos).attr("class") === "square boiteSurCible") {
        if (getSquareAt(nextPos2).attr("class") === "square espace cible") {
            getSquareAt(nextPos2).addClass("boiteSurCible");
            getSquareAt(nextPos).removeClass("boiteSurCible");
            return nextPos2;
        }
    }
    if (getSquareAt(nextPos).attr("class") === "square espace boite") {
        if (getSquareAt(nextPos2).attr("class") === "square espace cible") {
            getSquareAt(nextPos2).addClass("boiteSurCible");
            getSquareAt(nextPos).removeClass("boite");
            return nextPos2;
        }
    }
    if (getSquareAt(nextPos).attr("class") === "square espace boite") {
        if (getSquareAt(nextPos2).attr("class") === "square espace") {
            getSquareAt(nextPos2).addClass("boite");
            getSquareAt(nextPos).removeClass("boite");
            return nextPos2;
        }
    }
    return undefined;
}
/**
 * Permet de regarder la prochaine Position
 * @param {number} x la valeur en x de move
 * @param {number} y la valeur en y de move
 * @param {Position} position la prochaine position
 */
function nextPosition(x, y, position) {
    let posX = position.x;
    let posY = position.y;
    const nextPos = new Position(posX += x, posY += y);
    return nextPos;
}
/**
 * Permet de regarder si toutes les cibles sont remplies,
 * @param {number} level le niveau actuel
 * @returns true si c'est vrai false sinon
 */
function allOnTarget(level) {
    const all = false;
    for (let i = 0; i < levels[level].map.length; i++) {
        for (let j = 0; j < levels[level].map[i].length; j++) {
            if (getSquareAt(new Position(i, j)).attr("class") === "square espace boite") {
                return all;
            }
        }
    }
    return true;
}

/**
 * Permet d'instancier un nouveau niveau
 * @param {number} niv le numéro du niveau
 */
function initLevel(niv) {
    compteur = 0;
    buildLevel(niv);
    direction = [""];
    states = [];
    return compteur;
}
/**
 * Permet de terminer un niveau une fois toutes les cibles recouvertes
 */
function finishLevel() {
    if (niveau !== 7) {
        $("#world").html("");
        niveau += 1;
        compteur = initLevel(niveau);
        $("#compteur").text(`Compteur: ${compteur}`);
    } else {
        $("#FinishLevel").text("Félécitations vous avez terminé le jeu");
    }
}
/**
 * Permet de recommencer un niveau
 */
function restart() {
    $("#world").html("");
    initLevel(niveau);
}
/**
 * Permet d'afficher la fenêtre modale
 */
function help() {
    $("#myModal").css("display", "block");
}
/**
 * Permet de retirer la fenêtre modale
 */
function closeModal() {
    $("#myModal").css("display", "none");
}
/**
 * Permet d'annuler un mouvement
 */
function annulation() {
    const positions = states.pop();
    const dirJoueur = direction.pop();
    if (positions) {
        if (positions?.boxPosition === undefined) {
            removeAllClass();
            switch (dirJoueur) {
            case "Up": {
                getSquareAt(positions.playerPosition).addClass("joueurUp");
                break;}
            case "Down": {
                getSquareAt(positions.playerPosition).addClass("joueurBas");
                break;}
            case "Right": {
                getSquareAt(positions.playerPosition).addClass("joueurRight");
                break;}
            case "Left": {
                getSquareAt(positions.playerPosition).addClass("joueurLeft");
                break;}
            }
            getSquareAt(getPlayerPosition()).removeClass("joueur");
            getSquareAt(positions.playerPosition).addClass("joueur");
        } else {
            getSquareAt(positions.boxPosition).removeClass("boiteSurCible");
            getSquareAt(positions.boxPosition).removeClass("boite");
            getSquareAt(getPlayerPosition()).addClass("boite");
            removeAllClass();
            switch (dirJoueur) {
            case "Up": {
                getSquareAt(positions.playerPosition).addClass("joueurUp");
                break;}
            case "Down": {
                getSquareAt(positions.playerPosition).addClass("joueurBas");
                break;}
            case "Right": {
                getSquareAt(positions.playerPosition).addClass("joueurRight");
                break;}
            case "Left": {
                getSquareAt(positions.playerPosition).addClass("joueurLeft");
                break;}
            }
            getSquareAt(getPlayerPosition()).removeClass("joueur");
            getSquareAt(positions.playerPosition).addClass("joueur");
        }
        compteur--;
        $("#compteur").text(`Compteur: ${compteur}`);
    }
}

function finNiveau(){
    let best = levels[niveau].best;
    if(best != undefined){
    if(compteur === best*2){
        alert("vous avez perdu. Vous avez dépassé la limite de coups valides");
        restart();
        return true;
    }
}
return false;
}

function ChargerNiv(){
    let niv = $("#niv option:selected").val();
    $("#world").html("");
    initLevel(niv);
}
