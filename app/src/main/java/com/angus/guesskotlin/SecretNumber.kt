package com.angus.guesskotlin

import java.util.*
import javax.net.ssl.SNIServerName
import kotlin.random.Random

class SecretNumber{
    val secret = Random.nextInt(10) + 1
    var count = 0

    fun validate(number:Int) : Int{
        count++
        return number - secret
    }
}

fun main() {
    val secretNumber = SecretNumber()
//    println(secretNumber.secret)
    println(secretNumber.validate(2))
//    println(secretNumber.count)
    println("secret:${secretNumber.secret}\ncount:${secretNumber.count}")


}