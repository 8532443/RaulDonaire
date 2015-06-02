package biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class Interface {

    //Função responsável por criar o men inicial, apresentando todas as funções 
    //que o programa executa assim como os codigo dessas operações para que assim 
    //o usuario possa escolher entre elas. O usuario insere o codigo da operação 
    //que é retornado para a função principal para que esta a execute    
    public int Menu() {
        Scanner leitor = new Scanner(System.in);
        int op;

        System.out.println("-------------------------------------------------");
        System.out.println("                Library 2.0");
        System.out.println("Powered by: Leonardo Zanguetin - 8531866");
        System.out.println("            Raul Donaire - 8532443");
        System.out.println("-------------------------------------------------\n");

        System.out.println("1 - Carregar Arquivo");
        System.out.println("2 - Salvar Arquivo");
        System.out.println("3 - Incluir Novo Usuário");
        System.out.println("4 - Incluir Novo Livro");
        System.out.println("5 - Registrar Empréstimo");
        System.out.println("6 - Registrar Devolução");
        System.out.println("7 - Listar Livros do Acervo");
        System.out.println("8 - Listar Usuários");
        System.out.println("9 - Listar Empréstimos Correntes");
        System.out.println("10 - Consulta de Suspensão");
        System.out.println("11 - Sair\n");

        System.out.println("-------------------------------------------------");
        System.out.println("Insira a operação desejada:");
        op = leitor.nextInt();
        System.out.println("-------------------------------------------------");

        return op;
    }

    //O usuario insere um nome e um ID a serem registrados e com estas 
    //informações um novo cadastro de cliente é criado, sendo ao fim este novo 
    //cadastro retornado pela função.
    public Usuario AddUsuario() {

        Usuario AuxUsuario;

        Scanner leitor = new Scanner(System.in);
        String user;
        int ID;

        System.out.println("Insira o nome do usuario a ser registrado:");
        user = leitor.nextLine();

        System.out.println("-------------------------------------------------");

        System.out.println("Insira o ID do usuario a ser registrado:");
        ID = leitor.nextInt();

        System.out.println("-------------------------------------------------");

        AuxUsuario = new Usuario(ID, user, false);

        return AuxUsuario;
    }

    //O usuario insere um nome e um ID a serem registrados e com estas 
    //informações um novo cadastro de livro é criado na biblioteca, sendo ao 
    //fim este novo cadastro retornado pela função. 
    public Livro AddLivro() {

        Livro AuxLivro;

        Scanner leitor = new Scanner(System.in);
        String Book;
        int ID;
        boolean Free = false;
        int Taken = 0;

        System.out.println("Insira o nome do livro a ser registrado:");
        Book = leitor.nextLine();

        System.out.println("-------------------------------------------------");

        System.out.println("Insira o ID do livro a ser registrado:");
        ID = leitor.nextInt();

        System.out.println("-------------------------------------------------");

        AuxLivro = new Livro(ID, Book, Free, Taken);

        return AuxLivro;
    }

    //O usuario insere o ID do livro que será emprestado, o ID do cliente que 
    //esta realizando o emprestimo e o ID que identifica o emprestimo do livro. 
    //Com estas informações um novo emprestimo de livro é criado, sendo ao fim 
    //este emprestimo retornado pela função. 
    public Emprestimo AddEmprestimo(int data) {

        Emprestimo AuxEmprestimo;

        Scanner leitor = new Scanner(System.in);
        int IDBook;
        int IDUser;
        int IDBorrow;
        int DT;

        String Livro, Usuario;

        System.out.println("Insira o ID do livro a ser emprestado:");
        IDBook = leitor.nextInt();

        System.out.println("-------------------------------------------------");

        System.out.println("Insira o Nome do livro a ser emprestado:");
        leitor.nextLine();
        Livro = leitor.nextLine();

        System.out.println("-------------------------------------------------");

        System.out.println("Insira o ID do usuário que está emprestando:");
        IDUser = leitor.nextInt();

        System.out.println("-------------------------------------------------");

        System.out.println("Insira o Nome do usuário que está emprestando:");
        leitor.nextLine();
        Usuario = leitor.nextLine();

        System.out.println("-------------------------------------------------");

        System.out.println("Insira o ID do empréstimo:");
        IDBorrow = leitor.nextInt();

        System.out.println("-------------------------------------------------");

        DT = data;

        AuxEmprestimo = new Emprestimo(IDBorrow, IDBook, IDUser, DT, Livro, Usuario, -1);

        return AuxEmprestimo;
    }

    //Função responsavel por receber do usuário a data em que o emprestimo é 
    //efetivado, e com estas informações convertmos os valores no formato em 
    //que estamos trabalhando com a data
    public int Date() {
        Scanner data = new Scanner(System.in);

        int Day;
        int Month;
        int Final;

        System.out.println("Insira o Mês atual:");
        Month = data.nextInt();

        Final = Month * 100;

        System.out.println("Insira o Dia atual:");
        Day = data.nextInt();

        Final = Final + Day;

        return Final;
    }

    //Quando chamada imprime na tela todos os livros registrados no acervo, 
    //quando terminada a impressão o sistema espera que o usuario aperte 
    //qualquer tecla para retornar ao menu inicia
    public void PrintBook(ArrayList<Livro> livros) {
        for (Livro livro : livros) {
            System.out.println(livro.getNome());
        }

        System.out.println("Pressione uma tecla para continuar...");
        new java.util.Scanner(System.in).nextLine();
    }

    //Quando chamada imprime na tela todos os usuarios registrados na biblioteca, 
    //quando terminada a impressão o sistema espera que o usuario aperte 
    //qualquer tecla para retornar ao menu inicia
    public void PrintUser(ArrayList<Usuario> users) {
        for (Usuario user : users) {
            System.out.println(user.getNome());
        }
        System.out.println("Pressione uma tecla para continuar...");
        new java.util.Scanner(System.in).nextLine();
    }

     //Quando chamada imprime na tela todos os emprestimos corrente na biblioteca, 
    //quando terminada a impressão o sistema espera que o usuario aperte 
    //qualquer tecla para retornar ao menu inicia
    public void PrintBorrow(ArrayList<Emprestimo> emprestimos) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getDataDevolução() <= emprestimo.getData()) {
                System.out.println("O livro " + emprestimo.getNomeLivro() + " esta emprestado para " + emprestimo.getNomeUsuario());
            }
        }
        System.out.println("Pressione uma tecla para continuar...");
        new java.util.Scanner(System.in).nextLine();
    }

    //Pede ao usuario o nome do registro o qual deseja-se saber se esta suspenso
    public String ConsultaSuspensão() {
        String Nome;
        Scanner leitor = new Scanner(System.in);
        System.out.println("Insira o Nome do Usuario a ser consultado:");

        Nome = leitor.nextLine();

        return Nome;

    }

    //Pede ao usuario o nome do livro a ser devolvido
    public String devolveLivro() {
        String Nome;
        Scanner leitor = new Scanner(System.in);
        System.out.println("Insira o Nome do Livro a ser devolvido:");

        Nome = leitor.nextLine();

        return Nome;
    }
}
