import script_test.MaterialDefScript
import java.io.File
import kotlin.script.experimental.api.*
import kotlin.script.experimental.host.toScriptSource
import kotlin.script.experimental.jvmhost.BasicJvmScriptingHost
import kotlin.script.experimental.jvmhost.createJvmCompilationConfigurationFromTemplate
import kotlin.system.measureTimeMillis

fun main(vararg args: String) {
    println(MaterialDefScript::class)

    if (args.size != 1) {
        println("usage: <app> <script file>")
    } else {
        val scriptFile = File(args[0])
        println("Executing script $scriptFile")
        val report = evalFile(scriptFile)
        report.reports.forEach {
            println(it)
        }
        val result = report.valueOrThrow()
        println(result.returnValue.scriptClass)
        println(result.returnValue.scriptInstance)
        val convert = result.returnValue.scriptInstance as MaterialDefScript
        println(buildString {
            appendLine("Got result!")
            appendLine(" Name: ${convert.name}")
            appendLine(" Is magic: ${convert.magic}")
            appendLine(" State: ${convert.state}")
            appendLine(" Volatility: ${convert.volatility}")
            appendLine(" Has onCollide method: ${convert.onCollideAction != null}")
        })
        if (convert.onCollideAction != null) {
            convert.onCollideAction?.invoke("Hello from on collide action")
        }

        println("\nTiming script $scriptFile over 20 evaluations")
        val time = measureTimeMillis {
            repeat(20) {
                println("Running script $it")
                evalFile(scriptFile)
            }
        }
        println("Took $time ms")
    }
}

val config = createJvmCompilationConfigurationFromTemplate<MaterialDefScript>()
val host = BasicJvmScriptingHost()
fun evalFile(scriptFile: File): ResultWithDiagnostics<EvaluationResult> {
    return host.eval(scriptFile.toScriptSource(), config, null)
}
