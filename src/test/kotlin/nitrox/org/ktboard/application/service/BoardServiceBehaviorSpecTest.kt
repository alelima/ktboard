package nitrox.org.ktboard.application.service

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.spec.style.FunSpec
import io.kotest.core.test.TestCase
import io.kotest.matchers.longs.shouldBeExactly
import io.kotest.matchers.shouldBe
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import nitrox.org.ktboard.domain.board.Board
import nitrox.org.ktboard.infrastructure.bd.BoardRepositoryJPA
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.time.LocalDateTime

// BDD Frameworks Gherkin style
internal class BoardServiceBehaviorSpecTest : BehaviorSpec() {

    private lateinit var boardService: BoardService

    private val boardRepository: BoardRepositoryJPA = mockk<BoardRepositoryJPA>()

    override fun beforeTest(testCase: TestCase) {
        boardService = BoardService(boardRepository)
    }

    init {
        given("que o usuário tenha o perfil de gestor de quadro") {

            //User and role configuration here

            and(" o quadro não possui colunas") {

                val board = Board(1L, "Projeto X", "Projeto do produto X", LocalDateTime.now())

                When( "ele selecionar a opção arquivar quadro") {

                    every { boardRepository.save(board)} returns board
                    every { boardRepository.findByIdOrNull(1L)} returns board

                    val resultBoad = boardService.achieveBoard(1L)

                    then(" o quadro será arquivado e desaparece da listagem de quadros.") {

                        verify(exactly = 1) { boardRepository.save(board) }
                        resultBoad!!.id shouldBeExactly board.id
                        resultBoad!!.name shouldBe board.name

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