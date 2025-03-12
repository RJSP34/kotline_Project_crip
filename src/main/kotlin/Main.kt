
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 5000, host = "0.0.0.0") {
        install(ContentNegotiation) {
            gson {

            }
        }
        install(DefaultHeaders)
        install(CallLogging)
        install(Routing) {
            apiRoutes() // Register your API routes
        }
    }.start(wait = true)
}
