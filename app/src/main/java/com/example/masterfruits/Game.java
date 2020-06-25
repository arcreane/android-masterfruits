package com.example.masterfruits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game {


        private int nbTries = 10;
        private List<Fruit> mysteryFruits;
        private int score;
        private int hintAsked = 0;
    Fruit[] myReserve = new Fruit[8];


    public Game(){

        myReserve[0] = new Fruit("Fraise");
        myReserve[1] = new Fruit("Banane");
        myReserve[2] = new Fruit("Kiwi");
        myReserve[3] = new Fruit("Citron");
        myReserve[4] = new Fruit("Raisin");
        myReserve[5] = new Fruit("Framboise");
        myReserve[6] = new Fruit("Orange");
        myReserve[7] = new Fruit("Prune");

    }


        public int startGame() {
            init(myReserve);
            //play();

            //displayAttributesInfos();
            return score;
        }

        private void displayAttributesInfos() {
            for (Fruit fruit : mysteryFruits) {
              //  System.out.println(fruit.getSpecificAttribute());
            }

        }

        private void init(Fruit[] reserve) {

            score = 10;
            /***
             * Start by creating the fruits mix up to guess
             */

            mysteryFruits = new ArrayList<>(Arrays.asList(reserve));

            Random r = new Random();
            for (int i = 0; i < 4; i++) {
                int pick = r.nextInt(mysteryFruits.size());
                mysteryFruits.remove(pick);
            }

            Collections.shuffle(mysteryFruits);

//		for (int indexFruitToGuess = 0; indexFruitToGuess < 4; indexFruitToGuess++) {
//			System.out.print(mysteryFruits.get(indexFruitToGuess).getClass().getSimpleName().toLowerCase() + "-");
//		}
            //System.out.println();
        }

        void play(String playerInput) {

            while (nbTries > 0) {
                int nbFruitWellPlaced = 0;
                int nbFruitMisPlaced = 0;

              //  String playerInput = m_Scanner.nextLine();
                if (playerInput.contentEquals("Hint")) {
                    displayHint();
                } else {
                    String[] playerInputSplitted = playerInput.split("-");
                    for (int indexPlayerInput = 0; indexPlayerInput < 4; indexPlayerInput++) {
                        for (int indexFruitToGuess = 0; indexFruitToGuess < 4; indexFruitToGuess++) {
                            if (mysteryFruits.get(indexFruitToGuess).isSame(playerInputSplitted[indexPlayerInput])) {
                                if (indexPlayerInput == indexFruitToGuess)
                                    nbFruitWellPlaced++;
                                else
                                    nbFruitMisPlaced++;
                            }
                        }
                    }
                    if (nbFruitWellPlaced != 4) {
                        nbTries--;
                        System.out.println("You have " + nbFruitWellPlaced + " fruits well placed and " + nbFruitMisPlaced
                                + " misplaced ");
                        System.out.println("You have " + nbTries + " left");

                    } else {
                        break;
                    }
                }
            }
            score = nbTries;
        }

        private void displayHint() {
            if (hintAsked < 2) {
                if (hintAsked == 0) {
                    Fruit.getHint1Info();
                    nbTries -= 2;
                } else {
                    Fruit.getHint2Info();
                    nbTries -= 3;
                }
                for (Fruit fruit : mysteryFruits) {
                    if (hintAsked == 0)
                        System.out.print(fruit.getHint1() + "-");
                    else if (hintAsked == 1)
                        System.out.print(fruit.getHint2() + "-");
                }
                System.out.println();
                hintAsked++;

            } else {
                System.out.println("You have alread had your hints");
            }
        }

        public boolean getPlayAgain(String playerInput) {

            System.out.println("Do you want to keep playing (y/n)");
            //String playerInput = m_Scanner.nextLine();

            return playerInput.contentEquals("y");
        }


}
