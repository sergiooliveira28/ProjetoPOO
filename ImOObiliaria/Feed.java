import java.util.*;
import java.io.*;
public class Feed implements Serializable
{
    private Queue <String> feed = new LinkedList <String> ();
    
    public Feed (){
         this.feed = new LinkedList <String> ();
    }
    public Feed (Feed a){
         this.feed = a.getMorada();
    }
    public Queue <String> getMorada (){return this.feed;}
    
    public String imprimir (){
        StringBuilder sb = new StringBuilder("--- Feed ---\n");
        if (!this.feed.isEmpty()) {
            for (String r: this.feed) {
                sb.append ("> "+ r.toString());
            }
        }
        else sb.append ("Sem novidades");
        return sb.toString();
    }
    
    public void guardar (String r){ // FEED DE NOTICIAS!!!
        this.feed.add(r);
        if (this.feed.size()>10){
            this.feed.remove();
        }
    }
}
