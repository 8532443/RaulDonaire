package biblioteca;

public class Livro {

    private String Nome;
    private int ID;
    private boolean estaEmprestado;
    private int ID_Emprestimo;

    public Livro(int ID, String Nome, boolean estaEmprestado, int ID_Emprestimo) {
        this.ID = ID;
        this.Nome = Nome;
        this.estaEmprestado = estaEmprestado;
        this.ID_Emprestimo = ID_Emprestimo;
    }

    public String getNome() {
        return this.Nome;
    }

    public int getID() {
        return this.ID;
    }

    //Empresta o Livro para um Usuario e salva o ID do Emprestimo
    public void Emprestar(int ID_Emprestimo) {
        this.estaEmprestado = true;
        this.ID_Emprestimo = ID_Emprestimo;
    }

    //Devolve o Livro para o acervo
    public void Devolver() {
        this.estaEmprestado = false;
        this.ID_Emprestimo = 0;
    }

    public int getID_Emprestimo() {
        return this.ID_Emprestimo;
    }

    public boolean estaEmprestado() {
        return this.estaEmprestado;
    }
}
