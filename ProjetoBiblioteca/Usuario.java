package biblioteca;

public class Usuario {

    private int ID;
    private String Nome;
    private int livrosEmprestados[];
    private boolean estaSuspenso;

    public Usuario(int ID, String Nome, boolean Suspenso) {
        this.ID = ID;
        this.Nome = Nome;
        this.livrosEmprestados = new int[4];
        this.livrosEmprestados[0] = 0;
        this.livrosEmprestados[1] = 0;
        this.livrosEmprestados[2] = 0;
        this.livrosEmprestados[3] = 0;
        this.estaSuspenso = Suspenso;
    }

    //Empresa um livro da biblioteca caso o Usuario não esteja suspenso ou não tenha mais que 4 livros emprestados
    public boolean emprestarLivro(int ID_Emprestimo) {

        if (!this.estaSuspenso) {
            for (int aux = 0; aux < 4; aux++) {
                if (this.livrosEmprestados[aux] == 0) {
                    this.livrosEmprestados[aux] = ID_Emprestimo;
                    return true;
                }
            }
        }

        return false;
    }

    //Devolve um livro para o acervo
    public int devolverLivro(int ID_Emprestimo) {
        for (int aux = 0; aux < 4; aux++) {
            if (this.livrosEmprestados[aux] == ID_Emprestimo) {
                this.livrosEmprestados[aux] = 0;
                return 1;
            }
        }
        return 0;
    }

    public int getID() {
        return this.ID;
    }

    public String getNome() {
        return this.Nome;
    }

    public boolean getSuspenso() {
        return this.estaSuspenso;
    }

    public void setSuspenso() {
        this.estaSuspenso = true;
    }
}
