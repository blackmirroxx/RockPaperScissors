
// test driven development -> tdd
// (1) build prototypes
// (2) build tests
// (3) fill them with life

const val RUNS   = 100
const val ROUNDS = 3

const val ROCK     = 0
const val PAPER    = 1
const val SCISSORS = 2

const val PLAYERROCK   = 0
const val PLAYERRANDOM = 1
const val PLAYERDRAW   = 2

const val WINS   = 0
const val DRAWS  = 1
const val LOSSES = 2

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
        playRound(rockPaperScissors, participants, RUNS, ROUNDS)
    }
}


// test ----------------------------------------------------------------------------------------------------------------
fun runAllTest(l: List <String>, p: List<String>) {
    testListContent(l)

    testPlayerRock(l, p, ::playerRock)

    testRandomNumberRange()

    val playerRandomPics = testRandomPlayer(l,p,::playerRandom)
    testGamesBasedOnGivenRandomPics(l, p, playerRandomPics)

    testRockPaperScissors(l, p)

    testWhoWinsWithFunctionsAsParameter(l, p, ::playerRock, ::playerRandom)
    testWhoWinsHigherOrder(l,p,::playerRock, ::playerRandom)
    testGamesWithStats(l, p, ::playerRock, ::playerRandom, RUNS)
}

fun testRockPaperScissors(l: List<String>, p: List<String>){
    println()
    println("Testing core mechanic -> rock paper scissors ")
    print("rock against rock -> ")
    if ( playRockAgainst(l, p, ROCK).equals(p.get(PLAYERDRAW)) ){
        println("passed")
    } else {
        println("failed")
    }
    print("rock against paper -> ")
    if ( playRockAgainst(l, p, PAPER).equals(p.get(PLAYERRANDOM))){
        println("passed")
    } else {
        println("failed")
    }
    print("rock against scissors -> ")
    if ( playRockAgainst(l, p, SCISSORS).equals(p.get(PLAYERROCK))){
        println("passed")
    } else {
        println("failed")
    }
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
    if (l.get(ROCK).equals("rock")) println("passed") else println("failed")

    print("paper -> ")
    if (l.get(PAPER).equals("paper")) println("passed")  else  println("failed")

    print("scissors -> ")
    if (l.get(SCISSORS).equals("scissors")) println("passed") else println("failed")
}

fun testPlayer(l: List<String>, p: List<String>, player: (List<String>) -> String, expeted_pic: String): Boolean{
    if(p.get(PLAYERROCK).equals("play_random")) return false
    if (p.get(PLAYERROCK).equals("player_rock")) return player(l).equals(expeted_pic)
    return false
}

fun testPlayerContains(l: List<String>, p: List<String>, player: (List<String>) -> String): Boolean{
    if (p.size != 3) return false
    return l.contains(player(l)) // can be true or false

}

fun testGamesWithStats(l: List<String>,
                       p: List<String>,
                       player1: (List<String>) -> String,
                       player2: (List<String>) -> String,
                       runs: Int){
    println("Test games with stats : ")
    println("Create a sample with " + runs.toString() + " games : ")
    val testGames: List<String> = playManyGames(l, p, player1, player2, runs)
    for (index: Int in testGames.indices){
        println(" " + testGames.get(index))
    }
    println("----------------------------------------------------------------------------------")
    println("The statistics are : ")
    println("winrate of " + p.get(PLAYERROCK) + " : " + "%.2f".format(countWinsForPlayer(testGames, "player_rock")) )
    println("winrate op " + p.get(PLAYERRANDOM) + " : " + "%.2f".format(countWinsForPlayer(testGames, "player_random")) )
    println("draws are " + p.get(PLAYERDRAW) + " : " + "%.2f".format(countWinsForPlayer(testGames, "draw")) )
    println()
    println("statistics for " + p.get(PLAYERROCK) + " : " + createStatisticsForPlayer(testGames, p, PLAYERROCK))
    println("statistics for " + p.get(PLAYERRANDOM) + " : " + createStatisticsForPlayer(testGames, p, PLAYERRANDOM))
    println("----------------------------------------------------------------------------------")
    println()
}


// logic ---------------------------------------------------------------------------------------------------------------


// player 1
fun playerRock(l: List<String>): String {
    return l.get(ROCK)
}

// player 2
fun playerRandom(l: List<String>): String {
    return l.get(picRandomNumber())
}

fun picRandomNumber(): Int{
    var number :Int = (1..100).random()
    number %= 3 // improveRandomPic
    return number
}


fun playGame(l: List<String>, p: List<String>, player1: (List<String>) -> String, player2: (List<String>) -> String): String{
    val pl1choice : String = player1(l)
    val pl2choice : String = player2(l)

    if (pl1choice.equals(l.get(ROCK))) return playRockAgainst(l, p, l.indexOf(pl2choice))
    return ""
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
        statsPlayerRock = createStatisticsForPlayer(gameList,p,PLAYERROCK)
        statsPlayerRandom = createStatisticsForPlayer(gameList, p, PLAYERRANDOM)
        println("Round : " + index.toString())
        println("player Rock " + "%.2f".format(statsPlayerRock.get(WINS)) + "% wins "
                               + "%.2f".format(statsPlayerRock.get(DRAWS)) + "% draws "
                               + "%.2f".format(statsPlayerRock.get(LOSSES)) + "% losses " )
        println("player Random " + "%.2f".format(statsPlayerRandom.get(WINS)) + "% wins "
                                 + "%.2f".format(statsPlayerRandom.get(DRAWS)) + "% draws "
                                 + "%.2f".format(statsPlayerRandom.get(LOSSES)) + "% losses ")
        println()
        }
    


}

fun playRockAgainst(l: List<String>, p: List<String>, against: Int): String {

    if ( l.get(against).equals(l.get(ROCK)) ) return p.get(PLAYERDRAW)
    if ( l.get(against).equals(l.get(PAPER)) ) return p.get(PLAYERRANDOM)
    if ( l.get(against).equals(l.get(SCISSORS)) ) return p.get(PLAYERROCK)

    return p.get(PLAYERDRAW)
}

fun countWinsForPlayer(g: List<String>, player : String): Double{
    var winRate: Double  // in %
    val amountOfEntries: Int = g.size
    var count = 0
    for (index: Int in g.indices){
      if (g.get(index).equals(player))  count += 1
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
    val draws: Double = countWinsForPlayer(g, p.get(PLAYERDRAW))
    val losses: Double = 100.00 - (wins+draws)

    statistics =  mutableListOf(wins, draws, losses)

    return statistics
}


