package library;

import exception.DataExceptions.InvalidAnoException;
import exception.DataExceptions.InvalidDiaException;
import exception.DataExceptions.InvalidMesException;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;

/**
 * Representa uma data através do dia, mês e ano.

 */
public class Data implements Comparable<Data>, Serializable {

    /**
     * O ano da data.
     */
    private int ano;

    /**
     * O mês da data.
     */
    private Mes mes;

    /**
     * O dia da data.
     */
    private int dia;

    /**
     * O ano por omissão.
     */
    private static final int ANO_POR_OMISSAO = 0;

    /**
     * O mês por omissão.
     */
    private static final Mes MES_POR_OMISSAO = Mes.SEM_MES;

    /**
     * O dia por omissão.
     */
    private static final int DIA_POR_OMISSAO = 0;



    /**
     * Representa os meses do ano.
     */
    private static enum Mes {

        /**
         * Os meses do ano.
         */
        JANEIRO(31) {
            @Override
            public String toString() {
                return "Janeiro";
            }
        },
        FEVEREIRO(28) {
            @Override
            public String toString() {
                return "Fevereiro";
            }
        },
        MARCO(31) {
            @Override
            public String toString() {
                return "Março";
            }
        },
        ABRIL(30) {
            @Override
            public String toString() {
                return "Abril";
            }
        },
        MAIO(31) {
            @Override
            public String toString() {
                return "Maio";
            }
        },
        JUNHO(30) {
            @Override
            public String toString() {
                return "Junho";
            }
        },
        JULHO(31) {
            @Override
            public String toString() {
                return "Julho";
            }
        },
        AGOSTO(31) {
            @Override
            public String toString() {
                return "Agosto";
            }
        },
        SETEMBRO(30) {
            @Override
            public String toString() {
                return "Setembro";
            }
        },
        OUTUBRO(31) {
            @Override
            public String toString() {
                return "Outubro";
            }
        },
        NOVEMBRO(30) {
            @Override
            public String toString() {
                return "Novembro";
            }
        },
        DEZEMBRO(31) {
            @Override
            public String toString() {
                return "Dezembro";
            }
        },
        SEM_MES(0) {
            @Override
            public String toString() {
                return "Sem mês";
            }
        };


        /**
         * O número de dias de um mês.
         */
        private int numeroDeDias;

        /**
         * Constrói um mês com o número de dias recebido por parâmetro.
         *
         * @param numeroDeDias o número de dias do mês.
         */
        private Mes(int numeroDeDias) {
            this.numeroDeDias = numeroDeDias;
        }

        /**
         * Devolve o número de dias do mês do ano recebido por parâmetro.
         *
         * @param ano o ano do mês.
         * @return o número de dias do mês do ano.
         */
        public int numeroDeDias(int ano) {
            if (ordinal() == 1 && Data.isAnoBissexto(ano)) {
                return numeroDeDias + 1;
            }
            return numeroDeDias;
        }

        /**
         * Devolve o mês cuja ordem é recebida por parâmetro.
         *
         * @param ordemDoMes a ordem do mês.
         * @return o mês cuja ordem é recebida por parâmetro.
         */
        public static Mes obterMes(int ordemDoMes) {
            return Mes.values()[ordemDoMes - 1];
        }

    }

    /**
     * Constrói uma instância de Data recebendo o ano, o mês e o dia.
     *
     * @param ano o ano da data.
     * @param mes o mês da data.
     * @param dia o dia da data.
     */
    public Data(int ano, int mes, int dia) {
        setData(ano, mes, dia);
    }


    /**
     * Constrói uma instância de Data com a data por omissão.
     */
    public Data() {
        ano = ANO_POR_OMISSAO;
        mes = MES_POR_OMISSAO;
        dia = DIA_POR_OMISSAO;
    }

    /**
     * Constrói uma instância de Data com as mesmas caraterísticas da data
     * recebida por parâmetro.
     *
     * @param outraData a data com as características a copiar.
     */
    public Data(Data outraData) {
        ano = outraData.ano;
        mes = outraData.mes;
        dia = outraData.dia;
    }

    /**
     * Devolve o ano da data.
     *
     * @return ano da data
     */
    public int getAno() {
        return ano;
    }

    /**
     * Devolve o mês da data.
     *
     * @return mês da data.
     */
    public int getMes() {
        return mes.ordinal() + 1;
    }

    /**
     * Devolve o dia da data.
     *
     * @return dia da data.
     */
    public int getDia() {
        return dia;
    }

    /**
     * Modifica o ano, o mês e o dia da data.
     *
     * @param ano o novo ano da data.
     * @param mes o novo mês da data.
     * @param dia o novo dia da data.
     */
    public final void setData(int ano, int mes, int dia) {
        int anoAtual = LocalDate.now().getYear();
        if (ano < 0 || ano > anoAtual) {
            throw new InvalidAnoException();
        }
        if (mes < 1 || mes > 12) {
            throw new InvalidMesException();
        }
        if (dia < 1 || dia > Mes.obterMes(mes).numeroDeDias(ano)) {
            throw new InvalidDiaException();
        }
        this.ano = ano;
        this.mes = Mes.obterMes(mes);
        this.dia = dia;
    }

