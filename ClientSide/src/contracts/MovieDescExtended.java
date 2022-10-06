package contracts;

import java.io.Serializable;
import java.util.Arrays;

public class MovieDescExtended  extends MovieDesc implements Serializable {

    byte[] teaser;
    public MovieDescExtended(String movieName, String isbn, String synopsis, byte[] filmBytes,byte[] teaser) {
        super(movieName, isbn, synopsis, filmBytes);
        this.teaser = teaser;
    }

    public byte[] getTeaser() {
        return teaser;
    }

    @Override
    public String toString() {
        return super.toString() +
                "teaser=" + Arrays.toString(teaser) +
                '}';
    }
}
