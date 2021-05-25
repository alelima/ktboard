package nitrox.org.ktboard.domain.board

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Task(@Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long,
           val title: String,
           val description: String,
           val initDate: LocalDateTime,
           val finalDate: LocalDateTime) {

    @ManyToOne
    var column: Column? = null

    var finalized = false;
}