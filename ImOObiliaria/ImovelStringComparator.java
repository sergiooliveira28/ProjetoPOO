import java.util.*;
class ImovelStringComparator implements Comparator<Imovel>{
    public int compare(Imovel s1, Imovel s2){
        if (s1.getMorada().equals(s2.getMorada())) return -1;
        else return 1;
    }
}