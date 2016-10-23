
public class Moradia extends Imovel implements Pesquisas, Habitavel 
{
    // instance variables
    private String tipo;
    private double areaImplantacao,areaTotal,areaTerreno;
    private int numeroQuartos, numeroWC, numeroPorta;
    
    //
    public Moradia(){
       super();
       this.tipo = "";
       this.areaImplantacao = 0.0;
       this.areaTotal = 0.0;
       this.areaTerreno = 0.0;
       this.numeroQuartos = 0;
       this.numeroWC = 0;
       this.numeroPorta = 0;
    }
   
    public Moradia(String estado, String morada, double precoPedido,double precoMinimo, String tipo, double areaImplantacao, double areaTotal, double areaTerreno,int numeroQuartos, int numeroWC, int numeroPorta){
        super(estado,"Moradia",morada,precoPedido,precoMinimo);
        this.tipo = tipo;
        this.areaImplantacao = areaImplantacao;
        this.areaTotal = areaTotal;
        this.areaTerreno = areaTerreno;
        this.numeroQuartos = numeroQuartos;
        this.numeroWC = numeroWC;
        this.numeroPorta = numeroPorta;
    }
    
    public Moradia(Moradia a){
        super(a);
        this.tipo = a.getTipo();
        this.areaImplantacao = a.getareaImplantacao();
        this.areaTotal = a.getareaTotal();
        this.areaTerreno = a.getareaTerreno();
        this.numeroQuartos = a.getnumeroQuartos();
        this.numeroWC = a.getnumeroWC();
        this.numeroPorta = a.getnumeroPorta();
    }
    
    public String getTipo(){
        return tipo;
    }

    public double getareaImplantacao(){
        return areaImplantacao;
    }
    
    public double getareaTotal(){
        return areaTotal;
    }
    
    public double getareaTerreno(){
        return areaTerreno;
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
    
    public boolean getHabitavel(){
        return true;
    }
    
    public Moradia clone(){
        return new Moradia(this);
    }
    
    public boolean equals(Object o){
        if (this==o)
            return true;
        if (o==null || this.getClass()!=o.getClass())
            return false;
        Moradia a = (Moradia) o;
        return (this.tipo.equals(a.getTipo()) && 
                this.areaImplantacao == a.getareaImplantacao() && 
                this.areaTotal == a.getareaTotal() && 
                this.areaTerreno == a.getareaTerreno() &&
                this.numeroQuartos == a.getnumeroQuartos() &&
                this.numeroWC == a.getnumeroWC() &&
                this.numeroPorta == a.getnumeroPorta());
    }
    
    public String toString(){
        StringBuffer s = new StringBuffer();
        s.append(super.toString());
        s.append("Tipo: "+this.tipo+"\n");
        s.append("Area Implantação: "+this.areaImplantacao+"\n");
        s.append("Area Total: "+this.areaTotal+"\n");
        s.append("Area Terreno: "+this.areaTerreno+"\n");
        s.append("Nº Quartos: "+this.numeroQuartos+"\n");
        s.append("Nº WC's: "+this.numeroWC+"\n");
        s.append("Nº Porta: "+this.numeroPorta+"\n");
        s.append("-------------------------------");
        return s.toString();
    }
}
