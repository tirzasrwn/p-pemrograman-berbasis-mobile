package id.tirzasrwn.learnkotlin

fun main() {
    // val count: Int = 2
    // println("You have $count unread messages.")

    val unreadCount = 5
    val readCount = 100
    println("You have ${unreadCount + readCount} total messages in your inbox.")

    val numberOfPhotos = 100
    val photosDeleted = 10
    println("$numberOfPhotos photos")
    println("$photosDeleted photos deleted")
    println("${numberOfPhotos - photosDeleted} photos left")

    // compile error, val is like constant
    // use var for value that will change
    // val cartTotal = 0
    // cartTotal = 20
    // println("Total: $cartTotal")

    var cartTotal = 0
    println("Total: $cartTotal")

    cartTotal = 20
    println("Total: $cartTotal")

    // Increment and decrement operators
    var count = 10
    println("You have $count unread messages.")
    count++
    println("You have $count unread messages.")
    count = count - 1
    println("You have $count unread messages.")

    // Double
    val trip1 = 3.20
    val trip2 = 4.10
    val trip3 = 1.72
    val totalTripLength = trip1 + trip2 + trip3
    println("$totalTripLength miles left to destination")

    // String
    val nextMeeting = "Next meeting: "
    val date = "January 1"
    val reminder = nextMeeting + date + " at work"
    println(reminder)
    println("Say \"hello\"")

    // Boolean
    val notificationsEnabled: Boolean = false
    println("Are notifications enabled? " + notificationsEnabled)

    // This is a comment.

    /*
     * This program displays the number of messages
     * in the user's inbox.
     */

}

