package nitrox.org.ktboard.application.service

import io.kotest.core.spec.style.FunSpec
import io.mockk.junit5.MockKExtension
import nitrox.org.ktboard.extensions.KBoardFunSpec
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class, MockKExtension::class)
@SpringBootTest
internal class BoardServiceFunSpecTest(boardService: BoardService) : KBoardFunSpec() {

    init {
        test("Archive Boards with no columns") {
            boardService.achieveBoard(1L)
        }

        test("Archive Boards with columns and finalized tasks") {

        }

        test("Archive Boards with columns and active tasks") {

        }
    }
}