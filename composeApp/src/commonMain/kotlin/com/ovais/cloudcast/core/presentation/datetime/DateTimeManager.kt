package com.ovais.cloudcast.core.presentation.datetime

import com.ovais.cloudcast.utils.orZero

typealias DayNumber = Int
typealias MonthNumber = Int
typealias Year = Int

interface DateTimeManager {
    fun getMonth(monthNumber: Int): Month
    fun getFormattedDate(
        year: Int,
        month: Int,
        day: Int,
        separator: String = " "
    ): String

    fun breakDateIntoNumbers(dateString: String): Triple<DayNumber, MonthNumber, Year>
}

class DefaultDateTimeManager : DateTimeManager {
    override fun getMonth(monthNumber: Int): Month {
        return when (monthNumber) {
            1 -> Month.January
            2 -> Month.February
            3 -> Month.March
            4 -> Month.April
            5 -> Month.May
            6 -> Month.June
            7 -> Month.July
            8 -> Month.August
            9 -> Month.September
            10 -> Month.October
            11 -> Month.November
            else -> Month.December
        }
    }

    override fun getFormattedDate(year: Int, month: Int, day: Int, separator: String): String {
        return "${getMonth(month)} $day"
    }

    override fun breakDateIntoNumbers(dateString: String): Triple<DayNumber, MonthNumber, Year> {
        return try {
            val date = dateString.replace("-", ",").split(",").toList()
            Triple(
                date[0].toIntOrNull().orZero,
                date[1].toIntOrNull().orZero,
                date[2].toIntOrNull().orZero
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Triple(
                0,
                0,
                0
            )
        }
    }
}