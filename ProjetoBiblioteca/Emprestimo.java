package biblioteca;

//Classe responsável por "Registrar" o Emprestimo, salvado os IDS tanto do Usuario quanto do Livro
public class Emprestimo {

    private int ID;
    private int UsuarioID;
    private int LivroID;
    private int dataEmprestimo;
    private int dataDevolução;
    private String NomeLivro;
    private String NomeUsuario;

    public Emprestimo(int ID, int UsuarioID, int LivroID, int dataEmprestimo, String Livro, String Usuario, int dataDevolução) {
        this.ID = ID;
        this.UsuarioID = UsuarioID;
        this.LivroID = LivroID;
        this.dataEmprestimo = dataEmprestimo;
        this.NomeLivro = Livro;
        this.NomeUsuario = Usuario;
        this.dataDevolução = dataDevolução;
    }

    public int getID() {
        return this.ID;
    }

    public int getUsuarioID() {
        return this.UsuarioID;
    }

    public int getLivroID() {
        return this.LivroID;
    }

    public int getData() {
        return this.dataEmprestimo;
    }

    public String getNomeLivro() {
        return this.NomeLivro;
    }

    public String getNomeUsuario() {
        return this.NomeUsuario;
    }

    public int getDataDevolução() {
        return this.dataDevolução;
    }

    public void setDataDevolução(int data) {
        this.dataDevolução = data;
    }
}
