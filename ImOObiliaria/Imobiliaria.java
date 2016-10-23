import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.io.*;

public class Imobiliaria implements Serializable
{
    
   // instance variables
    private String nome;
    private HashMap<String,Imovel> imovel;
    private HashMap<String,Utilizadores> utilizadores;
    private Comprador compradorAux;
    private Vendedor vendedorAux;
    private Utilizadores atual;
    private Feed feed;
   //
   
   public Imobiliaria(){
       this.nome = "";
       this.imovel = new HashMap<String,Imovel>();
       this.utilizadores = new HashMap<String,Utilizadores>();
       this.feed = new Feed();
   }
   
   public Imobiliaria(String nome){
       this.nome = nome;
       this.imovel = new HashMap<String,Imovel>();
       this.utilizadores = new HashMap<String,Utilizadores>();
       this.feed = new Feed();
   }
    
   public Imobiliaria(String nome, HashMap<String,Imovel> im, HashMap<String,Utilizadores> ut){
       this.nome = nome;
       for(Imovel i:im.values()){
           this.imovel.put(i.getMorada(),i);
       }
       
       for(Utilizadores a:ut.values()){
           this.utilizadores.put(a.getEmail(),a);
       }
   }
   
   public Imobiliaria (Imobiliaria i){
       this.nome = i.getNome();
       this.imovel = i.getImovel();
       this.utilizadores = i.getUtilizadores();
   }
   
   public HashMap<String,Imovel> getImovel(){
       HashMap<String,Imovel> aux = new HashMap<String,Imovel>();
       for(Imovel l:imovel.values()){
            aux.put(l.getMorada(),l);
       }
       return aux;
       
   }
   
   public HashMap<String,Utilizadores> getUtilizadores(){
       HashMap<String,Utilizadores> aux = new HashMap<String,Utilizadores>();
       for(Utilizadores l:utilizadores.values()){
            aux.put(l.getEmail(),l);
       }
       return aux;
   }
   
   public Utilizadores getAtual (){
       return this.atual;
    }
   
   public String getNome(){
       return this.nome;
   }
   
    
   public String getFeed (){
       return this.feed.imprimir();
   }
   
   public void setFeed (String s){this.feed.guardar(s);}
   
   public boolean isEmptyImovel (){
       return this.imovel.isEmpty();
    }
   
   public boolean isEmptyUtilizador (){
       return this.utilizadores.isEmpty();
    }
    
   public void removeImovel (String i){
       this.imovel.remove(i);
       
   }
   
   public List <Habitavel> getHabitaveis (int preco){
      ArrayList <Habitavel> aux = new ArrayList <Habitavel>();
      Moradia moradiaAux;
      Apartamento apartamentoAux;
      LojaHabitavel lojaHabitavelAux;
      for (Imovel i : imovel.values()){
       if ((i instanceof Moradia) && (i.getPrecoPedido()<preco)){
           moradiaAux = (Moradia) i;
          if (moradiaAux.getHabitavel()){
              aux.add(moradiaAux);
           }
        
       }
       if ((i instanceof Apartamento) && (i.getPrecoPedido()<preco)){
           apartamentoAux = (Apartamento) i;
          if (apartamentoAux.getHabitavel()){
              aux.add(apartamentoAux);
           }
        
       }
       if ((i instanceof LojaHabitavel) && (i.getPrecoPedido()<preco)){
           lojaHabitavelAux = (LojaHabitavel) i;
          if (lojaHabitavelAux.getHabitavel()){
              aux.add(lojaHabitavelAux);
           }
        
       }
       
      }
     return aux;
    }
   
   public Imobiliaria clone(){
        return new Imobiliaria(this);
    }
    
