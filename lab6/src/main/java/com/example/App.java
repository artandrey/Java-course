package com.example;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Theater theater = new Theater(3, 12, 18, new TriangleSeatRankStrategy());
        theater.autoBook(0, 4);
        theater.printSeatingArrangement(0);
    }
}
