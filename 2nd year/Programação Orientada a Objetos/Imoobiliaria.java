
import java.io.IOException;
import java.util.Scanner;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.Collections;
import java.util.Iterator;

public class Imoobiliaria {
    
    private static Map<Imovel, Actores> imoveisutilizador;
    private static Map<Imovel, String> imoveisestado;
    private static Map<Imovel, String> imoveisid;
    private static Map<Integer, Imovel> imoveisconsulta;
    private static Set<Imovel> favoritos;
    private static Set<Actores> utilizadores;
    private static Actores current;
    
    // funções requeridas
    public static Imoobiliaria initApp() {
            imoveisutilizador = new TreeMap<Imovel, Actores>();
            imoveisestado = new TreeMap<Imovel, String>();
            imoveisid = new TreeMap<Imovel, String>();
            imoveisconsulta =  new TreeMap<Integer, Imovel>(Collections.reverseOrder());
            favoritos = new TreeMap<Imovel>();
            utilizadores = new TreeSet<Actores>();
            main();
    }
    
    public void iniciaSessao(String email, String password) throws SemAutorizacaoException{
           Iterator<Actores> it = new utilizadores.iterator();
           boolean found = false;
           try{
               while(it.hasNext() && !found){
                   if(it.getEmail() == email){
                       if(it.getPassword() == password){
                           current = this;
                           found = true;
                       }
                       else throw new SemAutorizacaoException();
                   }
                   it.next();
               }
               if(found == true) out.println("Bem-vindo, " + current.getNome() + "!");
               else out.println("Utilizador não encontrado. Registe um novo utilizador.");
           }
           catch(SemAutorizacaoException e){
               out.println("Palavra-passe errada! Não foi concedido o acesso à conta de " + email + ". " + e.getMessage());
           }
    }
    
    public void fechaSessao(){
            current = new Actores();
            //guardar favoritos com o user
    }
    
    public void registaImovel(Imovel im) throws ImovelExisteException, SemAutorizacaoException{
            try{
                imoveisutilizador.add(im, current);
                imoveisestado.add(im, "");
                imoveisconsulta.add(im, 0);
            }
            catch(ImovelExisteException e){
                out.println("O imóvel " + im + " que tentou registar já existe! " + e.getMessage());
            }
            catch(SemAutorizacaoException e){
                out.println("Não tem autorização para realizar esta operação! " + e.getMessage());
            }
    }
    
    /* public List<Consulta> getConsultas() throws SemAutorizacaoException{
    *        System.out.println("Opção ainda não implementada.");
    *}
    */
    
    public void setEstado(String idImovel, String estado) throws ImovelInexistenteException, SemAutorizacaoException, EstadoInvalidoException{
            try{
                //idimovel wtf
            }
            catch(ImovelInexistenteException e){
                out.println("O imóvel " + idImovel + "não existe na base de dados! " + e.getMessage());
            }
            catch(SemAutorizacaoException e){
                out.println("Não tem autorização para realizar esta operação! " + e.getMessage());
            }
            catch(EstadoInvalidoException e){
                out.println("O estado " + estado + " é inválido! " + e.getMessage());
            }        
    }
    
    public Set<String> getTopImoveis (int n){
            Set<String> maisvistos = new TreeSet<String>();
            Iterator<Int, Imoveis> it = new imoveisutilizador.iterator();
            
            while(n>0 && it.hasNext()){
                maisvistos.add(it.next());
                n--;
            }
            return maisvistos;    
    }
    
    public List<Imovel> getImovel(String classe, int preco){
            System.out.println("Opção ainda não implementada.");
    }
    
    /*public List<Habitavel> getHabitaveis(int preco){
    *        System.out.println("Opção ainda não implementada.");
    *}
    */
   
    public Map<Imovel,Vendedor> getMapeamentoImoveis(){
            System.out.println("Opção ainda não implementada.");
    }
    
