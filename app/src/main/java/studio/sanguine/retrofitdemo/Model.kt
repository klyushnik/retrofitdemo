package studio.sanguine.retrofitdemo

import org.json.JSONObject
import java.io.Serializable

data class Book(
                var id: Int?,
                var title: String,
                var subtitle: String,
                var author: String,
                var publisher: String
                ) : Serializable {
                    fun toJson() : JSONObject{
                        val jso = JSONObject()
                        jso.put("title", title)
                        jso.put("subtitle", subtitle)
                        jso.put("author", author)
                        jso.put("publisher", publisher)
                        return jso
                    }
                }