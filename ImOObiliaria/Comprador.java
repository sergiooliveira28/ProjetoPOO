import java.util.*;

public class Comprador extends Utilizadores  
{
    // instance variables
    private ArrayList<Imovel> lstImoveis;
    
    //construtores
    public Comprador(){
        super();
        this.lstImoveis = new ArrayList<Imovel>();
    }
    
    public Comprador(String email,String nome,String password,String morada,String dataNascimento, Imobiliaria imob, boolean registado){
        super(email,nome,password,morada,dataNascimento,imob,registado);
        this.lstImoveis = new ArrayList<Imovel>();
    }
    
    public Comprador(Comprador a){
        super(a);
        ArrayList<Imovel> aux = a.getImovel();
        lstImoveis = new ArrayList<Imovel>(aux.size());
        for(Imovel f: aux){
            lstImoveis.add(f.clone());
        }
    }
    
    public ArrayList<Imovel> getImovel(){
        ArrayList<Imovel> aux = new ArrayList<Imovel>();
        for(Imovel f: lstImoveis){
            aux.add(f.clone());
        }
        return aux;
    }
    
    public TreeSet <Imovel> getFavoritos () //throws SemAutorizacaoException{
    {
        TreeSet <Imovel> aux = new TreeSet <Imovel> (new ImovelStringComparator ());
        
        aux.addAll(lstImoveis);
        
        return aux;
    }
    
    
    // ???
    public void comprarImovel (String idImovel){ 
        for (Map.Entry <String,Imovel> i : this.getImob().getImovel().entrySet()){
            if (i.getKey().equals(idImovel) && this.getRegistado())
               i.getValue().setEstado("Vendido");
        }
    }
        
    public void setFavorito (String idImovel){ 
        for (Map.Entry <String,Imovel> i : this.getImob().getImovel().entrySet()){
            if (i.getKey().equals(idImovel) && this.getRegistado())
               lstImoveis.add(i.getValue());
        }
    }
    
    public void remFavorito (String idImovel){ 
        for (Map.Entry <String,Imovel> i : this.getImob().getImovel().entrySet()){
            if (i.getKey().equals(idImovel) && this.getRegistado())
               lstImoveis.remove(i.getValue());
        }
    }
    
    public Comprador clone(){
        return new Comprador(this);
    }
    
}
