package com.ovais.cloudcast.utils


val String?.orEmpty
    get() = this ?: EMPTY_STRING
val Boolean?.orFalse
    get() = this ?: false