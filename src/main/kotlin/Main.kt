fun main() {
    val card = "Maestro"
    val transfer = 16000
    val sumTransfer = 0
    println("Сумма перевода - $transfer")
    sysOut(card, transfer, sumTransfer)

}

fun sysOut(card: String, transfer: Int, sumTransfer: Int) {
    if (transfer > 150000) {
        println("Сумма больше лимита 150000")
    } else {
        when (card) {
            "Master card", "Maestro" -> {
                if (masterCard(transfer, sumTransfer) != -1.0) {
                    println("Комиссия: ${masterCard(transfer, sumTransfer)}")
                    println("Сумма перевода: ${transfer - masterCard(transfer, sumTransfer)}")
                } else {
                    println("Перевод невозможен")
                }
            }

            "Visa", "Мир" -> {
                if (visa(transfer, sumTransfer) != -1.0) {
                    println("Комиссия: ${visa(transfer, sumTransfer)}")
                    println("Сумма перевода: ${transfer - visa(transfer, sumTransfer)}")
                } else {
                    println("Перевод невозможен")

                }
            }

            "VK Pay" -> {
                if (vk(transfer, sumTransfer)) {
                    println("Комиссия: 0")
                    println("Сумма перевода: $transfer")
                } else {
                    println("Перевод невозможен")
                }
            }
        }

    }
}

fun masterCard(transfer: Int, sumTransfer: Int): Double = when (sumTransfer + transfer) {
    in 0..75_000 -> 0.0
    in 75_000..600_000 -> transfer / 100 * 0.6 + 20
    else -> -1.0
}

fun visa(transfer: Int, sumTransfer: Int): Double {
    if (sumTransfer + transfer > 600000) {
        return -1.0
    }
    val result: Double = transfer / 100 * 0.75
    if (transfer > 35.0) {
        if (result > 35.0) {
            return result
        } else {
            return 35.0
        }

    }
    return -1.0
}

fun vk(transfer: Int, sumTransfer: Int): Boolean {
    if (transfer < 15000) {
        if (sumTransfer + transfer < 40000) {
            return true
        }
    }
    return false
}