package services;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * a class Bill representing the price of a movie
 */
public class Bill implements Serializable {
    String movieName;
    BigInteger outrageousPrice;

    public Bill(String movieName, BigInteger outrageousPrice) {
        this.movieName = movieName;
        this.outrageousPrice = outrageousPrice;
    }

    public String getMovieName() {
        return movieName;
    }

    public BigInteger getOutrageousPrice() {
        return outrageousPrice;
    }

    @Override
    public String toString() {
        return "\n\t\tBill : " +
                "\n\t\t movieName : " + movieName  +
                "\n\t\t outrageousPrice :" + outrageousPrice + "€";
    }
}
