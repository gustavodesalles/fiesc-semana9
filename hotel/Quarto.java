package hotel;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Quarto {
    private TipoQuarto tipoQuarto;
    private Hospede hospede;
    private int numHospedes;
    private double conta;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public Quarto(TipoQuarto tipoQuarto, Hospede hospede, int numHospedes, LocalDate dataInicio, LocalDate dataFim) {
        this.tipoQuarto = tipoQuarto;
        this.hospede = hospede;
        this.numHospedes = 1 + numHospedes;
        this.conta = 0;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;

        LocalDate auxDia = dataInicio;
        long dias = ChronoUnit.DAYS.between(dataInicio, dataFim) + 1;
        for (int i = 0; i < dias; i++) {
            if (auxDia.getMonthValue() <= 3 || auxDia.getMonthValue() >= 11) { // caso esteja em temporada alta
                conta += tipoQuarto.getDiariaAlta() * numHospedes;
            } else {
                conta += tipoQuarto.getDiariaBaixa() * numHospedes;
            }
        }
    }
}
