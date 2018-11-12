import java.util.Scanner;

public class mazeRunner {

    public static Maze myMap = new Maze();
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int movesTaken = 0;
        intro();

        while (!myMap.didIWin()) {
            movesTaken += 1;
            String direction = userMove();
            takeMove(direction);
            movesMessage(movesTaken);
            if (movesTaken == 100) {
                break;
            }
        }

        if (myMap.didIWin()) {
            System.out.println("Congratulations, you made it out alive! and you did it in " + movesTaken + " moves");
        }


    }

    public static void intro() {
        System.out.println("Welcome to Maze Runner!");
        System.out.println("Here is your current position:");
        myMap.printMap();

        String move = userMove();
        takeMove(move);
    }

    public static String userMove() {
        String move;
        System.out.print("Where would you like to move? (R, L, U, D) ");
        move = input.next();
        while (!move.equals("R") && !move.equals("L") && !move.equals("U") && !move.equals("D")) {
            System.out.print("Incorrect response. Please select again. (R, L, U, D) ");
            move = input.next();
        }
        return move;
    }

    public static void takeMove(String move) {
        if (move.equals("L") && myMap.canIMoveLeft()) {
            myMap.moveLeft();
        } else if (move.equals("R") && myMap.canIMoveRight()) {
            myMap.moveRight();
        } else if (move.equals("U") && myMap.canIMoveUp()) {
            myMap.moveUp();
        } else if (move.equals("D") && myMap.canIMoveDown()) {
            myMap.moveDown();
        } else if (myMap.isThereAPit(move)) {
            navigatePit(move);
        } else {
            System.out.println("Sorry you have a brick wall!");
        }

        myMap.printMap();
    }

    public static void movesMessage(int move) {
        if (move == 50) {
            System.out.println("Warning: You have made 50 moves, you have 50 remaining before the maze exit closes");
        } else if (move == 75) {
            System.out.println("Alert! You have made 75 moves, you only have 25 moves left to escape.");
        } else if (move == 90) {
            System.out.println("DANGER! You have made 90 moves, you only have 10 moves left to escape!!");
        } else if (move == 100) {
            System.out.println("Oh no! You took too long to escape, and now the maze exit is closed FOREVER >:[");
            System.out.println("Sorry, but you didn't escape in time- you lose!");
        }
    }

    public static void navigatePit(String move) {
        System.out.print("Watch out! There's a pit ahead, jump it? (y/n) ");
        String option = input.next();
        if (option.substring(0, 1).equalsIgnoreCase("y")){
            myMap.jumpOverPit(move);
        }

    }
}
