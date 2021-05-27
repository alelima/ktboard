package nitrox.org.ktboard.application.service

import io.kotest.core.spec.style.FunSpec
import io.kotest.core.spec.style.WordSpec
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

//ScalaTest style
internal class BoardServiceWordSpecTest : WordSpec() {

    init {
        "Archive unused boards " When {
            "with no columns" should {
                "archive with sucess" {

                }
            }
            "with columns with finalized tasks" should {
                "archive with sucess" {

                }
            }
            "with columns with active tasks" should {
                "fail in archive" {

                }
            }
        }
    }
}