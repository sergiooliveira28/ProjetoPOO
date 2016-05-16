# ImOObiliaria
Um projeto da treta feito por nada mais nada menos que o grande Mossoró e o seu companheiro Sleep Dogg

# 20/05 (sexta-feira) - A fase de entregas encerra às 24h.

# 23/05 a 31/05 - Discussão dos trabalhos

# _________   Relatório | 16 Maio  _____________

Meti uma pasta com o relatório... mete aí a tua foto quando puderes...


# ________   Aviso  |  11 Maio    __________

Caros alunos,

A classe de testes já foi disponibilizada, tendo sofrido hoje uma pequena actualização.

Pelo que me é possível observar, a realização do trabalho prossegue a bom ritmo, existindo grupos já bastante perto da sua conclusão. Pelo menos, aqueles grupos que têm contactado a equipa docente.

Relativamente à definição de Habitavel (prometida no enunciado), esta foi fornecida nas aula teóricas. Resumidamente, trata-se de uma Interface que todas as classes representativas de imóveis habitáveis deverão implementar. Os métodos constantes dessa Interface ficam ao vosso critério. Como referido nas aulas, evitem a utilização de métodos default.

Bom trabalho,

Creissac

# _______ Classe Teste 9 Maio _____________

    public void mainTest() {
        imo = new Imoobiliaria();
        try {
            imo.iniciaSessao("",null);  // FALHA LOGO AQUI
			fail();
        } catch(SemAutorizacaoException e) {
            
        } catch(Exception e) {
            fail();
        }

