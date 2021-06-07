package nitrox.org.ktboard.application.service

import com.ninjasquad.springmockk.MockkBean
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.longs.shouldBeExactly
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.startWith
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import nitrox.org.ktboard.domain.board.Board
import nitrox.org.ktboard.domain.board.Column
import nitrox.org.ktboard.domain.board.Task
import nitrox.org.ktboard.extensions.KBoardFunSpec
import nitrox.org.ktboard.infrastructure.bd.BoardRepositoryJPA
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class, MockKExtension::class)
@SpringBootTest
internal class BoardServiceFunSpecTest(boardService: BoardService) : KBoardFunSpec() {

    @MockkBean
    private lateinit var boardRepository: BoardRepositoryJPA

    init {
        test("Arquivar quadros sem colunas") {

            val board = Board(1L, "Projeto X", "Projeto do produto X", LocalDateTime.now())

            every { boardRepository.save(board)} returns board
            every { boardRepository.findByIdOrNull(1L)} returns board

            val resultBoad = boardService.finishBoard(1L)

            resultBoad!!.id shouldBeExactly board.id
            resultBoad!!.name shouldBe board.name
        }

        test("Arquivar quadros com colunas e sem tarefas") {
            val board = Board(1L, "Projeto X", "Projeto do produto X", LocalDateTime.now())
            val column = Column(1L, "Backlog", LocalDateTime.now())
            board.columns = listOf<Column>(column)

            every { boardRepository.save(board)} returns board
            every { boardRepository.findByIdOrNull(1L)} returns board

            val resultBoad = boardService.finishBoard(1L)

            resultBoad!!.id shouldBeExactly board.id
            resultBoad!!.name shouldBe board.name
        }

        test("Arquivar quadros sem colunas e com tarefas finalizadas").config(invocations = 3) {
            val board = Board(1L, "Projeto X", "Projeto do produto X", LocalDateTime.now())
            val column = Column(1L, "Backlog", LocalDateTime.now())
            board.columns = listOf<Column>(column)
            val finalizedTask = Task(1L, "Contruir serviço x", "contruir serviço necessário",
                LocalDateTime.now(), LocalDateTime.now().plusMonths(1)).apply {
                this.finalized = true
            }

            column.tasks = listOf<Task>(finalizedTask)

            every { boardRepository.save(board)} returns board
            every { boardRepository.findByIdOrNull(1L)} returns board

            val resultBoad = boardService.finishBoard(1L)

            resultBoad!!.id shouldBeExactly board.id
            resultBoad!!.name shouldBe board.name
            finalizedTask shouldBeIn resultBoad.allTasks()
        }

        test("Arquivar quadros sem colunas e com tarefas ativas") {
            val board = Board(1L, "Projeto X", "Projeto do produto X", LocalDateTime.now())
            val column = Column(1L, "Backlog", LocalDateTime.now())
            val activeTask = Task(1L, "Contruir serviço x", "contruir serviço necessário",
                    LocalDateTime.now(), LocalDateTime.now().plusMonths(1))

            column.tasks = listOf<Task>(activeTask)
            board.columns = listOf<Column>(column)

            every { boardRepository.findByIdOrNull(1L)} returns board

            val exception = shouldThrow<RuntimeException> {
                boardService.finishBoard(1L)
            }

            verify(exactly = 0) { boardRepository.save(board) }
            exception.message should startWith("Não é possivel de arquivar esse quadro")
        }
    }
}