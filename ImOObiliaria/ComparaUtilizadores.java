
import java.util.Comparator;
public class ComparaUtilizadores implements Comparator<Utilizadores> {
    
    public int compare(Utilizadores u1, Utilizadores u2) {
        return u1.getEmail().compareTo(u2.getEmail());
    }
}