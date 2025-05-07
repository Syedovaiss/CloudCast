package com.ovais.cloudcast.utils


val String?.orEmpty
    get() = this ?: EMPTY_STRING
val Boolean?.orFalse
    get() = this ?: false
val Float?.orZero
    get() = this ?: 0F
val String.asImageUrl: String
    get() = "https:$this"
val Int?.orOne: Int
    get() = this ?: 1
val Int?.orZero: Int
    get() = this ?: 0