    /**
     * Devolve a descrição textual da data no formato: diaDaSemana, dia de mês
     * de ano.
     *
     * @return caraterísticas da data.
     */
    @Override
    public String toString() {
        return String.format("%d de %s de %d", dia, mes, ano);
    }

    /**
     * Devolve a data no formato:%04d/%02d/%02d.
     *
     * @return caraterísticas da data.
     */
    public String toAnoMesDiaString() {
        return String.format("%04d/%02d/%02d", ano, mes.ordinal() + 1, dia);
    }

    /**
     * Compara a data com o objeto recebido.
     *
     * @param outroObjeto o objeto a comparar com a data.
     * @return true se o objeto recebido representar uma data equivalente à
     * data. Caso contrário, retorna false.
     */
    @Override
    public boolean equals(Object outroObjeto) {
        if (this == outroObjeto) {
            return true;
        }
        if (outroObjeto == null || getClass() != outroObjeto.getClass()) {
            return false;
        }
        Data outraData = (Data) outroObjeto;
        return ano == outraData.ano && mes.equals(outraData.mes)
                && dia == outraData.dia;
    }

    /**
     * Compara a data com a outra data recebida por parâmetro.
     *
     * @param outraData a data a ser comparada.
     * @return o valor 0 se a outraData recebida é igual à data; o valor -1 se
     * a outraData for posterior à data; o valor 1 se a outraData for
     * anterior à data.
     */
    @Override
    public int compareTo(Data outraData) {
        return (outraData.isMaior(this)) ? -1 : (isMaior(outraData)) ? 1 : 0;
    }


    /**
     * Devolve true se a data for maior do que a data recebida por parâmetro. Se
     * a data for menor ou igual à data recebida por parâmetro, devolve false.
     *
     * @param outraData a outra data com a qual se compara a data.
     * @return true se a data for maior do que a data recebida por parâmetro,
     * caso contrário devolve false.
     */
    public boolean isMaior(Data outraData) {
        int totalDias = contaDias();
        int totalDias1 = outraData.contaDias();

        return totalDias > totalDias1;
    }

    /**
     * Devolve a diferença em número de dias entre a data e a data recebida por
     * parâmetro.
     *
     * @param outraData a outra data com a qual se compara a data para calcular
     *                  a diferença do número de dias.
     * @return diferença em número de dias entre a data e a data recebida por
     * parâmetro.
     */
    public int diferenca(Data outraData) {
        int totalDias = contaDias();
        int totalDias1 = outraData.contaDias();

        return Math.abs(totalDias - totalDias1);
    }

    /**
     * Devolve a diferença em número de dias entre a data e a data recebida por
     * parâmetro com ano, mês e dia.
     *
     * @param ano o ano da data com a qual se compara a data para calcular a
     *            diferença do número de dias.
     * @param mes o mês da data com a qual se compara a data para calcular a
     *            diferença do número de dias.
     * @param dia o dia da data com a qual se compara a data para calcular a
     *            diferença do número de dias.
     * @return diferença em número de dias entre a data e a data recebida por
     * parâmetro com ano, mês e dia.
     */
    public int diferenca(int ano, int mes, int dia) {
        int totalDias = contaDias();
        Data outraData = new Data(ano, mes, dia);
        int totalDias1 = outraData.contaDias();

        return Math.abs(totalDias - totalDias1);
    }

    /**
     * Devolve true se o ano passado por parâmetro for bissexto. Se o ano
     * passado por parâmetro não for bissexto, devolve false.
     *
     * @param ano o ano a validar.
     * @return true se o ano passado por parâmetro for bissexto, caso contrário
     * devolve false.
     */
    public static boolean isAnoBissexto(int ano) {
        return ano % 4 == 0 && ano % 100 != 0 || ano % 400 == 0;
    }

    /**
     * Devolve a data atual do sistema.
     *
     * @return a data atual do sistema.
     */
    public static Data dataAtual() {
        Calendar hoje = Calendar.getInstance();
        int ano = hoje.get(Calendar.YEAR);
        int mes = hoje.get(Calendar.MONTH) + 1;    // janeiro é representado por 0.
        int dia = hoje.get(Calendar.DAY_OF_MONTH);
        return new Data(ano, mes, dia);
    }

    /**
     * Devolve o número de dias desde o dia 1/1/1 até à data.
     *
     * @return número de dias desde o dia 1/1/1 até à data.
     */
    private int contaDias() {
        int totalDias = 0;

        for (int i = 1; i < ano; i++) {
            totalDias += isAnoBissexto(i) ? 366 : 365;
        }
        for (int i = 1; i < mes.ordinal() + 1; i++) {
            totalDias += Mes.obterMes(i).numeroDeDias(ano);
        }
        totalDias += dia;

        return totalDias;
    }
}