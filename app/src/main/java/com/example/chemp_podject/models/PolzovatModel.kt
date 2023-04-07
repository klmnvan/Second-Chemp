package com.example.chemp_podject.models

import java.io.Serializable

data class PolzovatModel(
    var firstname: String,
    var lastname: String,
    var middlename: String,
    var bith: String,
    var pol: String
) : Serializable
