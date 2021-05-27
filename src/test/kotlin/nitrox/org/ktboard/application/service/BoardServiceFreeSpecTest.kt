package nitrox.org.ktboard.application.service

import io.kotest.core.spec.style.FreeSpec
import io.kotest.core.spec.style.FunSpec
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class BoardServiceFreeSpecTest : FreeSpec() {

    init {
        "Archive unused boards " - {
            "with no columns" - {
                "archive with sucess" {
                    println("Teste 1")
                }
            }
            "with columns with finalized tasks" - {
                "archive with sucess" {
                    println("Teste 2")
                }
            }
            "with columns with active tasks" - {
                "fail in archive" {
                    println("Teste 3")
                }
            }
        }
    }
}