

fun main(args: Array<String>) {
    println("Welcome to Rock Paper Scissors! ")

    val rockPaperScissors = mapOf("rock" to 1, "paper" to 2, "scissors" to 3)

    println("Enstries " + rockPaperScissors.entries)
    println("Keys " + rockPaperScissors.keys)
    println("Values" + rockPaperScissors.values)

    test_map_content(rockPaperScissors)


}


// test
fun test_map_content(m: Map<String, Int>){
    print("rock -> ")
    if (m.get("rock") == 1) {
        print("passed")
    } else {
        print("failed")
    }
    if (m.containsKey("rock")) {
        println(" passed")
    } else {
        println(" failed")
    }

    print("paper -> ")
    if (m.get("paper") == 2) {
        print("passed")
    } else {
        print("failed")
    }
    if (m.containsKey("paper")) {
        println(" passed")
    } else {
        println(" failed")
    }

    print("scissors -> ")
    if (m.get("scissors") == 3) {
        print("passed")
    } else {
        print("failed")
    }
    if (m.containsKey("scissors")) {
        println(" passed")
    } else {
        println(" failed")
    }
}


// logic