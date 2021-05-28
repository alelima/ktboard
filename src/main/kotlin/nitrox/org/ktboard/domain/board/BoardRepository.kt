package nitrox.org.ktboard.domain.board

interface BoardRepository {
    fun save(board: Board): Board
    fun findByIdOrNull(id: Long): Board?
    fun findByName(name: String): List<Board>
}