    public void setFavorito(String idImovel) throws ImovelInexistenteException, SemAutorizacaoException{
            
            try{
                if(imoveisid.values().contains(idImovel)){
                    Iterator<Imovel, String> it = new imoveisid.iterator();
                    Imovel imovel;
                    boolean found = false;
                    while(!found){
                    }
                }
            }
            //need to re-do this
    }
    
    public TreeSet<Imovel> getFavoritos() throws SemAutorizacaoException{
            try{
                return this.favoritos;
            }
            catch(SemAutorizacaoException e){
                out.println("Não tem autorização para realizar esta operação! " + e.getMessage());
            }
    }

    // Método principal
    public static void main() {
        carregarMenus();
        carregarDados();
        int opcao = 0;
        menuprelogin.executa();
        switch (menuprelogin.getOpcao()) {
            case 1: menulogin.executa();
                    opcao = 1;
                    break;
            case 0: opcao = 0;
                    break;
        }
        try {
            tab.gravaObj("estado.tabemp");
            tab.log("log.txt", true);
        }
        catch (IOException e) {
            System.out.println("Não consegui gravar os dados!");
        }
        System.out.println("Até breve!...");     
    }
    
    // Métodos para menus
    
    private static void carregarMenus() {
        String[] prelogin = {"Iniciar sessão",
                             "Sair"
                            };
                            
        String [] login = {"Utilizador existente",
                           "Novo utilizador",
                           "Voltar atrás"
                           };
                           
        String [] register = {"Comprador",
                              "Vendedor",
                              "Voltar atrás"
                            };
                          
        String [] poslogin = {""
                             };

        menuprelogin = new Menu(prelogin);
        menulogin = new Menu(login);
        menuregister = new Menu(register);
        menuposlogin = new Menu(poslogin);
    }
    
    private static void carregarcodDados() {
        try {
            tab = EmpresaPOO.leObj("estado.tabemp");
        }
        catch (IOException e) {
            tab = new EmpresaPOO();
            System.out.println("Erro de leitura.");
        } 
        catch (ClassNotFoundException e) {
            tab = new EmpresaPOO();
            System.out.println("Ficheiro com formato desconhecido.");
        }
        catch (ClassCastException e) {
            tab = new EmpresaPOO();
            System.out.println("Erro de formato.");        
        }
    }
    
    private static void promptRegister() {
        Scanner scin = new Scanner(System.in);
        String email, nome, password, morada, datadenascimento;
        
        System.out.print("Email: ");
        email = scin.nextLine();
        System.out.print("Nome: ");
        nome = scin.nextLine();
        System.out.print("Password: ");
        password = scin.nextLine();
        System.out.print("Morada: ");
        morada = scin.nextLine();
        System.out.print("Data de nascimento: ");
        datadenascimento = scin.nextLine();
        
        switch (menuregister.getOpcao()) {
            case 1: Comprador novo = new Comprador(email, nome, password, morada, datadenascimento);
                    break;
            case 2: Vendedor novo =  new Vendedor(email, nome, password, morada, datadenascimento);
                    break;
            default: menu.loginexecuta();
                     break;
        }
        tab.registarUtilizador(novo);
        scin.close();
    }
    
    public void registarUtilizador(Actores actores) throws UtilizadorExistenteException{
            try{
                if(!imoveisutilizador.containsValue(actores))imoveisutilizador.add("",actores);
            }
            catch(UtilizadorExistenteException e){
                out.println("O utilizador " + actores.getNome() + " já existe na base de dados! " + e.getMessage());
            }
    }
    
    private static void consultarEmp() {
        String cod;
        Empregado emp;
        Scanner scin = new Scanner(System.in);
        
        System.out.print("Código: ");
        cod = scin.nextLine();
        try {
            emp = tab.getEmpregado(cod);
            System.out.println(emp.toString());
        }
        catch (EmpregadoException e){
            if (e.getCod()==1)
                System.out.println(e.getMessage());
            else
                System.out.println("Erro desconhecido!");
        }
        finally {
            scin.close();
        }
    }
}


