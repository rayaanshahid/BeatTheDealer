package org.example;

import org.example.blackjack.BlackJack;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class App {
    public static void main(String[] args) {
        String fileName;
        if (args.length < 1) {
            fileName = "deck-of-cards.txt";
        } else {
            fileName = args[0];
        }

        int chosenCards = menu();

        //Setting up the deck of cards
        BlackJack blackJack = new BlackJack();
        try {
            if(chosenCards == 1){
                blackJack.setCards(createStack(readFromFile(fileName)));
            }else{
                List<String> newDeck = Arrays.asList("H5", "C3", "CA", "H9", "HK", "HQ", "D8", "S3", "C7", "SA",
                        "C2", "DJ", "D6", "H10", "C10", "CJ", "SJ", "DQ", "SQ", "C8", "D5", "S9", "C9", "S6", "HA",
                        "H3", "D4", "S2", "S5", "HJ", "SK", "H2", "H4", "C4", "C5", "D3", "DA", "S7", "S10", "CK",
                        "D10", "H7", "C6", "CQ", "H6", "S8", "H8", "S4", "D2", "DK", "D7", "D9");
                Collections.shuffle(newDeck); //shuffle once more
                System.out.println("Shuffled Deck: " + newDeck);
                blackJack.setCards(createStack(newDeck));
            }
        } catch (Exception ex) {
            System.out.println("Couldn't read from file: " + ex);
        }

        //Distribute cards for the first time to both Sam (Player1) and the Dealer (Player2)
        blackJack.distributeCards();

        //Decide winner
        blackJack.drawOrDecideWinner();

        System.out.println("And the winner is: " + blackJack.getWinner());
        System.out.println(blackJack.getPlayer1().getName() + ": " + blackJack.getPlayer1().getCards() + " | " + "Score: " + blackJack.getPlayer1().getScore());
        System.out.println(blackJack.getPlayer2().getName() + ": " + blackJack.getPlayer2().getCards() + " | " + "Score: " + blackJack.getPlayer2().getScore());
    }

    public static List<String> readFromFile(String fileName) throws Exception {
        InputStream is = App.class.getClassLoader().getResourceAsStream(fileName);
        String fileContent = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        return Arrays.asList(fileContent.split(", "));
    }

    public static Stack<String> createStack(List<String> cardsAsList) {
        Stack<String> st = new Stack<>();
        Collections.reverse(cardsAsList);
        st.addAll(cardsAsList);
        return st;
    }

    public static int menu() {
        System.out.println("Choose the deck of cards!");
        System.out.println("1. Choose from file!");
        System.out.println("2. Create a new one!");

        Scanner input = new Scanner(System.in);
        System.out.print("Enter desired number: ");
        return input.nextInt();
    }
}
