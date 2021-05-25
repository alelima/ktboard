package nitrox.org.ktboard.application.rest

import nitrox.org.ktboard.application.rest.form.BoardForm
import nitrox.org.ktboard.application.service.BoardService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/boards")
class BoardController(val boardService: BoardService) {

    @PostMapping
    fun insert(@RequestBody @Valid boardForm: BoardForm) { //colocar BoletoDTO depois
        //
    }

    @PutMapping("/archieve/{id}")
    fun archieve(@PathVariable id: Long) { //colocar BoletoDTO depois
        boardService.achieveBoard(id)
    }
}