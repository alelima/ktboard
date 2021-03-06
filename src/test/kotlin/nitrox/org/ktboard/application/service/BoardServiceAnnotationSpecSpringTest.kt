package nitrox.org.ktboard.application.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.longs.shouldBeExactly
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.startWith
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import nitrox.org.ktboard.domain.board.Board
import nitrox.org.ktboard.domain.board.Column
import nitrox.org.ktboard.domain.board.Task
import nitrox.org.ktboard.infrastructure.bd.BoardRepositoryJPA
import java.time.LocalDateTime

internal class BoardServiceAnnotationSpecSpringTest : AnnotationSpec() {

    private lateinit var boardService: BoardService
    private val boardRepository: BoardRepositoryJPA = mockk<BoardRepositoryJPA>()

    @BeforeEach
    fun beforeTest() {
        clearMocks(boardRepository)
        boardService = BoardService(boardRepository)
    }

    @Test
    fun finishBoardWithNoColumns() {
        val board = Board(1L, "Projeto X", "Projeto do produto X", LocalDateTime.now())

        every { boardRepository.save(board)} returns board
        every { boardRepository.findByIdOrNull(1L)} returns board

        val resultBoad = boardService.finishBoard(1L)

        verify { boardRepository.save(board) }
        resultBoad!!.id shouldBeExactly board.id
        resultBoad.name shouldBe board.name
    }

    @Test
    fun finishBoardWithColumnsAndNoTasks() {
        val board = Board(1L, "Projeto X", "Projeto do produto X", LocalDateTime.now())
        val column = Column(1L, "Backlog", LocalDateTime.now())
        board.columns = listOf<Column>(column)

        every { boardRepository.save(board)} returns board
        every { boardRepository.findByIdOrNull(1L)} returns board

        val resultBoad = boardService.finishBoard(1L)

        resultBoad!!.id shouldBeExactly board.id
        resultBoad.name shouldBe board.name
    }

    @Test
    fun finishBoardWithColumnsAndNoActiveTasks() {
        val board = Board(1L, "Projeto X", "Projeto do produto X", LocalDateTime.now())
        val column = Column(1L, "Backlog", LocalDateTime.now())
        val finalizedTask = Task(1L, "Contruir servi??o x", "contruir servi??o necess??rio",
            LocalDateTime.now(), LocalDateTime.now().plusMonths(1)).apply {
            this.finalized = true
        }

        column.tasks = listOf<Task>(finalizedTask)
        board.columns = listOf<Column>(column)

        every { boardRepository.save(board)} returns board
        every { boardRepository.findByIdOrNull(1L)} returns board

        val resultBoad = boardService.finishBoard(1L)

        resultBoad!!.id shouldBeExactly board.id
        resultBoad.name shouldBe board.name
        finalizedTask shouldBeIn resultBoad.allTasks()
    }

    @Test
    fun finishBoardWithColumnsAndActiveTasks() {
        val board = Board(1L, "Projeto X", "Projeto do produto X", LocalDateTime.now())
        val column = Column(1L, "Backlog", LocalDateTime.now())
        val activeTask = Task(1L, "Contruir servi??o x", "contruir servi??o necess??rio",
            LocalDateTime.now(), LocalDateTime.now().plusMonths(1))
        val finalizedTask = Task(2L, "Contruir servi??o y", "contruir servi??o necess??rio",
            LocalDateTime.now(), LocalDateTime.now().plusMonths(1)).apply {
            this.finalized = true
        }


        column.tasks = listOf<Task>(activeTask, finalizedTask)
        board.columns = listOf<Column>(column)

        every { boardRepository.findByIdOrNull(1L)} returns board

        val exception = shouldThrow<RuntimeException> {
            boardService.finishBoard(1L)
        }

        verify(exactly = 0) { boardRepository.save(board) }
        exception.message should startWith("N??o ?? possivel arquivar")
    }

}