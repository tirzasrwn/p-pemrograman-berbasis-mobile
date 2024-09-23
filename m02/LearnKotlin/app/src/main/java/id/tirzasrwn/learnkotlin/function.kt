package id.tirzasrwn.learnkotlin

fun main() {
    birthdayGreeting()
    birthdayGreetingUint()
    println(birthdayGreetingString())
    println(birthdayGreetingByName("Tirza"))
    println(birthdayGreetingByNameAndAge("Tirza", 5))
    println(birthdayGreetingByNameAndAge("Danilo", 2))
    // Named Argument
    println(birthdayGreetingByNameAndAge(age = 5, name = "Tirza"))
    println(birthdayGreetingByNameAndAge(name = "Danilo", age = 2))
    // Default arguments
    println(birthdayGreetingByNameAndAge(age = 5))
}

fun birthdayGreeting() {
    println("Happy Birthday, Rover!")
    println("You are now 5 years old!")
}

// The Unit type
fun birthdayGreetingUint(): Unit {
    println("Happy Birthday, Rover!")
    println("You are now 5 years old!")
}

// Return a String from birthdayGreeting()
fun birthdayGreetingString(): String {
    val nameGreeting = "Happy Birthday, Rover!"
    val ageGreeting = "You are now 5 years old!"
    return "$nameGreeting\n$ageGreeting"
}

// Add a parameter to the birthdayGreeting() function
fun birthdayGreetingByName(name: String): String {
    val nameGreeting = "Happy Birthday, $name!"
    val ageGreeting = "You are now 5 years old!"
    return "$nameGreeting\n$ageGreeting"
}

// Functions with multiple parameters
fun birthdayGreetingByNameAndAge(name: String = "Unknown", age: Int): String {
    val nameGreeting = "Happy Birthday, $name!"
    val ageGreeting = "You are now $age years old!"
    return "$nameGreeting\n$ageGreeting"
}
