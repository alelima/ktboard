package nitrox.org.ktboard.application.service

import com.ninjasquad.springmockk.MockkBean
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.longs.shouldBeExactly
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.startWith
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import nitrox.org.ktboard.domain.board.Board
import nitrox.org.ktboard.domain.board.BoardRepository
import nitrox.org.ktboard.domain.board.Column
import nitrox.org.ktboard.domain.board.Task
import nitrox.org.ktboard.extensions.KBoardFunSpec
import nitrox.org.ktboard.infrastructure.bd.BoardRepositoryJPA
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class, MockKExtension::class)
@SpringBootTest
internal class BoardServiceFunSpecTest(boardService: BoardService) : KBoardFunSpec() {

    @MockkBean
    private lateinit var boardRepository: BoardRepositoryJPA

    init {
        test("Archive Boards with no columns") {

            val board = Board(1L, "Projeto X", "Projeto do produto X", LocalDateTime.now())

            every { boardRepository.save(board)} returns board
            every { boardRepository.findByIdOrNull(1L)} returns board

            val resultBoad = boardService.achieveBoard(1L)

            resultBoad!!.id shouldBeExactly board.id
            resultBoad!!.name shouldBe board.name
        }

        test("Archive Boards with columns and finalized tasks") {
            val board = Board(1L, "Projeto X", "Projeto do produto X", LocalDateTime.now())
            val column = Column(1L, "Backlog", LocalDateTime.now())
            board.columns = listOf<Column>(column)

            every { boardRepository.save(board)} returns board
            every { boardRepository.findByIdOrNull(1L)} returns board

            val resultBoad = boardService.achieveBoard(1L)

            resultBoad!!.id shouldBeExactly board.id
            resultBoad!!.name shouldBe board.name
        }

        test("Archive Boards with columns and active tasks") {
            val board = Board(1L, "Projeto X", "Projeto do produto X", LocalDateTime.now())
            val column = Column(1L, "Backlog", LocalDateTime.now())
            val activeTask = Task(1L, "Contruir serviço x", "contruir serviço necessário",
                    LocalDateTime.now(), LocalDateTime.now().plusMonths(1))

            column.tasks = listOf<Task>(activeTask)
            board.columns = listOf<Column>(column)

            every { boardRepository.findByIdOrNull(1L)} returns board

            val exception = shouldThrow<RuntimeException> {
                boardService.achieveBoard(1L)
            }

            verify(exactly = 1) { boardRepository.save(board) }
            exception.message should startWith("Quadro não é possivel de arquivar")
        }
    }
}