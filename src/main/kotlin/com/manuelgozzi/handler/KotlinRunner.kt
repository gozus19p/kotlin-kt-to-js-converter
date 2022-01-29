package com.manuelgozzi.handler

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File

/**
 * @author Manuel Gozzi
 */
interface KotlinRunner {

    private val log: Logger
        get() = LoggerFactory.getLogger(KotlinRunner::class.java)

    /**
     * It translates Kotlin source script to another language (JavaScript or Java for example).
     */
    fun doActionOnKotlinSourceCode(kotlinSourceScript: String): String

    /**
     * It retrieves Kotlin source code as file and desired output as file.
     */
    fun command(ktsFile: File, outputFile: File): String

    /**
     * Extension function that
     */
    fun String.runCommandInsideUserDirectory() {

        val proc = Runtime.getRuntime().exec(this)
        val error = String(proc.errorStream.readAllBytes())
        if (error.isNotEmpty() && error.isNotBlank()) {

            log.error(error)
        } else {

            log.info("Command [$this] has been executed successfully")
        }
    }
}