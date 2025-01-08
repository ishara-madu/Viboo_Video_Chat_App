package com.pixeleye.viboo

// File: GlobalStorage.kt

// A HashMap defined at the top level (globally accessible)
val globalMap = HashMap<String, String>()

// Function to add data to the HashMap
fun addToMap(key: String, value: String) {
    globalMap[key] = value
}

// Function to retrieve data from the HashMap
fun getFromMap(key: String): String? {
    return globalMap[key]
}

// Function to check if a key exists in the HashMap
fun containsInMap(key: String): Boolean {
    return globalMap.containsKey(key)
}
