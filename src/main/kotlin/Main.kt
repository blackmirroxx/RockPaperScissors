

fun main(args: Array<String>) {
    println("Welcome to Rock Paper Scissors! ")

    // list are simpler than maps .. keep it simple
    val rockPaperScissors = listOf("rock", "paper", "scissors")


    test_map_content(rockPaperScissors)


}


// test
fun test_map_content(l: List <String>){
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


// logic
// test driven development -> tdd
// (1) build prototypes
// (2) build tests
// (3) fill them with life

// player 1
fun player_rock(l: List<String>):Int{
    var choice_1 :Int = 0
    return choice_1
}

// player 2
fun player_random(l: List<String>):Int{
    var choice_2 : Int = 1 // random
    return choice_2
}

