package nitrox.org.ktboard.application.rest.form

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

class BoardForm(@field:NotNull @field:NotEmpty val name: String, @field:NotNull @field:NotEmpty val description: String)