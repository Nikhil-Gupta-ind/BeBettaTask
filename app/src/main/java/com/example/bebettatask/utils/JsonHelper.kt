package com.example.bebettatask.utils

import android.content.Context
import androidx.annotation.RawRes
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader
import java.io.StringWriter
import java.io.Writer

class JsonHelper(
    val context: Context
) {

    fun getFeedFromRaw(@RawRes resId: Int): String {
        val stream: InputStream = context.resources.openRawResource(resId)
        val writer: Writer = StringWriter()
        val buffer = CharArray(1024)
        try {
            val reader: Reader = BufferedReader(InputStreamReader(stream, "UTF-8"))
            var n: Int
            while (reader.read(buffer).also { n = it } != -1) {
                writer.write(buffer, 0, n)
            }
        } finally {
            stream.close()
        }

        return writer.toString()
    }
}