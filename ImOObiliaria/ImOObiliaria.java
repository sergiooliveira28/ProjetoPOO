import java.util.*;
import java.io.*;
public class ImOObiliaria
{
    private static String aviso,aviso2 = "";
    private static Leiloes leiloes;
    private static Vendedor vendedorAux;
    private static Comprador compradorAux;
    private static Imobiliaria imobi = new Imobiliaria ();   
    
    // _______________________________ JANELAS _____________________________________________   
    
    public static ImOObiliaria initApp (){
        return new ImOObiliaria ();
    }

    public static void main(String[] args)throws Exception{
        imobi.init();
        int escolha,acabado=0;imobi.carregaDados("Imobiliaria.obj"); setAviso("Bem-vindo");      
        while (acabado != 1){

            System.out.println("\n\n\n\n\n = = =  I m O O b i l i á r i a  = = =\n");
            System.out.println("<i> : "+ getAviso());
            System.out.println("<i> : "+ getAviso2());
            menuInicio();
            escolha = Input.lerInt();
            switch (escolha){
                case 1:
                    limparTexto();
                    utilizadores();
                break;
                
                case 2:
                    limparTexto();
                    pesquisar();
                break;
                
                case 3:
                    limparTexto();
                    janelaFeed();
                break;
                case 4:
                    limparTexto();
                    vendedor();
                break;
                case 5:
                    limparTexto();
                    comprador();
                break;
                case 6:
                    limparTexto();
                    leiloes ();
                break; 
                case 7:
                    limparTexto();
                    mapeamentoImoveis();
                break;
                case 0:
                    limparTexto();
                    acabado = sair();
                break; // FIM DO PROGRAMA
                default:
                    limparTexto();
                    setAviso("Opção errada");
                break;
            }        
        }
    }
    

    // _______________________________ UTILIZADORES _____________________________________________ 


    public static void utilizadores () throws UtilizadorInexistenteException, SemAutorizacaoException{
        int escolha,acabado=0;      
        String email,nome,password,morada,dataNascimento; boolean registado,erro;
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n = = =  U t i l i z a d o r e s  = = =\n\n");
        
        while (acabado != 1){
            System.out.println("<i> : "+ getAviso()); 
            System.out.println("<i> : "+ getAviso2());
            menuUtilizadores();
            escolha= Input.lerInt();
            switch (escolha){
                
                case 1:
                try{
                    limparTexto();
                    System.out.println("Tipo de utilizador (Vendedor / Comprador)\n");
                    String tipo = Input.lerString();
                    
                    if (tipo.equals("Vendedor")){
                        System.out.println("- Email: ");
                        email = Input.lerString();
                        System.out.println("- Nome: ");
                        nome = Input.lerString();
                        System.out.println("- Password: ");
                        password = Input.lerString();
                        System.out.println("- Morada: ");
                        morada = Input.lerString();
                        System.out.println("- Data de nascimento(Dia/Mês/Ano): ");
                        dataNascimento = Input.lerString();
                        registado = true;
                        vendedorAux = new Vendedor (email,nome,password,morada,dataNascimento,imobi,registado);
                        erro=imobi.registarUtilizador(vendedorAux);
                        setAviso("Vendedor criado");
                    }
                    else if (tipo.equals("Comprador")){   
                        System.out.println("- Email: ");
                        email = Input.lerString();
                        System.out.println("- Nome: ");
                        nome = Input.lerString();
                        System.out.println("- Password: ");
                        password = Input.lerString();
                        System.out.println("- Morada: ");
                        morada = Input.lerString();
                        System.out.println("- Data de nascimento(Dia/Mês/Ano): ");
                        dataNascimento = Input.lerString();
                        registado = true;
                        compradorAux = new Comprador (email,nome,password,morada,dataNascimento,imobi,registado);
                        erro=imobi.registarUtilizador(compradorAux);
                        setAviso("Comprador criado");
                    }
                    else {throw new UtilizadorExistenteException("Ocorreu um erro.");}
                    
                    if (erro) {setAviso("Este utilizador já existe!");}
                } catch (UtilizadorExistenteException e){setAviso(e.getMessage() + "Tipo de utilizador inválido");}
                    
                    
                break;
                case 2:
                    limparTexto();
                    System.out.println("\nInsira o e-mail de utilizador");
                    email = Input.lerString();
                    System.out.println("Insira a password");
                    password = Input.lerString();
                    try{
                        erro=imobi.iniciaSessao(email,password);
                        if (erro) {throw new UtilizadorInexistenteException("Ocorreu um erro.");}
                        else {setAviso("Sessão iniciada"); setAviso2("Utilizador atual: " + email);}
                    } catch (UtilizadorInexistenteException e){setAviso(e.getMessage());}
                break;
                case 3:
                    limparTexto();
                    listarUtilizador();
                    System.out.println("\n");
                    setAviso("Concluído");
                break; // GRAVA
                case 4:
                    limparTexto();
                    imobi.fechaSessao();
                    setAviso("Terminou a sessão");
                    setAviso2("");
                break;
                
                case 0:
                    limparTexto();
                    setAviso("Saiu da gestão de utilizadores");
                    acabado = 1;
                break;
                default:
                    limparTexto();
                    setAviso("Opção errada");
                break;
            }        
        }    
    }
    
