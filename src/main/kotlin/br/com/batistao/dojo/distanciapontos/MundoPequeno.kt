package br.com.batistao.dojo.distanciapontos

class MundoPequeno {

    fun calculaDistanciaDosPontosEmUmaReferencia(pontoReferencia: Pair<Int, Int>, numeroMaximoPontosProximos: Int, vararg listaPontos: Pair<Int, Int>): List<Pair<Int, Int>> {
        if(numeroMaximoPontosProximos > listaPontos.size) {
            throw IllegalArgumentException("Número de pontos esperados para serem retornardos é maior que a lista de pontos.")
        }

        val distanciaPontos = calculaDistancia(listaPontos.toList() as MutableList<Pair<Int, Int>>, pontoReferencia)

        return getNumeroPontosProximos(numeroMaximoPontosProximos, distanciaPontos)
    }

    internal fun calculaDistancia(listaPontos: MutableList<Pair<Int, Int>>, pontoReferencia: Pair<Int, Int>) : List<Pair<Int, Pair<Int, Int>>> {
        listaPontos.sortBy { it.first }

        val listaPontosComDistancias: MutableList<Pair<Int, Pair<Int, Int>>> = mutableListOf()

        listaPontos.forEachIndexed { i, ponto ->
            val distancia: Int = calcularDistanciaEntreReferenciaEPonto(pontoReferencia, ponto)

//                println("Distancia entre o ponto ($pontoReferencia) ao ponto ($ponto) é: $distancia")
            val pontoComDistancia = Pair(distancia, ponto)

            listaPontosComDistancias.add(pontoComDistancia)
        }

        listaPontosComDistancias.sortBy { it.first }

        return listaPontosComDistancias
    }

    internal fun calcularDistanciaEntreReferenciaEPonto(pontoReferencia: Pair<Int, Int>, ponto: Pair<Int, Int>): Int {
        val x = Math.pow(((pontoReferencia.first - ponto.first).toDouble()), 2.0)
        val y = Math.pow(((pontoReferencia.second - ponto.second).toDouble()), 2.0)
        return Math.sqrt(x + y).toInt()
    }

    internal fun getNumeroPontosProximos(totalPontosProximo: Int, distanciaPontos: List<Pair<Int, Pair<Int, Int>>>): List<Pair<Int, Int>> {
        return distanciaPontos.slice(0 until totalPontosProximo).map { it.second }
    }
}
