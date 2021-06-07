package nitrox.org.ktboard.application.service

import io.kotest.core.spec.style.FunSpec
import io.kotest.core.spec.style.WordSpec
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

//ScalaTest style
internal class BoardServiceWordSpecTest : WordSpec() {

    init {
        "Arquivar quadros sem uso " When {
            "sem colunas" should {
                "com sucesso" {

                }
            }
            "com colunas e sem tarefas" should {
                "com sucesso" {

                }
            }
            "com colunas e com tarefas finalizadas" should {
                "com sucesso" {

                }
            }
            "com colunas e com tarefas ativas" should {
                "não deve ser permitido" {

                }
            }
        }
    }
}