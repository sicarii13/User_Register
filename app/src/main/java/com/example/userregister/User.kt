package com.example.userregister

data class User (val userName: String? = null, val userPhone: String? = null) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}