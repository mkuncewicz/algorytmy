package com.example.alg6;

public class KalkulatorKredytowy {


    public double calMountLoan(double amountOfLoan, double percentPerYear, int years){

        double percentPerMount = (percentPerYear / 100) /12;

        double result = (amountOfLoan * percentPerMount) / (1 - Math.pow((1+percentPerMount),-percentPerYear*12));

        return result;
    }
}
