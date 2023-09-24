package g58412.boulder;

import g58412.boulder.controller.Controller;

public class Main {

    private Controller controller;

    /**
     * Controller for the main
     */
    public Main() {
        this.controller = new Controller();
    }

    /**
     * Starts the game
     */
    public void start(){
        controller.play();
    }

    public static void main(String[] args) {
        Main test = new Main();
        test.start();
    }
}