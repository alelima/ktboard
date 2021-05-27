package nitrox.org.ktboard.extensions

import io.kotest.core.spec.style.*
import io.kotest.extensions.spring.SpringExtension

/**
 *  Essa classe dever ser usada quando é preciso fazer injeções pelo mecanismo de injeção do Spring
 */
open class KBoardFunSpec : FunSpec() {
    override fun extensions() = listOf(SpringExtension)
}

open class KBoardWordSpec : WordSpec() {
    override fun extensions() = listOf(SpringExtension)
}

open class KBoardShouldSpec : ShouldSpec() {
    override fun extensions() = listOf(SpringExtension)
}

open class KBoardFreeSpec : FreeSpec() {
    override fun extensions() = listOf(SpringExtension)
}

open class KBoardFeatureSpec : FeatureSpec() {
    override fun extensions() = listOf(SpringExtension)
}

open class KBoardExpectSpec : ExpectSpec() {
    override fun extensions() = listOf(SpringExtension)
}

open class KBoardDescribeSpec : DescribeSpec() {
    override fun extensions() = listOf(SpringExtension)
}

open class KBoardBehaviorSpec : BehaviorSpec() {
    override fun extensions() = listOf(SpringExtension)
}

open class KBoardAnnotationSpec : AnnotationSpec() {
    override fun extensions() = listOf(SpringExtension)
}

open class KBoardStringSpec : StringSpec() {
    override fun extensions() = listOf(SpringExtension)
}