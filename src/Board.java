public class Board {
    int[][] board;
    //0 is empty unchecked
    //1 is empty checked
    //2 is ship unchecked
    //3 is ship checked
    public int shipsLeft = 4;

    public Board(){
        this.board = new int[][] {{0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}};
        this.placeShips();
    }

    /*public boolean containsShips(){
        for(int y = 0; y<8; y++){
            for(int x = 0; x<8; x++){
                if(board[y][x]==2){
                    return true;
                }
            }
        }
        return false;
    }*/

    public void placeShips(){
        int randY = (int)(Math.random()*8);
        int randX = (int)(Math.random()*8);
        for(int n = 0; n<4; n++){
            while(board[randY][randX]!=0){
                randY = (int)(Math.random()*8);
                randX = (int)(Math.random()*8);
            }
            board[randY][randX]=2;
        }
    }

    public void renderBoard(boolean hide, int[] checkCoords){
        int space;
        if(hide){
            System.out.println("🔵: unchecked space; ⚪: checked space; 🔥: sunk ship\n" + this.shipsLeft + " ships remain.");
        }else{
            System.out.println("🔵: unchecked space; ⚪: checked space;\n⛵: unsunk ship; 🔥: sunk ship\n" + this.shipsLeft + " ships remain.");
        }
        String[] coords = new String[] {"1 ", "2 ", "3 ", "4 ", "5 ", "6 ", "7 ", "8 "};
        System.out.println("0  1 2 3  4 5 6  7 8");
        for(int y = 0; y<8; y++){
            System.out.print(coords[y]);
            for(int x = 0; x<8; x++){
                space = board[y][x];
                if(y==checkCoords[0]&&x==checkCoords[1]){
                    System.out.print("❌");
                }else if(space==0){
                    System.out.print("🔵");
                }else if(space==1){
                    System.out.print("⚪");
                }else if(space==2){
                    if(hide){
                        System.out.print("🔵");
                    }else{
                        System.out.print("⛵");
                    }
                }else{
                    System.out.print("🔥");
                }
            }
            System.out.println();
        }
    }

    public int checkSpace(int x, int y, boolean alter){
        if(x>7||y>7||board[y][x]%2!=0){
            return 5;
        }else{
            if(alter){
                if(board[y][x] ==2){
                    this.shipsLeft--;
                }
                board[y][x]++;
            }
            return board[y][x];
        }
    }
}