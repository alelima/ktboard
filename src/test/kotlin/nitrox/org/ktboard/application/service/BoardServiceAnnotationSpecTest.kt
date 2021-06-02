package nitrox.org.ktboard.application.service

import com.ninjasquad.springmockk.MockkBean
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.longs.shouldBeExactly
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.startWith
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import nitrox.org.ktboard.domain.board.Board
import nitrox.org.ktboard.domain.board.Column
import nitrox.org.ktboard.domain.board.Task
import nitrox.org.ktboard.infrastructure.bd.BoardRepositoryJPA
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

//JUnit classic style
@ExtendWith(SpringExtension::class, MockKExtension::class)
@SpringBootTest
internal class BoardServiceAnnotationSpecTest : AnnotationSpec() {

    private lateinit var boardService: BoardService

    private val boardRepository: BoardRepositoryJPA = mockk<BoardRepositoryJPA>()

    @BeforeEach
    fun beforeTest() {
        clearMocks(boardRepository)
        boardService = BoardService(boardRepository)
    }

    @Test
    fun achieveBoardWithNoColumns() {
        val board = Board(1L, "Projeto X", "Projeto do produto X", LocalDateTime.now())

        every { boardRepository.save(board)} returns board
        every { boardRepository.findByIdOrNull(1L)} returns board

        val resultBoad = boardService.achieveBoard(1L)

        resultBoad!!.id shouldBeExactly board.id
        resultBoad!!.name shouldBe board.name
    }

    @Test
    fun achieveBoardWithColumnsAndNoTasks() {
        val board = Board(1L, "Projeto X", "Projeto do produto X", LocalDateTime.now())
        val column = Column(1L, "Backlog", LocalDateTime.now())
        board.columns = listOf<Column>(column)

        every { boardRepository.save(board)} returns board
        every { boardRepository.findByIdOrNull(1L)} returns board

        val resultBoad = boardService.achieveBoard(1L)

        resultBoad!!.id shouldBeExactly board.id
        resultBoad!!.name shouldBe board.name
    }

    @Test
    fun achieveBoardWithColumnsAndNoActiveTasks() {
        val board = Board(1L, "Projeto X", "Projeto do produto X", LocalDateTime.now())
        val column = Column(1L, "Backlog", LocalDateTime.now())
        val finalizedTask = Task(1L, "Contruir serviço x", "contruir serviço necessário",
            LocalDateTime.now(), LocalDateTime.now().plusMonths(1)).apply {
            this.finalized = true
        }

        column.tasks = listOf<Task>(finalizedTask)
        board.columns = listOf<Column>(column)

        every { boardRepository.save(board)} returns board
        every { boardRepository.findByIdOrNull(1L)} returns board

        val resultBoad = boardService.achieveBoard(1L)

        resultBoad!!.id shouldBeExactly board.id
        resultBoad!!.name shouldBe board.name
        finalizedTask shouldBeIn resultBoad.allTasks()
    }

    @Test
    fun achieveBoardWithColumnsAndActiveTasks() {
        val board = Board(1L, "Projeto X", "Projeto do produto X", LocalDateTime.now())
        val column = Column(1L, "Backlog", LocalDateTime.now())
        val activeTask = Task(1L, "Contruir serviço x", "contruir serviço necessário",
            LocalDateTime.now(), LocalDateTime.now().plusMonths(1))
        val finalizedTask = Task(2L, "Contruir serviço y", "contruir serviço necessário",
            LocalDateTime.now(), LocalDateTime.now().plusMonths(1)).apply {
            this.finalized = true
        }


        column.tasks = listOf<Task>(activeTask, finalizedTask)
        board.columns = listOf<Column>(column)

        every { boardRepository.findByIdOrNull(1L)} returns board

        val exception = shouldThrow<RuntimeException> {
            boardService.achieveBoard(1L)
        }

        verify(exactly = 0) { boardRepository.save(board) }
        exception.message should startWith("Não é possivel de arquivar esse quadro")
    }

}