    // _______________________________ SESSÃƒO  _____________________________________________ 

    
    public static void vendedor () throws ImovelInexistenteException,EstadoInvalidoException,TipoInvalidoException,ImovelExisteException{
        int escolha,acabado=0; boolean erro; String morada, estado;
        // SESSAO VENDEDOR
        try {
           if (imobi.getAtual() instanceof Vendedor && imobi.getAtual().getRegistado()){
                 
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n= = =  V e n d e d o r  = = =\n\n");
                setAviso("Bem-vindo "+ imobi.getAtual().getNome());
                    while (acabado != 1){
                        System.out.println("<i> : "+ getAviso()); 
                        System.out.println("<i> : "+ getAviso2());
                        menuVendedor();
                        escolha= Input.lerInt();
  
                        switch (escolha){
                            case 1:
                                limparTexto();
                                pesquisar();
                                setAviso("Pesquisa concluída");
                            break;
                            case 2:
                                limparTexto();
                                insereImovel();
                                limparTexto();
                            break;
                            case 3:
                                limparTexto();
                                System.out.println("- Morada do imóvel que quer remover \n");
                                morada = Input.lerString();
                                
                                if (imobi.getImovel().isEmpty()) {throw new ImovelInexistenteException("Não existem imóveis na base de dados");}
                                else if (imobi.getImovel().containsKey(morada)){
                                    
                                    imobi.setFeed(imobi.getAtual().getNome() + " removeu dos anúncios o imóvel " + imobi.getImovel().get(morada).getEstado() + " situado na seguinte morada: " + morada + "\n");
                                    imobi.removeImovel(morada);
                                    setAviso("Imóvel removido");
                                }else {throw new ImovelInexistenteException("Imóvel não existente");}
                            break;  
                            case 4: 
                                limparTexto();
                                try{
                                    imobi.listarImovel();
                                    System.out.println("\nENTER para continuar");Input.lerString();
                                    setAviso("Imóveis listados");
                                }catch (ImovelInexistenteException e){setAviso("Não existem imóveis nos anúncios");}
                                
                               
                        break;
                        case 5:
                            limparTexto();
                            listarImoveisP();
                        break;
                        case 6:
                            limparTexto();
                            listarImoveisV();
                        break;
                        case 7:
                        limparTexto();
                        erro=true;
                        
                        System.out.println("- Morada do imóvel que quer alterar \n");
                        morada = Input.lerString();
                        System.out.println("Qual é o novo estado que quer para " + morada + "?\n");
                        estado = Input.lerString();
                        erro = imobi.alteraEstadoImovel(morada,estado);
                        
                        if (erro) setAviso ("estado de imóvel nÃ£o alterado");
                        else {
                            setAviso ("estado de imóvel alterado");
                            imobi.setFeed(imobi.getAtual().getNome() + " alterou o estado do imóvel para " + imobi.getImovel().get(morada).getEstado() + " situado na seguinte morada: " + morada + "\n");
                        }
                        
         
                        break;
                        case 0:
                             limparTexto();
                             
                             setAviso("Saiu do menu de vendedores");
                             acabado = 1;
                        break;
                        default:
                            limparTexto();
                            setAviso("Opção errada");
                        break;
                    }        
                }
           }else throw new SemAutorizacaoException("Sem permissão");
        }
        catch (SemAutorizacaoException e){System.out.println(e.getMessage());setAviso ("Não tem permissão para aceder a esta opção");}
        catch (ImovelInexistenteException e){System.out.println(e.getMessage());setAviso ("Base de dados vazia");}
    }
    
