package nitrox.org.ktboard.application.service

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.spec.style.FunSpec
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

//JavaScript e RSpec style
internal class BoardServiceDescribeSpecTest : DescribeSpec() {

    init {
        describe("Archive unused boards") {
            it("with no columns") {
                println("Teste 1")
            }
            it("columns with finalized tasks") {
                println("Teste 2")
            }
            it("columns with active tasks") {
                println("Teste 3")
            }
        }

    }
}