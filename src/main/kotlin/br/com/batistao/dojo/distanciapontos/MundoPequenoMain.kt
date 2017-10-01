package br.com.batistao.dojo.distanciapontos

class MundoPequenoMain {

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            val ponto1 = Pair(5, 2)
            val ponto2 = Pair(2, 5)
            val ponto3 = Pair(9, 8)
            val ponto4 = Pair(3, 2)
            val ponto5 = Pair(-1, 4)

            val ponto6 = Pair(-2, -2)
            val ponto7 = Pair(-1, 0)
            val ponto8 = Pair(4, 3)
            val ponto9 = Pair(2, -2)

            val pontoReferencia = Pair(3, 3)

            val numeroMaximoPontosProximos = 3

            val mundoPequeno = MundoPequeno()

            val pontosProximos = mundoPequeno.calculaDistanciaDosPontosEmUmaReferencia(pontoReferencia, numeroMaximoPontosProximos,
                    ponto1, ponto2, ponto3, ponto4, ponto5, ponto6, ponto7, ponto8, ponto9)

            println("Os 3 pontos mais próximos do ponto ($pontoReferencia) são: $pontosProximos")
        }
    }
}
