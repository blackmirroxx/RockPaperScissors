
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
    var gamesList: List<String> = mutableListOf("")
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


