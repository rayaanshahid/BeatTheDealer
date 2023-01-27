package org.example.blackjack;

import java.util.List;
import java.util.Stack;

public class BlackJack {
    Stack<String> cards = new Stack<>();
    Player player1 = new Player("Sam");
    Player player2 = new Player("Dealer");
    String Winner;

    public String getWinner() {
        return Winner;
    }

    public void setWinner(String winner) {
        Winner = winner;
    }

    public void setCards(Stack<String> cards) {
        this.cards = cards;
    }

    public List<String> getCards() {
        return cards;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void distributeCards() {
        for(int i = 1; i<5; i++) {
            if(i%2==0) {
                player2.cards.add(cards.pop());
            } else {
                player1.cards.add(cards.pop());
            }
        }
        calculateScore(player1);
        calculateScore(player2);
    }

    public void calculateScore(Player player) {
        player.score = 0;
        for(String card: player.cards) {
            String stringValue = card.substring(1);
            int value;
            if(stringValue.equals("J") || stringValue.equals("Q") || stringValue.equals("K")) {
                value = 10;
            } else if(stringValue.equals("A")) {
                value = 11;
            } else {
                value = Integer.parseInt(stringValue);
            }
            player.score = player.score + value;
        }
    }

    public void player1Draw() {
        while(player1.score < 17) {
            player1.cards.add(cards.pop());
            calculateScore(player1);
        }
    }

    public void player2Draw(){
        while(player2.score <= player1.score) {
            player2.cards.add(cards.pop());
            calculateScore(player2);
        }
    }

    public void drawOrDecideWinner(){
        //Check if someone won after the first Distribution
        if ((player1.getScore() > 21) || (player2.getScore() > 21)) {
            setWinner(player2.getName());
        }else if (player1.getScore() == 21) {
            setWinner(player1.getName());
        } else if (player2.getScore() == 21) {
            setWinner(player2.getName());
        } else {
            //If neither one win in the first round
            //Sam starts drawing
            player1Draw();
            if (player1.getScore() >= 21) {
                //Dealer wins if Sam reaches 21 or higher
                setWinner(player2.getName());
            } else {
                //Dealer starts Drawing
                player2Draw();
                if (player2.getScore() >= 21) {
                    //Sam wins if Dealer reaches 21 or higher
                    setWinner(player1.getName());
                } else {
                    //Dealer wins if Dealer does not reach 21 or higher and still is higher than Sam
                    setWinner(player2.getName());
                }
            }
        }
    }
}