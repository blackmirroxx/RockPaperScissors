
// test driven development -> tdd
// (1) build prototypes
// (2) build tests
// (3) fill them with life

fun main() {
    println("Welcome to Rock Paper Scissors! ")

    val rockPaperScissors = listOf<String>("rock", "paper", "scissors")
    val participants = listOf<String>("player_rock", "player_random", "draw") // whoWins

    val testEnabled = true
    if (testEnabled) {
        runAllTest(rockPaperScissors, participants)
    }

    val gamesEnabled = true
    if (gamesEnabled) {
        playRound(rockPaperScissors, participants, 100, 3)
    }
}


// test ----------------------------------------------------------------------------------------------------------------
fun runAllTest(l: List <String>, p: List<String>) {
    // run all the tests
    // make reports ... passes and fails

    testListContent(l)

    testPlayerRock(l, p, ::playerRock)

    testRandomNumberRange()

    val playerRandomPics = testRandomPlayer(l,p,::playerRandom)
    testGamesBasedOnGivenRandomPics(l, p, playerRandomPics)

    testWhoWinsWithFunctionsAsParameter(l, p, ::playerRock, ::playerRandom)
    testWhoWinsHigherOrder(l,p,::playerRock, ::playerRandom)

    println("Testing playing against -> ")
    print("rock against rock -> ")
    if ( playRockAgainst(l, p, 0).equals("draw") ){
        println("passed")
    } else {
        println("failed")
    }
    print("rock against paper -> ")
    if ( playRockAgainst(l, p, 1).equals(p.get(1))){
        println("passed")
    } else {
        println("failed")
    }
    print("rock against scissors -> ")
    if ( playRockAgainst(l, p, 2).equals(p.get(0))){
        println("passed")
    } else {
        println("failed")
    }

    // play many games
    println()
    println()
    println("Create a sample with 100 games : ")
    val testGames: List<String> = playManyGames(l, p, ::playerRock, ::playerRandom, 100)
    for (index: Int in testGames.indices){
        println(" " + testGames.get(index))
    }
    println("winrate of " + p.get(0) + " : " + countWinsForPlayer(testGames, "player_rock") )
    println("winrate op " + p.get(1) + " : " + countWinsForPlayer(testGames, "player_random") )
    println("draws are " + p.get(2) + " : " + countWinsForPlayer(testGames, "draw") )
    println()
    println("statistics for " + p.get(0) + " : " + createStatisticsForPlayer(testGames, p, 0))
    println("statistics for " + p.get(0) + " : " + createStatisticsForPlayer(testGames, p, 1))
    println()
}

fun testWhoWinsHigherOrder(l: List<String>,
                           p: List<String>,
                           player1: (List<String>) -> String,
                           player2: (List<String>) -> String){

    println()
    println("play who wins - higher order functions ...")
    for (index: Int in 1..10){
        val whoWins: String = playGame(l, p, player1, player2)
        println("player rock vs player random -> " + whoWins )
    }
    println()
}

fun testWhoWinsWithFunctionsAsParameter(l: List<String>,
                                        p: List<String>,
                                        player1: (List<String>) -> String,
                                        player2: (List<String>) -> String){
    println()
    println("play some more with functions as parameter ...")
    for (index: Int in 1..10){
        val whoWins: String = playGame(l, p, player1, player2)
        println("player rock vs player random -> " + whoWins )
    }
    println()
}

fun testGamesBasedOnGivenRandomPics(l: List<String>, p: List<String>, playerRandomPics: List<String>){
    println("Play some games -> based on given List")
    for (index: Int in playerRandomPics.indices){
        val whoWins: String = playRockAgainst( l, p, l.indexOf(playerRandomPics.get(index)) )
        println("player rock vs player random -> rock vs " + playerRandomPics.get(index) + " -> " + whoWins )
    }
}

fun testRandomPlayer(l: List<String>, p: List<String>, player2: (List<String>) -> String): List<String>{
    print("player random pics ->")
    for (index: Int in 1..10){
        print("" + playerRandom(l) + " ")
    }
    println()
    val playerRandomPics : MutableList<String> = mutableListOf<String>().toMutableList()
    for (index: Int in 1 .. 10){
        val e: List<String> =  mutableListOf<String>(playerRandom(l))
        playerRandomPics += e
    }
    println("player random pics -> " + playerRandomPics)

    print("testing player random -> ")
    for (index: Int in playerRandomPics.indices) {
        if (testPlayerContains(l, p, player2)) {
            print("passed ")
        } else {
            print("failed ")
        }
    }
    println()
    return playerRandomPics
}

fun testPlayerRock(l: List<String>, p: List<String>, player1: (List<String>) -> String){
    print("testing player rock -> : ")
    if ( testPlayer(l, p, player1, "rock") ) {
        println("passed")
    } else {
        println("failed")
    }
}

