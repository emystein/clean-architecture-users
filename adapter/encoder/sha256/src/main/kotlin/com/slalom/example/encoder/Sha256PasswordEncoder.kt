package com.slalom.example.encoder

import com.slalom.example.usecase.port.PasswordEncoder
import org.apache.commons.codec.digest.DigestUtils

class Sha256PasswordEncoder : PasswordEncoder {
    override fun encode(str: String?): String? {
        return DigestUtils.sha256Hex(str)
    }
}