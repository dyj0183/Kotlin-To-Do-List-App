//import java.security.KeyStore

// create a Task class that contains task name and task deadline
class Task constructor(newTaskName: String?, newDeadline: String?) {
    var taskName = newTaskName
    var deadline = newDeadline
}

// display all the tasks in the to-do list
fun display(toDoList: MutableList<Task?>) {
    println()
    // check the to-do list is empty or not
    if (toDoList.isNullOrEmpty()){
        println("To-Do list is currently empty. Time to add some new tasks into it!")
    }
    else {
        println("Here are the tasks in Your To-Do List: ")
        // if the to-do list is not empty, loop through the whole list to show users what tasks are inside
        for (task in toDoList) {
            println("Task: " + task?.taskName + ", Deadline: " + task?.deadline)
        }
    }
}

// add a new task into the toDoList
fun add(toDoList: MutableList<Task?>) {
    println()
    var newTaskName: String? // define a newTaskName variable

    // use a do while loop to check for repetition or null & empty task name input
    do {
        var checkRepeatAndError = false // reset it to false so if we didn't catch any error, then it will leave the while loop
        print("Which task do you want to add? ")
        newTaskName = readLine()

        // use a for loop to check for any repetition task name input
        for (task in toDoList) {
            if (task?.taskName == newTaskName) {
                println("The task '$newTaskName' already exists in the to-do list. Please enter a new task.")
                checkRepeatAndError = true // make it 'true' so the while loop will run again
                break // already found the repeated task, no need to keep running the for loop
            }
        }

        // use an if statement to check for null or empty task name input
        if (newTaskName == "null" || newTaskName == "") {
            println("null or empty is not a valid task name. Please enter a new task.")
            checkRepeatAndError = true // make it 'true' so the while loop will run again
        }

    } while(checkRepeatAndError)

    print("When is the deadline for this task? ")
    val deadline: String? = readLine()

    val newTask = Task(newTaskName, deadline) // create a new task object to store valid task name and deadline
    toDoList.add(newTask) // add the valid new task object into the toDoList
    println("Congrats! You have added the task '$newTaskName' successfully to your list!") // print out successful message
}

// remove a task from the toDoList
fun remove(toDoList: MutableList<Task?>) {
    println()
    print("Which task do you want to remove? ")
    val removeTask = readLine()
    var matchedTask: Task? = null // initialize the matchedTask to null

    // loop through the whole toDoList to check if the user request name matches any task in the toDoList
    for (task in toDoList)
    {
        // check for task name match
        if (task?.taskName == removeTask) {
            matchedTask = task // must remove it outside of the for loop, otherwise would get an error due to the memory issue
            println("Congrats! You have removed the task '$removeTask' successfully from your list!")
        }
    }

    // if matchedTask == null, that means there isn't any matched task name in the list, so we won't be able to remove anything
    if (matchedTask == null) {
        println("The task '$removeTask' wasn't removed. Please make sure you enter the task name correctly.")
    } else {
        toDoList.remove(matchedTask) // remove the entire object from the list
    }
}

// check if the user wants to play again or not
fun playAgain(): Boolean {
    println()
    var doubleCheck: String?

    // double-check if the user really wants to quit the game or just accidentally press the 0 key
    do {
        print("Are you sure that you want to quit the App (y/n)")
        doubleCheck = readLine()
    } while(doubleCheck != "y" && doubleCheck != "n")

    return doubleCheck != "y"
}

// main function of the To-Do List App
fun main() {
    val task1 = Task("homework", "2/7/2021") // create first task object
    val task2 = Task("mop floor", "2/8/2021") // create second task object
    val toDoList: MutableList<Task?> = mutableListOf(task1, task2) // create a mutable list to store all the task objects

    do {
        var isPlayAgain = true // by default, the app will keep running until the user press 0 to quit
        println()
        println("Welcome to Amazing To-Do List App")
        println("1: Display To-Do List")
        println("2: Add Item to Your List")
        println("3: Remove Item from Your List")
        println("0: Quit The App")
        print("Please choose an option from the menu: ")
        val userChoice = readLine()

        // call different functions based on the user's choice
        when (userChoice) {
            "1" -> display(toDoList)
            "2" -> add(toDoList)
            "3" -> remove(toDoList)
            "0" -> isPlayAgain = playAgain()
            else -> println("Invalid Input")
        }
    } while (isPlayAgain)

    // when the user is done using the app, print out the thank you message
    println("Thank you for using our App! We look forward to serving you again soon!")
}

