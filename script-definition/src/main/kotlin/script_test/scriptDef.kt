package script_test

import kotlin.script.experimental.annotations.KotlinScript
import kotlin.script.experimental.api.*
import kotlin.script.experimental.jvm.dependenciesFromCurrentContext
import kotlin.script.experimental.jvm.jvm

@KotlinScript(
    fileExtension = "materialDef.kts",
    compilationConfiguration = MaterialDefConfiguration::class
)
abstract class MaterialDefScript {
    var name = ""
    var volatility = 0.5
    var magic = false
    var state = MaterialState.SOLID

    var onCollideAction: ((Any?)->Unit)? = null

    fun onCollide(action: (Any?)->Unit) {
        onCollideAction = action
    }

    override fun toString(): String {
        return "script_test.MaterialDefScript(name='$name', volatility=$volatility, magic=$magic, state=$state)"
    }
}

object MaterialDefConfiguration: ScriptCompilationConfiguration({
    jvm {
        // Extract the whole classpath from context classloader and use it as dependencies
        dependenciesFromCurrentContext(wholeClasspath = true)
    }
    defaultImports("script_test.MaterialState.*", "script_test.MaterialDefScript")
})


