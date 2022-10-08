package contrats;

import java.io.Serializable;
import java.util.Arrays;


public class MovieDescExtended extends MovieDesc implements Serializable {
    byte[] teaser;

    public MovieDescExtended(String movieName, String isbn, String synopsis, byte[] movie,byte[] teaser) {
        super(movieName, isbn, synopsis, movie);
        this.teaser = teaser;
    }

    public byte[] getTeaser() {
        return teaser;
    }

    @Override
    public String toString() {
        return "[[[ MovieDescriptionExtended{" +
                "movieName='" + movieName + '\'' +
                ", isbn='" + isbn + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", teaser=" + Arrays.toString(teaser) +
                " ]]]";
    }
}
