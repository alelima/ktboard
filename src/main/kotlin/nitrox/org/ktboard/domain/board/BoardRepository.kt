package nitrox.org.ktboard.domain.board

interface BoardRepository {
    fun save(board: Board)
    fun findById(id: Long): Board
    fun findByName(name: String): List<Board>
}