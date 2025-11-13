import kotlin.random.Random

fun main(){
    val DAUS: String = "⚀ ⚁ ⚂ ⚃ ⚄ ⚅"
    val CARES_DAU: Array<String> = arrayOf("⚀", "⚁", "⚂", "⚃", "⚄", "⚅")

    var partides: Int?
    var tiradesPerPartida: Int?
    println("-------------------------------------")
    println("Benvingut/da al joc dels daus.")
    println(DAUS)
    println("=====================================")
    println("Per guanyar cada partida, la suma dels punts de les teves tirades dels teus daus ha de ser superior a la de la CPU")
    println(DAUS)
    println("=====================================")
    // Llegim el número de partides que volem jugar
    do {
        println("Quantes partides vols jugar? (de 1 a 3)")
        partides = readLine()?.toIntOrNull()

        if (partides != null && (partides < 1 || partides > 3)){
            partides = null
            println("ERROR: Valor no acceptat!")
        }
    }while(partides == null)

    // Llegim el número de quantes tirades volem fer per cada partida
    do {
        println("Quantes tirades vols fer per cada partida? (de 1 a 6)")
        tiradesPerPartida = readLine()?.toIntOrNull()

        if (tiradesPerPartida != null && (tiradesPerPartida < 1 || tiradesPerPartida > 6)){
            tiradesPerPartida = null
            println("ERROR: Valor no acceptat!")
        }
    }while(tiradesPerPartida == null)

    // Declarem la matriu
    var tiradesGuardades: Array<IntArray>

    // Inicialitzem la matriu de partides files i (tiradesPerPartida + 1) columnes
    tiradesGuardades = Array(partides){IntArray((tiradesPerPartida + 1)) }

    // Variables per comptar els resultats
    var guanyades: Int = 0
    var perdudes: Int = 0
    var empatades: Int = 0

    // Repetim tantes vegades com partides
    for(partida in 0 until partides) {
        var acumuladorCPU: Int = 0
        var tiradaActual: Int = 0
        val tiradesCPU: MutableList<String> = mutableListOf() // Llista per guardar les tirades de la CPU

        for (tirada in 0 until tiradesGuardades[partida].size - 1) {
            /** Tirades persona **/
            println("Tira el dau! (Intent $tirada)")
            tiradaActual = Random.nextInt(1, 6 + 1)
            println("Has tret un ${CARES_DAU[tiradaActual-1]} !")
            println("=====================================")
            // Guardem la tirada de l'usuari
            tiradesGuardades[partida][tirada] = tiradaActual

            // Acumulem la tirada a l'última columna
            tiradesGuardades[partida][tiradesPerPartida] += tiradaActual

            /** Tirades CPU **/
            val tiradaCPU = Random.nextInt(1, 6 + 1)
            tiradesCPU.add(CARES_DAU[tiradaCPU - 1]) // Afegim la tirada de la CPU a la llista
            acumuladorCPU += tiradaCPU
        }


        println("Tirades de la CPU: ${tiradesCPU.joinToString(", ")}")

        println("Partida acabada!")
        println("Tu has aconseguit ${tiradesGuardades[partida][tiradesPerPartida]} punts")
        println("La CPU ha aconseguit $acumuladorCPU punts")

        if (tiradesGuardades[partida][tiradesPerPartida] > acumuladorCPU){
            println("Has guanyat!")
            guanyades++
        }else if (tiradesGuardades[partida][tiradesPerPartida] < acumuladorCPU){
            println("Has perdut!")
            perdudes++
        }else{
            println("Heu empatat!")
            empatades++
        }
        println("=====================================")
    }

    println("=====================================")

    // Resultats:
    println("Resultats de les partides jugades: $partides")
    println("-------------------------------------")
    println("Partides guanyades: $guanyades")
    println("Partides perdudes: $perdudes")
    println("Partides empatades: $empatades")

    // Percentatge de les partides guanyades
    val percentGuanyades = (guanyades.toDouble() / partides) * 100
    println("Percentatge de totes les partides guanyades: %.2f%%".format(percentGuanyades))
    println("=====================================")
}