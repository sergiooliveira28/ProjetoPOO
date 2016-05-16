public class Consulta
{
    private String data;
    private Imovel i;
    
    public Consulta(){
        this.data = "";
    }
    
    public String getData(){return this.data;}
    
    public void setData(String ddata){this.data = ddata;}
    
    public Imovel getImovel(){return this.i;}
    public void setImovel(Imovel ii){this.i = ii;}
    
    
}
