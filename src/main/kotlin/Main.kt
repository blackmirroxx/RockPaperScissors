
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
    runAllTest(rockPaperScissors)

    //play many games
    //create stats
    //present them

}


// test ----------------------------------------------------------------------------------------------------------------
fun runAllTest(l: List <String>) {
    // run all the tests
    // maker reports ... passes and fails
    test_list_content(l)
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


// logic ---------------------------------------------------------------------------------------------------------------


// player 1
fun player_rock(l: List<String>):String{
    var choice_1 :String = ""
    return choice_1
}

// player 2
fun player_random(l: List<String>):String{
    var choice_2 : String = "" // random from list
    return choice_2
}

fun picRandomNumber(): Int{
    var number :Int = 0
    return number
}

// parameter: list, participants, player1, player2, player3 (draws)
// player1 and player2 are functions passed as a parameter
// return String
fun playGame(l: List<String>, p: List<String>, player1: (List<String>) -> String, player2: (List<String>) -> String): String{
    var whoWins : String = "";
    return whoWins
}

fun playManyGames(l: List<String>,
                  p: List<String>,
                  player1: (List<String>) -> String,
                  player2: (List<String>) -> String,
                  runs: Int): List<String>{
    var gamesList: List<String> = mutableListOf("")
    return gamesList
}

fun countWinsForPlayer(g: List<String>, player : String): Double{
    var winRate: Double = 0.0 // in %
    // speoial use case for player "draw"
    return winRate
}

fun countDraws(g: List<String>): Double{
    var drawRate: Double = 0.0 // in %
    return drawRate
}

fun createStatisticsForPlayer(g: List<String>, player: String): List<Double>{
    var statistics: List<Double> = mutableListOf(0.0, 0.0)
    return statistics
}


