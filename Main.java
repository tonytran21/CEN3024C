package com.company;

import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;

class Animal implements Runnable {
    private String name;
    private int position;
    private int speed;
    private int restMax;
    public static boolean winner = false;
    private Food food;
    Random random = new Random();

    public Animal(String name, int speed, int restMax, Food food) {
        this.name = name;
        this.speed = speed;
        this.restMax = restMax;
        this.food = food;
    }

    @Override
    public void run() {
        int finishPosition = 120;

        while (this.position <= finishPosition && !winner) {

            System.out.println(this.name + ": " + this.position + " yards");

            try {

                int rest = this.random.nextInt(this.restMax);
                Thread.sleep(rest);

                food.eat(this.name);

                this.position += this.speed;

                System.out.println();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (this.position >= finishPosition) {

                winner = true;

                System.out.println("The race is over!");
                System.out.println(this.name + " wins!");
            }
        }
    }
}

class Food {

    synchronized void eat(String name) {

        System.out.println("Starting to eat");

        try {

            int eatTime = (int) ((name.equals("Hare") ? 200 : 100) * Math.random());
            Thread.sleep(eatTime);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Food food = new Food();

        Animal hare = new Animal("Hare", 9 ,220, food);
        new Thread(hare).start();

        Animal tortoise = new Animal("Tortoise", 5, 165, food);
        new Thread(tortoise).start();
    }
}