import java.util.Timer;
import java.util.ArrayList;
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
    private ArrayList <Comprador> compradores = new ArrayList<Comprador> ();
    private Imovel imovelAtual;
    private Imobiliaria imob;
    
    public void iniciaLeilao ( Imovel im , int horas ){
        emExecucao=true;
        this.imovelAtual = im;
        msFinal = new Date();
        timer = new Timer ();
        msFinal.setTime(msFinal.getTime() + horas*1000*60*60);
        timer.schedule(new TimeoutLeilao(), horas*1000*60 );

    }
    
    public String calculaTempoRestante(){
        return ("Leilão > Hora de encerramento: " + msFinal.getHours() + "h" + msFinal.getMinutes() + ":" + msFinal.getSeconds());
        //int secFinal = (int) msFinal.getTime()/1000;
        //return tempoRealString(secFinal);
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
            encerraLeilao(); //Terminate the timer thread
            
            
        }
    }
    
    public void adicionaComprador ( String idComprador ,double limite ,double incrementos ,double minutos ){
        compradores.add((Comprador) imob.getUtilizadores().get(idComprador));
        
    }
    
    public Comprador encerraLeilao(){
        timer.cancel();
        return this.compradores.get(0);
    }
}
