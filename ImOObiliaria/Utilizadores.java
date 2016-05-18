
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.stream.Collectors;

import java.io.*;
public abstract class Utilizadores implements Serializable
{
    // instance variables
    private String email;
    private String nome;
    private String password;
    private String morada;
    private String dataNascimento;
    private Imobiliaria imob;
    private boolean registado;
    //construtores
    public Utilizadores (){
        this.email = "";
        this.nome = "";
        this.password = "";
        this.morada ="";
        this.dataNascimento = "";
        this.imob = new Imobiliaria();
        this.registado = false;
    }
    public Utilizadores (String email,String nome,String password,String morada,String dataNascimento, Imobiliaria imob, boolean registado){
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.morada =morada;
        this.dataNascimento = dataNascimento;
        this.imob = imob;
        this.registado = registado;
    }
    
    public Utilizadores (Utilizadores a){
        this.email = a.getEmail();
        this.nome = a.getNome();
        this.password = a.getPassword();
        this.morada = a.getMorada();
        this.dataNascimento = a.getDN();
        this.imob = a.getImob();
        this.registado = a.getRegistado();
    }
    
    public Imobiliaria getImob(){
        return this.imob;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public String getNome(){
        return this.nome;
    }
      
    public String getPassword(){
        return this.password;
    }
    
    public String getMorada(){
        return this.morada;
    }
    
    public String getDN(){
        return this.dataNascimento;
    }
    
    public boolean getRegistado(){
        return this.registado;
    }
    
    public void setEmail(String e){
        this.email = e;
    }
    
    public void setPassword(String p){
        this.password = p;
    }
    
    public void setMorada(String m){
        this.morada = m;
    }
    
    public void setRegistado(boolean r){
        this.registado = r;
    }
    
    //Sérgio:
    
    public int consultarImovel (Imovel i){
        int r=0;
        for (Map.Entry <String, Imovel> entry : imob.getImovel().entrySet()) {
           if (
           entry.getKey().equals(i.getMorada()) ||
           entry.getValue().getMorada().equals(i.getMorada()) &&
           entry.getValue().getPrecoMinimo() == (i.getPrecoMinimo()) &&
           entry.getValue().getPrecoPedido() == (i.getPrecoPedido())){    
               System.out.println("\n" + entry.getValue().getMorada() +
               "\n" + entry.getValue().getPrecoMinimo() + 
               "\n" + entry.getValue().getPrecoPedido() + "\n");
               r=0;
           }
           else r=-1; // SERVE PARA A MAIN.. DEPOIS EXPLICO
        }
        return r;
    }
    
    
    public boolean equals(Object o){
        if (this==o) return true;
        if((o==null) || (o.getClass()!=this.getClass())) return false;
        Utilizadores a = (Utilizadores) o;
        return this.email.equals(a.getEmail());
    }
    
    public abstract Utilizadores clone();
    
    public String pesquisaPorChave(String m){
        String aux = new String();
        for(Imovel i:imob.getImovel().values()){
            if(i.getMorada().equals(m)){
                i.incVisitas();
                aux = i.toString();
            }
        }
        return aux;
    }
    
    public List pesquisaPorPrecoMaximo (double precoMaximo){
        ArrayList<String> lista = new ArrayList<String>();
        for(Imovel i:imob.getImovel().values()){
            if(i.getPrecoPedido()<precoMaximo){
                i.incVisitas();
                lista.add(i.toString());
            }
        }
        return lista;
    }
    
    public List pesquisaPorTipodeImovel (String m){
        ArrayList<String> lista = new ArrayList<String>();
        for(Imovel i:imob.getImovel().values()){
            if(i.getTipoImovel().equals(m)){
                i.incVisitas();
                lista.add(i.toString());
            }
        }
        return lista;
    }
    
    public List pesquisaPorPrecoMinimo (double precoMinimo){
        ArrayList<String> lista = new ArrayList<String>();
        for(Imovel i:imob.getImovel().values()){
            if(i.getPrecoPedido()>precoMinimo){
                i.incVisitas();
                lista.add(i.toString());
            }
        }
        return lista;
    }
    
    public List pesquisaPorQuartos (int x){
        ArrayList<String> lista = new ArrayList<String>();
        for(Imovel i:imob.getImovel().values()){
            if(i instanceof Pesquisas){
                i.incVisitas();
                Pesquisas q = (Pesquisas)i;
                if(q.getnumeroQuartos()>=x){
                lista.add(q.toString());
                }
            }
        }
        return lista;
    }
   

    public List pesquisaPorWC (int x){
        ArrayList<String> lista = new ArrayList<String>();
        for(Imovel i:imob.getImovel().values()){
            if(i instanceof Pesquisas){
                i.incVisitas();
                Pesquisas q = (Pesquisas)i;
                if(q.getnumeroWC()>=x){
                lista.add(q.toString());
                }
            }
        }
        return lista;
    }
    
     public List pesquisaPorHabitavel (){
        ArrayList<String> lista = new ArrayList<String>();
        for(Imovel i:imob.getImovel().values()){
            if(i instanceof Habitavel){
                i.incVisitas();
                Habitavel q = (Habitavel)i;
                if(q.getHabitavel()){
                lista.add(q.toString());
                }
            }
        }
        return lista;
    }
    
     public List pesquisaPorHabitavelPreco (int preco){
        ArrayList<String> lista = new ArrayList<String>();
        for(Imovel i:imob.getImovel().values()){
            if(i instanceof Habitavel){
                i.incVisitas();
                Habitavel q = (Habitavel)i;
                if(q.getHabitavel() && q.getPrecoPedido()<preco){
                lista.add(q.toString());
                }
            }
        }
        return lista;
    }
}
