import java.util.Map;
import java.util.Set;
import java.util.HashSet;
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
       /*
       HashMap<String,Imovel> aux = new HashMap<String,Imovel>();
       for(Imovel l:imovel.values()){
            aux.put(l.getMorada(),l);
       }
       return aux;
       */
      return this.imovel;
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
   
    public boolean iniciaSessao(String email, String password) throws UtilizadorInexistenteException,SemAutorizacaoException {
        boolean erro = true;
        try {
        if (this.atual.getRegistado()){throw new SemAutorizacaoException("Ja tem sessao iniciada\n Termine a sessao atual.");}
        else{
                for (Utilizadores u : this.utilizadores.values()){
                    if (email.equals(u.getEmail())&&password.equals(u.getPassword())&& u instanceof Vendedor) {
                        atual = (Vendedor) u; erro=false;
                    }
                    else if (email.equals(u.getEmail())&&password.equals(u.getPassword())&& u instanceof Comprador) {
                        atual =  (Comprador) u; erro=false;
                    } 
                }
                if (erro) {throw new SemAutorizacaoException("Utilizador nao encontrado ou palavra passe incorreta\n");}
            }
        } 
        catch (SemAutorizacaoException e){System.out.println(e.getMessage());} 
        return erro;
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
   
   public boolean addImovel (Imovel i)throws ImovelExisteException{
        vendedorAux = (Vendedor) atual;
        boolean erro = this.imovel.containsKey(i.getMorada());
        try {
            if (erro){ throw new ImovelExisteException("Im�vel j� existe\n");}
            else {
                   imovel.put(i.getMorada(),i);
                   vendedorAux.setImovelP(i);
            }
        }catch (ImovelExisteException e){System.out.println(e.getMessage());}
        atual = vendedorAux;
        return erro;
   }
   
   public void registaImovel (Imovel i) throws ImovelExisteException{
       addImovel(i);
   }
   
   public void remImovel (String i){
       this.imovel.remove(i);
   }
   
   public boolean listarImovel () throws ImovelInexistenteException {
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
        return erro;
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
        ArrayList <Consulta> c = new ArrayList <Consulta> ();
        Consulta consulta = new Consulta ();
        for (Imovel i : imovel.values()){
            consulta.setContagem(i.getVisitas()); consulta.setImovel(i);
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
    
   public boolean registarUtilizador (Utilizadores u) throws UtilizadorExistenteException{
       boolean erro=true;
        try {
           if (this.utilizadores.containsKey(u.getEmail())) {throw new UtilizadorExistenteException("");}
           else {
               this.utilizadores.put(u.getEmail(),u);erro=false;
            } 
       
       } catch (UtilizadorExistenteException e) {System.out.println(e.getMessage());}
       return erro;
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
   
   public boolean pesquisaPorChave(String m){
        return imovel.containsKey(m);
   }
   
   public Set<String> getTopImoveis (int i){
       HashSet <String> imoveis = new HashSet <String>();
       try{
           if (imovel.isEmpty()) {throw new ImovelInexistenteException ("N�o existem im�veis nos an�ncios");}
           else {
           for (;i>0;i--){
               if (this.imovel.containsKey(i)) imoveis.add(this.imovel.get(i).toString());
           }
        }
       }catch (ImovelInexistenteException e) {System.out.println(e.getMessage());}
       return imoveis;
    }
   
   public Map<Imovel,Vendedor> getMapeamentoImoveis(){
       HashMap<Imovel,Vendedor> aux = new HashMap<Imovel,Vendedor>();
       for(Utilizadores u : utilizadores.values()){
           if (u instanceof Vendedor){
               Vendedor vendedorAux = (Vendedor) u;
               for (Imovel i : vendedorAux.getImoveisP()){
                   aux.put(i, vendedorAux);
                   i.incVisitas();
               }
               
               for (Imovel i : vendedorAux.getImoveisV()){
                   aux.put(i, vendedorAux);
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
        else {throw new ImovelInexistenteException ("");}
      }catch (ImovelInexistenteException e) {System.out.println(e.getMessage());}
      
    }
    
    public void adicFavorito(String morada) throws ImovelExisteException {
        compradorAux = (Comprador) atual;
        try {
        for (Imovel i : this.imovel.values()){
            if (i.getMorada().equals(morada)) {
                if (compradorAux.getFavoritos().contains(morada)) {throw new ImovelExisteException ("");}
                else compradorAux.setFavorito(morada);
            }
            
        }
        }catch (ImovelExisteException e) {}
        atual = compradorAux;
    }
    
    public void setFavorito(String morada) throws ImovelExisteException{
        adicFavorito(morada);
    }
    
    public void listarFavorito () throws ImovelInexistenteException{
        compradorAux = (Comprador) atual;
        try{
            if (compradorAux.getFavoritos().isEmpty()) {throw new ImovelInexistenteException ("");}
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
    
    public Set <Imovel> getFavoritos () throws ImovelInexistenteException{
        compradorAux = (Comprador) atual;
        return compradorAux.getFavoritos();
    }
    
    public void removeFavorito (String morada) throws ImovelInexistenteException{
        compradorAux = (Comprador) atual;
        if  (compradorAux.getFavoritos().isEmpty()) {throw new ImovelInexistenteException ("Favoritos vazios");}
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
       try {
           if (imovel.isEmpty()) {throw new ImovelInexistenteException ("N�o existem im�veis nos an�ncios");}
           else if (!imovel.containsKey(morada)) {throw new ImovelInexistenteException ("N�o existe este im�vel nos an�ncios");}
           else if (imovel.get(morada).getEstado().equals("Reservado")) {throw new ImovelInexistenteException ("Im�vel j� foi reservado");}
           else if (imovel.get(morada).getEstado().equals("Vendido")) {throw new ImovelInexistenteException ("Im�vel j� foi vendido");}
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
       catch (ImovelInexistenteException e){ System.out.println(e.getMessage());}
    }
    
    public void confirmarImovelComprado (String morada){
        try {
           if (imovel.isEmpty()) {throw new ImovelInexistenteException ("N�o existem im�veis nos an�ncios");}
           else if (!imovel.containsKey(morada)) {throw new ImovelInexistenteException ("N�o existe este im�vel nos an�ncios");}
           else if (imovel.get(morada).getEstado().equals("Em venda")) {throw new ImovelInexistenteException ("Im�vel est� em venda");}
           else if (imovel.get(morada).getEstado().equals("Vendido")) {throw new ImovelInexistenteException ("Im�vel j� foi vendido");}
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
        }catch (ImovelInexistenteException e){ System.out.println(e.getMessage());}
    }
   
   public void carregaDados(String fich) throws Exception {
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fich));
            this.nome = (String) ois.readObject();
            //this.inicio = (Date) ois.readObject();
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
        //ficheiro.writeObject(this.inicio);
        ficheiro.writeObject((HashMap<String,Imovel>) this.imovel);
        ficheiro.writeObject((HashMap<String,Utilizadores>) this.utilizadores);
        ficheiro.writeObject((Feed) this.feed);
        ficheiro.flush();
        ficheiro.close();
   }
}