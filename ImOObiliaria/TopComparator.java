import java.util.*;
class TopComparator implements Comparator<Imovel>{
 
    @Override
    public int compare(Imovel pre1, Imovel pre2) {
        if (pre1.getVisitas() > pre2.getVisitas()) return -1;
        if (pre1.getVisitas() < pre2.getVisitas()) return 1;
        return 0;
   
    }
}
