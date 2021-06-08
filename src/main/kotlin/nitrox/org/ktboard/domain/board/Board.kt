package nitrox.org.ktboard.domain.board

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Board(@Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long,
                 val name: String,
                 val description: String,
                 val creationDate: LocalDateTime) {

    @OneToMany(mappedBy = "board", cascade=[CascadeType.PERSIST])
    var columns: List<Column>? = null

    var finished = false

    fun isFinishable(): Boolean {
        return when {
            this.columns == null || this.columns!!.isEmpty()-> true
            this.columns!!.size > 0 && this.columns!!.none{it.hasActiveTasks()} -> true
            else -> false
        }
    }

    fun allTasks(): List<Task> {
        var tasksBoard = listOf<Task>()
        if(columns != null && columns!!.isNotEmpty()) {
            tasksBoard = columns!!.flatMap {
                if (it.tasks != null) {
                    it.tasks!!
                } else {
                    listOf<Task>()
                }
            }
        }
        return tasksBoard
    }
}