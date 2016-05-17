
public class Apartamento extends Imovel implements Pesquisas, Habitavel
{
    // instance variables
    private String tipo;
    private double areaTotal;
    private int numeroQuartos, numeroWC, numeroPorta, numeroAndar;
    private boolean garagem;
    
    //
    public Apartamento(String estado,String morada, double precoPedido,double precoMinimo,String tipo, double areaTotal,int numeroQuartos, int numeroWC, int numeroPorta, int numeroAndar, boolean garagem){
        super(estado,"Apartamento",morada,precoPedido,precoMinimo);
        this.tipo = tipo;
        this.areaTotal = areaTotal;
        this.numeroQuartos = numeroQuartos;
        this.numeroWC = numeroWC;
        this.numeroPorta = numeroPorta;
        this.numeroAndar = numeroAndar;
        this.garagem = garagem;
    }
    
    public Apartamento(Apartamento a){
        super(a);
        this.tipo = a.getTipo();
        this.areaTotal = a.getareaTotal();
        this.numeroQuartos = a.getnumeroQuartos();
        this.numeroWC = a.getnumeroWC();
        this.numeroPorta = a.getnumeroPorta();
        this.numeroAndar = a.getnumeroAndar();
        this.garagem = a.getgaragem();
    }
    
    public String getTipo(){
        return tipo;
    }
    
    public double getareaTotal(){
        return areaTotal;
    }
    
    public int getnumeroQuartos(){
        return numeroQuartos;
    }
    
    public int getnumeroWC(){
        return numeroWC;
    }
    
    public int getnumeroPorta(){
        return numeroPorta;
    }
    
    public int getnumeroAndar(){
        return numeroAndar;
    }
    
    public boolean getgaragem(){
        return garagem;
    }
    
    public Apartamento clone(){
        return new Apartamento(this);
    }
    
    public boolean getHabitavel(){
        return true;
    }
    
    public boolean equals(Object o){
        if (this==o)
            return true;
        if (o==null || this.getClass()!=o.getClass())
            return false;
        Apartamento a = (Apartamento) o;
        return (this.tipo.equals(a.getTipo()) && 
                this.areaTotal == a.getareaTotal() && 
                this.numeroQuartos == a.getnumeroQuartos() &&
                this.numeroWC == a.getnumeroWC() &&
                this.numeroPorta == a.getnumeroPorta() &&
                this.numeroAndar == a.getnumeroAndar() &&
                this.garagem == a.getgaragem());
    }
    
    public String toString(){
        StringBuffer s = new StringBuffer();
        s.append(super.toString());
        s.append("Tipo de Apartamento: "+this.tipo+"\n");
        s.append("Area Total: "+this.areaTotal+"\n");
        s.append("Nº de Wc's: "+this.numeroWC+"\n");
        s.append("Nº de Porta: "+this.numeroPorta+"\n");
        s.append("Nº de Andar: "+this.numeroAndar+"\n");
        s.append("Garagem: "+this.garagem+"\n");
        s.append("-------------------------------");
        return s.toString();
    }
}
