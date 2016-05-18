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
        //apostaMaxima=0.0;
        emExecucao=true;
        this.imovelAtual = im;
        msFinal = new Date();
        timer = new Timer ();
        msFinal.setTime(msFinal.getTime() + horas*1000*60*60);
        timer.schedule(new TimeoutLeilao(), horas*1000*60 /* *24 (demora horas reais se descomentar)*/ );

    }
    
    public String calculaTempoRestante(){
        StringBuffer s = new StringBuffer();
        s.append(" [Leilão a decorrer]\n > Leilão do imóvel "+ imovelAtual.getTipoImovel() +
        " com a morada seguinte: " + imovelAtual.getMorada() +
        "\n > Hora de encerramento: " +
        (msFinal.getHours() < 10 ? "0" : "") + msFinal.getHours() + "h" + 
        (msFinal.getMinutes() < 10 ? "0" : "") + msFinal.getMinutes());
        
        return (s.toString());
    }

    public Imovel getImovelLeilao (){
        return this.imovelAtual;
    }
    public boolean getEmExecucao (){return this.emExecucao;}
    public void setEmExecucao (boolean e){this.emExecucao = e;}    
    public double getApostaMaxima (){return this.apostaMaxima;}
    public void setApostaMaxima (double a){this.apostaMaxima = a;}
    
        public void adicionaComprador ( String idComprador ,double limite ,double incrementos ,double minutos) throws SemAutorizacaoException{
        Aposta a = new Aposta (idComprador,limite,incrementos,minutos,imovelAtual);
        
        if (!compradores.containsKey(idComprador)) compradores.put(idComprador,a);  
        else {throw new SemAutorizacaoException ("Comprador já existe.\n");}
        
        atualizarVencedor(idComprador);
        
    }
    
    public void atualizarVencedor(String email) throws SemAutorizacaoException{
        boolean erro = true; 
        for (Aposta a : compradores.values()){
             if ((a.getEmail().equals(email)) && (getApostaMaxima() < a.getIncrementos())) {
                 apostaMaxima = a.getIncrementos(); this.vencedor = a.getEmail(); erro = false;
             }
        }
        if (erro) {throw new SemAutorizacaoException ("Licitou menos dinheiro do que o comprador anterior. Por favor Tente novamente. ");}
       
    }
     
    public void aposta (String idComprador, double euros) throws SemAutorizacaoException {
        boolean erro = true;
        Aposta a = new Aposta (idComprador,0.0,0.0,0.0,imovelAtual);
         for (Aposta ap : compradores.values()){
             if ((ap.getEmail().equals(idComprador)) && (ap.getIncrementos() < euros)) {
                 if(ap.getLimite() < euros){throw new SemAutorizacaoException ("Excedeu o seu próprio limite. Tente novamente. ");}
                 else {
                    this.compradores.put(idComprador, a);
                    this.compradores.get(idComprador).setLimite(ap.getLimite());
                    this.compradores.get(idComprador).setIncrementos(euros);
                    this.compradores.get(idComprador).setMinutos(ap.getMinutos());
                    atualizarVencedor(idComprador);erro = false;
                 }
            }
        }
        if (erro) {throw new SemAutorizacaoException ("Licitou menos dinheiro. Por favor Tente novamente. ");}
       
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
    
    class TimeoutLeilao extends TimerTask { 
        public void run() {
            //return this.compradorAtual;
            System.out.println("Acabou o leilão!");
            encerraLeilao(); //Terminate the timer thread
            
            
        }
    }
    

    
    public Aposta encerraLeilao(){
        Aposta aux = new Aposta ();
        try {
            if (imovelAtual.getPrecoPedido()<apostaMaxima){
                
                timer.cancel();
                aux = this.compradores.get(vencedor);
                emExecucao = false;
                limparLeilao();
            }else {throw new Exception ("");}
            }
        catch (Exception e) {System.out.println("Ainda não chegou ao preço pedido do imóvel\n");}
        return aux;
    }   
}
