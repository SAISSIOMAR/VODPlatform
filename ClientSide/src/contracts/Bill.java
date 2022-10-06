package contracts;

import java.io.Serializable;
import java.math.BigInteger;

public class Bill implements Serializable {

    String movieName;
    Integer price;

    public Bill(String movieName, Integer price) {
        this.movieName = movieName;
        this.price = price;
    }

    public String getMovieName() {
        return movieName;
    }

    public Integer getOutrageousPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "\n\t\tBill => " +
                "\n\t\tmovieName : " + movieName  +
                "\n\t\toutrageousPrice :" + price + "€";
    }
}
