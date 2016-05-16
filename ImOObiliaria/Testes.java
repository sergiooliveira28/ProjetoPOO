import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Set;

public class Testes
{
    private Imobiliaria imo;
    private Vendedor v;
    private Terreno t;

    @Test
    public void mainTest() {
        imo = new Imobiliaria();
        try {
            imo.iniciaSessao("",null);
			fail();
       // } catch(SemAutorizacaoException e) {
            
        } catch(Exception e) {
            fail();
        }
        
        try {
            v = new Vendedor();  // Preencher parÃ¢metros do construtor
            imo.registarUtilizador(v);
        } catch(Exception e) {
            fail();
        }
        
        String email = v.getEmail();
        String password = v.getPassword();
        
        try {
            imo.iniciaSessao(email, password);
        } catch(Exception e) {
            fail();
        }
        
        t = new Terreno();  // Preencher parÃ¢metros do construtor
        try {
            imo.addImovel(t);
        } catch (Exception e) {
            fail();
        }
            
        //int s = imo.getImovel("Terreno", Integer.MAX_VALUE).size();
        int s=imo.getImovel().size();
        assertTrue(s>0);
        Set<String> ids = imo.getTopImoveis(0);
        assertTrue(ids.contains(t.getMorada()));
        assertTrue(imo.getMapeamentoImoveis().keySet().contains(t));
        try {
            assertTrue(imo.getConsultas().size()>0);
        } catch(Exception e) {
            fail();
        }
        
        imo.fechaSessao();
        Comprador c = new Comprador();  // Preencher parÃ¢metros do construtor
        try {
            imo.registarUtilizador(c);
        } catch(Exception e) {
            fail();
        }
        email = c.getEmail();
        password = c.getPassword();
        try {
            imo.iniciaSessao(email, password);
            imo.adicFavorito(t.getMorada());
            assertTrue(imo.getFavoritos().contains(t));
        } catch(Exception e) {
            e.printStackTrace();
            fail();
        }
    }
    
}
