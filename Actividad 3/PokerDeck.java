import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class Card {
    private String suit; // Palo: tréboles, corazones, picas o diamantes
    private String color; // Color: rojo o negro
    private String value; // Valor: 2-10, A, J, Q, K

    public Card(String suit, String color, String value) {
        this.suit = suit;
        this.color = color;
        this.value = value;
    }

    @Override
    public String toString() {
        return suit + "," + color + "," + value;
    }
}

class Deck {
    private ArrayList<Card> cards; // Almacena las cartas
    private ArrayList<Card> dealtCards; // Almacena cartas repartidas (opcional, para seguimiento)

    public Deck() {
        cards = new ArrayList<>();
        dealtCards = new ArrayList<>();
        // Inicializar las 52 cartas
        String[] suits = {"Tréboles", "Corazones", "Picas", "Diamantes"};
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] colors = {"Negro", "Rojo", "Negro", "Rojo"}; // Tréboles:Negro, Corazones:Rojo, Picas:Negro, Diamantes:Rojo

        for (int i = 0; i < suits.length; i++) {
            for (String value : values) {
                cards.add(new Card(suits[i], colors[i], value));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
        System.out.println("Se mezcló el Deck");
    }

    public void head() {
        if (cards.isEmpty()) {
            System.out.println("El deck está vacío");
            return;
        }
        Card card = cards.remove(0);
        dealtCards.add(card);
        System.out.println(card.toString());
        System.out.println("Quedan " + cards.size() + " cartas en el deck");
    }

    public void pick() {
        if (cards.isEmpty()) {
            System.out.println("El deck está vacío");
            return;
        }
        Random rand = new Random();
        int index = rand.nextInt(cards.size());
        Card card = cards.remove(index);
        dealtCards.add(card);
        System.out.println(card.toString());
        System.out.println("Quedan " + cards.size() + " cartas en el deck");
    }

    public Card[] hand() {
        if (cards.size() < 5) {
            System.out.println("No hay suficientes cartas en el deck");
            return new Card[0];
        }
        Card[] hand = new Card[5];
        for (int i = 0; i < 5; i++) {
            Card card = cards.remove(0);
            hand[i] = card;
            dealtCards.add(card);
            System.out.println(card.toString());
        }
        System.out.println("Quedan " + cards.size() + " cartas en el deck");
        return hand;
    }
}

// Clase de prueba para demostrar el funcionamiento
public class PokerDeck {
    public static void main(String[] args) {
        Deck deck = new Deck();

        // Probar shuffle
        deck.shuffle();

        // Probar head
        System.out.println("\nProbando head:");
        deck.head();

        // Probar pick
        System.out.println("\nProbando pick:");
        deck.pick();

        // Probar hand
        System.out.println("\nProbando hand:");
        deck.hand();
    }
}