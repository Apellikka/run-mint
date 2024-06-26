package com.apellikka.runmint.viewmodels.viewmodelutils

import java.text.DecimalFormatSymbols

class InputValidator(
    symbols: DecimalFormatSymbols = DecimalFormatSymbols.getInstance()
) {
    private val decimalSeparator = symbols.decimalSeparator

    /**
     * Validates input to be between 1-299 for cadence, stride length and heart rate.
     * Could be specified for each one individually more precisely.
     */
    fun validateIntegerUnderThreeHundred(input: String): String {
        if (input.matches("\\D".toRegex())) return ""
        if (input.matches("0+".toRegex())) return ""

        val sb = StringBuilder()

        for (char in input) {
            if (char.isDigit()) {
                if (sb.length == 2 && sb[0].digitToInt() > 2) {
                    break
                }
                if (sb.isNotEmpty()) {
                    if (sb.length == 3) {
                        break
                    }
                }
                sb.append(char)
                continue
            }
        }
        return sb.toString()
    }

    /**
     * Validates speed to have a max of 2 numbers before decimal separator
     * and max of 2 after it. Input also can't start with a 0
     * Format examples: 25.15, 2.55
     */
    fun validateSpeedInput(input: String): String {
        if (input.matches("\\D".toRegex())) return ""
        if (input.matches("0+".toRegex())) return ""

        val sb = StringBuilder()

        var hasDecimalSep = false

        for (char in input) {
            if (char.isDigit()) {
                if (sb.isNotEmpty()) {
                    val split = sb.split(decimalSeparator)
                    if (split.size == 1 && sb.length == 2) {
                        break
                    }
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
     * Validates Pace to have a max of 2 numbers before decimal separator
     * and max of 2 after it. Input also can't start with a 0 and numbers after decimal separator
     * cannot exceed 59, because pace indicates minutes:seconds per kilometre.
     * Minutes are capped at 29.
     * Format examples: 15.18, 7.35
     */
    fun validatePaceInput(input: String): String {
        if (input.matches("\\D".toRegex())) return ""
        if (input.matches("0+".toRegex())) return ""

        val sb = StringBuilder()

        var hasDecimalSep = false

        for (char in input) {
            if (char.isDigit()) {
                if (sb.isNotEmpty()) {
                    val split = sb.split(decimalSeparator)
                    if (split.size == 1 && sb.length == 1) {
                        if (sb[0].digitToInt() > 2) {
                            break
                        }
                    }
                    if (split.size == 1 && sb.length == 2) {
                        break
                    }
                    if (split.size > 1 && split[1].isEmpty()) {
                        if (char.digitToInt() > 5) {
                            break
                        }
                    }
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
     * Validates distance input to be of form NUMBER.NUMBERNUMBER
     * Format examples: 2.44 or 54.23
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
    fun validateMinutesAndSecondsInput(input: String): String {
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