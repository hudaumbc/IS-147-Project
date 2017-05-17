package com.company;

import java.util.Scanner;
import java.util.Objects;
public class Main {

    static int[] deck = {2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 8, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 11, 11, 11, 11};
    static int [] deck2;
    static boolean playerAce;
    static boolean dealerAce;
    static boolean playerBlackjack;
    static boolean dealerBlackjack;
    static int playerScore;
    static int dealerScore;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        shuffle(deck);
         deck2 = deck;
        draw();
        draw();
        if(playerScore == 21){
            playerBlackjack = true;
            System.out.println("Blackjack!!");
        }
        if(playerBlackjack==true && dealerBlackjack==false){
            System.out.println("You win");
            System.exit(0);
        }
        System.out.println(playerScore);
        choice();
        dealer();
        if(playerScore>dealerScore){
            System.out.println("You win");
        }
        if(playerScore<=dealerScore){
            System.out.println("You lose");
        }




    }


    public static void shuffle(int... deck) {
        for (int i = deck.length - 1; i > 0; i--) {
            int rand = (int) (Math.random() * (i + 1));
            int temp = deck[i];
            deck[i] = deck[rand];
            deck[rand] = temp;
        }
    }
    public static void draw(){
        int range = deck.length;
        int card = (int)(Math.random() * range);
        int drawnCard = deck2[card];
        if (drawnCard >= 2){
           playerScore = drawnCard + playerScore;
        }
        if(drawnCard == 11) {
            playerAce = true;
            System.out.println("You have an ace");
        }
        if(drawnCard == 0) {
            draw();
        }
        deck2[drawnCard] = 0;
        if(playerScore >= 22 && playerAce == true){
            playerScore = playerScore - 10;
            playerAce = false;
        }
        else if(playerScore >=22 && playerAce ==false) {
            System.out.println("Over 21, you lose" + playerScore);
            System.exit(0);
        }
    }

    public static void dealer (){
       dealerDraw();
       dealerDraw();
        if(dealerScore == 21) {
            dealerBlackjack = true;
        }
        if(dealerBlackjack==true && playerBlackjack==true){
            System.out.println("Tie");
            System.exit(0);
        }
        if(dealerBlackjack==true && playerBlackjack==false) {
            System.out.println("Dealer blackjack, you lose");
            System.exit(0);
        }
        dealerChoice();
    }

    public static void dealerDraw () {
        int range = deck.length;
        int card = (int) (Math.random() * range);
        int drawnCard = deck2[card];
        if (drawnCard >= 2) {
            dealerScore = drawnCard + dealerScore;
        }
        if (drawnCard == 11) {
            dealerAce = true;
        }
        if(drawnCard == 0) {
            draw();
        }
        deck2[drawnCard] = 0;
        if(dealerScore >= 22){
            System.out.println("Dealer busts, you win");
            System.exit(0);
        }
    }

    public static void choice() {
        System.out.println("Draw or keep?");
        String input = scanner.nextLine();
        if (Objects.equals(input,"draw")) {
            draw();
            System.out.println(playerScore);
            choice();
        }

    }
    public static void dealerChoice(){
        if(dealerScore>16){
            dealerDraw();
            dealerChoice();
        }
    }

}
