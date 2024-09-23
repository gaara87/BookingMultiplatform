package com.awesome.shizzle

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform