package biblioteca;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

//Classe responsável por fazer toda a manipulação de dados da biblioteca
//É o centro do programa, delega funções às outras classes para gerenciar
//os registros
public class Shell {

    private Interface I;
    private FILEReader F;
    private Livro AUXLivro;
    private Usuario AUXUsuario;
    private Emprestimo AUXEmprestimo;
    private ArrayList<Livro> Livros;
    private ArrayList<Usuario> Usuarios;
    private ArrayList<Emprestimo> Emprestimos;
    private int Date;
    private CalculadoraData calculadoraData;

    public Shell() throws IOException {
        this.I = new Interface();
        try {
            this.F = new FILEReader();
        } catch (FileNotFoundException ex) {
            System.out.println("ERRO: Arquivos não encontrados!");
        }
        this.Livros = new ArrayList();
        this.Usuarios = new ArrayList();
        this.Emprestimos = new ArrayList();
        this.calculadoraData = new CalculadoraData();
    }

    //Roda o menu até que o usuario deseje fechar o programa
    public void run() throws IOException {
        int aux = 1;

        this.Date = this.I.Date();

        while (aux != 0) {
            aux = this.I.Menu();
            switch (aux) {
                case 1:
                    CarregaArquivos();
                    break;
                case 2:
                    SalvaArquivos();
                    break;
                case 3:
                    AdicionaUsuario();
                    break;
                case 4:
                    AdicionaLivro();
                    break;
                case 5:
                    AdicionaEmprestimo();
                    break;
                case 6:
                    FechaEmprestimo();
                    break;
                case 7:
                    ListaLivros();
                    break;
                case 8:
                    ListaUsuarios();
                    break;
                case 9:
                    ListaEmprestimos();
                    break;
                case 10:
                    ConsultaSuspensão();
                    break;
                case 11:
                    aux = 0;
                    break;
                default:
                    System.out.println("ERRO: Este não é uma opção válida!!");
            }
        }
    }

    //Carrega na memoria todos os dados dos arquivos
    private void CarregaArquivos() {

        this.Usuarios = this.F.CarregaUsuario();

        this.Livros = this.F.CarregaLivro();

        this.Emprestimos = this.F.CarregaEmprestimo();

    }

    //Salva todos os dados da memoria nos arquivos.
    //Cada tipo de registro (Livro, Usuario, Emprestimo) possui um arquivo
    //Individual
    private void SalvaArquivos() throws IOException {
        int aux;

        this.F.Open();

        for (aux = 0; aux < this.Usuarios.size(); aux++) {
            this.F.SalvaUsuario(Usuarios.get(aux));
        }

        for (aux = 0; aux < this.Livros.size(); aux++) {
            this.F.SalvaLivro(Livros.get(aux));
        }

        for (aux = 0; aux < this.Emprestimos.size(); aux++) {
            this.F.SalvaEmprestimo(Emprestimos.get(aux));
        }

        this.F.Close();
    }

    //Adiciona um usuario à memoria pelo terminal.
    private void AdicionaUsuario() {
        this.AUXUsuario = this.I.AddUsuario();

        this.Usuarios.add(this.AUXUsuario);
        System.out.println("O usuario: " + this.AUXUsuario.getNome() + " foi adicionado!");

    }

    //Adiciona um livro à memoria pelo terminal.
    private void AdicionaLivro() {
        this.AUXLivro = this.I.AddLivro();

        this.Livros.add(this.AUXLivro);
        System.out.println("O livro: " + this.AUXLivro.getNome() + " foi adicionado!");

    }

    //Adiciona um emprestimo à memoria pelo terminal
    //Caso o usuario tenha mais que 4 emprestimos ou esteja suspenso o emprestimo
    //nao pode ser executado
    private void AdicionaEmprestimo() {
        this.AUXEmprestimo = this.I.AddEmprestimo(this.Date);

        boolean podeEmprestar = this.Usuarios.get(SearchUser(this.AUXEmprestimo.getUsuarioID())).emprestarLivro(this.AUXEmprestimo.getID());

        if (podeEmprestar) {
            this.Emprestimos.add(this.AUXEmprestimo);

            this.Livros.get(SearchBook(this.AUXEmprestimo.getLivroID())).Emprestar(this.AUXEmprestimo.getID());

            System.out.println("O emprestimo: " + this.AUXEmprestimo.getID() + " foi adicionado!");
        } else {
            System.out.println("O emprestimo: " + this.AUXEmprestimo.getID() + " não pode ser feito pois o Usuario esta suspenso ou atingiu o máximo de emprestimos!");
        }
    }

