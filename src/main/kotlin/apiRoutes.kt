
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import java.security.KeyFactory
import java.security.spec.PKCS8EncodedKeySpec
import java.util.*
import javax.crypto.Cipher

data class TwoArgs(val privatekeybase64: String,val textbase64: String)
data class DecryptionResponse(val decryptedText: String)

fun Route.apiRoutes() {
    route("/api") {
        post("/decrypt") {
            try {
                val twoArgs = call.receive<TwoArgs>()

                if (twoArgs.privatekeybase64.isEmpty() || twoArgs.textbase64.isEmpty()) {
                    call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Empty input"))
                    return@post
                }

                val keyBytes = Base64.getDecoder().decode(twoArgs.privatekeybase64)
                val keySpec = PKCS8EncodedKeySpec(keyBytes)
                val keyFactory = KeyFactory.getInstance("RSA")

                val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
                cipher.init(Cipher.DECRYPT_MODE, keyFactory.generatePrivate(keySpec))
                val cipherBytes = Base64.getDecoder().decode(twoArgs.textbase64)
                val decryptedBytes = cipher.doFinal(cipherBytes)
                val base64EncodedString = Base64.getEncoder().encodeToString(decryptedBytes)

                call.respond(HttpStatusCode.OK, DecryptionResponse(base64EncodedString))
            } catch (e: Exception) {
                call.respond(HttpStatusCode.OK, DecryptionResponse(""))
            }
        }
    }
    route("/hello") {
        get("/") {
            call.respond("hello")
        }
    }
}