    public static void comprador () throws ImovelExisteException,ImovelInexistenteException{
        int escolha,acabado=0; String morada;
         // SESSAO COMPRADOR
        try {      
           if (imobi.getAtual() instanceof Comprador && imobi.getAtual().getRegistado()){
               System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n= = =  C o m p r a d o r  = = =\n\n");
               setAviso("Bem-vindo "+ imobi.getAtual().getNome()); 
              
               while (acabado != 1){
                   System.out.println("<i> : "+ getAviso()); 
                   System.out.println("<i> : "+ getAviso2());
                   
                   menuComprador();
                   escolha= Input.lerInt();
                
                   switch (escolha){
                       case 1:
                           limparTexto();
                           pesquisar();
                       break;
                       
                       case 2:
                            limparTexto();
                                try{
                                    imobi.listarImovel();
                                    System.out.println("\nENTER para continuar");Input.lerString();
                                    setAviso("Imóveis listados");
                                }catch (ImovelInexistenteException e){setAviso("Não existem imóveis nos anúncios");}
                       break;
                       
                       case 3:
                            limparTexto();
                            System.out.println("Insira a morada do imóvel que quer comprar\n");
                            morada = Input.lerString();
                            imobi.comprarImovel(morada);
                            
                       break;
                       case 4:
                           limparTexto();
                           System.out.println("Insira a morada do imóvel que quer adicionar aos favoritos");morada = Input.lerString();
                           imobi.adicFavorito(morada);
                           limparTexto();
                       break;
                       case 5:
                           limparTexto();
                           imobi.listarFavorito();
                       break;
                       case 6:
                           limparTexto();System.out.println("Insira a morada do imóvel que quer remover dos favoritos");morada = Input.lerString();
                           imobi.removeFavorito(morada);
                           limparTexto();
                       break;
                       case 0:
                             limparTexto();
                             
                             setAviso("Saiu do menu de compradores");
                           
                             acabado = 1;
                       break;  
                       default:
                            limparTexto();
                           setAviso("Opção errada");
                       break;
                   }        
               }
                   
           }
           else throw new SemAutorizacaoException("Sem permissão");
        } catch (SemAutorizacaoException e){System.out.println(e.getMessage());setAviso ("NÃ£o tem permissÃ£o para aceder a esta opção");}
    }
    
    // _______________________________ PESQUISAR _____________________________________________ 


    public static void pesquisar (){
        int escolha,acabado=0, m; String n;      
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n= = =  P e s q u i s a  = = =\n\n");
        
        while (acabado != 1){
            setAviso("");
            System.out.println("<i> : "+ getAviso());
            System.out.println("<i> : "+ getAviso2());
             
            menuPesquisar(); 
            escolha= Input.lerInt();
            
            switch (escolha){
                case 1:
                    limparTexto();
                    System.out.println("- Insira a morada (chave):");
                    n= Input.lerString();
                    limparTexto();
                    System.out.println("RESULTADOS:\n");
                    System.out.println(imobi.getAtual().pesquisaPorChave(n));
                    System.out.println("\nENTER para continuar");Input.lerString();
                    System.out.println("\n");
                break;
                case 2:
                    limparTexto();
                    System.out.println("- Insira o preço mínimo pretendido:");
                    m = Input.lerInt();
                    limparTexto();
                    System.out.println("RESULTADOS:\n");
                    System.out.println(imobi.getAtual().pesquisaPorPrecoMinimo(m));
                    System.out.println("\nENTER para continuar");Input.lerString();
                    System.out.println("\n");
                break;
                case 3:
                    limparTexto();
                    System.out.println("- Insira o preço máximo pretendido:");
                    m = Input.lerInt();
                    limparTexto();
                    System.out.println("RESULTADOS:\n");
                    System.out.println(imobi.getAtual().pesquisaPorPrecoMaximo(m));
                    System.out.println("\nENTER para continuar");Input.lerString();
                    System.out.println("\n");
                break; // GRAVA
                case 4:
                    limparTexto();
                    System.out.println("- Insira o tipo de imóvel (Moradia / Terreno / Apartamento / Loja):");
                    n = Input.lerString();
                    limparTexto();
                    System.out.println("RESULTADOS:\n");
                    System.out.println(imobi.getAtual().pesquisaPorTipodeImovel(n));
                    System.out.println("\nENTER para continuar");Input.lerString();
                    System.out.println("\n");
                break;
                case 5:
                    limparTexto();
                    System.out.println("- Insira o nº mínimo de quartos:");
                    m = Input.lerInt();
                    limparTexto();
                    System.out.println("RESULTADOS:\n");
                    System.out.println(imobi.getAtual().pesquisaPorQuartos(m));
                    System.out.println("\nENTER para continuar");Input.lerString();
                    System.out.println("\n");
                break;
                case 6:
                    limparTexto();
                    System.out.println("- Insira o nº mánimo de WC's:");
                    m = Input.lerInt();
                    limparTexto();
                    System.out.println("RESULTADOS:\n");
                    System.out.println(imobi.getAtual().pesquisaPorWC(m));
                    System.out.println("\nENTER para continuar");Input.lerString();
                    System.out.println("\n");
                break;
                case 7:
                    limparTexto();
                    System.out.println("RESULTADOS:\n");
                    System.out.println(imobi.getAtual().pesquisaPorHabitavel());
                    System.out.println("\nENTER para continuar");Input.lerString();
                    System.out.println("\n");
                break;
                case 8:
                    limparTexto();
                    System.out.println("- Insira o preço mínimo:");
                    m = Input.lerInt();
                    limparTexto();
                    System.out.println("RESULTADOS:\n");
                    System.out.println(imobi.getAtual().pesquisaPorHabitavelPreco(m));
                    System.out.println("\nENTER para continuar");Input.lerString();
                
                break;
                case 0:
                    limparTexto();
                    acabado = 1;
                    setAviso("Saiu da pesquisa");
                break;
                default:
                    limparTexto();
                    setAviso("Opção errada");
                break;
            }        
        }
    }
    
