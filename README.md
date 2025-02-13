# 🚀 GraphQL in Kotlin Multiplatform (KMP) with Apollo | Full Guide

Learn how to integrate **GraphQL in a Kotlin Multiplatform (KMP) project** using **Apollo GraphQL**. In this tutorial, we fetch country data from `https://countries.trevorblades.com/` using Apollo’s GraphQL client, making it easy to work with strongly typed data across Android, iOS, and other platforms.

## 📌 What You’ll Learn
- Setting up **Apollo GraphQL** in a KMP project
- Writing **GraphQL Queries** and handling responses
- Using **coroutines for asynchronous network calls**
- Sharing GraphQL data between **Android & iOS**

## 🛠️ Setup & Installation
### 1️⃣ Add Apollo to `shared` module
```kotlin
kotlin {
    sourceSets.commonMain.dependencies {
        implementation("com.apollographql.apollo3:apollo-runtime:3.x.x")
    }
}
```

### 2️⃣ Define GraphQL Query (`countriesQuery.graphql`)
```graphql
query GetCountries {
  countries {
    code
    name
    emoji
    languages {
      name
    }
  }
}
```

### 3️⃣ Execute Query in Kotlin
```kotlin
class CountryRepository(apolloClient: ApolloClient) {
    suspend fun getCountries(): List<Country> {
        val response = apolloClient.query(GetCountriesQuery()).execute()
        return response.data?.countries?.map { it.toCountry() } ?: emptyList()
    }
}
```

## 📺 Watch the Full Video
🎥 **[Watch on YouTube](#)** (Link to your video here)

## 🔗 Resources
- [Apollo GraphQL Docs](https://www.apollographql.com/docs/android/)
- [Kotlin Multiplatform Guide](https://kotlinlang.org/docs/multiplatform.html)

⭐ **Star this repo if you found it useful!** 🚀

