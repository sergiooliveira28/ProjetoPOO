import java.util.*;
import java.io.*;

public abstract class Imovel implements Serializable
{
    private String estado;
    private String tipoImovel;
    private String morada;
    private double precoPedido,precoMinimo;
    
    public Imovel(){
        estado = "N/A";
        tipoImovel = "N/A";
        morada = "N/A";
        precoPedido = 0.0;
        precoMinimo = 0.0;
    }
    
    public Imovel (String estado, String tipoImovel,String morada, double precoPedido, double precoMinimo){
        this.estado = estado;
        this.tipoImovel = tipoImovel;
        this.morada = morada;
        this.precoPedido = precoPedido; 
        this.precoMinimo = precoMinimo;
    }
    
    public Imovel (Imovel i){
        this.estado = i.getEstado();
        this.tipoImovel = i.getTipoImovel();
        this.morada = i.getMorada();
        this.precoPedido = i.getPrecoPedido();
        this.precoMinimo = i.getPrecoMinimo();
    }
    
    public String getEstado(){
        return this.estado;
    }
    
    public String getTipoImovel(){
        return tipoImovel;
    }
    
    public String getMorada(){
        return this.morada;
    }
    
    public double getPrecoPedido(){
        return this.precoPedido;
    }
    
    public double getPrecoMinimo(){
        return this.precoMinimo;
    }
    
    public void setEstado(String s){
        this.estado = s;
    }
    
    public void setMorada(String m){
        this.morada = m;
    }
    
    public void setPrecoPedido(double pp){
        this.precoPedido = pp;
    }
    
    public void setPrecoMinimo(double pm){
        this.precoMinimo = pm;
    }
    
    public boolean equals(Object o){
        if (this==o) return true;
        if((o==null) || (o.getClass()!=this.getClass())) return false;
        Imovel a = (Imovel) o;
        return this.morada.equals(a.getMorada());
    }
    
    public abstract Imovel clone();
    
    public String toString(){
        StringBuffer s = new StringBuffer();
        s.append("\n-------------------------------\n");
        s.append(" *** "+this.tipoImovel+" ***\n ");
        s.append(" * Estado: "+this.estado+" *\n");
        s.append("Morada: "+this.morada+"\n");
        s.append("Preço Pedido: "+this.precoPedido+"\n");
        s.append("Preço Minimo: "+this.precoMinimo+"\n");
        return s.toString();
    }
}
