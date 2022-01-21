package hotel;

public enum TipoQuarto {
    SIMPLES(90, 140),
    LUXO(150, 400),
    SUPREMA(500, 800);
    private final int diariaBaixa;
    private final int diariaAlta;

    public int getDiariaBaixa() {
        return diariaBaixa;
    }

    public int getDiariaAlta() {
        return diariaAlta;
    }

    TipoQuarto(int diariaBaixa, int diariaAlta) {
        this.diariaBaixa = diariaBaixa;
        this.diariaAlta = diariaAlta;
    }
}
