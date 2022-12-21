package br.com.alura.orgs.database.converter

import androidx.room.TypeConverter
import java.math.BigDecimal

class Converters {

    @TypeConverter
    fun fromDouble(double: Double?): BigDecimal?{
      return  double?.let {
            BigDecimal(double.toString()) } ?: BigDecimal.ZERO
    }

    @TypeConverter
    fun BigDecimalToDouble(bigDecimal: BigDecimal?): Double?{
        return bigDecimal?.let{ bigDecimal.toDouble()}
    }

}