public class LojaHabitavel extends Loja implements Habitavel
{
    private Apartamento apartamento;
    
    public LojaHabitavel(String estado,String morada, double precoPedido,double precoMinimo,String tiponegocio, double area,boolean wc, int numeroPorta,Apartamento apartamento){
        super(estado,morada,precoPedido,precoMinimo,tiponegocio,area,wc,numeroPorta);
        this.apartamento = apartamento;
    }
    
    public LojaHabitavel(LojaHabitavel a){
        super(a);
        this.apartamento = a.getApartamento();
    }
    
    public Apartamento getApartamento(){
        Apartamento res = new Apartamento(apartamento);
        res = apartamento.clone();
        return res;
    }
    
    public LojaHabitavel clone(){
        return new LojaHabitavel(this);
    }
    
    public boolean getHabitavel(){
        return true;
    }
    
    public boolean equals(Object o){
        if (this==o)
            return true;
        if (o==null || this.getClass()!=o.getClass())
            return false;
        LojaHabitavel a = (LojaHabitavel) o;
        return (this.apartamento.equals(a.getApartamento()));
    }
    
    
    public String toString(){
        StringBuffer s = new StringBuffer();
        s.append(super.toString());
        s.append("-- Loja Habitavel com o seguinte Apartamento --\n");
        s.append(this.apartamento);
        return s.toString();
    }
}
