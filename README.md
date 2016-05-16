# ImOObiliaria
Um projeto da treta feito por nada mais nada menos que o grande Mossoró e o seu companheiro Sleep Dogg

Fala aí ó Sr. Carlos Silva..


# _____________  Classe Teste  _________________


import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Set;

public class Testes
{
    private Imoobiliaria imo;
    private Vendedor v;
    private Terreno t;
    @Test
    public void mainTest() {
        imo = new Imoobiliaria();
        try {
            imo.iniciaSessao("",null);  // FALHA LOGO AQUI
			fail();
        } catch(SemAutorizacaoException e) {
            
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
        
}


# ________   Aviso 11 Maio    __________
Caros alunos,

A classe de testes já foi disponibilizada, tendo sofrido hoje uma pequena actualização.

Pelo que me é possível observar, a realização do trabalho prossegue a bom ritmo, existindo grupos já bastante perto da sua conclusão. Pelo menos, aqueles grupos que têm contactado a equipa docente.

Relativamente à definição de Habitavel (prometida no enunciado), esta foi fornecida nas aula teóricas. Resumidamente, trata-se de uma Interface que todas as classes representativas de imóveis habitáveis deverão implementar. Os métodos constantes dessa Interface ficam ao vosso critério. Como referido nas aulas, evitem a utilização de métodos default.

Bom trabalho,

Creissac


