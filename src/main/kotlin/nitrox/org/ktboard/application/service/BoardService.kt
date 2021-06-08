package nitrox.org.ktboard.application.service

import nitrox.org.ktboard.domain.board.Board
import nitrox.org.ktboard.domain.board.BoardRepository
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class BoardService(val boardRepository: BoardRepository) {

    fun finishBoard(boardId: Long) : Board? {
        val board = boardRepository.findByIdOrNull(boardId)
        return if(board != null && board.isFinishable()) {
            board.archieved = true
            boardRepository.save(board)
        } else {
            throw RuntimeException("Não é possivel arquivar esse quadro, ainda possui tarefas ativas")
        }
    }
}