fun testRandomNumberRange(){
    print("random numbers -> ")
    val randomNumbers: MutableList<Int> = mutableListOf<Int>().toMutableList()
    for (index: Int in 1 .. 30){
        val e: List<Int> = listOf(picRandomNumber())
        randomNumbers += e
    }
    for (index: Int in randomNumbers.indices){
        print("" + (randomNumbers.get(index)).toString() + " ")
    }
    println()
    println("testing random numbers within range 0 to 2 -> ")
    for (inex: Int in randomNumbers.indices){
        if (0 <= randomNumbers.get(inex) && randomNumbers.get(inex) <= 2){
            print("p ")
        }else{
            print("f ")
        }
    }
    println()
}

fun testListContent(l: List <String>){
    print("rock -> ")
    if (l.get(0).equals("rock")) {
        println("passed")
    } else {
        println("failed")
    }

    print("paper -> ")
    if (l.get(1).equals("paper")) {
        println("passed")
    } else {
        println("failed")
    }

    print("scissors -> ")
    if (l.get(2).equals("scissors")) {
        println("passed")
    } else {
        println("failed")
    }

}

fun testPlayer(l: List<String>, p: List<String>, player: (List<String>) -> String, expeted_pic: String): Boolean{
    val test: Boolean
    if (p.get(0).equals("player_rock")) {
        test = player(l).equals(expeted_pic)
    }else{
        println("Not implemented yet")
        test = false
    }
    return test
}

fun testPlayerContains(l: List<String>, p: List<String>, player: (List<String>) -> String): Boolean{
    val test: Boolean
    if (p.size>=3) {
        test = l.contains(player(l))
    } else {
        println("something went wrong")
        test = false
    }
    return test
}



// logic ---------------------------------------------------------------------------------------------------------------


// player 1
fun playerRock(l: List<String>):String{
    val choice1 :String = l.get(0)
    return choice1
}

// player 2
fun playerRandom(l: List<String>):String{
    val choice2 : String = l.get(picRandomNumber()) // random from list
    return choice2
}

fun picRandomNumber(): Int{
    var number :Int = (1..100).random()
    number %= 3 // improveRandomPic
    return number
}

// parameter: list, participants, player1, player2, player3 (draws)
// player1 and player2 are functions passed as a parameter
// return String
fun playGame(l: List<String>, p: List<String>, player1: (List<String>) -> String, player2: (List<String>) -> String): String{
    val whoWins : String
    val pl1choice : String = player1(l)
    val pl2choice : String = player2(l)

    if (pl1choice.equals("rock")) {
        whoWins = playRockAgainst(l, p, l.indexOf(pl2choice))
    }else{
        whoWins = ""
    }
    return whoWins
}

fun playManyGames(l: List<String>,
                  p: List<String>,
                  player1: (List<String>) -> String,
                  player2: (List<String>) -> String,
                  runs: Int): List<String>{

    val gamesList: MutableList<String> = mutableListOf<String>().toMutableList()
    for (index: Int in 1..runs){
        val e: List<String> = mutableListOf(playGame(l, p, player1, player2))
        gamesList += e
    }
    return gamesList
}

fun playRound(l: List<String>,
              p: List<String>,
              runs: Int,
              rounds: Int){

    var gameList: MutableList<String>
    var statsPlayerRock: List<Double>
    var statsPlayerRandom: List<Double>
    for (index: Int in 1..rounds){
        gameList = playManyGames(l, p, ::playerRock, ::playerRandom, runs) as MutableList<String>
        statsPlayerRock = createStatisticsForPlayer(gameList,p,0)
        statsPlayerRandom = createStatisticsForPlayer(gameList, p, 1)
        println("Round : " + index.toString())
        println("player Rock " + "%.2f".format(statsPlayerRock.get(0)) + "% wins "
                               + "%.2f".format(statsPlayerRock.get(1)) + "% draws "
                               + "%.2f".format(statsPlayerRock.get(2)) + "% losses " )
        println("player Random " + "%.2f".format(statsPlayerRandom.get(0)) + "% wins "
                                 + "%.2f".format(statsPlayerRandom.get(1)) + "% draws "
                                 + "%.2f".format(statsPlayerRandom.get(2)) + "% losses ")
        println()
        }
    


}

fun playRockAgainst(l: List<String>, p: List<String>, against: Int): String {
    var whoWins: String = p.get(2) // draw
    if (l.get(against).equals("rock")) {
        whoWins = p.get(2)
    } else if (l.get(against).equals("paper")) {
        whoWins = p.get(1)
    } else if (l.get(against).equals("scissors")) {
        whoWins = p.get(0)
    }
    return whoWins
}

fun countWinsForPlayer(g: List<String>, player : String): Double{
    var winRate: Double  // in %
    val amountOfEntries: Int = g.size
    var count = 0
    for (index: Int in g.indices){
      if (g.get(index).equals(player)){
            count += 1
      }
    }

    // calculate win rate
    winRate = count.toDouble() / amountOfEntries.toDouble()
    winRate *= 100 // convert to percent
    // special use case for player "draw"
    return winRate
}


fun createStatisticsForPlayer(g: List<String>, p: List<String>, player: Int): List<Double>{
    val statistics: List<Double>
    val wins: Double = countWinsForPlayer(g, p.get(player))
    val draws: Double = countWinsForPlayer(g, p.get(2))
    val losses: Double = 100.00 - (wins+draws)

    statistics =  mutableListOf(wins, draws, losses)

    return statistics
}


