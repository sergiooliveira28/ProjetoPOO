
public class Terreno extends Imovel
{
    // instance variables
    private String apropriadoPara;
    private double diametroCanal,redeElectrica;
    private boolean redeEsgotos;
    
    //
    public Terreno(){
        super();
        this.apropriadoPara = "";
        this.diametroCanal = 0.0;
        this.redeElectrica = 0.0;
        this.redeEsgotos = false;
    }
    
    public Terreno(String estado,String morada, double precoPedido,double precoMinimo, String apropriadoPara, double diametroCanal,double redeElectrica, boolean redeEsgotos){
        super(estado,"Terreno",morada,precoPedido,precoMinimo);
        this.apropriadoPara = apropriadoPara;
        this.diametroCanal = diametroCanal;
        this.redeElectrica = redeElectrica;
        this.redeEsgotos = redeEsgotos;
    }
    
    public Terreno(Terreno a){
        super(a);
        this.apropriadoPara = a.getApropriadoPara();
        this.diametroCanal = a.getDiametroCanal();
        this.redeElectrica = a.getRedeElectrica();
        this.redeEsgotos = a.getRedeEsgotos();
    }
    
    public String getApropriadoPara(){
        return apropriadoPara;
    }
    
    public double getDiametroCanal(){
        return diametroCanal;
    }
    
    public double getRedeElectrica(){
        return redeElectrica;
    }
    
    public boolean getRedeEsgotos(){
        return redeEsgotos;
    }
    
    public Terreno clone(){
        return new Terreno(this);
    }
    
    public boolean equals(Object o){
        if (this==o)
            return true;
        if (o==null || this.getClass()!=o.getClass())
            return false;
        Terreno a = (Terreno) o;
        return (this.diametroCanal == a.getDiametroCanal() && 
                this.redeElectrica == a.getRedeElectrica() &&
                this.redeEsgotos == a.getRedeEsgotos());
    }
    
    public String toString(){
        StringBuffer s = new StringBuffer();
        s.append(super.toString());
        s.append("Terreno Apropriado para: "+this.apropriadoPara+"\n");
        s.append("Diametro: "+this.diametroCanal+"\n");
        s.append("Rede Electrica: "+this.redeElectrica+"\n");
        s.append("Rede Esgotos: "+this.redeEsgotos+"\n");
        s.append("-------------------------------");
        return s.toString();
    }
}
