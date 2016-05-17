import java.util.Timer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
import java.util.TimerTask;

/**
 * Write a description of class Leiloes here.
 * 
 * @author (Sergio, Carlos) 
 * @version (16-05-2016)
 */
public class Leiloes
{
    private boolean emExecucao;
    private Timer timer;
    private Date msFinal;
    private HashMap <String,Aposta> compradores = new HashMap<String,Aposta> ();
    private String vencedor = new String ();
    private Imovel imovelAtual;
    
    private double apostaMaxima;
    
    public void iniciaLeilao ( Imovel im , int horas ) {
        emExecucao=true;
        this.imovelAtual = im;
        msFinal = new Date();
        timer = new Timer ();
        msFinal.setTime(msFinal.getTime() + horas*1000*60*60);
        timer.schedule(new TimeoutLeilao(), horas*1000*60 );

    }
    
    public String calculaTempoRestante(){
        return ("Leilão do imóvel "+ imovelAtual.getTipoImovel() + " com a morada seguinte: " + imovelAtual.getMorada() +
        "\n > Hora de encerramento: " +
        (msFinal.getHours() < 10 ? "0" : "") + msFinal.getHours() + "h" + 
        (msFinal.getMinutes() < 10 ? "0" : "") + msFinal.getMinutes());
    }
    
    public Imovel getImovelLeilao (){
        return this.imovelAtual;
    }
    public boolean getEmExecucao (){return this.emExecucao;}
    public void setEmExecucao (boolean e){this.emExecucao = e;}    
    
    class TimeoutLeilao extends TimerTask { 
        public void run() {
            
            //return this.compradorAtual;
            System.out.println("Acabou o leilão!");
            emExecucao = false;
            limparLeilao();
            encerraLeilao(); //Terminate the timer thread
            
            
        }
    }
    
    public void adicionaComprador ( String idComprador ,double limite ,double incrementos ,double minutos){
        Aposta a = new Aposta (idComprador,limite,incrementos,minutos,imovelAtual);
        
        if (!compradores.containsKey(idComprador)) compradores.put(idComprador,a);  
        else {System.out.println("Comprador já existe.\n");}
        
        atualizarVencedor(idComprador);
    }
    
    public void atualizarVencedor(String email){
        Aposta aux = new Aposta();
        for (Aposta a : compradores.values()){
             if (aux.getIncrementos()<=a.getIncrementos()){
                 this.vencedor = a.getEmail();apostaMaxima = a.getIncrementos();
             }
        }
        
        
    }
    
    public void aposta (String idComprador, double euros){
        Aposta a = new Aposta (idComprador,0.0,euros,0.0,imovelAtual);
        this.compradores.put(idComprador, a);
        this.compradores.get(idComprador).setIncrementos(euros);
    }
    
    public String listarLicitadores (){
       StringBuffer s = new StringBuffer();
       for (Aposta a : compradores.values()){
           s.append("O licitador " + a.getEmail() + " tem " + a.getIncrementos() + " € apostados.\n");
        }
        return s.toString();
    }
    
    public void limparLeilao(){
        compradores = new HashMap<String,Aposta> ();
        vencedor = new String ();
        
    }
    
    public Aposta encerraLeilao(){
        Aposta aux = new Aposta ();
        try {
            if (imovelAtual.getPrecoPedido()<apostaMaxima){
                timer.cancel();
                aux = this.compradores.get(vencedor);
            }else {throw new Exception ("");}
            }
        catch (Exception e) {System.out.println("Ainda não chegou ao preço pedido do imóvel\n");}
        return aux;
    }   
}
