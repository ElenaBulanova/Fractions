package com.example.fractions.controller;

import com.example.fractions.entitis.Fraction;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
   //@Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/api")
    public String myFractionProperImproper(){
        Fraction fraction = new Fraction(2,5);
        String result = fraction.getNumerator() + " / " + fraction.getDenominator();
        if(fraction.getNumerator()  < fraction.getDenominator())
            result = result + " - proper fraction";
        else
            result = result + " - improper fraction";
        return result;
    }

    @PostMapping("/api/new")
    public String yourFractionProperImproper(@RequestParam Integer numerator, @RequestParam Integer denominator){
        Fraction fraction = new Fraction(numerator, denominator);
        String result = fraction.getNumerator() + " / " + fraction.getDenominator();
        //System.out.println(result);
        if(fraction.getNumerator()  < fraction.getDenominator())
            result = result + " - proper fraction";
        else
            result = result + " - improper fraction";
        return result;
    }

    @GetMapping("/api/reduce")
    public String reduceFraction(Fraction fraction){
        String result = "";
        if (fraction == null){fraction = new Fraction(5,10);}
        int gcd = gcd(fraction.getNumerator(), fraction.getDenominator());
        result = fraction.getNumerator() / gcd + "/" + fraction.getDenominator() / gcd;
        //System.out.println(fraction.getNumerator() + "  " + fraction.getDenominator() + "  " + gcd);
        return fraction + " = " +result;
    }

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    @GetMapping("/api/add")
    public String addFractions() {
        String result = "";
        Fraction fraction1 = new Fraction(5, 30);
        Fraction fraction2 = new Fraction(5, 30);
        int cDenominator = nok(fraction1.getDenominator(), fraction2.getDenominator());
        int cNumerator =
                fraction1.getNumerator() * (cDenominator / fraction1.getDenominator()) +
                        fraction2.getNumerator() * (cDenominator / fraction2.getDenominator());
        Fraction c = new Fraction(cNumerator, cDenominator);
        result = reduceFraction(c);
        return fraction1.toString() + " + " + fraction2.toString() + " = " + result;
    }
        static int nok(int a, int b) {
            return a * b / gcd(a, b);
        }

    @GetMapping("/api/subtract")
    public String subtractionFractions() {
        String result = "";
        Fraction fraction1 = new Fraction(5, 30);
        Fraction fraction2 = new Fraction(2, 30);
        int cDenominator = nok(fraction1.getDenominator(), fraction2.getDenominator());
        int cNumerator =
                fraction1.getNumerator() * (cDenominator / fraction1.getDenominator()) -
                        fraction2.getNumerator() * (cDenominator / fraction2.getDenominator());
        Fraction c = new Fraction(cNumerator, cDenominator);
        result = reduceFraction(c);
        return fraction1.toString() + " - " + fraction2.toString() + " = " + result;
    }

    @GetMapping("/api/mult")
    public String multiplicationFractions() {
        String result = "";
        Fraction fraction1 = new Fraction(5, 30);
        Fraction fraction2 = new Fraction(2, 30);
        int cDenominator = fraction1.getDenominator() * fraction2.getDenominator();
        int cNumerator = fraction1.getNumerator() * fraction2.getNumerator();
        Fraction c = new Fraction(cNumerator, cDenominator);
        result = reduceFraction(c);
        return fraction1.toString() + " * " + fraction2.toString() + " = " + result;
    }
    @GetMapping("/api/div")
    public String divisionFractions() {
        String result = "";
        Fraction fraction1 = new Fraction(4, 8);
        Fraction fraction2 = new Fraction(5, 7);
        int cDenominator = fraction1.getDenominator() * fraction2.getNumerator();
        int cNumerator = fraction1.getNumerator() * fraction2.getDenominator();
        Fraction c = new Fraction(cNumerator, cDenominator);
        result = reduceFraction(c);
        return fraction1.toString() + " / " + fraction2.toString() + " = " + result;
    }

}
