import java.util.HashMap;
import java.util.stream.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

    public class Vendedor extends Utilizadores
    {   
        // instance variables
    private ArrayList<Imovel> lstImoveisPort;
    private ArrayList<Imovel> lstImoveisVendidos;
    
    //construtores
    
    public Vendedor(){
        super();
        this.lstImoveisPort = new ArrayList<Imovel>();
        this.lstImoveisVendidos = new ArrayList<Imovel>();
    }
    
    public Vendedor(String email,String nome,String password,String morada,String dataNascimento, Imobiliaria im, boolean registado){
        super(email,nome,password,morada,dataNascimento,im,registado);
        this.lstImoveisPort = new ArrayList<Imovel>();
        this.lstImoveisVendidos = new ArrayList<Imovel>();
    }

    public Vendedor(Vendedor a){
        super(a);
        ArrayList<Imovel> aux = a.getImoveisP();
        lstImoveisPort = new ArrayList<Imovel>(aux.size());
        for(Imovel f: aux){
            lstImoveisPort.add(f.clone());
        }
        ArrayList<Imovel> aux2 = a.getImoveisV();
        lstImoveisVendidos = new ArrayList<Imovel>(aux2.size());
        for(Imovel f: aux2){
            lstImoveisVendidos.add(f.clone());
        }
    }   
    
    public ArrayList<Imovel> getImoveisP(){
        return this.lstImoveisPort;
    }
    
    public ArrayList<Imovel> getImoveisV(){
        return this.lstImoveisVendidos;
    }
    
    public void setImovelP(Imovel i){this.lstImoveisPort.add(i);}
    public void setImovelV(Imovel i){this.lstImoveisVendidos.add(i);}    
    
    // SERGIO
    
    public void removerImovel (String i){
        getImob().remImovel(i);
    }
    
    // /SERGIO
    
    public Vendedor clone(){
        return new Vendedor(this);
    }
}