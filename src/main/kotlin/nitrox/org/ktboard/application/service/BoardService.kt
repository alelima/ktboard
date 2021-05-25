package nitrox.org.ktboard.application.service

import nitrox.org.ktboard.domain.board.BoardRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Service
class BoardService(val boardRepository: BoardRepository) {

    fun achieveBoard(boardId: Long) {
        val board = boardRepository.findById(boardId);
        if(board.isArchievable()) {
            board.archieved = true
            boardRepository.save(board)
        }
    }
}