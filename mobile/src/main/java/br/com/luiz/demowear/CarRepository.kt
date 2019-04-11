package br.com.luiz.demowear

import android.content.Context
import br.com.luiz.shared.Car
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader

object CarRepository {

    fun searchAll(context: Context): MutableList<Car> {
        val stream = context.resources.openRawResource(R.raw.cars)
        val reader = BufferedReader(InputStreamReader(stream, "UTF-8"))
        val listType = object : TypeToken<ArrayList<Car>>() {}.type
        return Gson().fromJson(reader, listType)
    }
}