    public static void leiloes(){
        int escolha,acabado=0, m; String n;      
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n= = =  L e i l õ e s  = = =\n\n");
        
        try{
           if (!imobi.getAtual().getRegistado()) throw new SemAutorizacaoException("Sem permissão");  
           else {
            setAviso("");
            
            while (acabado != 1){
                System.out.println("<i> : "+ getAviso()); 
                System.out.println("<i> : "+ getAviso2());
                menuLeiloes(); 
                escolha= Input.lerInt();
            
                switch (escolha){
                    case 1:
                         limparTexto();
                         if (imobi.getAtual() instanceof Vendedor){
                             //leiloes.iniciaLeilao (im,horas)
                            }
                         else setAviso("Tem de ser um vendedor para efetuar esta operação");
                         
                         break;
                    case 2:
                         limparTexto();
                         if (imobi.getAtual() instanceof Comprador){
                             //leiloes.adicionaComprador (idComprador ,double limite ,double incrementos ,double minutos )
                            }
                         else  setAviso("Tem de ser um comprador para efetuar esta operação");
                         
                         break;
                    case 3:
                        limparTexto();
                         if (imobi.getAtual() instanceof Vendedor){
                             /*
                             compradorAux= leiloes.encerraLeilao();
                             for (Utilizadores c : imobi.getUtilizadores().values()){
                                 if (c.getEmail().equals(compradorAux.getEmail()) && c instanceof Comprador){
                                     setAviso(c.getNome() + "ganhou o leilÃ£o");
                                     imobi.setFeed("O comprador " + compradorAux.getNome() + " ganhou um imÃ³vel num leilÃ£o " + "\n");
                                }
                             }
                             */
                            
                            }
                         else setAviso("Tem de ser um vendedor para efetuar esta operação");
                         
                         break;
                    //case 4:
                    case 0:
                        limparTexto();
                        acabado = 1;
                    break;
                    default:
                        limparTexto();
                        setAviso("Opção errada");
                    break;
                }
            }
           } 
        } catch (SemAutorizacaoException e){System.out.println(e.getMessage());setAviso ("NÃ£o tem permissÃ£o para aceder a esta opÃ§Ã£o");}
    }

    // _______________________________ AVISO _____________________________________________  
    
