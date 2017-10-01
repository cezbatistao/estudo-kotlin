package br.com.batistao.dojo.distanciapontos

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.Test
import org.junit.runner.RunWith
import org.tools4j.spockito.Spockito

@RunWith(Spockito::class)
class MundoPequenoTest {

    @Spockito.Name("[{row}]: Distância de {2} entre os pontos {0} e {1}")
    @Spockito.Unroll(
            "| X   | Y   | Esperado  |",
            "| 5   | 2   | 2         |",
            "| 2   | 5   | 2         |",
            "| 9   | 8   | 7         |",
            "| 3   | 2   | 1         |",
            "| -1  | 4   | 4         |",
            "| 2   | -2  | 5         |",
            "| 0   | 0   | 4         |",
            "| 3   | 3   | 0         |"
    )
    @Test fun verificaCalculoDaDistanciaEntreDoisPontos(x: Int, y: Int, esperado: Int) {
        //given
        val mundoPequeno = MundoPequeno()
        val pontoReferencia = Pair<Int, Int>(3, 3)

        //when
        val distanciaCalculada = mundoPequeno.calcularDistanciaEntreReferenciaEPonto(pontoReferencia, Pair(x, y))

        //then
        assertThat(distanciaCalculada).isEqualTo(esperado)
    }

    @Test fun verificaAListaOrdenadaDaDistanciaEntreDoisPontos() {
        //given
        val mundoPequeno = MundoPequeno()
        val pontoReferencia = Pair<Int, Int>(3, 3)

        val pontosComDistanciasEsperadas: Array<Pair<Int, Pair<Int, Int>>> = arrayOf(
                Pair(1, Pair(3, 2)),
                Pair(2, Pair(2, 5)),
                Pair(2, Pair(5, 2)),
                Pair(4, Pair(-1, 4)),
                Pair(7, Pair(9, 8))
        )

        val listaPontos = mutableListOf(Pair(5, 2), Pair(2, 5), Pair(9, 8), Pair(3, 2), Pair(-1, 4))

        //when
        val listaPontosComDistanciasCalculada: List<Pair<Int, Pair<Int, Int>>> = mundoPequeno.calculaDistancia(listaPontos, pontoReferencia)

        //then
        assertThat(listaPontosComDistanciasCalculada).isNotNull().hasSize(pontosComDistanciasEsperadas.size).containsExactly(*pontosComDistanciasEsperadas)
    }

    @Test fun verificaONumeroDeRetornoMaximoInformado() {
        //given
        val mundoPequeno = MundoPequeno()
        val totalPontosProximo = 3

        val pontosComDistancias: List<Pair<Int, Pair<Int, Int>>> = listOf(
                Pair(1, Pair(3, 2)),
                Pair(2, Pair(2, 5)),
                Pair(2, Pair(5, 2)),
                Pair(4, Pair(-1, 4)),
                Pair(7, Pair(9, 8))
        )

        val pontosComDistanciasRetornadoEsperado: Array<Pair<Int, Int>> = arrayOf(
                Pair(3, 2),
                Pair(2, 5),
                Pair(5, 2)
        )

        val pontosComDistanciasNaoRetornadoEsperado: Array<Pair<Int, Int>> = arrayOf(
                Pair(-1, 4),
                Pair(9, 8)
        )

        //when
        val listaComPontosCalculada: List<Pair<Int, Int>> = mundoPequeno.getNumeroPontosProximos(totalPontosProximo, pontosComDistancias)

        //then
        assertThat(listaComPontosCalculada).isNotNull().hasSize(totalPontosProximo).containsExactly(*pontosComDistanciasRetornadoEsperado).doesNotContain(*pontosComDistanciasNaoRetornadoEsperado)
    }

    @Test fun verificaQueOParametroDoNumeroDePontosRetornadoRespeitaAListaDePontosInformados() {
        //given
        val mundoPequeno = MundoPequeno()
        val pontoReferencia = Pair<Int, Int>(3, 3)
        val totalPontosProximo = 6
        val listaPontos = arrayOf(Pair(5, 2), Pair(2, 5), Pair(9, 8), Pair(3, 2), Pair(-1, 4))

        //when
        val thrown: Throwable = catchThrowable({ mundoPequeno.calculaDistanciaDosPontosEmUmaReferencia(pontoReferencia, totalPontosProximo, *listaPontos) });

        // when
        assertThat(thrown).isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("Número de pontos esperados para serem retornardos é maior que a lista de pontos.")
    }
}