   public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Imobiliaria im = (Imobiliaria) obj;
        return im.getNome().equals(nome) && 
               im.getImovel().equals(imovel) &&
               im.getUtilizadores().equals(utilizadores);

   }
   
   public String toString() {
        StringBuilder sb = new StringBuilder(nome);
        sb.append("(");
        sb.append(imovel.toString());
        sb.append(")");
        sb.append(utilizadores.toString());
        return sb.toString();
   }
   
   // IMOVEIS
   
    public void iniciaSessao(String email, String password) throws SemAutorizacaoException {
        boolean erro = true;
        if (this.atual.getRegistado()){throw new SemAutorizacaoException("Ja tem sessao iniciada\n Termine a sessao atual. ");}
        else{
                for (Utilizadores u : this.utilizadores.values()){
                    if (email.equals(u.getEmail())&&password.equals(u.getPassword())&& u instanceof Vendedor) {
                        atual = (Vendedor) u; erro = false;
                    }
                    else if (email.equals(u.getEmail())&&password.equals(u.getPassword())&& u instanceof Comprador) {
                        atual =  (Comprador) u; erro = false;
                    } 
                }
                if (erro) {throw new SemAutorizacaoException("Utilizador nao encontrado ou palavra passe incorreta. ");}
            }
        
    }
    
    public void fechaSessao(){
        if (this.atual.getRegistado()) {saveUtilizador(atual);init();}
    }
   
   public boolean existeImovel(Imovel a) {
        return imovel.containsKey(a.getMorada());
   }
    
   public Map <String, Imovel> getImovel(String s, int n) {
        return this.imovel;
   }
   
   public void addImovel (Imovel i)throws ImovelExisteException{
        vendedorAux = (Vendedor) atual;
        if (this.imovel.containsKey(i.getMorada())){throw new ImovelExisteException("Imóvel já existe. ");}
        else {
               imovel.put(i.getMorada(),i);
               vendedorAux.setImovelP(i);
        }
        atual = vendedorAux;
   }
   
   public void registaImovel (Imovel i) throws ImovelExisteException{
       addImovel(i);
   }
   
   public void remImovel (String i){
       this.imovel.remove(i);
   }
   
   public void listarImovel () throws ImovelInexistenteException {
        boolean erro = this.imovel.isEmpty();
        try{
            if (!erro){
                for (Imovel i : this.imovel.values()){
                    System.out.println(i.toString());
                    i.incVisitas();
                }
            
            }
            else {throw new ImovelInexistenteException("");}
        }catch(ImovelInexistenteException e){System.out.println(e.getMessage());}
        
   }
   
   public void listarImovelComprador () throws ImovelInexistenteException {
        boolean erro = this.imovel.isEmpty();
        try{
            if (!erro){
                for (Imovel i : this.imovel.values()){
                    System.out.println(i.toStringParaCompradores());
                    i.incVisitas();
                }
            
            }
            else {throw new ImovelInexistenteException("");}
        }catch(ImovelInexistenteException e){System.out.println(e.getMessage());}
        
   }
   /*
   public void removeImovel (String morada){
            for (Imovel i : this.imovel.values()){
                if (i.getMorada().equals(morada)){
                    imobi.remImovel(morada);
                }
            }
        
    }*/
   
   //UTILIZADORES
   
   public void init(){
       atual = new Comprador ("","","","","",this,false);
   }
   
   
   public List <Consulta> getConsultas(){
        ArrayList <Consulta> c = new ArrayList <Consulta> (10);
        
        for (Imovel i : imovel.values()){
            Consulta consulta = new Consulta ();
            consulta.setImovel(i);
            consulta.setContagem(i.getVisitas()+1);
            c.add(consulta);
        }
         
        return c;
   }
   
   public boolean existeUtilizador(Utilizadores u) {
        return utilizadores.containsKey(u.getEmail());
   }
   
   public void saveUtilizador(Utilizadores u){
       this.utilizadores.put(u.getEmail(),u);
   }
    
   public void registarUtilizador (Utilizadores u) throws UtilizadorExistenteException{
       
        
       if (this.utilizadores.containsKey(u.getEmail())) {throw new UtilizadorExistenteException("Utilizador já existe! ");}
       else {
           this.utilizadores.put(u.getEmail(),u);
        } 
       
    }
    
   public void listarVendedores (){
       for (Utilizadores u : this.utilizadores.values()){
           if (u instanceof Vendedor){
               System.out.println(u.getEmail());
           }
       }
    }
    
   public void listarCompradores(){
       for (Utilizadores u : this.utilizadores.values()){
           if (u instanceof Comprador){
               System.out.println(u.getEmail());
           }
       }
    }
    
   /*
   public boolean pesquisaPorChave(String m){
        
        for (Imovel im : this.imovel.values()){ 
            if (im.getMorada().equals(m)) {System.out.println("PASSOU");im.incVisitas();}
        }
        return this.imovel.containsKey(m);
   }
   */

   public Set<Imovel> getTopImoveis (int i){
       TreeSet <Imovel> topImoveis = new TreeSet <Imovel>(new TopComparator ());
       HashSet <Imovel> topImoveisAux = new HashSet <Imovel>();
       try{
           if (imovel.isEmpty()) {throw new ImovelInexistenteException ("Não existem imóveis nos anúncios. ");}
           else {
           for (Imovel im : this.imovel.values()){
                topImoveis.add(im);
           }
           for (Imovel im : topImoveis){
               if (i>0){ 
                   topImoveisAux.add(im);
                   
               }
               i--;
            }
        }
       }catch (ImovelInexistenteException e) {System.out.println(e.getMessage());}
       return topImoveisAux;
    }
   
   public Map<Imovel,Vendedor> getMapeamentoImoveis(){
        Map <Imovel,Vendedor> aux = new HashMap <Imovel,Vendedor> ();
        for (Utilizadores u : utilizadores.values()){
            if (u instanceof Vendedor){
                vendedorAux = (Vendedor) u;
               for (Imovel i : vendedorAux.getImoveisP()){
                   aux.put(i.clone(), vendedorAux.clone());
                   i.incVisitas();
                }
            }
        }
        return aux;
    }
  

   public void alteraEstadoImovel (String morada, String estado) throws ImovelInexistenteException {
      try{
         if (estado.equals("Em venda")||estado.equals("Vendido")||estado.equals("Reservado")){
           if (this.imovel.containsKey(morada)){
              imovel.get(morada).setEstado(estado);
              
           }
         }
         else {throw new ImovelInexistenteException ("Inseriu um estado inválido. ");}
      }catch (ImovelInexistenteException e) {System.out.println(e.getMessage());}
      
    }
    
    public void adicFavorito(String morada) throws ImovelExisteException {
        
        compradorAux = (Comprador) atual;
        boolean erro = true;
        for (Imovel i : this.imovel.values()){
            if (i.getMorada().equals(morada)) {
                if (compradorAux.getFavoritosNaoOrdenados().contains(i)) {throw new ImovelExisteException ("Favorito já existente. ");}
                else {compradorAux.setFavorito(morada);erro = false;}
            
            }
            
        }
        if (erro) {throw new ImovelExisteException ("Imóvel inválido ");}
        atual = compradorAux;     
    }
    
    public void setFavorito(String morada) throws ImovelExisteException{
        adicFavorito(morada);
    }
    
    public void listarFavorito () throws ImovelInexistenteException{
        compradorAux = (Comprador) atual;
        try{
            if (compradorAux.getFavoritosNaoOrdenados().isEmpty()) {throw new ImovelInexistenteException ("");}
            else {
                Iterator<Imovel> itr=compradorAux.getFavoritos().iterator();
                while (itr.hasNext()){
                    Imovel i=itr.next();
                    System.out.println(i.toString());
                    i.incVisitas();
                }
            }
        }catch (ImovelInexistenteException e) {System.out.println(e.getMessage());}
    }
    public void listarComprados () throws ImovelInexistenteException{
        compradorAux = (Comprador) atual;
        try{
            if (compradorAux.getFavoritos().isEmpty()) {throw new ImovelInexistenteException ("");}
            else {
                for (Imovel i : compradorAux.getImoveisComprados()){
                    System.out.println(i.toString());
                    i.incVisitas();
                }
            }
        }catch (ImovelInexistenteException e) {System.out.println(e.getMessage());}
    }
    
    public void removeComprado (Imovel i) throws ImovelInexistenteException{
        compradorAux = (Comprador) atual;
        try{
            if (compradorAux.getFavoritos().isEmpty()) {throw new ImovelInexistenteException ("");}
            else {
                   compradorAux.getImoveisComprados().remove(i);               
                }
            
        }catch (ImovelInexistenteException e) {System.out.println(e.getMessage());}
    
    }
    
    public Set <Imovel> getFavoritos () throws ImovelInexistenteException{
        compradorAux = (Comprador) atual;
        return compradorAux.getFavoritos();
    }
    
    public void removeFavorito (String morada) throws ImovelInexistenteException{
        compradorAux = (Comprador) atual;
        if  (compradorAux.getFavoritos().isEmpty()) {throw new ImovelInexistenteException ("Favoritos vazios. ");}
        else {
            Iterator<Imovel> itr=compradorAux.getFavoritos().iterator();
            while (itr.hasNext()){
                Imovel i=itr.next();
                if (i.getMorada().equals(morada)){
                    compradorAux.remFavorito(morada);
                }
                else {throw new ImovelInexistenteException ("Nao encontrado");}
            }
        }
      
   }
       
   public void comprarImovel (String morada) throws ImovelInexistenteException{
           if (imovel.isEmpty()) {throw new ImovelInexistenteException ("Não existem imóveis nos anúncios. ");}
           else if (!imovel.containsKey(morada)) {throw new ImovelInexistenteException ("Não existe este imóvel nos anúncios. ");}
           else if (imovel.get(morada).getEstado().equals("Reservado")) {throw new ImovelInexistenteException ("Imóvel já foi reservado. ");}
           else if (imovel.get(morada).getEstado().equals("Vendido")) {throw new ImovelInexistenteException ("Imóvel já foi vendido. ");}
           else {
               for (Utilizadores u: utilizadores.values()){
                   if (u instanceof Vendedor){
                       vendedorAux = (Vendedor) u;
                       if (vendedorAux.getImoveisP().contains(imovel.get(morada))){
                           alteraEstadoImovel(morada,"Reservado");
                       }
                       u = vendedorAux;
                    }
               }
  
           }
    }
    
    public void confirmarImovelComprado (String morada) throws ImovelInexistenteException {
           if (imovel.isEmpty()) {throw new ImovelInexistenteException ("Não existem imóveis nos anúncios. ");}
           else if (!imovel.containsKey(morada)) {throw new ImovelInexistenteException ("Não existe este imóvel nos anúncios. ");}
           else if (imovel.get(morada).getEstado().equals("Em venda")) {throw new ImovelInexistenteException ("Imóvel está em venda. ");}
           else if (imovel.get(morada).getEstado().equals("Vendido")) {throw new ImovelInexistenteException ("Imóvel já foi vendido. ");}
           else {
                for (Utilizadores u: utilizadores.values()){
                   if (u instanceof Vendedor){
                       vendedorAux = (Vendedor) u;
                       if (vendedorAux.getImoveisP().contains(imovel.get(morada))){
                           vendedorAux.setImovelV(imovel.get(morada));
                           vendedorAux.getImoveisP().remove(imovel.get(morada));
                           alteraEstadoImovel(morada,"Vendido");
                           
                        }
                   }
                   if (u instanceof Comprador){
                       compradorAux = (Comprador) u;
                       compradorAux.setComprado(imovel.get(morada));
                   }
                }
            }
       
    }
   
   public void carregaDados(String fich) throws Exception {
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fich));
            this.nome = (String) ois.readObject();
            this.imovel = (HashMap<String,Imovel>) ois.readObject();
            this.utilizadores = (HashMap<String,Utilizadores>) ois.readObject();
            this.feed = (Feed) ois.readObject();
            ois.close();
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage() + "ClassNotFound");
        }catch(java.io.IOException e){
            System.out.println(e.getMessage() + " IOExecption");
        }
   }
    
   public void gravaDados(String fich) throws Exception{
        ObjectOutputStream ficheiro = new ObjectOutputStream(new FileOutputStream(fich));
        ficheiro.writeObject((String) this.nome);
        ficheiro.writeObject((HashMap<String,Imovel>) this.imovel);
        ficheiro.writeObject((HashMap<String,Utilizadores>) this.utilizadores);
        ficheiro.writeObject((Feed) this.feed);
        ficheiro.flush();
        ficheiro.close();
   }
}