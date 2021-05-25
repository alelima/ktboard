package nitrox.org.ktboard.domain.board

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Column(@Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long,
             val title: String,
             val creationDate: LocalDateTime) {

    @ManyToOne
    var board: Board? = null

    @OneToMany(mappedBy = "column", cascade=[CascadeType.PERSIST])
    var tasks: List<Task>? = null

    fun hasTasks(): Boolean {
        return tasks != null && tasks!!.isNotEmpty()
    }

    fun hasActiveTasks(): Boolean {
        return if (tasks == null) {
            false
        } else {
            tasks!!.any {
                it.finalized == false
            }
        }
    }
}