    public static String getAviso(){return aviso;}
    public static void setAviso(String aaviso) {aviso=aaviso;}
    public static String getAviso2(){return aviso2;}
    public static void setAviso2(String aaviso) {aviso2=aaviso;}
    public static void janelaFeed () throws Exception{setAviso("ConcluÃ­do");System.out.println(imobi.getFeed()+"\nENTER para continuar");Input.lerString();} 
    public static void limparTexto () {System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");}
    // _______________________________ TITULOS ___________________________________________
    
    public static void menuInicio(){
        System.out.println("           __                              ");
        System.out.println("   _______|_ |_____________________________");
        System.out.println("  /       |  |                              \\");
        System.out.println(" /        |  |                               \\");
        System.out.println("|         |__|                               |_________________");
        System.out.println("|____________________________________________|                  \\");
        System.out.println("|     ___    **Opções**                      |                   \\");
        System.out.println("|    |_|_|     1: Gestão de utilizadores     |____________________|");
        System.out.println("|    |_|_|     2: Pesquisar                  |                    |");
        System.out.println("|              3: Ver feed                   | Nº de Utilizadores:|");
        System.out.println("|              4: Menu de vendedor           |   -- "+imobi.getUtilizadores().size()+" --          |");
        System.out.println("|    _____     5: Menu de comprador          |                    |");
        System.out.println("|   |     |    6: Leilões                    | Nº de Imóveis:     |");
        System.out.println("|   |.    |    7: Mapeamento de imóveis      |   -- "+imobi.getImovel().size()+" --          |");
        System.out.println("|   |     |    0: Sair do programa           |                    |");
        System.out.println("|___|_____|__________________________________|____________________|");
        System.out.println("\n***Introduza a sua opcao: ");
    }
     
    public static void menuPesquisar(){
        System.out.println("   _______|_ |______________________________");
        System.out.println("  /       |  |                              \\");
        System.out.println(" /        |  |                               \\");
        System.out.println("/         |__|                                \\");
        System.out.println("+------------------- OpÃ§Ãµes ------------------+");
        System.out.println("| 1: Pesquisa por chave (morada)              |");
        System.out.println("| 2: Pesquisa por preço minimo                |");
        System.out.println("| 3: Pesquisa por preço máximo                |");
        System.out.println("| 4: Pesquisa por tipo imóvel                 |");
        System.out.println("| 5: Pesquisa por Nº de quartos               |");
        System.out.println("| 6: Pesquisa por Nº de WC's                  |");
        System.out.println("| 7: Pesquisa por imóveis habitáveis          |");
        System.out.println("| 8: Pesquisa por preço de imóveis habitáveis |");
        System.out.println("| 0: Sair da pesquisa                         |");
        System.out.println("+---------------------------------------------+\n***Introduza a sua opcao: ");
    }
    public static void menuUtilizadores(){
        System.out.println("   _______|_ |________________");
        System.out.println("  /       |  |                \\");
        System.out.println(" /        |  |                 \\");
        System.out.println("/         |__|                  \\");
        System.out.println("+----------   Opções -----------+");
        System.out.println("| 1: Criar utilizador           |");
        System.out.println("| 2: Iniciar sessão             |");
        System.out.println("| 3: Listar utilizadores        |");
        System.out.println("| 4: Terminar sessão            |");
        System.out.println("| 0: Sair dos utilizadores      |");
        System.out.println("+-------------------------------+\n***Introduza a sua opcao:     ");
    }
    public static void menuVendedor(){
        System.out.println("   _______|_ |__________________________");
        System.out.println("  /       |  |                          \\");
        System.out.println(" /        |  |                           \\");
        System.out.println("/         |__|                            \\");
        System.out.println("+---------- Opções -----------------------+");
        System.out.println("| 1: Pesquisar imóvel                     |");
        System.out.println("| 2: Criar imóvel                         |");
        System.out.println("| 3: Remover imóvel                       |");
        System.out.println("| 4: Listar imóveis                       |");
        System.out.println("| 5: Listar portfólio de imóveis em venda |");
        System.out.println("| 6: Listar histórico de imóveis vendidos |");
        System.out.println("| 7: Alterar estado de imóvel             |");
        System.out.println("| 0: Sair                                 |");
        System.out.println("+-----------------------------------------+\n***Introduza a sua opcao: ");
    }
    public static void menuComprador(){
        System.out.println("   _______|_ |________________");
        System.out.println("  /       |  |                \\");
        System.out.println(" /        |  |                 \\");
        System.out.println("/         |__|                  \\");
        System.out.println("+---------- Opções -------------+");
        System.out.println("| 1: Pesquisar imóvel           |");
        System.out.println("| 2: Listar imóveis             |");
        System.out.println("| 3: Comprar Imóvel             |");
        System.out.println("| 4: Adicionar como favorito    |");
        System.out.println("| 5: Listar favoritos           |");
        System.out.println("| 6: Remover dos favoritos      |");
        System.out.println("| 0: Sair                       |");
        System.out.println("+-------------------------------+\n***Introduza a sua opcao: ");
    }
    public static void menuLeiloes(){
        System.out.println("   _______|_ |________________");
        System.out.println("  /       |  |                \\");
        System.out.println(" /        |  |                 \\");
        System.out.println("/         |__|                  \\");
        System.out.println("+---------- Opções -------------+");
        System.out.println("| 1: Iniciar um leilÃ£o          |");
        System.out.println("| 2: Adicionar comprador        |");
        System.out.println("| 3: Encerrar um leilão         |");
        System.out.println("|                               |");
        System.out.println("| 0: Sair dos leilões           |");
        System.out.println("+-------------------------------+\n***Introduza a sua opcao: ");
    }

    
    public static void listarUtilizador (){
       System.out.println("\n** Utilizadores existentes no Sistema **\n");
       System.out.println("\n-- VENDEDORES: --\n");
       imobi.listarVendedores();
       System.out.println("\n-- COMPRADORES: --\n");
       imobi.listarCompradores();
    }

    public static void insereImovel () throws EstadoInvalidoException,TipoInvalidoException,ImovelExisteException{
        boolean erro;
        String estado;
        String estado2;
        String morada;
        String morada2;
        String negocio;
        String apropriado;
        double precoPedido;
        double precoPedido2;
        double precoMinimo;
        double precoMinimo2;
        String tipo;
        double areaImplantacao;
        double areaTotal;
        double areaTerreno;
        int numeroQuartos;
        int numeroWC;
        int numeroPorta;
        int numeroAndar;
        double diametroCanal;
        double redeElectrica;
        boolean garagem;
        boolean esgotos;
        boolean wc;
        double area;
        
        try{
                System.out.println("Que tipo de imóvel será (Moradia / Terreno / Loja / Apartamento/ Loja Habitavel): ");
                String escolha = Input.lerString();
                if(escolha.equals("Moradia")){
                    System.out.println("- Estado da moradia (Em venda / Reservado / Vendido): ");
                    estado = Input.lerString();
                    if (!estado.equals("Em venda")&&!estado.equals("Reservado")&&!estado.equals("Vendido")) {throw new EstadoInvalidoException("");}
                    System.out.println("- Morada: ");
                    morada = Input.lerString();
                    System.out.println("- Preço Pedidoc: ");
                    precoPedido= Input.lerDouble();
                    System.out.println("- Preço Minimo(Euros): ");
                    precoMinimo= Input.lerDouble();
                    System.out.println("- Tipo(Isolada,Germinada,Banda,Gaveto): ");
                    tipo = Input.lerString();
                    if (!tipo.equals("Isolada")&&!tipo.equals("Germinada")&&!tipo.equals("Banda")&&!tipo.equals("Gaveto")) {throw new TipoInvalidoException("");}
                    
                    System.out.println("- Area Implantacao(metros quadrados): ");
                    areaImplantacao = Input.lerDouble();
                    System.out.println("- Area Total(metros quadrados): ");
                    areaTotal =Input.lerDouble();
                    System.out.println("- Area Terreno(metros quadrados): ");
                    areaTerreno = Input.lerDouble();
                    System.out.println("- Numero Quartos: ");
                    numeroQuartos = Input.lerInt();
                    System.out.println("- Numero WC's: ");
                    numeroWC = Input.lerInt();
                    System.out.println("- Numero de Porta: ");
                    numeroPorta = Input.lerInt();
                    Moradia a = new Moradia (estado,morada,precoPedido,precoMinimo,tipo,areaImplantacao,areaTotal,areaTerreno,numeroQuartos,numeroWC,numeroPorta);
                    erro= imobi.addImovel(a);

                    imobi.setFeed(imobi.getAtual().getNome() + " inseriu nos anúncios o imóvel " + escolha + " situado na seguinte morada: " + a.getMorada() + "\n");
                    
                }
                else
                    if(escolha.equals("Apartamento")){
                       System.out.println("- Estado do apartamento (Em venda / Reservado / Vendido): ");
                       estado = Input.lerString();
                       if (!estado.equals("Em venda")&&!estado.equals("Reservado")&&!estado.equals("Vendido")) {throw new EstadoInvalidoException("");};
                       System.out.println("- Morada: ");
                       morada = Input.lerString();
                       System.out.println("- Preço Pedido(Euros): ");
                       precoPedido= Input.lerDouble();
                       System.out.println("- Preço Minimo(Euros): ");
                       precoMinimo= Input.lerDouble();
                       System.out.println("- Tipo(Simples, Duplex, Triplex): ");
                       tipo = Input.lerString();
                       if (!tipo.equals("Simples")&&!tipo.equals("Duplex")&&!tipo.equals("Triplex")) {throw new TipoInvalidoException("");}
                    
                       System.out.println("- Area Total(metros quadrados): ");
                       areaTotal =Input.lerDouble();
                       System.out.println("- Numero Quartos: ");
                       numeroQuartos = Input.lerInt();
                       System.out.println("- Numero WC's: ");
                       numeroWC = Input.lerInt();
                       System.out.println("- Numero de Porta: ");
                       numeroPorta = Input.lerInt();
                       System.out.println("- Numero do Andar: ");
                       numeroAndar = Input.lerInt();
                       System.out.println("- Com Garagem: ");
                       garagem = Input.lerBoolean();
                       Apartamento b = new Apartamento (estado,morada,precoPedido,precoMinimo,tipo,areaTotal,numeroQuartos,numeroWC,numeroPorta,numeroAndar,garagem);
                       erro= imobi.addImovel(b);
                       imobi.setFeed(imobi.getAtual().getNome() + " inseriu nos anúncios o imóvel " +escolha+ " situado na seguinte morada: " + b.getMorada() + "\n");
                       
                   }
                   else
                    if(escolha.equals("Terreno")){
                       System.out.println("- Estado do terreno (Em venda / Reservado / Vendido): ");
                       estado = Input.lerString();
                       if (!estado.equals("Em venda")&&!estado.equals("Reservado")&&!estado.equals("Vendido")) {throw new EstadoInvalidoException("");}
                       System.out.println("- Morada: ");
                       morada = Input.lerString();
                       System.out.println("- Preço Pedido(Euros): ");
                       precoPedido= Input.lerDouble();
                       System.out.println("- Preço Minimo(Euros): ");
                       precoMinimo= Input.lerDouble();
                       System.out.println("- Apropriado para(Construção/Habitação): ");
                       apropriado = Input.lerString();
                       System.out.println("- Diametro das Canalizações(metros) : ");
                       diametroCanal =Input.lerDouble();
                       System.out.println("- kWh máximos suportados pela Rede Elétrica: ");
                       redeElectrica = Input.lerDouble();
                       System.out.println("- Tem Rede de Esgotos: ");
                       esgotos = Input.lerBoolean();
                       Terreno c = new Terreno (estado,morada,precoPedido,precoMinimo,apropriado,diametroCanal,redeElectrica,esgotos);
                       erro= imobi.addImovel(c);
                       imobi.setFeed(imobi.getAtual().getNome() + " inseriu nos anúncios o imóvel " + escolha + " situado na seguinte morada: " + c.getMorada() + "\n");
                       
                   }
                   else
                    if(escolha.equals("Loja")){
                       System.out.println("- Estado da loja (Em venda / Reservado / Vendido): ");
                       estado = Input.lerString();
                       if (!estado.equals("Em venda")&&!estado.equals("Reservado")&&!estado.equals("Vendido")) {throw new EstadoInvalidoException("");}
                       System.out.println("- Morada: ");
                       morada = Input.lerString();
                       System.out.println("- Preço Pedido(Euros): ");
                       precoPedido= Input.lerDouble();
                       System.out.println("- Preço Minimo(Euros): ");
                       precoMinimo= Input.lerDouble();
                       System.out.println("- Loja viável para o negócio: ");
                       negocio = Input.lerString();
                       System.out.println("- Area(metros quadrados): ");
                       area =Input.lerDouble();
                       System.out.println("- Tem wc: ");
                       wc = Input.lerBoolean();
                       System.out.println("- Numero da Porta: ");
                       numeroPorta = Input.lerInt();
                       Loja d = new Loja (estado,morada,precoPedido,precoMinimo,negocio,area,wc,numeroPorta);
                       erro= imobi.addImovel(d);
                       imobi.setFeed(imobi.getAtual().getNome() + " inseriu nos anúncios o imóvel " + escolha + " situado na seguinte morada: " + d.getMorada() + "\n");
                       
                   }
                   else
                    if(escolha.equals("Loja Habitavel")){
                       System.out.println("** Informação sobre a Loja **");
                       System.out.println("- Estado da loja (Em venda / Reservado / Vendido): ");
                       estado = Input.lerString();
                       if (!estado.equals("Em venda")&&!estado.equals("Reservado")&&!estado.equals("Vendido")) {throw new EstadoInvalidoException("");}
                       System.out.println("- Morada: ");
                       morada = Input.lerString();
                       System.out.println("- Preço Pedido(Euros): ");
                       precoPedido= Input.lerDouble();
                       System.out.println("- Preço Minimo(Euros): ");
                       precoMinimo= Input.lerDouble();
                       System.out.println("- Loja viável para o negócio: ");
                       negocio = Input.lerString();
                       System.out.println("- Area(metros quadrados): ");
                       area =Input.lerDouble();
                       System.out.println("- Tem wc: ");
                       wc = Input.lerBoolean();
                       System.out.println("- Numero da Porta: ");
                       numeroPorta = Input.lerInt();
                       System.out.println("** Informação sobre o Apartamento **");
                       System.out.println("- Estado do apartamento (Em venda / Reservado / Vendido): ");
                       estado2 = Input.lerString();
                       if (!estado2.equals("Em venda")&&!estado2.equals("Reservado")&&!estado2.equals("Vendido")) {throw new EstadoInvalidoException("");}
                       System.out.println("- Morada: ");
                       morada2 = Input.lerString();
                       System.out.println("- Preço Pedido(Euros): ");
                       precoPedido2= Input.lerDouble();
                       System.out.println("- Preço Minimo(Euros): ");
                       precoMinimo2= Input.lerDouble();
                       System.out.println("- Tipo(Simples, Duplex, Triplex): ");
                       tipo = Input.lerString();
                       if (!tipo.equals("Simples")&&!tipo.equals("Duplex")&&!tipo.equals("Triplex")) {throw new TipoInvalidoException("");}
                       System.out.println("- Area Total(metros quadrados): ");
                       areaTotal =Input.lerDouble();
                       System.out.println("- Numero Quartos: ");
                       numeroQuartos = Input.lerInt();
                       System.out.println("- Numero WC's: ");
                       numeroWC = Input.lerInt();
                       System.out.println("- Numero de Porta: ");
                       numeroPorta = Input.lerInt();
                       System.out.println("- Numero do Andar: ");
                       numeroAndar = Input.lerInt();
                       System.out.println("- Com Garagem: ");
                       garagem = Input.lerBoolean();
                       Apartamento f = new Apartamento (estado,morada,precoPedido,precoMinimo,tipo,areaTotal,numeroQuartos,numeroWC,numeroPorta,numeroAndar,garagem);
                       LojaHabitavel d = new LojaHabitavel (estado,morada,precoPedido,precoMinimo,negocio,area,wc,numeroPorta,f);
                       erro= imobi.addImovel(d);
                       imobi.setFeed(imobi.getAtual().getNome() + " inseriu nos anúncios o imóvel " + escolha + " situado na seguinte morada: " + d.getMorada() + "\n");
                       
                }else {throw new TipoInvalidoException("");}
            if (erro) setAviso("Imóvel já existe!");
            else setAviso("Imóvel adicionado");
                

        }
        catch (TipoInvalidoException e) {setAviso ("Tipo de imóvel inválido");}
        catch (EstadoInvalidoException e) {setAviso ("Estado do imóvel inválido");}
        
    }
    
    public static void listarImoveisP (){
        System.out.println(" ** Portfolio de imóveis em venda ** \n\n");
        vendedorAux = (Vendedor) imobi.getAtual();
        System.out.println(vendedorAux.getImoveisP().toString());
        setAviso ("Imóveis listados");
    }
    public static void listarImoveisV (){ 
        System.out.println(" ** Histórico de imóveis vendidos ** \n\n");
        vendedorAux = (Vendedor) imobi.getAtual();
        System.out.println(vendedorAux.getImoveisV().toString());
        setAviso ("Imóveis listados");
    }
    
    public static void mapeamentoImoveis(){
        for (Vendedor v: imobi.getMapeamentoImoveis().values()){
             System.out.println("-- Vendedor " +v.getNome() + " com o user name "+v.getEmail()+ " tem em venda os Imóveis:" + imobi.getMapeamentoImoveis().keySet());
        }
    }
    
    public static int sair() throws Exception{
        int acabado = 0;
        String c;
        System.out.println("<i> Tem a certeza que pretende sair? (s/n)"); 
        setAviso("Concluído");
        c=Input.lerString(); 
        if(c.equals("s")||c.equals("S")){acabado = 1;imobi.gravaDados("Imobiliaria.obj");}
        return acabado;
    }
}
