package nitrox.org.ktboard.application.service

import io.kotest.core.spec.style.*

//JUnit classic style
internal class BoardServiceAnnotationSpecTest : AnnotationSpec() {

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun finishBoardWithNoColumns() {
    }

    @Test
    fun finishBoardWithColumnsAndNoTasks() {
    }

    @Test
    fun finishBoardWithColumnsAndNoActiveTasks() {
    }

    @Test
    fun finishBoardWithColumnsAndActiveTasks() {
    }
}

internal class BoardServiceFunSpecTest : FunSpec() {
    init {
        test("Arquivar quadros sem colunas") {

        }

        test("Arquivar quadros com colunas e sem tarefas") {

        }

        test("Arquivar quadros sem colunas e com tarefas finalizadas") {

        }

        test("Arquivar quadros sem colunas e com tarefas ativas") {

        }
    }
}

internal class BoardServiceShouldSpecTest : ShouldSpec() {
    init {
        should("Arquivar quadros sem colunas") {

        }

        should("Arquivar quadros com colunas e sem tarefas") {

        }

        should("Arquivar quadros sem colunas e com tarefas finalizadas") {

        }

        should("Arquivar quadros sem colunas e com tarefas ativas") {

        }
    }
}

internal class BoardServiceExpectSpecTest : ExpectSpec() {
    init {
        expect ("Arquivar quadros sem colunas") {

        }
        expect("Arquivar quadros com colunas e sem tarefas") {

        }
        expect("Arquivar quadros sem colunas e com tarefas finalizadas").config(invocations = 3) {

        }
        expect("Arquivar quadros sem colunas e com tarefas ativas") {

        }
    }
}

internal class BoardServiceStringSpecTest : StringSpec() {
    init {
        "Arquivar quadros sem colunas" {

        }
        "Arquivar quadros com colunas e sem tarefas" {

        }
        "Arquivar quadros sem colunas e com tarefas finalizadas" {

        }
        "Arquivar quadros sem colunas e com tarefas ativas" {

        }
    }
}

//JavaScript e RSpec style
internal class BoardServiceDescribeSpecTest : DescribeSpec() {

    init {
        describe("Arquivar quadros sem uso") {
            it("sem colunas") {

            }
            it(" com colunas e sem tarefas") {

            }
            it("sem colunas e com tarefas finalizadas") {

            }
            it("sem colunas e com tarefas ativas") {

            }
        }
    }
}

//ScalaTest style
internal class BoardServiceWordSpecTest : WordSpec() {
    init {
        "Arquivar quadros sem uso " When {
            "sem colunas" should {
                "com sucesso" {

                }
            }
            "com colunas e sem tarefas" should {
                "com sucesso" {

                }
            }
            "com colunas e com tarefas finalizadas" should {
                "com sucesso" {

                }
            }
            "com colunas e com tarefas ativas" should {
                "não deve ser permitido" {

                }
            }
        }
    }
}

internal class BoardServiceFreeSpecTest : FreeSpec() {
    init {
        "Arquivar Quadros sem uso " - {
            "sem colunas" - {
                "com sucesso" {

                }
            }
            "com colunas e sem tarefas" - {
                "com sucesso" {

                }
            }
            "com colunas e com tarefas finalizadas" - {
                "com sucesso" {

                }
            }
            "com colunas e com tarefas ativas" - {
                "não deve ser permitido" {

                }
            }
        }
    }
}

// Cucumber Style
internal class BoardServiceFeatureSpecTest : FeatureSpec() {
    init {
        feature("Quadro") {
            scenario("Arquivar Quadro Kanban sem colunas") {

            }
            scenario("Arquivar Quadro Kanban com colunas sem tarefas ativas") {

            }
            scenario("Arquivar Quadro Kanban com colunas e tarefas ativas") {

            }
        }
    }
}

// BDD Frameworks Gherkin style
internal class BoardServiceBehaviorSpecTest : BehaviorSpec() {
    init {
        given("que o usuário tenha o perfil de gestor de quadro") {
            and(" o quadro não possui colunas") {
                When( "ele selecionar a opção arquivar quadro") {
                    then(" o quadro será arquivado e desaparece da listagem de quadros.") {

                    }
                }
            }
            and(" o quadro possui colunas e estas não possuam tarefas ativas") {
                When( "ele selecionar a opção arquivar quadro") {
                    then(" o quadro será arquivado e desaparece da listagem de quadros.") {

                    }
                }
            }
            and(" o quadro possui colunas e estas possuam tarefas ativas") {
                When( "ele selecionar a opção arquivar quadro") {
                    then(" ocorrerá um alerta que o quadro não pode ser arquivado pois ainda tem tarefas ativas") {

                    }
                }
            }
        }
    }
}




