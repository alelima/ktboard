package nitrox.org.ktboard.application.service

import com.ninjasquad.springmockk.MockkBean
import io.kotest.matchers.longs.shouldBeExactly
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.junit5.MockKExtension
import nitrox.org.ktboard.domain.board.Board
import nitrox.org.ktboard.domain.board.Column
import nitrox.org.ktboard.domain.board.Task
import nitrox.org.ktboard.extensions.KBoardExpectSpec
import nitrox.org.ktboard.infrastructure.bd.BoardRepositoryJPA
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

//Dynamic Test style

@ExtendWith(SpringExtension::class, MockKExtension::class)
@SpringBootTest
internal class BoardServiceExpectSpecDynTest(boardService: BoardService) : KBoardExpectSpec() {

    @MockkBean
    private lateinit var boardRepository: BoardRepositoryJPA

    init {

        val boardNoColumns = Board(1L, "Projeto X", "Projeto do produto X", LocalDateTime.now())

        val boardWithColumnsWithoutTasks = Board(2L, "Projeto Y", "Projeto do produto Y", LocalDateTime.now())
        val columnWithoutTasks = Column(1L, "Backlog", LocalDateTime.now())
        boardWithColumnsWithoutTasks.columns = listOf<Column>(columnWithoutTasks)

        val boardWithColumnsAndFinalizedTasks = Board(3L, "Projeto Z", "Projeto do produto Z", LocalDateTime.now())
        val columnWithTasks = Column(2L, "Backlog", LocalDateTime.now())
        val taskFinalized = Task(1L, "Contruir serviço Z", "contruir serviço necessário",
            LocalDateTime.now(), LocalDateTime.now().plusMonths(1)).apply {
            this.finalized = true
        }
        columnWithTasks.tasks = listOf<Task>(taskFinalized)
        boardWithColumnsAndFinalizedTasks.columns = listOf<Column>(columnWithTasks)

        listOf(boardNoColumns, boardWithColumnsWithoutTasks, boardWithColumnsAndFinalizedTasks).forEachIndexed { index, board->
            expect("Arquivar quadro ${board.name} que ou não tenham colunas ou não tenham colunas com tarefas ativas") {
                every { boardRepository.save(board)} returns board
                every { boardRepository.findByIdOrNull(index.toLong())} returns board

                val resultBoad = boardService.finishBoard(index.toLong())
                resultBoad!!.id shouldBeExactly board.id
                resultBoad!!.name shouldBe board.name
            }
        }
    }
}