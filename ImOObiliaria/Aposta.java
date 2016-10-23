
/**
 * Write a description of class Aposta here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Aposta
{
    private String email;
    
    private double limite;
    private double incrementos;
    private double minutos;
    private Imovel imovelAtual;
    public Aposta(){
        this.email = "";
        this.limite = 0.0;
        this.incrementos = 0.0;
        this.minutos = 0.0;
    }
    public Aposta(String email,double limite,double incrementos,double minutos, Imovel imovel){
        this.email = email;
        this.limite = limite;
        this.incrementos = incrementos;
        this.minutos = minutos;
        this.imovelAtual = imovel;
    }
    
     public String getEmail(){
        return this.email;
    }
    
    public double getLimite(){
        return this.limite;
    }
    
    public double getIncrementos(){
        return this.incrementos;
    }
    
    
    public double getMinutos(){
        return this.incrementos;
    }
    
    public Imovel getImovel(){
        return this.imovelAtual;
    }
    public void setLimite(double l){
        this.limite = l;
    }
    public void setEmail(String s){
        this.email = s;
    }
    public void setMinutos(double s){
        this.minutos = s;
    }
    public void setIncrementos (double s){
        this.incrementos = s;
    }
    public void setImovel(Imovel i){
        this.imovelAtual = i;
    }
}
