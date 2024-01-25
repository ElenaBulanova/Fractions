package com.example.fractions.entitis;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Fraction {
    private  int numerator;
    private  int denominator;

    @Override
    public String toString() {
        return  numerator + "/"
                 + denominator;
    }
}
