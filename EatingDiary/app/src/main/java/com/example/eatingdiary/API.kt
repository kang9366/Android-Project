package com.example.eatingdiary

import com.example.eatingdiary.Model.Data
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface API {
    @GET("api/2c1ab25537fb463f9a53/I2570/json/1/3/BRCD_NO={barcode}")
    fun getFoodName(
        @Path("barcode") barcode: String
    ):Call<Data>
}