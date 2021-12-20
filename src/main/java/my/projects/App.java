package my.projects;

// First Project: Terminal blackjack
// Uses one deck per game
// Dealer stays on 17
// Aces always count as 11
// Make sure common-lang3.jar is added to libraries

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;

public class App {
    public static void main( String[] args ) {
        Scanner input = new Scanner(System.in);
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> userCards = new ArrayList<>();
        ArrayList<String> dealerCards = new ArrayList<>();
        int userSum, dealerSum;
        char hitOrStay;

        names.add("Ace");
        names.add("Ace");
        names.add("Ace");
        names.add("Ace");
        names.add("King");
        names.add("King");
        names.add("King");
        names.add("King");
        names.add("Queen");
        names.add("Queen");
        names.add("Queen");
        names.add("Queen");
        names.add("Jack");
        names.add("Jack");
        names.add("Jack");
        names.add("Jack");
        names.add("Ten");
        names.add("Ten");
        names.add("Ten");
        names.add("Ten");
        names.add("Nine");
        names.add("Nine");
        names.add("Nine");
        names.add("Nine");
        names.add("Eight");
        names.add("Eight");
        names.add("Eight");
        names.add("Eight");
        names.add("Seven");
        names.add("Seven");
        names.add("Seven");
        names.add("Seven");
        names.add("Six");
        names.add("Six");
        names.add("Six");
        names.add("Six");
        names.add("Five");
        names.add("Five");
        names.add("Five");
        names.add("Five");
        names.add("Four");
        names.add("Four");
        names.add("Four");
        names.add("Four");
        names.add("Three");
        names.add("Three");
        names.add("Three");
        names.add("Three");
        names.add("Two");
        names.add("Two");
        names.add("Two");
        names.add("Two");

        ArrayList<String> namesCopy = new ArrayList<>(names);
        int[] cardValues = new int[namesCopy.size()];

        // Sets integer values for cards
        for (int i = 0; i < namesCopy.size(); i++) {
            switch (namesCopy.get(i)) {
                case "Ace":
                    cardValues[i] = 11;
                    break;
                case "King":
                case "Queen":
                case "Jack":
                case "Ten":
                    cardValues[i] = 10;
                    break;
                case "Nine":
                    cardValues[i] = 9;
                    break;
                case "Eight":
                    cardValues[i] = 8;
                    break;
                case "Seven":
                    cardValues[i] = 7;
                    break;
                case "Six":
                    cardValues[i] = 6;
                    break;
                case "Five":
                    cardValues[i] = 5;
                    break;
                case "Four":
                    cardValues[i] = 4;
                    break;
                case "Three":
                    cardValues[i] = 3;
                    break;
                case "Two":
                    cardValues[i] = 2;
                    break;
            }
        }

        System.out.println("Welcome to blackjack!");
        System.out.println("=====================");

        // Generates user's first card
        int userCard1 = ThreadLocalRandom.current().nextInt(0, names.size());
        String userCard1Name = names.get(userCard1);
        userCards.add(userCard1Name);

        // Removes card from deck
        for (int i = 0; i < names.size(); i++) {
            if (userCard1Name.equals(names.get(i))) {
                names.remove(i);
                break;
            }
        }

        // Generates dealer's first card
        int dealerCard1 = ThreadLocalRandom.current().nextInt(0, names.size());
        String dealerCard1Name = names.get(dealerCard1);
        dealerCards.add(dealerCard1Name);

        // Removes card from deck
        for (int i = 0; i < names.size(); i++) {
            if (dealerCard1Name.equals(names.get(i))) {
                names.remove(i);
                break;
            }
        }

        // Generates user's second card
        int userCard2 = ThreadLocalRandom.current().nextInt(0, names.size());
        String userCard2Name = names.get(userCard2);
        userCards.add(userCard2Name);

        // Removes card from deck
        for (int i = 0; i < names.size(); i++) {
            if (userCard2Name.equals(names.get(i))) {
                names.remove(i);
                break;
            }
        }

        // Generates dealer's second card
        int dealerCard2 = ThreadLocalRandom.current().nextInt(0, names.size());
        String dealerCard2Name = names.get(dealerCard2);
        dealerCards.add(dealerCard2Name);

        // Removes card from deck
        for (int i = 0; i < names.size(); i++) {
            if (dealerCard2Name.equals(names.get(i))) {
                names.remove(i);
                break;
            }
        }

        // Invokes method to find hand values
        userSum = getSum(namesCopy, userCards, cardValues);
        dealerSum = getSum(namesCopy, dealerCards, cardValues);

        // Prints hand, asks for user's next move
        System.out.println("\nDealer: X - " + dealerCard2Name);
        System.out.println("You: " + userCards.get(0) + " - " + userCards.get(1) +
                " (" + userSum + ")");

        // Determines outcome for user blackjack
        if (userSum == 21 && dealerSum != 21) {
            System.out.println("Blackjack! You win!");
            System.exit(0);
        }

        System.out.print("\nDo you hit or stay? (h/s): ");
        hitOrStay = input.next().charAt(0);

        // Restricts input
        while (hitOrStay != 'h' && hitOrStay != 's') {
            System.out.println("Invalid input.");
            System.out.print("\nDo you hit or stay? (h/s): ");
            hitOrStay = input.next().charAt(0);
        }

        // User could theoretically hit 6 times
        // First hit
        if (hitOrStay == 'h') {

            // Generates user's third card
            int userCard3 = ThreadLocalRandom.current().nextInt(0, names.size());
            String userCard3Name = names.get(userCard3);
            userCards.add(userCard3Name);

            // Removes card from deck
            for (int i = 0; i < names.size(); i++) {
                if (userCard3Name.equals(names.get(i))) {
                    names.remove(i);
                    break;
                }
            }

            // Invokes methods to find hand value and to check sum
            userSum = getSum(namesCopy, userCards, cardValues);
            boolean busted = checkSum(userSum);

            // Prints hand
            System.out.println("\nDealer: X - " + dealerCard2Name);
            System.out.println("You: " + userCards.get(0) + " - " + userCards.get(1) +
                    " - " + userCards.get(2) + " (" + userSum + ")");

            // Runs if user busted
            if (busted) {
                System.out.println("You busted! Dealer wins.");
                System.exit(0);
            }

            // Second hit
            else {
                System.out.print("\nDo you hit or stay? (h/s): ");
                hitOrStay = input.next().charAt(0);

                // Restricts input
                while (hitOrStay != 'h' && hitOrStay != 's') {
                    System.out.println("Invalid input.");
                    System.out.print("\nDo you hit or stay? (h/s): ");
                    hitOrStay = input.next().charAt(0);
                }

                if (hitOrStay == 'h') {

                    // Generates user's fourth card
                    int userCard4 = ThreadLocalRandom.current().nextInt(0, names.size());
                    String userCard4Name = names.get(userCard4);
                    userCards.add(userCard4Name);

                    // Removes card from deck
                    for (int i = 0; i < names.size(); i++) {
                        if (userCard4Name.equals(names.get(i))) {
                            names.remove(i);
                            break;
                        }
                    }

                    // Invokes methods to find hand value and to check sum
                    userSum = getSum(namesCopy, userCards, cardValues);
                    boolean busted2 = checkSum(userSum);

                    // Prints hand
                    System.out.println("\nDealer: X - " + dealerCard2Name);
                    System.out.println("You: " + userCards.get(0) + " - " + userCards.get(1) +
                            " - " + userCards.get(2) + " - " + userCards.get(3) + " (" + userSum + ")");

                    // Runs if user busted
                    if (busted2) {
                        System.out.println("You busted! Dealer wins.");
                        System.exit(0);
                    }

                    // Third hit
                    else {
                        System.out.print("\nDo you hit or stay? (h/s): ");
                        hitOrStay = input.next().charAt(0);

                        // Restricts input
                        while (hitOrStay != 'h' && hitOrStay != 's') {
                            System.out.println("Invalid input.");
                            System.out.print("\nDo you hit or stay? (h/s): ");
                            hitOrStay = input.next().charAt(0);
                        }

                        if (hitOrStay == 'h') {

                            // Generates user's fifth card
                            int userCard5 = ThreadLocalRandom.current().nextInt(0, names.size());
                            String userCard5Name = names.get(userCard5);
                            userCards.add(userCard5Name);

                            // Removes card from deck
                            for (int i = 0; i < names.size(); i++) {
                                if (userCard5Name.equals(names.get(i))) {
                                    names.remove(i);
                                    break;
                                }
                            }

                            // Invokes methods to find hand value and to check sum
                            userSum = getSum(namesCopy, userCards, cardValues);
                            boolean busted3 = checkSum(userSum);

                            // Prints hand
                            System.out.println("\nDealer: X - " + dealerCard2Name);
                            System.out.println("You: " + userCards.get(0) + " - " + userCards.get(1) +
                                    " - " + userCards.get(2) + " - " + userCards.get(3) +
                                    " - " + userCards.get(4) + " (" + userSum + ")");

                            // Runs if user busted
                            if (busted3) {
                                System.out.println("You busted! Dealer wins.");
                                System.exit(0);
                            }

                            // Fourth hit
                            else {
                                System.out.print("\nDo you hit or stay? (h/s): ");
                                hitOrStay = input.next().charAt(0);

                                // Restricts input
                                while (hitOrStay != 'h' && hitOrStay != 's') {
                                    System.out.println("Invalid input.");
                                    System.out.print("\nDo you hit or stay? (h/s): ");
                                    hitOrStay = input.next().charAt(0);
                                }

                                if (hitOrStay == 'h') {

                                    // Generates user's sixth card
                                    int userCard6 = ThreadLocalRandom.current().nextInt(0, names.size());
                                    String userCard6Name = names.get(userCard6);
                                    userCards.add(userCard6Name);

                                    // Removes card from deck
                                    for (int i = 0; i < names.size(); i++) {
                                        if (userCard6Name.equals(names.get(i))) {
                                            names.remove(i);
                                            break;
                                        }
                                    }

                                    // Invokes methods to find hand value and to check sum
                                    userSum = getSum(namesCopy, userCards, cardValues);
                                    boolean busted4 = checkSum(userSum);

                                    // Prints hand
                                    System.out.println("\nDealer: X - " + dealerCard2Name);
                                    System.out.println("You: " + userCards.get(0) + " - " + userCards.get(1) +
                                            " - " + userCards.get(2) + " - " + userCards.get(3) +
                                            " - " + userCards.get(4) + " - " + userCards.get(5) + " (" + userSum + ")");

                                    // Runs if user busted
                                    if (busted4) {
                                        System.out.println("You busted! Dealer wins.");
                                        System.exit(0);
                                    }

                                    // Fifth hit
                                    else {
                                        System.out.print("\nDo you hit or stay? (h/s): ");
                                        hitOrStay = input.next().charAt(0);

                                        // Restricts input
                                        while (hitOrStay != 'h' && hitOrStay != 's') {
                                            System.out.println("Invalid input.");
                                            System.out.print("\nDo you hit or stay? (h/s): ");
                                            hitOrStay = input.next().charAt(0);
                                        }

                                        if (hitOrStay == 'h') {

                                            // Generates user's seventh card
                                            int userCard7 = ThreadLocalRandom.current().nextInt(0, names.size());
                                            String userCard7Name = names.get(userCard7);
                                            userCards.add(userCard7Name);

                                            // Removes card from deck
                                            for (int i = 0; i < names.size(); i++) {
                                                if (userCard7Name.equals(names.get(i))) {
                                                    names.remove(i);
                                                    break;
                                                }
                                            }

                                            // Invokes methods to find hand value and to check sum
                                            userSum = getSum(namesCopy, userCards, cardValues);
                                            boolean busted5 = checkSum(userSum);

                                            // Prints hand
                                            System.out.println("\nDealer: X - " + dealerCard2Name);
                                            System.out.println("You: " + userCards.get(0) + " - " + userCards.get(1) +
                                                    " - " + userCards.get(2) + " - " + userCards.get(3) +
                                                    " - " + userCards.get(4) + " - " + userCards.get(5) +
                                                    " - " + userCards.get(6) + " (" + userSum + ")");

                                            // Runs if user busted
                                            if (busted5) {
                                                System.out.println("You busted! Dealer wins.");
                                                System.exit(0);
                                            }

                                            // Sixth hit
                                            else {
                                                System.out.print("\nDo you hit or stay? (h/s): ");
                                                hitOrStay = input.next().charAt(0);

                                                // Restricts input
                                                while (hitOrStay != 'h' && hitOrStay != 's') {
                                                    System.out.println("Invalid input.");
                                                    System.out.print("\nDo you hit or stay? (h/s): ");
                                                    hitOrStay = input.next().charAt(0);
                                                }

                                                if (hitOrStay == 'h') {

                                                    // Generates user's seventh card
                                                    int userCard8 = ThreadLocalRandom.current().nextInt(
                                                            0, names.size());
                                                    String userCard8Name = names.get(userCard8);
                                                    userCards.add(userCard8Name);

                                                    // Removes card from deck
                                                    for (int i = 0; i < names.size(); i++) {
                                                        if (userCard8Name.equals(names.get(i))) {
                                                            names.remove(i);
                                                            break;
                                                        }
                                                    }

                                                    // Invokes methods to find hand value and to check sum
                                                    userSum = getSum(namesCopy, userCards, cardValues);
                                                    boolean busted6 = checkSum(userSum);

                                                    // Prints hand
                                                    System.out.println("\nDealer: X - " + dealerCard2Name);
                                                    System.out.println("You: " + userCards.get(0) + " - " +
                                                            userCards.get(1) + " - " + userCards.get(2) +
                                                            " - " + userCards.get(3) + " - " + userCards.get(4) +
                                                            " - " + userCards.get(5) + " - " + userCards.get(6) +
                                                            " - " + userCards.get(7) + " (" + userSum + ")");

                                                    // Runs if user busted
                                                    if (busted6) {
                                                        System.out.println("You busted! Dealer wins.");
                                                        System.exit(0);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // Runs if user picks stay
        // Dealer could theoretically hit 5 times
        if (hitOrStay == 's') {
            dealerSum = getSum(namesCopy, dealerCards, cardValues);

            System.out.println("\nDealer: " + dealerCards.get(0) + " - "
                    + dealerCards.get(1) + " (" + dealerSum + ")");

            // Determines outcome for dealer blackjack
            if (dealerSum == 21 && userSum != 21) {
                System.out.println("Dealer has blackjack. You lose.");
                System.exit(0);
            }

            if (dealerSum == userSum && dealerSum == 21) {
                System.out.println("You both have blackjack. It is a push.");
                System.exit(0);
            }

            // Dealer's first hit
            if (canDealerHit(dealerSum)) {
                System.out.println("Dealer hits.");

                // Generates dealer's third card
                int dealerCard3 = ThreadLocalRandom.current().nextInt(0, names.size());
                String dealerCard3Name = names.get(dealerCard3);
                dealerCards.add(dealerCard3Name);

                // Removes card from deck
                for (int i = 0; i < names.size(); i++) {
                    if (dealerCard3Name.equals(names.get(i))) {
                        names.remove(i);
                        break;
                    }
                }

                // Invokes method to find value of dealer's hand
                dealerSum = getSum(namesCopy, dealerCards, cardValues);
                boolean dBusted = checkSum(dealerSum);

                if (dBusted) {
                    System.out.println("\nDealer: " + dealerCards.get(0) + " - " + dealerCards.get(1)
                            + " - " + dealerCards.get(2) + " (" + dealerSum + ")");
                    System.out.println("Dealer busted! You win!");
                    System.exit(0);
                }

                System.out.println("\nDealer: " + dealerCards.get(0) + " - " + dealerCards.get(1)
                        + " - " + dealerCards.get(2) + " (" + dealerSum + ")");

                // Dealer's second hit
                if (canDealerHit(dealerSum)) {
                    System.out.println("Dealer hits.");

                    // Generates dealer's fourth card
                    int dealerCard4 = ThreadLocalRandom.current().nextInt(0, names.size());
                    String dealerCard4Name = names.get(dealerCard4);
                    dealerCards.add(dealerCard4Name);

                    // Removes card from deck
                    for (int i = 0; i < names.size(); i++) {
                        if (dealerCard4Name.equals(names.get(i))) {
                            names.remove(i);
                            break;
                        }
                    }

                    // Invokes method to find value of dealer's hand
                    dealerSum = getSum(namesCopy, dealerCards, cardValues);
                    boolean dBusted2 = checkSum(dealerSum);

                    if (dBusted2) {
                        System.out.println("\nDealer: " + dealerCards.get(0) + " - " + dealerCards.get(1)
                                + " - " + dealerCards.get(2) + " - " + dealerCards.get(3) + " (" + dealerSum + ")");
                        System.out.println("Dealer busted! You win!");
                        System.exit(0);
                    }

                    System.out.println("\nDealer: " + dealerCards.get(0) + " - " + dealerCards.get(1)
                            + " - " + dealerCards.get(2) + " - " + dealerCards.get(3) + " (" + dealerSum + ")");

                    // Dealer's third hit
                    if (canDealerHit(dealerSum)) {
                        System.out.println("Dealer hits.");

                        // Generates dealer's fifth card
                        int dealerCard5 = ThreadLocalRandom.current().nextInt(0, names.size());
                        String dealerCard5Name = names.get(dealerCard5);
                        dealerCards.add(dealerCard5Name);

                        // Removes card from deck
                        for (int i = 0; i < names.size(); i++) {
                            if (dealerCard5Name.equals(names.get(i))) {
                                names.remove(i);
                                break;
                            }
                        }

                        // Invokes method to find value of dealer's hand
                        dealerSum = getSum(namesCopy, dealerCards, cardValues);
                        boolean dBusted3 = checkSum(dealerSum);

                        if (dBusted3) {
                            System.out.println("\nDealer: " + dealerCards.get(0) + " - " + dealerCards.get(1)
                                    + " - " + dealerCards.get(2) + " - " + dealerCards.get(3) +
                                    " - " + dealerCards.get(4) + " (" + dealerSum + ")");
                            System.out.println("Dealer busted! You win!");
                            System.exit(0);
                        }

                        System.out.println("\nDealer: " + dealerCards.get(0) + " - " + dealerCards.get(1)
                                + " - " + dealerCards.get(2) + " - " + dealerCards.get(3) +
                                " - " + dealerCards.get(4) + " (" + dealerSum + ")");

                        // Dealer's fourth hit
                        if (canDealerHit(dealerSum)) {
                            System.out.println("Dealer hits.");

                            // Generates dealer's sixth card
                            int dealerCard6 = ThreadLocalRandom.current().nextInt(0, names.size());
                            String dealerCard6Name = names.get(dealerCard6);
                            dealerCards.add(dealerCard6Name);

                            // Removes card from deck
                            for (int i = 0; i < names.size(); i++) {
                                if (dealerCard6Name.equals(names.get(i))) {
                                    names.remove(i);
                                    break;
                                }
                            }

                            // Invokes method to find value of dealer's hand
                            dealerSum = getSum(namesCopy, dealerCards, cardValues);
                            boolean dBusted4 = checkSum(dealerSum);

                            if (dBusted4) {
                                System.out.println("\nDealer: " + dealerCards.get(0) + " - " + dealerCards.get(1)
                                        + " - " + dealerCards.get(2) + " - " + dealerCards.get(3) +
                                        " - " + dealerCards.get(4) + " - " + dealerCards.get(5) +
                                        " (" + dealerSum + ")");
                                System.out.println("Dealer busted! You win!");
                                System.exit(0);
                            }

                            System.out.println("\nDealer: " + dealerCards.get(0) + " - " + dealerCards.get(1)
                                    + " - " + dealerCards.get(2) + " - " + dealerCards.get(3) +
                                    " - " + dealerCards.get(4) + " - " + dealerCards.get(5) + " (" + dealerSum + ")");

                            // Dealer's fifth hit
                            if (canDealerHit(dealerSum)) {
                                System.out.println("Dealer hits.");

                                // Generates dealer's seventh card
                                int dealerCard7 = ThreadLocalRandom.current().nextInt(0, names.size());
                                String dealerCard7Name = names.get(dealerCard7);
                                dealerCards.add(dealerCard7Name);

                                // Removes card from deck
                                for (int i = 0; i < names.size(); i++) {
                                    if (dealerCard7Name.equals(names.get(i))) {
                                        names.remove(i);
                                        break;
                                    }
                                }

                                // Invokes method to find value of dealer's hand
                                dealerSum = getSum(namesCopy, dealerCards, cardValues);
                                boolean dBusted5 = checkSum(dealerSum);

                                if (dBusted5) {
                                    System.out.println("\nDealer: " + dealerCards.get(0) + " - " + dealerCards.get(1)
                                            + " - " + dealerCards.get(2) + " - " + dealerCards.get(3) +
                                            " - " + dealerCards.get(4) + " - " + dealerCards.get(5) +
                                            " - " + dealerCards.get(6) + " (" + dealerSum + ")");
                                    System.out.println("Dealer busted! You win!");
                                    System.exit(0);
                                }
                            }
                            else
                                System.out.println("Dealer stays.");
                        }
                        else
                            System.out.println("Dealer stays.");
                    }
                    else
                        System.out.println("Dealer stays.");
                }
                else
                    System.out.println("Dealer stays.");
            }
            else
                System.out.println("Dealer stays.");

            // Runs if dealer can no longer hit
            System.out.println("\nResults");
            System.out.println("=======");
            System.out.println("Dealer: " + dealerSum);
            System.out.println("You: " + userSum + "\n");
            results(dealerSum, userSum);
        }
    }

    // Method to find numeric value of user and dealer hands
    public static int getSum(ArrayList<String> namesCopy, ArrayList<String> cards,
                             int[] cardValues) {
        int sum = 0;

        // Checks each hand card against the cards in a full deck
        // Adds numeric value to sum when match is found
        for (int i = 0; i < cards.size(); i++) {
            for (int j = 0; j < namesCopy.size(); j++) {
                if (cards.get(i).equals(namesCopy.get(j))) {
                    sum += cardValues[j];
                    break;
                }
            }
        }

        return sum;
    }

    // Checks if hand value is > 21
    public static boolean checkSum(int sum) {
        if (sum > 21)
            return true;
        else
            return false;
    }

    // Determines if dealer can hit again
    public static boolean canDealerHit(int sum) {
        if (sum >= 17)
            return false;
        else
            return true;
    }

    // Finds game results and prints them
    public static void results(int dealerSum, int userSum) {
        if (dealerSum > userSum)
            System.out.println("Dealer has " + dealerSum + ". You lose.");
        else if (dealerSum == userSum)
            System.out.println("You both have " + userSum + ". It is a push.");
        else
            System.out.println("You have " + userSum + ". You win!");
    }
}