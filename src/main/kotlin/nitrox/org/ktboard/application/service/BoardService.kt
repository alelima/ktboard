package nitrox.org.ktboard.application.service

import nitrox.org.ktboard.domain.board.Board
import nitrox.org.ktboard.domain.board.BoardRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.RuntimeException

@Service
class BoardService(val boardRepository: BoardRepository) {

    fun achieveBoard(boardId: Long) : Board? {
        val board = boardRepository.findByIdOrNull(boardId);
        return if(board != null && board!!.isArchievable()) {
            board.archieved = true
            boardRepository.save(board)
        } else {
            throw RuntimeException("Quadro não é possivel de arquivar")
        }
    }
}