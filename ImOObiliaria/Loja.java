
public class Loja extends Imovel
{
    // instance variables
    private String tiponegocio;
    private double area;
    private boolean wc;
    private int numeroPorta;
    //
    
    public Loja(String estado,String morada, double precoPedido,double precoMinimo,String tiponegocio, double area,boolean wc, int numeroPorta){
        super(estado,"Loja", morada,precoPedido,precoMinimo);
        this.tiponegocio = tiponegocio;
        this.area = area;
        this.wc = wc;
        this.numeroPorta = numeroPorta;
    }
    
    public Loja(Loja a){
        super(a);
        this.tiponegocio = a.getTipoNegocio();
        this.area = a.getArea();
        this.wc = a.getWc();
        this.numeroPorta = a.getNumeroPorta();
    }
    
    public String getTipoNegocio(){
        return tiponegocio;
    }
    
    public double getArea(){
        return area;
    }
    
    public boolean getWc(){
        return wc;
    }
    
    public int getNumeroPorta(){
        return numeroPorta;
    }
    
    public Loja clone(){
        return new Loja(this);
    }
    
    public boolean equals(Object o){
        if (this==o)
            return true;
        if (o==null || this.getClass()!=o.getClass())
            return false;
        Loja a = (Loja) o;
        return (this.tiponegocio.equals(a.getTipoNegocio()) && 
                this.area == a.getArea() && 
                this.wc == a.getWc() &&
                this.numeroPorta == a.getNumeroPorta());
    }
    
    public String toString(){
        StringBuffer s = new StringBuffer();
        s.append(super.toString());
        s.append("Tipo de negocio: "+this.tiponegocio+"\n");
        s.append("Area: "+this.area+"\n");
        s.append("Tem Wc: "+this.wc+"\n");
        s.append("Numero de Porta: "+this.numeroPorta+"\n");
        return s.toString();
    }
}