package biblioteca;

//Classe Usada para calcular o atraso durante a devolução do Livro.
//O prazo é de 15 dias
public class CalculadoraData {

    private int Janeiro;
    private int Fevereiro;
    private int Março;
    private int Abril;
    private int Maio;
    private int Junho;
    private int Julho;
    private int Agosto;
    private int Setembro;
    private int Outubro;
    private int Novembro;
    private int Dezembro;

    public CalculadoraData() {
        this.Janeiro = 1300;
        this.Fevereiro = 200;
        this.Março = 300;
        this.Abril = 400;
        this.Maio = 500;
        this.Junho = 600;
        this.Julho = 700;
        this.Agosto = 800;
        this.Setembro = 900;
        this.Outubro = 1000;
        this.Novembro = 1100;
        this.Dezembro = 1200;
    }

    public boolean CalculaAtraso(int dataInicial, int dataFinal) {

        if (Soma15(dataInicial) >= dataFinal) {
            return false;
        } else {
            return true;
        }
    }

    public int Soma15(int data) {
        data = data + 15;

        if (data < Fevereiro) {
            if (data > 131) {
                data = data + 100;
                data = data - 31;
            }
            return data;
        }

        if (data < Março) {
            if (data > 228) {
                data = data + 100;
                data = data - 28;
            }
            return data;
        }

        if (data < Abril) {
            if (data > 331) {
                data = data + 100;
                data = data - 31;
            }
            return data;
        }

        if (data < Maio) {
            if (data > 430) {
                data = data + 100;
                data = data - 30;
            }
            return data;
        }

        if (data < Junho) {
            if (data > 531) {
                data = data + 100;
                data = data - 31;
            }
            return data;
        }

        if (data < Julho) {
            if (data > 630) {
                data = data + 100;
                data = data - 30;
            }
            return data;
        }

        if (data < Agosto) {
            if (data > 731) {
                data = data + 100;
                data = data - 31;
            }
            return data;
        }

        if (data < Setembro) {
            if (data > 831) {
                data = data + 100;
                data = data - 31;
            }
            return data;
        }

        if (data < Outubro) {
            if (data > 930) {
                data = data + 100;
                data = data - 30;
            }
            return data;
        }

        if (data < Novembro) {
            if (data > 1031) {
                data = data + 100;
                data = data - 31;
            }
            return data;
        }

        if (data < Dezembro) {
            if (data > 1130) {
                data = data + 100;
                data = data - 30;
            }
            return data;
        }

        if (data < Janeiro) {
            if (data > 1231) {
                data = data - 1100;
                data = data - 31;
            }

            return data;
        }

        return 0;
    }
}
