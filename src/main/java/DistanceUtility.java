package main.java;

public class DistanceUtility {

    public static double getDifference(int[] location1, int []location2){
       return Math.pow(location1[0]-location2[0],2) + Math.pow(location1[1]-location2[1],2);
    }
}
