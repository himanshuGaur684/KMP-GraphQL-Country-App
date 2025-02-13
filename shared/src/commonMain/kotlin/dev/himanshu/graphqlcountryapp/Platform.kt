package dev.himanshu.graphqlcountryapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform