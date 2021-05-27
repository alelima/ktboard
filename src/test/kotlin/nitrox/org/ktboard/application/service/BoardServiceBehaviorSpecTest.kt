package nitrox.org.ktboard.application.service

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.spec.style.FunSpec
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

// BDD Frameworks Gherkin style
internal class BoardServiceBehaviorSpecTest : BehaviorSpec() {

    init {
        given("que o usuário tenha o perfil de gestor de quadro") {
            and(" o quadro não possui colunas") {
                When( "ele selecionar a opção arquivar quadro") {
                    then(" o quadro será arquivado e desaparece da listagem de quadros.") {

                    }
                }
            }
            and(" o quadro possui colunas e estas não possuam tarefas ativas") {
                When( "ele selecionar a opção arquivar quadro") {
                    then(" o quadro será arquivado e desaparece da listagem de quadros.") {

                    }
                }
            }
            and(" o quadro possui colunas e estas possuam tarefas ativas") {
                When( "ele selecionar a opção arquivar quadro") {
                    then(" ocorrerá um alerta que o quadro não pode ser arquivado pois ainda tem tarefas ativas") {

                    }
                }
            }
        }

    }
}