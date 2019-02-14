import java.util.Scanner;

public class Game {
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        System.out.println("Begin the game!\n");
        Board myBoard = new Board();
        Board oppBoard = new Board();

        String userInput;
        int result;
        boolean error;
        int guessX = 9; //initialize to a number outside of the range
        int guessY = 9; //initialize to a number outside of the range
        int turn = 0;
        while(myBoard.shipsLeft>0&&oppBoard.shipsLeft>0){
            while(turn==0){
                System.out.println("What do you want to do?\nS: shoot       C: check your board");
                //userInput = scan.nextLine().toLowerCase();
                userInput = getResp(new String[] {"s", "c"});

                if(userInput.equals("c")){
                    System.out.println();
                    myBoard.renderBoard(false, new int[] {9, 9});
                    System.out.println();
                }else if(userInput.equals("h")){
                    System.out.print("\nHacking grid");
                    printWait();

                    System.out.println("\nYour opponent's board:");
                    oppBoard.renderBoard(false, new int[] {9,9});
                    try{
                        Thread.sleep(2000);
                    }catch(InterruptedException e){}
                    System.out.println();
                }else{
                    System.out.println("\nThe opponent's board:");
                    oppBoard.renderBoard(true, new int[] {9, 9});
                    while(oppBoard.checkSpace(guessX, guessY, false)==5){
                        System.out.println("Please enter the X coordinate of your target.");
                        //check to make sure it's an int, between 1 and 8
                        guessX = getCoord();

                        System.out.println("Please enter the Y coordinate of your target.");
                        guessY = getCoord();

                        if(oppBoard.checkSpace(guessX, guessY, false)==5){
                            System.out.println("\nUh oh! It looks like you've already fired on that coordinate.");
                        }
                    }

                    System.out.println();
                    oppBoard.renderBoard(true, new int[] {guessY, guessX});
                    System.out.println("\nWould you like to fire on this coordinate? Y/N");
                    userInput = getResp(new String[] {"y", "n"});
                    if(userInput.equals("y")){
                        result = oppBoard.checkSpace(guessX, guessY, true);
                        System.out.print("Firing");
                        printWait();
                        if(result == 1){
                            System.out.println("You hit empty water. 💧");
                        }else{
                            System.out.println("You hit a ship! 🔥");

                        }
                        turn++;
                    }else{
                        guessX=9;
                        guessY=9;
                        System.out.println();
                    }
                }
            }

            turnChange();

            if(oppBoard.shipsLeft==0){
                break;
            }

            guessX = (int)(Math.random()*8);
            guessY = (int)(Math.random()*8);
            System.out.println("It's the computer's turn!");
            while(myBoard.checkSpace(guessX, guessY, false)==5){
                guessX = (int)(Math.random()*8);
                guessY = (int)(Math.random()*8);
            }
            try{
                Thread.sleep(2000);
            }catch(InterruptedException e){}
            System.out.println("The computer is firing on (" + (guessX+1) + ", " + (guessY+1) + ").");
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){}

            printWait();
            result = myBoard.checkSpace(guessX, guessY, true);
            if(result == 1){
                System.out.println("😅 The computer hit empty water. 💧");
            }else{
                System.out.println("😱 The computer hit a ship! 🔥\nYou have " + myBoard.shipsLeft + " ships left.");
            }

            guessX = 9;
            guessY = 9;
            turn--;

            turnChange();
        }
        System.out.println("Game over!");
        if(oppBoard.shipsLeft==0){
            System.out.println("🎉 Congratulations, you win! 🎉");
        }else{
            System.out.println("🤕 Oh no, you lost! 🤕");
        }
    }

    private static int getCoord(){
        Scanner scan = new Scanner(System.in);
        int coord = 9;
        boolean error;
        while(coord>7||coord<0){
            error = true;
            while(error){
                error = !scan.hasNextInt();
                if(error){
                    System.out.println("Please enter an integer.");
                    scan.nextLine();
                }
            }
            coord = scan.nextInt()-1;
            if(coord>7||coord<0){
                System.out.println("Please enter an integer between 1 and 8.");
                scan.nextLine();
            }
        }
        return coord;
    }

    private static String getResp(String[] okAns){
        Scanner scan = new Scanner(System.in);
        String resp = "";
        boolean error = true;
        String endMsg = "Please enter \"";
        for(int n = 0; n<okAns.length; n++){
            endMsg += okAns[n].toUpperCase();
            if(okAns.length>2){
                endMsg += ",\" \"";
            }else if(n==okAns.length-2){
                endMsg +="\" or \"";
            }
        }
        endMsg += ".\"";

        while(error){
            resp = scan.nextLine().toLowerCase();
            for(int n = 0; n<okAns.length; n++){
                if(okAns[n].equals(resp)||resp.equals("h")){
                    error = false;
                }
            }
            if(error){
                System.out.println(endMsg);
            }
        }
        return resp;
    }

    private static void printWait(){
        try{
            Thread.sleep(500);
        }catch(InterruptedException e){}
        for(int n =0; n<3; n++){
            System.out.print(".");
            try{
                Thread.sleep(500);
            }catch(InterruptedException e){}
        }
        System.out.println();
    }

    private static void turnChange(){
        try{
            Thread.sleep(500);
        }catch(InterruptedException e){}
        System.out.println();
        try{
            Thread.sleep(500);
        }catch(InterruptedException e){}
        System.out.println("—— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— ——");
        try{
            Thread.sleep(500);
        }catch(InterruptedException e){}
        System.out.println();
        try{
            Thread.sleep(500);
        }catch(InterruptedException e){}
    }
}
