package com.company;


public class Dekker {
     private volatile boolean flag1 = false;
     private volatile boolean flag2 = false;
     private volatile int turn = 1;
     private int loop = 10;

    Runnable r1 = () -> {
        for (int i=0; i<loop; ++i) {
            flag1 = true;
            while (flag2) {
                if (turn != 1) {
                    flag1 = false;
                    while (turn != 1);
                    flag1 = true;
                }
            }

            try {
                System.out.printf(i + " t1 is sleeping%n");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            turn = 2;
            flag1 = false;
        }
    };

    Runnable r2 = () -> {
        for (int i=0; i<loop; ++i) {
            flag2 = true;
            while (flag1) {
                if (turn != 2) {
                    flag2 = false;
                    while (turn != 2);
                    flag2= true;
                }
            }

            try {
                System.out.printf(i + " t2 is sleeping%n");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            turn = 1;
            flag2 = false;
        }
    };


        Dekker(){

            Thread t1 = new Thread(r1);
            Thread t2 = new Thread(r2);
            t1.start();
            t2.start();

        }
    }
