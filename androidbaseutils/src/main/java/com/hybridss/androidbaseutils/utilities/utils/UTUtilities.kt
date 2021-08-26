package com.baz.utils

import org.apache.commons.codec.binary.Base64
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object UTUtilities {
    fun base64Encoding(str: String): String {
        var str = str
        str = str.replace(" ", "+")
        val bytesEncoded =
            String(str.toByteArray(StandardCharsets.UTF_8))
        return String(Base64.encodeBase64(bytesEncoded.toByteArray()))
    }

    fun base64Dencoding(str: String): String {
        val bytesEncoded =
            String(str.toByteArray(StandardCharsets.UTF_8))
        return String(Base64.decodeBase64(bytesEncoded.toByteArray()))
    }

    fun encryptionPass(s: String): String {
        val MD5 = "MD5"
        try {
            // Sea crea el MD5 hash
            val digest = MessageDigest
                .getInstance(MD5)
            digest.update(s.toByteArray())
            val messageDigest = digest.digest()

            // Se crea la cadena Hexadecimal
            val hexString = StringBuilder()
            for (aMessageDigest in messageDigest) {
                var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                while (h.length < 2)
                    h = "0$h"
                hexString.append(h)
            }
            return hexString.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
    }

    fun capitalizarPrimerPalabra(it: String): Any? {
        if (it.isEmpty()) return it
        else return it.substring(0, 1).toUpperCase() + it.substring(1).toLowerCase()
    }
}