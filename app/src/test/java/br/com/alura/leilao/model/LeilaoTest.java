package br.com.alura.leilao.model;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.List;

public class LeilaoTest {
    public static final double DELTA = 0.0001;   //evita o uso de magic numbers
    private final Leilao COMPUTADOR_USADO = new Leilao("Computador usado");  //é utilizado em todos os testes!
    private final Usuario FERNANDO = new Usuario("fernando");  // é usado na maioria dos casos

    //como nomear  de forma mais descritiva possivel!
    //[deve] [resultado esperado] [estado de teste]

    @Test
    public void deve_DevolvereDescricao_QuandoRecebeDescricao() {


        //executar ação esperada
        String descricaoDevolvida = COMPUTADOR_USADO.getDescricao();

        //testar resultado esperado
        assertEquals("Computador usado", descricaoDevolvida);
    }


    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeApenasUmLance() {
        //delta pega a diferença entre os valores com ponto flutuante e se ele for maior,
        // significa que os valores são equivalentes
        COMPUTADOR_USADO.propoe(new Lance(FERNANDO, 200.0));
        double maiorLanceDevolvido = COMPUTADOR_USADO.getMaiorLance();
        assertEquals(200.0, maiorLanceDevolvido, DELTA);  //valor padrao de delta
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {
        COMPUTADOR_USADO.propoe(new Lance(FERNANDO, 100));
        COMPUTADOR_USADO.propoe(new Lance(new Usuario("Rogerio"), 200));
        double maiorLanceDevolvido = COMPUTADOR_USADO.getMaiorLance();
        assertEquals(200.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente() {
        COMPUTADOR_USADO.propoe(new Lance(FERNANDO, 10000));
        COMPUTADOR_USADO.propoe(new Lance(new Usuario("Rogerio"), 9000));
        double maiorLanceDevolvido = COMPUTADOR_USADO.getMaiorLance();
        assertEquals(10000, maiorLanceDevolvido, DELTA);
    }


    @Test
    public void deve_DevolverMenorLance_QuandoRecebeApenasUmLance() {
        //delta pega a diferença entre os valores com ponto flutuante e se ele for maior,
        // significa que os valores são equivalentes
        COMPUTADOR_USADO.propoe(new Lance(FERNANDO, 200.0));
        double menorLanceDevolvido = COMPUTADOR_USADO.getMenorLance();
        assertEquals(200.0, menorLanceDevolvido, DELTA);  //valor padrao de delta
    }


    @Test
    public void deve_DevolverMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {
        COMPUTADOR_USADO.propoe(new Lance(FERNANDO, 100));
        COMPUTADOR_USADO.propoe(new Lance(new Usuario("Rogerio"), 200));
        double menorLanceDevolvido = COMPUTADOR_USADO.getMenorLance();
        assertEquals(100.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolvereMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente() {
        COMPUTADOR_USADO.propoe(new Lance(FERNANDO, 10000));
        COMPUTADOR_USADO.propoe(new Lance(new Usuario("Rogerio"), 9000));
        double maiorLanceDevolvido = COMPUTADOR_USADO.getMenorLance();


        assertEquals(9000, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeExatosTresLances(){
        COMPUTADOR_USADO.propoe(new Lance(FERNANDO, 100));
        COMPUTADOR_USADO.propoe(new Lance(new Usuario("Pedro"), 500));
        COMPUTADOR_USADO.propoe(new Lance(FERNANDO, 400));

        List<Lance> tresMaioresLancesDevolvidos = COMPUTADOR_USADO.tresMaioresLances();


        assertEquals(3, tresMaioresLancesDevolvidos.size());
        assertEquals(500, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(400, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
        assertEquals(100, tresMaioresLancesDevolvidos.get(2).getValor(), DELTA);
    }


    @Test
    public void deve_DevolverTresMaioresLances_QuandoNaoRecebeLances(){
        List<Lance> tresMaioresLancesDevolvidos = COMPUTADOR_USADO.tresMaioresLances();
        assertEquals(0, tresMaioresLancesDevolvidos.size());
    }


    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeApenasUmLance(){
        COMPUTADOR_USADO.propoe(new Lance(FERNANDO, 100));

        List<Lance> tresMaioresLancesDevolvidos = COMPUTADOR_USADO.tresMaioresLances();


        assertEquals(1, tresMaioresLancesDevolvidos.size());
        assertEquals(100, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
    }
    

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeApenasDoisLances(){
        COMPUTADOR_USADO.propoe(new Lance(FERNANDO, 100));
        COMPUTADOR_USADO.propoe(new Lance(new Usuario("Pedro"), 500));

        List<Lance> tresMaioresLancesDevolvidos = COMPUTADOR_USADO.tresMaioresLances();


        assertEquals(2, tresMaioresLancesDevolvidos.size());
        assertEquals(500, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(100, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeMaisDeTresLances(){
        COMPUTADOR_USADO.propoe(new Lance(FERNANDO, 100));
        COMPUTADOR_USADO.propoe(new Lance(new Usuario("Pedro"), 500));
        COMPUTADOR_USADO.propoe(new Lance(FERNANDO, 400));
        COMPUTADOR_USADO.propoe(new Lance(FERNANDO, 600));

        List<Lance> tresMaioresLancesDevolvidosParaQuatroLances = COMPUTADOR_USADO.tresMaioresLances();


        assertEquals(3, tresMaioresLancesDevolvidosParaQuatroLances.size());
        assertEquals(600, tresMaioresLancesDevolvidosParaQuatroLances.get(0).getValor(), DELTA);
        assertEquals(500, tresMaioresLancesDevolvidosParaQuatroLances.get(1).getValor(), DELTA);
        assertEquals(400, tresMaioresLancesDevolvidosParaQuatroLances.get(2).getValor(), DELTA);

        COMPUTADOR_USADO.propoe(new Lance(FERNANDO, 800));
        List<Lance> tresMaioresLancesDevolvidosParaCincoLances = COMPUTADOR_USADO.tresMaioresLances();
        assertEquals(3, tresMaioresLancesDevolvidosParaCincoLances.size());
        assertEquals(800, tresMaioresLancesDevolvidosParaCincoLances.get(0).getValor(), DELTA);
        assertEquals(600, tresMaioresLancesDevolvidosParaCincoLances.get(1).getValor(), DELTA);
        assertEquals(500, tresMaioresLancesDevolvidosParaCincoLances.get(2).getValor(), DELTA);
    }


}