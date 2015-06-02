package biblioteca;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//Classe responsável por fazer toda a interação entre a Shell e arquivos
//Todos os arquivos são lidos e escritos nesta classe
public class FILEReader {

    private Scanner FILELivro;
    private Scanner FILEUsuario;
    private Scanner FILEEmprestimo;
    private Livro AUXLivro;
    private Usuario AUXUsuario;
    private Emprestimo AUXEmprestimo;
    private FileWriter FWLivro;
    private FileWriter FWUsuario;
    private FileWriter FWEmprestimo;
    private ArrayList<Usuario> Usuarios;
    private ArrayList<Livro> Livros;
    private ArrayList<Emprestimo> Emprestimos;

    public FILEReader() throws FileNotFoundException, IOException {
        this.FILELivro = new Scanner(new File("livro.csv"));
        this.FILEUsuario = new Scanner(new File("usuario.csv"));
        this.FILEEmprestimo = new Scanner(new File("emprestimo.csv"));

        this.Usuarios = new ArrayList();
        this.Livros = new ArrayList();
        this.Emprestimos = new ArrayList();
    }

    //Le os usuarios do arquivo usuario.csv
    public ArrayList<Usuario> CarregaUsuario() throws RuntimeException {
        String Nome, ID, Suspenso;

        this.FILEUsuario.useDelimiter(",");
        while ((this.FILEUsuario.hasNext())) {
            ID = this.FILEUsuario.next();
            Nome = this.FILEUsuario.next();
            Suspenso = this.FILEUsuario.next();

            this.AUXUsuario = new Usuario(Integer.parseInt(ID), Nome, Boolean.parseBoolean(Suspenso));
            this.Usuarios.add(this.AUXUsuario);

            System.out.println("O usuario: " + this.AUXUsuario.getNome() + " foi adicionado!");
        }

        return this.Usuarios;
    }

    //Le os livros do arquivo livro.csv
    public ArrayList<Livro> CarregaLivro() {
        String Nome;
        String ID;
        String estaEmprestado;
        String ID_Emprestimo;

        this.FILELivro.useDelimiter(",");

        while ((this.FILELivro.hasNext())) {
            ID = this.FILELivro.next();
            Nome = this.FILELivro.next();
            estaEmprestado = this.FILELivro.next();
            ID_Emprestimo = this.FILELivro.next();

            this.AUXLivro = new Livro(Integer.parseInt(ID), Nome, Boolean.parseBoolean(estaEmprestado), Integer.parseInt(ID_Emprestimo));
            this.Livros.add(this.AUXLivro);
            System.out.println("O livro: " + this.AUXLivro.getNome() + " foi adicionado!");
        }
        return this.Livros;
    }

    //Le os emprestimos do arquivo emprestimo.csv 
    public ArrayList<Emprestimo> CarregaEmprestimo() throws RuntimeException {
        String UsuarioID, ID, LivroID, Data, Livro, Usuario, DataDevolução;

        this.FILEEmprestimo.useDelimiter(",");

        while ((this.FILEEmprestimo.hasNext())) {
            ID = this.FILEEmprestimo.next();
            UsuarioID = this.FILEEmprestimo.next();
            LivroID = this.FILEEmprestimo.next();
            Data = this.FILEEmprestimo.next();
            Livro = this.FILEEmprestimo.next();
            Usuario = this.FILEEmprestimo.next();
            DataDevolução = this.FILEEmprestimo.next();

            this.AUXEmprestimo = new Emprestimo(Integer.parseInt(ID), Integer.parseInt(UsuarioID), Integer.parseInt(LivroID), Integer.parseInt(Data), Livro, Usuario, Integer.parseInt(DataDevolução));
            this.Emprestimos.add(this.AUXEmprestimo);
            System.out.println("O emprestimo: " + this.AUXEmprestimo.getID() + " foi adicionado!");
        }
        return this.Emprestimos;
    }

    //Salva UM livro no arquivo livro.csv
    public void SalvaLivro(Livro AUX) throws IOException {
        this.FWLivro.append(String.valueOf(AUX.getID()));
        this.FWLivro.append(',');
        this.FWLivro.append(AUX.getNome());
        this.FWLivro.append(',');
        this.FWLivro.append(String.valueOf(AUX.estaEmprestado()));
        this.FWLivro.append(',');
        this.FWLivro.append(String.valueOf(AUX.getID_Emprestimo()));
        this.FWLivro.append(',');
        System.out.println("O livro: " + AUX.getNome() + " foi salvo!");
    }

    //Salva UM usuario no arquivo usuario.csv
    public void SalvaUsuario(Usuario AUX) throws IOException {
        this.FWUsuario.append(String.valueOf(AUX.getID()));
        this.FWUsuario.append(',');
        this.FWUsuario.append(AUX.getNome());
        this.FWUsuario.append(',');
        this.FWUsuario.append(String.valueOf(AUX.getSuspenso()));
        this.FWUsuario.append(',');
        System.out.println("O Usuario: " + AUX.getNome() + " foi salvo!");
    }

    //Salva UM emprestimo no arquivo emprestimo.csv
    public void SalvaEmprestimo(Emprestimo AUX) throws IOException {
        this.FWEmprestimo.append(String.valueOf(AUX.getID()));
        this.FWEmprestimo.append(',');
        this.FWEmprestimo.append(String.valueOf(AUX.getUsuarioID()));
        this.FWEmprestimo.append(',');
        this.FWEmprestimo.append(String.valueOf(AUX.getLivroID()));
        this.FWEmprestimo.append(',');
        this.FWEmprestimo.append(String.valueOf(AUX.getData()));
        this.FWEmprestimo.append(',');
        this.FWEmprestimo.append(AUX.getNomeLivro());
        this.FWEmprestimo.append(',');
        this.FWEmprestimo.append(AUX.getNomeUsuario());
        this.FWEmprestimo.append(',');
        this.FWEmprestimo.append(String.valueOf(AUX.getDataDevolução()));
        this.FWEmprestimo.append(',');
        System.out.println("O Emprestimo: " + AUX.getID() + " foi salvo!");
    }

    //Fecha os três arquivos utilizados
    public void Close() throws IOException {
        this.FWLivro.close();
        this.FWUsuario.close();
        this.FWEmprestimo.close();
    }

    //Abre os três arquivos utilizados
    public void Open() throws IOException {
        this.FWLivro = new FileWriter("livro.csv");
        this.FWUsuario = new FileWriter("usuario.csv");
        this.FWEmprestimo = new FileWriter("emprestimo.csv");

    }
}