    //Fecha o emprestimo, desvinculando-o do usuario e do livro, este que é
    //retornado ao acervo
    private void FechaEmprestimo() {
        String Livro = this.I.devolveLivro();
        int auxLivro = SearchLivro(Livro);
        System.out.println("Entrei aqui1 ");
        int auxEmprestimo = SearchBorrow(this.Livros.get(auxLivro).getID_Emprestimo());
        System.out.println("Entrei aqui2" + this.Livros.get(auxLivro).getID_Emprestimo());
        boolean atraso;

        this.Emprestimos.get(auxEmprestimo).setDataDevolução(this.Date);
        System.out.println("Entrei aqui3");
        atraso = this.calculadoraData.CalculaAtraso(this.Emprestimos.get(auxEmprestimo).getData(), this.Emprestimos.get(auxEmprestimo).getDataDevolução());
        System.out.println("Entrei aqui4");
        int auxUsuario = SearchUser(this.Emprestimos.get(auxEmprestimo).getUsuarioID());
        System.out.println("Entrei aqui5");
        this.Usuarios.get(auxUsuario).devolverLivro(this.Emprestimos.get(auxEmprestimo).getID());
        System.out.println("Entrei aqui6");
        this.Livros.get(auxLivro).Devolver();

        if (atraso) {
            this.Usuarios.get(auxUsuario).setSuspenso();
        }

    }

    //Retorna a posicao do lviro na lista a partir de seu ID
    private int SearchBook(int LivroID) {

        for (int i = 0; i < this.Livros.size(); i++) {
            if (this.Livros.get(i).getID() == LivroID) {
                return i;
            }
        }

        return -1;
    }

    //Retorna a posicao de um usuario na lista a partir de seu ID
    private int SearchUser(int UsuarioID) {

        for (int i = 0; i < this.Usuarios.size(); i++) {
            if (this.Usuarios.get(i).getID() == UsuarioID) {
                return i;
            }
        }

        return -1;
    }

    //Retorna a posicao de um emprestimo a lista a partir de seu ID
    private int SearchBorrow(int EmprestimoID) {

        for (int i = 0; i < this.Emprestimos.size(); i++) {
            if (this.Emprestimos.get(i).getID() == EmprestimoID) {
                return i;
            }
        }

        return -1;
    }

    //Retorna a posicao de um usuario na lista a partir de seu nome
    private int SearchUsuario(String Nome) {

        for (int i = 0; i < this.Usuarios.size(); i++) {
            if (this.Usuarios.get(i).getNome().equals(Nome)) {
                return i;
            }
        }

        return -1;
    }

    //Retorna a posicao de um livro na lista a partir de seu nome
    private int SearchLivro(String Nome) {

        for (int i = 0; i < this.Livros.size(); i++) {
            if (this.Livros.get(i).getNome().equals(Nome)) {
                return i;
            }
        }

        return -1;
    }

    //Pede para função interface printar na tela todos os usuarios
    private void ListaUsuarios() {
        this.I.PrintUser(this.Usuarios);
    }

    //Pede para função interface printar na tela todos os livros
    private void ListaLivros() {
        this.I.PrintBook(this.Livros);
    }

    //Pede para função interface printar na tela todos os emprestimos
    private void ListaEmprestimos() {
        this.I.PrintBorrow(this.Emprestimos);
    }

    //Consulta se um usuario está suspenso
    private void ConsultaSuspensão() {
        String Nome = this.I.ConsultaSuspensão();

        if (this.Usuarios.get(SearchUsuario(Nome)).getSuspenso()) {
            System.out.println("O usuario esta suspenso!");
        } else {
            System.out.println("O usuario não está suspenso!");
        }
    }
}
