package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.example.blackjack.BlackJack;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;


public class BlackJackTest{
    @Test
    public void testDistributeCorrectCards() {
        BlackJack blackjack = new BlackJack();

        List<String> newDeck = Arrays.asList("H5", "C3", "CA", "H9", "HK", "HQ", "D8", "S3", "C7", "SA",
                "C2", "DJ", "D6", "H10", "C10", "CJ", "SJ", "DQ", "SQ", "C8", "D5", "S9", "C9", "S6", "HA",
                "H3", "D4", "S2", "S5", "HJ", "SK", "H2", "H4", "C4", "C5", "D3", "DA", "S7", "S10", "CK",
                "D10", "H7", "C6", "CQ", "H6", "S8", "H8", "S4", "D2", "DK", "D7", "D9");

        blackjack.setCards(App.createStack(newDeck));
        blackjack.distributeCards();

        assertEquals(Arrays.asList("H5", "CA"),blackjack.getPlayer1().getCards());
        assertEquals(Arrays.asList("C3", "H9"),blackjack.getPlayer2().getCards());
        assertEquals(16, blackjack.getPlayer1().getScore());
        assertEquals(12, blackjack.getPlayer2().getScore());
    }

    @Test
    public void testDistributeInCorrectCards() {
        BlackJack blackjack = new BlackJack();

        List<String> newDeck = Arrays.asList("H5", "C3", "CA", "H9", "HK", "HQ", "D8", "S3", "C7", "SA",
                "C2", "DJ", "D6", "H10", "C10", "CJ", "SJ", "DQ", "SQ", "C8", "D5", "S9", "C9", "S6", "HA",
                "H3", "D4", "S2", "S5", "HJ", "SK", "H2", "H4", "C4", "C5", "D3", "DA", "S7", "S10", "CK",
                "D10", "H7", "C6", "CQ", "H6", "S8", "H8", "S4", "D2", "DK", "D7", "D9");

        blackjack.setCards(App.createStack(newDeck));
        blackjack.distributeCards();

        assertNotEquals(Arrays.asList("H3", "C10"),blackjack.getPlayer1().getCards());
        assertNotEquals(Arrays.asList("C1", "H5"),blackjack.getPlayer2().getCards());
        assertNotEquals(13, blackjack.getPlayer1().getScore());
        assertNotEquals(6, blackjack.getPlayer2().getScore());
    }

    @Test
    public void testCorrectWinner() {
        BlackJack blackjack = new BlackJack();

        List<String> newDeck = Arrays.asList("H5", "C3", "CA", "H9", "HK", "HQ", "D8", "S3", "C7", "SA",
                "C2", "DJ", "D6", "H10", "C10", "CJ", "SJ", "DQ", "SQ", "C8", "D5", "S9", "C9", "S6", "HA",
                "H3", "D4", "S2", "S5", "HJ", "SK", "H2", "H4", "C4", "C5", "D3", "DA", "S7", "S10", "CK",
                "D10", "H7", "C6", "CQ", "H6", "S8", "H8", "S4", "D2", "DK", "D7", "D9");

        blackjack.setCards(App.createStack(newDeck));
        blackjack.distributeCards();
        blackjack.drawOrDecideWinner();

        assertEquals("Dealer",blackjack.getWinner());
    }

    @Test
    public void testInCorrectWinner() {
        BlackJack blackjack = new BlackJack();

        List<String> newDeck = Arrays.asList("H5", "C3", "CA", "H9", "HK", "HQ", "D8", "S3", "C7", "SA",
                "C2", "DJ", "D6", "H10", "C10", "CJ", "SJ", "DQ", "SQ", "C8", "D5", "S9", "C9", "S6", "HA",
                "H3", "D4", "S2", "S5", "HJ", "SK", "H2", "H4", "C4", "C5", "D3", "DA", "S7", "S10", "CK",
                "D10", "H7", "C6", "CQ", "H6", "S8", "H8", "S4", "D2", "DK", "D7", "D9");

        blackjack.setCards(App.createStack(newDeck));
        blackjack.distributeCards();
        blackjack.drawOrDecideWinner();

        assertNotEquals("Sam",blackjack.getWinner());
    }
}
