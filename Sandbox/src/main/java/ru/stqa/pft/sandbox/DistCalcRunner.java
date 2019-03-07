package ru.stqa.pft.sandbox;

public class DistCalcRunner {

    public static void main(String[] args) {


      Point p1 = new Point(3, 2);
      Point p2 = new Point(3, 5);
        System.out.println( "Расстояние между двумя точками равно " + p1.distance(p2));
    }
}
