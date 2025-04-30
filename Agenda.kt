// ---------- MODELO DE DADOS ----------
data class Pessoa(val nome: String, val endereco: String, val telefone: String)
data class Aluno(val nome: String, val nota1: Double, val nota2: Double) {
    val media = (nota1 + nota2) / 2
    val status = if (media >= 5) "Aprovado" else "Reprovado"
}
data class PessoaAltura(val nome: String, val altura: Double)
data class Funcionario(val matricula: Int, val nome: String, val salario: Double)

// -------SISTEMA AGENDA ----------
fun sistemaAgenda() {
    val agenda = mutableListOf<Pessoa>()
    while (true) {
        println(
            """
            === MENU AGENDA ===
            1. Cadastrar
            2. Pesquisar por nome
            3. Classificar por nome
            4. Apresentar todos os registros
            5. Voltar ao menu principal
        """.trimIndent()
        )
        when (readln()) {
            "1" -> {
                if (agenda.size >= 10) println("Agenda cheia!") else {
                    repeat(10 - agenda.size) {
                        print("Nome: "); val nome = readln()
                        print("Endereço: "); val endereco = readln()
                        print("Telefone: "); val telefone = readln()
                        agenda.add(Pessoa(nome, endereco, telefone))
                    }
                }
            }
            "2" -> {
                print("Digite o nome: ")
                val nome = readln()
                val pessoa = agenda.find { it.nome.equals(nome, true) }
                println(pessoa ?: "Pessoa não encontrada.")
            }
            "3" -> {
                agenda.sortBy { it.nome }
                println("Registros ordenados.")
            }
            "4" -> agenda.forEach { println(it) }
            "5" -> return
            else -> println("Opção inválida.")
        }
    }
}

// -- SISTEMA DE NOTAS
fun sistemaNotas() {
    val alunos = mutableListOf<Aluno>()
    while (true) {
        println(
            """
            === MENU NOTAS ===
            1. Cadastrar e ordenar por nome
            2. Pesquisar por nome
            3. Apresentar todos os registros
            4. Voltar ao menu principal
        """.trimIndent()
        )
        when (readln()) {
            "1" -> {
                if (alunos.size >= 20) println("Registros já cadastrados.") else {
                    repeat(20 - alunos.size) {
                        print("Nome: "); val nome = readln()
                        print("Nota 1: "); val n1 = readln().toDouble()
                        print("Nota 2: "); val n2 = readln().toDouble()
                        alunos.add(Aluno(nome, n1, n2))
                    }
                    alunos.sortBy { it.nome }
                }
            }
            "2" -> {
                print("Digite o nome: ")
                val nome = readln()
                val aluno = alunos.find { it.nome.equals(nome, true) }
                if (aluno != null)
                    println("${aluno.nome}: Média = ${aluno.media}, ${aluno.status}")
                else
                    println("Aluno não encontrado.")
            }
            "3" -> alunos.forEach {
                println("${it.nome}: Nota1 = ${it.nota1}, Nota2 = ${it.nota2}, Média = ${it.media}, ${it.status}")
            }
            "4" -> return
            else -> println("Opção inválida.")
        }
    }
}

// -SISTEMA ALTURA ----------
fun sistemaAltura() {
    val pessoas = mutableListOf<PessoaAltura>()
    while (true) {
        println(
            """
            === MENU ALTURA ===
            1. Cadastrar
            2. Mostrar altura <= 1.5m
            3. Mostrar altura > 1.5m
            4. Mostrar altura entre 1.5m e 2.0m
            5. Média das alturas
            6. Voltar ao menu principal
        """.trimIndent()
        )
        when (readln()) {
            "1" -> {
                if (pessoas.size >= 15) println("Registros já cadastrados.") else {
                    repeat(15 - pessoas.size) {
                        print("Nome: "); val nome = readln()
                        print("Altura: "); val altura = readln().toDouble()
                        pessoas.add(PessoaAltura(nome, altura))
                    }
                }
            }
            "2" -> pessoas.filter { it.altura <= 1.5 }.forEach { println(it) }
            "3" -> pessoas.filter { it.altura > 1.5 }.forEach { println(it) }
            "4" -> pessoas.filter { it.altura > 1.5 && it.altura < 2.0 }.forEach { println(it) }
            "5" -> println("Média das alturas: ${pessoas.map { it.altura }.average()}")
            "6" -> return
            else -> println("Opção inválida.")
        }
    }
}

// ------- SISTEMA FUNCIONÁRIOS -----
fun sistemaFuncionarios() {
    val funcionarios = mutableListOf<Funcionario>()
    while (true) {
        println(
            """
            === MENU FUNCIONÁRIOS ===
            1. Cadastrar e ordenar por matrícula
            2. Pesquisar por matrícula
            3. Mostrar salário > R$1000
            4. Mostrar salário < R$1000
            5. Mostrar salário == R$1000
            6. Voltar ao menu principal
        """.trimIndent()
        )
        when (readln()) {
            "1" -> {
                if (funcionarios.size >= 20) println("Registros já cadastrados.") else {
                    repeat(20 - funcionarios.size) {
                        print("Matrícula: "); val mat = readln().toInt()
                        print("Nome: "); val nome = readln()
                        print("Salário: "); val salario = readln().toDouble()
                        funcionarios.add(Funcionario(mat, nome, salario))
                    }
                    funcionarios.sortBy { it.matricula }
                }
            }
            "2" -> {
                print("Matrícula: "); val mat = readln().toInt()
                val f = funcionarios.find { it.matricula == mat }
                println(f ?: "Funcionário não encontrado.")
            }
            "3" -> funcionarios.filter { it.salario > 1000 }.forEach { println(it) }
            "4" -> funcionarios.filter { it.salario < 1000 }.forEach { println(it) }
            "5" -> funcionarios.filter { it.salario == 1000.0 }.forEach { println(it) }
            "6" -> return
            else -> println("Opção inválida.")
        }
    }
}

// ------- MENU PRINCIPAL ---
fun main() {
    while (true) {
        println(
            """
            ===== SISTEMA MULTIFUNÇÕES =====
            1. Sistema de Agenda
            2. Sistema de Notas dos Alunos
            3. Sistema de Nome e Altura
            4. Sistema de Funcionários
            5. Sair
        """.trimIndent()
        )
        when (readln()) {
            "1" -> sistemaAgenda()
            "2" -> sistemaNotas()
            "3" -> sistemaAltura()
            "4" -> sistemaFuncionarios()
            "5" -> {
                println("Encerrando o programa...")
                return
            }
            else -> println("Opção inválida.")
        }
    }
}
