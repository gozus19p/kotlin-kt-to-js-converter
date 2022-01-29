package com.manuelgozzi.handler.translate

import com.manuelgozzi.handler.KotlinRunner
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException

/**
 * @author manu
 * @date 29/01/22
 * @time 10:26
 */
class KotlinToJavaScriptTranslator : KotlinRunner {

    override fun doActionOnKotlinSourceCode(kotlinSourceScript: String): String {

        val filePrefix = "" + System.currentTimeMillis()

        val kotlinTempFile = File.createTempFile(filePrefix, ".kt")
        val kotlinTempFileWriter = FileWriter(kotlinTempFile)

        val javaScriptTempFile = File.createTempFile(filePrefix, ".js")
        val javaScriptFileReader = FileReader(javaScriptTempFile)

        try {

            // Scrivo il sorgente Kotlin in un file temporaneo
            kotlinTempFileWriter.write(kotlinSourceScript)
            kotlinTempFileWriter.flush()
            kotlinTempFileWriter.close()

            // Lancio il comando di compilazione
            this.command(kotlinTempFile, javaScriptTempFile)
                .runCommandInsideUserDirectory()

            // Leggo l'output salvato nel file temporaneo JavaScript
            return javaScriptFileReader.readText()
        } catch (e: IOException) {

            throw IllegalArgumentException(e)
        } finally {

            // Chiudo gli stream
            javaScriptFileReader.close()

            // Cancello i file temporanei
            kotlinTempFile.delete()
            javaScriptTempFile.delete()
        }
    }

    override fun command(ktsFile: File, outputFile: File): String {

        return System.getProperty("user.home") + "/kotlinc/bin/kotlinc-js -script ${ktsFile.absolutePath} -output ${outputFile.absolutePath}"
    }
}