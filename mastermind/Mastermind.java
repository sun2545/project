/**
 * July, 11, 2017
 * Sunhye Kwon
 * Course : Computer programming
 * PROG24178 Object Oriented Programming 2 - Java - 1175_13054
 */
package assignment1;

import java.util.Scanner;

public class Mastermind implements MastermindInterface {

    // random 4 number into array
    int[] pc = new int[4];

    // 'X' release answer if fini is true
    static boolean fini = false;

    //4 number which get from user into array
    static int[] innum = new int[4];

    //make default 4 X 10 array with '9' for user input
    int[][] array = {{9, 9, 9, 9}, {9, 9, 9, 9,}, {9, 9, 9, 9}, {9, 9, 9, 9},
            {9, 9, 9, 9}, {9, 9, 9, 9}, {9, 9, 9, 9}, {9, 9, 9, 9},
            {9, 9, 9, 9}, {9, 9, 9, 9}};

    //make default 4 X 10 array with '9' for validate
    int[][] validate = {{9, 9, 9, 9}, {9, 9, 9, 9}, {9, 9, 9, 9}, {9, 9, 9, 9}, {9, 9, 9, 9},
            {9, 9, 9, 9}, {9, 9, 9, 9}, {9, 9, 9, 9}, {9, 9, 9, 9}, {9, 9, 9, 9}};

    public void newGame() {

        //random array
        int[] randomMap = {1, 2, 3, 4, 5, 6, 7, 8};

        // avoid to happen the same number
        for (int n = 0; n < 5; n++) {
            for (int i = 0; i < 8; i++) {
                int random = (int) (Math.random() * 8);
                int Num = randomMap[random];
                int temp = randomMap[i];
                randomMap[i] = Num;
                randomMap[random] = temp;
            }
        }

       //input random value to array
        for (int i = 0; i < 4; i++) {
            pc[i] = randomMap[i];
        }
        drawGame();
    }

    public void drawGame() {
        System.out.println("-----------------");

        //display 'X' before finishing the game
        if (!fini) {
            for (int i = 0; i < 4; i++) {
                System.out.print("| X ");
            }
            System.out.print("|");

         // display the answer after finishing the gmae
        } else {
            for (int j = 0; j < 4; j++) {
                System.out.print("| " + pc[j] + " ");
            }
            System.out.print("|");
        }
        System.out.println();

        // display space without value in user input space
        for (int outer = 0; outer < array.length; outer++) {
            System.out.println("-----------------     ----------");
            for (int inner = 0; inner < array[outer].length; inner++) {
                System.out.print("| ");
                if (array[outer][inner] == 9) {
                    System.out.print("  ");
                } else System.out.print(array[outer][inner] + " ");
            }
            System.out.print("|==|");

            //display space without value in validate space
            for (int inner = 0; inner < validate[inner].length; inner++) {
                if (validate[outer][inner] == 9) {
                    System.out.print("   ");
                } else System.out.print("  " + validate[outer][inner]);
            }
            System.out.println("  |");
        }
        System.out.println("-----------------     ----------");
    }


    public void checkValues(int cnt) {

        //check array it can compare with between user value and random value
        int[] befcheck = new int[4];

        //make array for check to avoid the same number
        boolean[] flag = new boolean[4];
        int tem = 0;
        for (int i = 0; i < innum.length; i++) {
            if (innum[i] == pc[i]) {
                befcheck[i] = 2;
                flag[i] = true;
            }
            tem = pc[i];
            for (int j = 0; j < innum.length; j++) {
                if (innum[j] == tem) {
                    if (!flag[i]) {
                        flag[i] = true;
                    }
                }

                if (innum[i] == pc[j]) {
                    if (!flag[j]) {
                        befcheck[j] = 1;
                        flag[j] = true;
                    }
                }
            }
        }

        //sort the array for display descending
        for (int i = 0; i < befcheck.length; i++) {
            for (int j = i + 1; j < befcheck.length; j++) {
                if (befcheck[i] < befcheck[j]) {
                    int temp = befcheck[i];
                    befcheck[i] = befcheck[j];
                    befcheck[j] = temp;
                }
            }
        }

        //display forward from bottom to up
        for(int k=0; k<innum.length;k++){
            validate[validate.length - cnt][k] = befcheck[k];
        }

        //check if user input value and random are the same
        if(befcheck[0]==2 && befcheck[1]==2 && befcheck[2]==2 && befcheck[3]==2){
            fini=true;
            System.out.println("You win");
        }
    }


    public void updateGame(int cnt) {

        //input the user value to array and reload( drawGame())
        for(int i=0; i<4;i++){
            for(int j=0; j<4; j++){
                array[array.length-cnt][i]= innum[i];
            }
        }
        drawGame();

    }


    public static void main(String[] args) {
        Mastermind msmind = new Mastermind();
        msmind.newGame();
        int cnt=0;
        Scanner input = new Scanner(System.in);
        System.out.println("Let's start the game!!");

        //get the value from user and convert data type as String to int
        while(cnt<10) {
            System.out.println("Enter 4 number");
            String number = input.nextLine();
            String[] num = number.split(" ");
            if (num.length == 4) {
                for (int i = 0; i < 4; i++) {
                    innum[i] = Integer.parseInt(num[i]);
                }
                cnt++;
                msmind.checkValues(cnt);
                msmind.updateGame(cnt);
            }
        }

        fini=true;
        msmind.updateGame(cnt);
        System.out.println("You lost");
    }
}