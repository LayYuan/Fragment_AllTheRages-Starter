

package com.raywenderlich.alltherages

import java.io.Serializable

data class Comic(val imageResId: Int, val name: String, val description: String, val url: String,
                 var text: String = "") : Serializable
