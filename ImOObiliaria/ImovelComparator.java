import java.util.*;
class ImovelComparator implements Comparator<Imovel>{
 
    @Override
    public int compare(Imovel pre1, Imovel pre2) {
        if (pre1.getPrecoPedido() < pre2.getPrecoPedido()) return -1;
        if (pre1.getPrecoPedido() > pre2.getPrecoPedido()) return 1;
        return 0;
   
    }
}