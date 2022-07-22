
// test driven development -> tdd
// (1) build prototypes
// (2) build tests
// (3) fill them with life

fun main(args: Array<String>) {
    println("Welcome to Rock Paper Scissors! ")

    // list are simpler than maps .. keep it simple
    val rockPaperScissors = listOf<String>("rock", "paper", "scissors")
    val participants = listOf<String>("player_rock", "player_random", "draw") // whoWins


    // tests
    runAllTest(rockPaperScissors, participants)

    //play many games
    //create stats
    //present them

}


// test ----------------------------------------------------------------------------------------------------------------
fun runAllTest(l: List <String>, p: List<String>) {
    // run all the tests
    // maker reports ... passes and fails
    test_list_content(l)

    // test player_rock
    // pics always rock
    print("testing player rock -> : ")
    if ( test_player(l, p, ::player_rock, "rock") ) {
        println("passed")
    } else {
        println("failed")
    }

    // check random
    print("random numbers -> ")
    for (index: Int in 1..30){
        print("" +picRandomNumber() + " ")
    }
    println()

    // check player random
    print("player random pics ->")
    for (index: Int in 1..10){
        print("" + player_random(l) + " ")
    }
    println()
    var playerRandomPics : List<String> = mutableListOf<String>()
    for (index: Int in 1 .. 10){
        var e: List<String> =  mutableListOf<String>(player_random(l))
        playerRandomPics += e
    }
    println("player random pics -> " + playerRandomPics)

    print("testing player random -> ")
    for (index: Int in playerRandomPics.indices) {
        if (test_player_contains(l, p, ::player_random)) {
            print("passed ")
        } else {
            print("failed ")
        }
    }
    println()

    println("Play some games -> ")
    for (index: Int in playerRandomPics.indices){
        var whoWins: String = playRockAgainst( l, p, l.indexOf(playerRandomPics.get(index)) )
        println("player rock vs player random -> rock vs " + playerRandomPics.get(index) + " -> " + whoWins )
    }

    println()
    println("play some more ...")
    for (index: Int in 1..10){
        var whoWins: String = playGame(l, p, ::player_rock, ::player_random)
        println("player rock vs player random -> " + whoWins )
    }
    println()

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
    var testGames: List<String> = playManyGames(l, p, ::player_rock, ::player_random, 100)
    for (index: Int in testGames.indices){
        println(" " + testGames.get(index))
    }
    println("winrate of " + p.get(0) + " : " + countWinsForPlayer(testGames, "player_rock") )
    println("winrate op " + p.get(1) + " : " + countWinsForPlayer(testGames, "player_random") )
    println("draws are " + p.get(2) + " : " + countWinsForPlayer(testGames, "draw") )
    println()
    println("statistics for " + p.get(0) + " : " + createStatisticsForPlayer(testGames, p, 0))
    println("statistics for " + p.get(0) + " : " + createStatisticsForPlayer(testGames, p, 1))
}

fun test_list_content(l: List <String>){
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

fun test_player(l: List<String>, p: List<String>, player: (List<String>) -> String, expeted_pic: String): Boolean{
    var test: Boolean = false

    test = player(l).equals(expeted_pic)
    return test
}

fun test_player_contains(l: List<String>, p: List<String>, player: (List<String>) -> String): Boolean{
    var test: Boolean = false

    test = l.contains( player(l) )
    return test
}



// logic ---------------------------------------------------------------------------------------------------------------


// player 1
fun player_rock(l: List<String>):String{
    var choice1 :String = l.get(0)
    return choice1
}

// player 2
fun player_random(l: List<String>):String{
    var choice2 : String = l.get(picRandomNumber()) // random from list
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
    var whoWins : String = ""
    var pl1choice : String = player1(l)
    var pl2choice : String = player2(l)

    if (pl1choice.equals("rock")) {
        whoWins = playRockAgainst(l, p, l.indexOf(pl2choice))
    }else{

    }
    return whoWins
}

fun playManyGames(l: List<String>,
                  p: List<String>,
                  player1: (List<String>) -> String,
                  player2: (List<String>) -> String,
                  runs: Int): List<String>{
    var gamesList: List<String> = mutableListOf<String>()


    for (index: Int in 1..runs){
        var e: List<String> = mutableListOf(playGame(l, p, player1, player2))
        gamesList += e
    }
    return gamesList
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
    var winRate: Double = 0.0 // in %
    var amountOfEntries: Int = g.size
    var count: Int = 0
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
    var statistics: List<Double> = mutableListOf<Double>()
    var wins: Double = countWinsForPlayer(g, p.get(player))
    var draws: Double = countWinsForPlayer(g, p.get(2))
    var losses: Double = 100.00 - (wins+draws)

    statistics =  mutableListOf(wins, draws, losses)

    return statistics
}


