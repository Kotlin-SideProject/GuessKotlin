package com.angus.guesskotlin

import java.util.*
import javax.net.ssl.SNIServerName
import kotlin.random.Random

class SecretNumber{
    var secret = Random.nextInt(10) + 1
    var count = 0

    fun validate(number:Int) : Int{
        count++
        return number - secret
    }
    fun reset(){
        secret = Random.nextInt(10) + 1
        count = 0
    }
}

fun main() {
    val secretNumber = SecretNumber()
//    println(secretNumber.secret)
    println(secretNumber.validate(2))
//    println(secretNumber.count)
    println("secret:${secretNumber.secret}\ncount:${secretNumber.count}")


}