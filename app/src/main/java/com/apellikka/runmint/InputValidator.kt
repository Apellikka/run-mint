package com.apellikka.runmint

import java.text.DecimalFormatSymbols

class InputValidator(
    symbols: DecimalFormatSymbols = DecimalFormatSymbols.getInstance()
) {
    private val decimalSeparator = symbols.decimalSeparator

    /**
     * Validates distance input to be of form NUMBER.NUMBERNUMBER
     * e.g. 2.44 or 54.23
     */
    fun validateDistanceInput(input: String): String {
        if (input.matches("\\D".toRegex())) return ""
        if (input.matches("0+".toRegex())) return "0"

        val sb = StringBuilder()

        var hasDecimalSep = false

        for (char in input) {
            if (char.isDigit()) {
                if (sb.isNotEmpty()) {
                    val split = sb.split(decimalSeparator)
                    if (split.size > 1 && split[1].length == 2) {
                        break
                    }
                }
                sb.append(char)
                continue
            }
            if (char == decimalSeparator && !hasDecimalSep && sb.isNotEmpty()) {
                sb.append(char)
                hasDecimalSep = true
            }
        }
        return sb.toString()
    }

    /**
     * Validates hour input to be between 0 and 23
     */
    fun validateHourInput(input: String): String {
        if (input.matches("\\D".toRegex())) return ""
        if (input.matches("0+".toRegex())) return "0"

        val sb = StringBuilder()
        var first = true

        for (char in input) {
            if (sb.isNotEmpty() && sb.length == 2) {
                break
            }
            if (first && char.isDigit()) {
                sb.append(char)
                first = false
                continue
            }
            if (sb.isNotEmpty() && sb[0] == '1' && char.isDigit()) {
                sb.append(char)
                continue
            }
            if (sb.isNotEmpty() && sb[0] == '2' && char.isDigit() && char.digitToInt() < 4) {
                sb.append(char)
                continue
            }
        }
        return sb.toString()
    }

    /**
     * Validates minutes input to be between 0 and 59
     */
    fun validateMinuteInput(input: String): String {
        if (input.matches("\\D".toRegex())) return ""
        if (input.matches("0+".toRegex())) return "0"

        val sb = StringBuilder()
        var first = true

        for (char in input) {
            if (sb.isNotEmpty() && sb.length == 2) {
                break
            }
            if (first && char.isDigit() && char.digitToInt() < 6) {
                sb.append(char)
                first = false
                continue
            }
            if (sb.isNotEmpty() && char.isDigit() && sb[0].digitToInt() < 9) {
                sb.append(char)
                continue
            }
        }
        return sb.toString()
    }
}