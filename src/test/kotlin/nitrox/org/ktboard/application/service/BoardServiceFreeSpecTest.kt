package nitrox.org.ktboard.application.service

import io.kotest.core.spec.style.FreeSpec
import io.kotest.core.spec.style.FunSpec
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class BoardServiceFreeSpecTest : FreeSpec() {

    init {
        "Arquivar Quadros sem uso " - {
            "sem colunas" - {
                "com sucesso" {

                }
            }
            "com colunas e sem tarefas" - {
                "com sucesso" {

                }
            }
            "com colunas e com tarefas finalizadas" - {
                "com sucesso" {

                }
            }
            "com colunas e com tarefas ativas" - {
                "não deve ser permitido" {

                }
            }
        }
    }
}