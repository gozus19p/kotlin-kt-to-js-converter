package com.manuelgozzi.plugins

import com.manuelgozzi.handler.translate.KotlinToJavaScriptTranslator
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*

fun Application.configureRouting() {

    routing {
        post("/api/v1/translate/javascript") {

            call.respondText(
                KotlinToJavaScriptTranslator()
                .doActionOnKotlinSourceCode(call.receiveText()))
        }
        post("/api/v1/execute") {

            TODO("To be implemented")
        }
    }
}
