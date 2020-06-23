package com.example.myapp

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_country_data.*
import kotlinx.android.synthetic.main.activity_data.*
import kotlinx.android.synthetic.main.activity_graphic.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class CountryDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_data)

        val bundle : Bundle? = intent.extras
        val msg = bundle!!.getString("country")
        view1.setText(msg)

        if (msg == "USA")
        {
            drapeau.setBackgroundResource(R.drawable.usa)
        }
        if (msg == "Brazil")
        {
            drapeau.setBackgroundResource(R.drawable.bresil)
        }
        if (msg == "Russia")
        {
            drapeau.setBackgroundResource(R.drawable.russia)
        }
        if (msg == "India")
        {
            drapeau.setBackgroundResource(R.drawable.inde)
        }
        if (msg == "Spain")
        {
            drapeau.setBackgroundResource(R.drawable.espagne)
        }
        if (msg == "Italy")
        {
            drapeau.setBackgroundResource(R.drawable.italie)
        }
        if (msg == "Peru")
        {
            drapeau.setBackgroundResource(R.drawable.peru)
        }
        if (msg == "France")
        {
            drapeau.setBackgroundResource(R.drawable.france)
        }
        if (msg == "Iran")
        {
            drapeau.setBackgroundResource(R.drawable.iran)
        }
        if (msg == "United-Kingdom")
        {
            drapeau.setBackgroundResource(R.drawable.uk)
        }
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        var rDay = 0
        var rMonths = 0
        var rYear = 0
        button_cal.setOnClickListener(){
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { viewModelStore, mYear, mMonths, mDay ->
                dateTv.setText("" + mDay + "/" + mMonths + "/" + mYear)
                rDay = mDay
                rMonths = mMonths
                rYear = mYear
                if (msg == "USA") {
                    get_usa(rDay, rMonths, rYear)
                }
                if (msg == "Brazil") {
                    get_brasil(rDay, rMonths, rYear)
                }
                if (msg == "Russia") {
                    get_russia(rDay, rMonths, rYear)
                }
                if (msg == "India"){
                    get_india(rDay, rMonths, rYear)
                }
                if (msg == "Spain"){
                    get_spain(rDay, rMonths, rYear)
                }
                if (msg == "Italy"){
                    get_italy(rDay, rMonths, rYear)
                }
                if (msg == "Peru"){
                    get_peru(rDay, rMonths, rYear)
                }
                if (msg == "France"){
                    get_france(rDay, rMonths, rYear)
                }
                if (msg == "Iran"){
                    get_iran(rDay, rMonths, rYear)
                }
                if (msg == "United-Kingdom"){
                    get_united_kingdom(rDay, rMonths, rYear)
                }
            }, year, month, day)

            dpd.show()

        }
    }

    fun get_usa(d: Int, m:Int, y: Int){
        val baseUrl = "https://api.covid19api.com/"
        val jsonConverter = GsonConverterFactory.create(GsonBuilder().create())
        val retrofitClient = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(jsonConverter)
            .build()
        val service = retrofitClient.create(WSCountryInterface::class.java)
        val callback : Callback<List<CountryObject>> = object : Callback<List<CountryObject>> {
            override fun onFailure(call: Call<List<CountryObject>>, t: Throwable) {
                TODO("Not yet implemented")
                nb_conf.setText("error")
            }

            override fun onResponse(call: Call<List<CountryObject>>, response: Response<List<CountryObject>>) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        val data : List<CountryObject> = response.body()!!
                        if (data.get(0).Date.subSequence(0,4).toString().toInt() > y) {
                            nb_conf1.setText("no data")
                            nb_dead1.setText("no data")
                            nb_recov1.setText("no data")
                        }
                        else if (data.get(0).Date.subSequence(0,4).toString().toInt() < y){
                            var i : Int = 0
                            var cond: Boolean = true
                            while (i < data.size && cond)
                            {
                                if (data.get(i).Date.subSequence(0,4).toString().toInt() == y && data.get(i).Date.subSequence(5,7).toString().toInt() == m && data.get(i).Date.subSequence(8,10).toString().toInt() == d){
                                    nb_conf1.setText(data.get(i).Confirmed.toString())
                                    nb_dead1.setText(data.get(i).Deaths.toString())
                                    nb_recov1.setText(data.get(i).Recovered.toString())
                                    cond = false
                                }
                                i++
                            }
                            if (cond)
                            {
                                nb_conf1.setText("no data")
                                nb_dead1.setText("no data")
                                nb_recov1.setText("no data")
                            }
                        }
                        else{

                            if (data.get(0).Date.subSequence(5,7).toString().toInt() > m) {
                                nb_conf1.setText("no data")
                                nb_dead1.setText("no data")
                                nb_recov1.setText("no data")
                            }
                            else if (data.get(0).Date.subSequence(5,7).toString().toInt() < m){
                                var i : Int = 0
                                var cond: Boolean = true
                                while (i < data.size && cond)
                                {
                                    if (data.get(i).Date.subSequence(0,4).toString().toInt() == y && data.get(i).Date.subSequence(5,7).toString().toInt() == m && data.get(i).Date.subSequence(8,10).toString().toInt() == d){
                                        nb_conf1.setText(data.get(i).Confirmed.toString())
                                        nb_dead1.setText(data.get(i).Deaths.toString())
                                        nb_recov1.setText(data.get(i).Recovered.toString())
                                        cond = false
                                    }
                                    i++
                                }
                                if (cond)
                                {
                                    nb_conf1.setText("no data")
                                    nb_dead1.setText("no data")
                                    nb_recov1.setText("no data")
                                }
                            }
                            else{
                                if (data.get(0).Date.subSequence(8,10).toString().toInt() > y) {
                                    nb_conf1.setText("no data")
                                    nb_dead1.setText("no data")
                                    nb_recov1.setText("no data")
                                }
                                else if (data.get(0).Date.subSequence(8,10).toString().toInt() < y){
                                    var i : Int = 0
                                    var cond: Boolean = true
                                    while (i < data.size && cond)
                                    {
                                        if (data.get(i).Date.subSequence(0,4).toString().toInt() == y && data.get(i).Date.subSequence(5,7).toString().toInt() == m && data.get(i).Date.subSequence(8,10).toString().toInt() == d){
                                            nb_conf1.setText(data.get(i).Confirmed.toString())
                                            nb_dead1.setText(data.get(i).Deaths.toString())
                                            nb_recov1.setText(data.get(i).Recovered.toString())
                                            cond = false
                                        }
                                        i++
                                    }
                                    if (cond)
                                    {
                                        nb_conf1.setText("no data")
                                        nb_dead1.setText("no data")
                                        nb_recov1.setText("no data")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        service.get_usa().enqueue(callback)
    }


    fun get_brasil(d: Int, m:Int, y: Int){
        val baseUrl = "https://api.covid19api.com/"
        val jsonConverter = GsonConverterFactory.create(GsonBuilder().create())
        val retrofitClient = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(jsonConverter)
            .build()
        val service = retrofitClient.create(WSCountryInterface::class.java)
        val callback : Callback<List<CountryObject>> = object : Callback<List<CountryObject>> {
            override fun onFailure(call: Call<List<CountryObject>>, t: Throwable) {
                TODO("Not yet implemented")
                nb_conf.setText("error")
            }

            override fun onResponse(call: Call<List<CountryObject>>, response: Response<List<CountryObject>>) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        val data : List<CountryObject> = response.body()!!
                        if (data.get(0).Date.subSequence(0,4).toString().toInt() > y) {
                            nb_conf1.setText("no data")
                            nb_dead1.setText("no data")
                            nb_recov1.setText("no data")
                        }
                        else if (data.get(0).Date.subSequence(0,4).toString().toInt() < y){
                            var i : Int = 0
                            var cond: Boolean = true
                            while (i < data.size && cond)
                            {
                                if (data.get(i).Date.subSequence(0,4).toString().toInt() == y && data.get(i).Date.subSequence(5,7).toString().toInt() == m && data.get(i).Date.subSequence(8,10).toString().toInt() == d){
                                    nb_conf1.setText(data.get(i).Confirmed.toString())
                                    nb_dead1.setText(data.get(i).Deaths.toString())
                                    nb_recov1.setText(data.get(i).Recovered.toString())
                                    cond = false
                                }
                                i++
                            }
                            if (cond)
                            {
                                nb_conf1.setText("no data")
                                nb_dead1.setText("no data")
                                nb_recov1.setText("no data")
                            }
                        }
                        else{

                            if (data.get(0).Date.subSequence(5,7).toString().toInt() > m) {
                                nb_conf1.setText("no data")
                                nb_dead1.setText("no data")
                                nb_recov1.setText("no data")
                            }
                            else if (data.get(0).Date.subSequence(5,7).toString().toInt() < m){
                                var i : Int = 0
                                var cond: Boolean = true
                                while (i < data.size && cond)
                                {
                                    if (data.get(i).Date.subSequence(0,4).toString().toInt() == y && data.get(i).Date.subSequence(5,7).toString().toInt() == m && data.get(i).Date.subSequence(8,10).toString().toInt() == d){
                                        nb_conf1.setText(data.get(i).Confirmed.toString())
                                        nb_dead1.setText(data.get(i).Deaths.toString())
                                        nb_recov1.setText(data.get(i).Recovered.toString())
                                        cond = false
                                    }
                                    i++
                                }
                                if (cond)
                                {
                                    nb_conf1.setText("no data")
                                    nb_dead1.setText("no data")
                                    nb_recov1.setText("no data")
                                }
                            }
                            else{
                                if (data.get(0).Date.subSequence(8,10).toString().toInt() > y) {
                                    nb_conf1.setText("no data")
                                    nb_dead1.setText("no data")
                                    nb_recov1.setText("no data")
                                }
                                else if (data.get(0).Date.subSequence(8,10).toString().toInt() < y){
                                    var i : Int = 0
                                    var cond: Boolean = true
                                    while (i < data.size && cond)
                                    {
                                        if (data.get(i).Date.subSequence(0,4).toString().toInt() == y && data.get(i).Date.subSequence(5,7).toString().toInt() == m && data.get(i).Date.subSequence(8,10).toString().toInt() == d){
                                            nb_conf1.setText(data.get(i).Confirmed.toString())
                                            nb_dead1.setText(data.get(i).Deaths.toString())
                                            nb_recov1.setText(data.get(i).Recovered.toString())
                                            cond = false
                                        }
                                        i++
                                    }
                                    if (cond)
                                    {
                                        nb_conf1.setText("no data")
                                        nb_dead1.setText("no data")
                                        nb_recov1.setText("no data")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        service.get_brazil().enqueue(callback)
    }

    fun get_russia(d: Int, m:Int, y: Int){
        val baseUrl = "https://api.covid19api.com/"
        val jsonConverter = GsonConverterFactory.create(GsonBuilder().create())
        val retrofitClient = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(jsonConverter)
            .build()
        val service = retrofitClient.create(WSCountryInterface::class.java)
        val callback : Callback<List<CountryObject>> = object : Callback<List<CountryObject>> {
            override fun onFailure(call: Call<List<CountryObject>>, t: Throwable) {
                TODO("Not yet implemented")
                nb_conf.setText("error")
            }

            override fun onResponse(call: Call<List<CountryObject>>, response: Response<List<CountryObject>>) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        val data : List<CountryObject> = response.body()!!
                        if (data.get(0).Date.subSequence(0,4).toString().toInt() > y) {
                            nb_conf1.setText("no data")
                            nb_dead1.setText("no data")
                            nb_recov1.setText("no data")
                        }
                        else if (data.get(0).Date.subSequence(0,4).toString().toInt() < y){
                            var i : Int = 0
                            var cond: Boolean = true
                            while (i < data.size && cond)
                            {
                                if (data.get(i).Date.subSequence(0,4).toString().toInt() == y && data.get(i).Date.subSequence(5,7).toString().toInt() == m && data.get(i).Date.subSequence(8,10).toString().toInt() == d){
                                    nb_conf1.setText(data.get(i).Confirmed.toString())
                                    nb_dead1.setText(data.get(i).Deaths.toString())
                                    nb_recov1.setText(data.get(i).Recovered.toString())
                                    cond = false
                                }
                                i++
                            }
                            if (cond)
                            {
                                nb_conf1.setText("no data")
                                nb_dead1.setText("no data")
                                nb_recov1.setText("no data")
                            }
                        }
                        else{

                            if (data.get(0).Date.subSequence(5,7).toString().toInt() > m) {
                                nb_conf1.setText("no data")
                                nb_dead1.setText("no data")
                                nb_recov1.setText("no data")
                            }
                            else if (data.get(0).Date.subSequence(5,7).toString().toInt() < m){
                                var i : Int = 0
                                var cond: Boolean = true
                                while (i < data.size && cond)
                                {
                                    if (data.get(i).Date.subSequence(0,4).toString().toInt() == y && data.get(i).Date.subSequence(5,7).toString().toInt() == m && data.get(i).Date.subSequence(8,10).toString().toInt() == d){
                                        nb_conf1.setText(data.get(i).Confirmed.toString())
                                        nb_dead1.setText(data.get(i).Deaths.toString())
                                        nb_recov1.setText(data.get(i).Recovered.toString())
                                        cond = false
                                    }
                                    i++
                                }
                                if (cond)
                                {
                                    nb_conf1.setText("no data")
                                    nb_dead1.setText("no data")
                                    nb_recov1.setText("no data")
                                }
                            }
                            else{
                                if (data.get(0).Date.subSequence(8,10).toString().toInt() > y) {
                                    nb_conf1.setText("no data")
                                    nb_dead1.setText("no data")
                                    nb_recov1.setText("no data")
                                }
                                else if (data.get(0).Date.subSequence(8,10).toString().toInt() < y){
                                    var i : Int = 0
                                    var cond: Boolean = true
                                    while (i < data.size && cond)
                                    {
                                        if (data.get(i).Date.subSequence(0,4).toString().toInt() == y && data.get(i).Date.subSequence(5,7).toString().toInt() == m && data.get(i).Date.subSequence(8,10).toString().toInt() == d){
                                            nb_conf1.setText(data.get(i).Confirmed.toString())
                                            nb_dead1.setText(data.get(i).Deaths.toString())
                                            nb_recov1.setText(data.get(i).Recovered.toString())
                                            cond = false
                                        }
                                        i++
                                    }
                                    if (cond)
                                    {
                                        nb_conf1.setText("no data")
                                        nb_dead1.setText("no data")
                                        nb_recov1.setText("no data")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        service.get_russia().enqueue(callback)
    }

    fun get_india(d: Int, m:Int, y: Int){
        val baseUrl = "https://api.covid19api.com/"
        val jsonConverter = GsonConverterFactory.create(GsonBuilder().create())
        val retrofitClient = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(jsonConverter)
            .build()
        val service = retrofitClient.create(WSCountryInterface::class.java)
        val callback : Callback<List<CountryObject>> = object : Callback<List<CountryObject>> {
            override fun onFailure(call: Call<List<CountryObject>>, t: Throwable) {
                TODO("Not yet implemented")
                nb_conf.setText("error")
            }

            override fun onResponse(call: Call<List<CountryObject>>, response: Response<List<CountryObject>>) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        val data : List<CountryObject> = response.body()!!
                        if (data.get(0).Date.subSequence(0,4).toString().toInt() > y) {
                            nb_conf1.setText("no data")
                            nb_dead1.setText("no data")
                            nb_recov1.setText("no data")
                        }
                        else if (data.get(0).Date.subSequence(0,4).toString().toInt() < y){
                            var i : Int = 0
                            var cond: Boolean = true
                            while (i < data.size && cond)
                            {
                                if (data.get(i).Date.subSequence(0,4).toString().toInt() == y && data.get(i).Date.subSequence(5,7).toString().toInt() == m && data.get(i).Date.subSequence(8,10).toString().toInt() == d){
                                    nb_conf1.setText(data.get(i).Confirmed.toString())
                                    nb_dead1.setText(data.get(i).Deaths.toString())
                                    nb_recov1.setText(data.get(i).Recovered.toString())
                                    cond = false
                                }
                                i++
                            }
                            if (cond)
                            {
                                nb_conf1.setText("no data")
                                nb_dead1.setText("no data")
                                nb_recov1.setText("no data")
                            }
                        }
                        else{

                            if (data.get(0).Date.subSequence(5,7).toString().toInt() > m) {
                                nb_conf1.setText("no data")
                                nb_dead1.setText("no data")
                                nb_recov1.setText("no data")
                            }
                            else if (data.get(0).Date.subSequence(5,7).toString().toInt() < m){
                                var i : Int = 0
                                var cond: Boolean = true
                                while (i < data.size && cond)
                                {
                                    if (data.get(i).Date.subSequence(0,4).toString().toInt() == y && data.get(i).Date.subSequence(5,7).toString().toInt() == m && data.get(i).Date.subSequence(8,10).toString().toInt() == d){
                                        nb_conf1.setText(data.get(i).Confirmed.toString())
                                        nb_dead1.setText(data.get(i).Deaths.toString())
                                        nb_recov1.setText(data.get(i).Recovered.toString())
                                        cond = false
                                    }
                                    i++
                                }
                                if (cond)
                                {
                                    nb_conf1.setText("no data")
                                    nb_dead1.setText("no data")
                                    nb_recov1.setText("no data")
                                }
                            }
                            else{
                                if (data.get(0).Date.subSequence(8,10).toString().toInt() > y) {
                                    nb_conf1.setText("no data")
                                    nb_dead1.setText("no data")
                                    nb_recov1.setText("no data")
                                }
                                else if (data.get(0).Date.subSequence(8,10).toString().toInt() < y){
                                    var i : Int = 0
                                    var cond: Boolean = true
                                    while (i < data.size && cond)
                                    {
                                        if (data.get(i).Date.subSequence(0,4).toString().toInt() == y && data.get(i).Date.subSequence(5,7).toString().toInt() == m && data.get(i).Date.subSequence(8,10).toString().toInt() == d){
                                            nb_conf1.setText(data.get(i).Confirmed.toString())
                                            nb_dead1.setText(data.get(i).Deaths.toString())
                                            nb_recov1.setText(data.get(i).Recovered.toString())
                                            cond = false
                                        }
                                        i++
                                    }
                                    if (cond)
                                    {
                                        nb_conf1.setText("no data")
                                        nb_dead1.setText("no data")
                                        nb_recov1.setText("no data")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        service.get_india().enqueue(callback)
    }

    fun get_spain(d: Int, m:Int, y: Int){
        val baseUrl = "https://api.covid19api.com/"
        val jsonConverter = GsonConverterFactory.create(GsonBuilder().create())
        val retrofitClient = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(jsonConverter)
            .build()
        val service = retrofitClient.create(WSCountryInterface::class.java)
        val callback : Callback<List<CountryObject>> = object : Callback<List<CountryObject>> {
            override fun onFailure(call: Call<List<CountryObject>>, t: Throwable) {
                TODO("Not yet implemented")
                nb_conf.setText("error")
            }

            override fun onResponse(call: Call<List<CountryObject>>, response: Response<List<CountryObject>>) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        val data : List<CountryObject> = response.body()!!
                        if (data.get(0).Date.subSequence(0,4).toString().toInt() > y) {
                            nb_conf1.setText("no data")
                            nb_dead1.setText("no data")
                            nb_recov1.setText("no data")
                        }
                        else if (data.get(0).Date.subSequence(0,4).toString().toInt() < y){
                            var i : Int = 0
                            var cond: Boolean = true
                            while (i < data.size && cond)
                            {
                                if (data.get(i).Date.subSequence(0,4).toString().toInt() == y && data.get(i).Date.subSequence(5,7).toString().toInt() == m && data.get(i).Date.subSequence(8,10).toString().toInt() == d){
                                    nb_conf1.setText(data.get(i).Confirmed.toString())
                                    nb_dead1.setText(data.get(i).Deaths.toString())
                                    nb_recov1.setText(data.get(i).Recovered.toString())
                                    cond = false
                                }
                                i++
                            }
                            if (cond)
                            {
                                nb_conf1.setText("no data")
                                nb_dead1.setText("no data")
                                nb_recov1.setText("no data")
                            }
                        }
                        else{

                            if (data.get(0).Date.subSequence(5,7).toString().toInt() > m) {
                                nb_conf1.setText("no data")
                                nb_dead1.setText("no data")
                                nb_recov1.setText("no data")
                            }
                            else if (data.get(0).Date.subSequence(5,7).toString().toInt() < m){
                                var i : Int = 0
                                var cond: Boolean = true
                                while (i < data.size && cond)
                                {
                                    if (data.get(i).Date.subSequence(0,4).toString().toInt() == y && data.get(i).Date.subSequence(5,7).toString().toInt() == m && data.get(i).Date.subSequence(8,10).toString().toInt() == d){
                                        nb_conf1.setText(data.get(i).Confirmed.toString())
                                        nb_dead1.setText(data.get(i).Deaths.toString())
                                        nb_recov1.setText(data.get(i).Recovered.toString())
                                        cond = false
                                    }
                                    i++
                                }
                                if (cond)
                                {
                                    nb_conf1.setText("no data")
                                    nb_dead1.setText("no data")
                                    nb_recov1.setText("no data")
                                }
                            }
                            else{
                                if (data.get(0).Date.subSequence(8,10).toString().toInt() > y) {
                                    nb_conf1.setText("no data")
                                    nb_dead1.setText("no data")
                                    nb_recov1.setText("no data")
                                }
                                else if (data.get(0).Date.subSequence(8,10).toString().toInt() < y){
                                    var i : Int = 0
                                    var cond: Boolean = true
                                    while (i < data.size && cond)
                                    {
                                        if (data.get(i).Date.subSequence(0,4).toString().toInt() == y && data.get(i).Date.subSequence(5,7).toString().toInt() == m && data.get(i).Date.subSequence(8,10).toString().toInt() == d){
                                            nb_conf1.setText(data.get(i).Confirmed.toString())
                                            nb_dead1.setText(data.get(i).Deaths.toString())
                                            nb_recov1.setText(data.get(i).Recovered.toString())
                                            cond = false
                                        }
                                        i++
                                    }
                                    if (cond)
                                    {
                                        nb_conf1.setText("no data")
                                        nb_dead1.setText("no data")
                                        nb_recov1.setText("no data")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        service.get_spain().enqueue(callback)
    }

    fun get_italy(d: Int, m:Int, y: Int){
        val baseUrl = "https://api.covid19api.com/"
        val jsonConverter = GsonConverterFactory.create(GsonBuilder().create())
        val retrofitClient = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(jsonConverter)
            .build()
        val service = retrofitClient.create(WSCountryInterface::class.java)
        val callback : Callback<List<CountryObject>> = object : Callback<List<CountryObject>> {
            override fun onFailure(call: Call<List<CountryObject>>, t: Throwable) {
                TODO("Not yet implemented")
                nb_conf.setText("error")
            }

            override fun onResponse(call: Call<List<CountryObject>>, response: Response<List<CountryObject>>) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        val data : List<CountryObject> = response.body()!!
                        if (data.get(0).Date.subSequence(0,4).toString().toInt() > y) {
                            nb_conf1.setText("no data")
                            nb_dead1.setText("no data")
                            nb_recov1.setText("no data")
                        }
                        else if (data.get(0).Date.subSequence(0,4).toString().toInt() < y){
                            var i : Int = 0
                            var cond: Boolean = true
                            while (i < data.size && cond)
                            {
                                if (data.get(i).Date.subSequence(0,4).toString().toInt() == y && data.get(i).Date.subSequence(5,7).toString().toInt() == m && data.get(i).Date.subSequence(8,10).toString().toInt() == d){
                                    nb_conf1.setText(data.get(i).Confirmed.toString())
                                    nb_dead1.setText(data.get(i).Deaths.toString())
                                    nb_recov1.setText(data.get(i).Recovered.toString())
                                    cond = false
                                }
                                i++
                            }
                            if (cond)
                            {
                                nb_conf1.setText("no data")
                                nb_dead1.setText("no data")
                                nb_recov1.setText("no data")
                            }
                        }
                        else{

                            if (data.get(0).Date.subSequence(5,7).toString().toInt() > m) {
                                nb_conf1.setText("no data")
                                nb_dead1.setText("no data")
                                nb_recov1.setText("no data")
                            }
                            else if (data.get(0).Date.subSequence(5,7).toString().toInt() < m){
                                var i : Int = 0
                                var cond: Boolean = true
                                while (i < data.size && cond)
                                {
                                    if (data.get(i).Date.subSequence(0,4).toString().toInt() == y && data.get(i).Date.subSequence(5,7).toString().toInt() == m && data.get(i).Date.subSequence(8,10).toString().toInt() == d){
                                        nb_conf1.setText(data.get(i).Confirmed.toString())
                                        nb_dead1.setText(data.get(i).Deaths.toString())
                                        nb_recov1.setText(data.get(i).Recovered.toString())
                                        cond = false
                                    }
                                    i++
                                }
                                if (cond)
                                {
                                    nb_conf1.setText("no data")
                                    nb_dead1.setText("no data")
                                    nb_recov1.setText("no data")
                                }
                            }
                            else{
                                if (data.get(0).Date.subSequence(8,10).toString().toInt() > y) {
                                    nb_conf1.setText("no data")
                                    nb_dead1.setText("no data")
                                    nb_recov1.setText("no data")
                                }
                                else if (data.get(0).Date.subSequence(8,10).toString().toInt() < y){
                                    var i : Int = 0
                                    var cond: Boolean = true
                                    while (i < data.size && cond)
                                    {
                                        if (data.get(i).Date.subSequence(0,4).toString().toInt() == y && data.get(i).Date.subSequence(5,7).toString().toInt() == m && data.get(i).Date.subSequence(8,10).toString().toInt() == d){
                                            nb_conf1.setText(data.get(i).Confirmed.toString())
                                            nb_dead1.setText(data.get(i).Deaths.toString())
                                            nb_recov1.setText(data.get(i).Recovered.toString())
                                            cond = false
                                        }
                                        i++
                                    }
                                    if (cond)
                                    {
                                        nb_conf1.setText("no data")
                                        nb_dead1.setText("no data")
                                        nb_recov1.setText("no data")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        service.get_italy().enqueue(callback)
    }

    fun get_peru(d: Int, m:Int, y: Int){
        val baseUrl = "https://api.covid19api.com/"
        val jsonConverter = GsonConverterFactory.create(GsonBuilder().create())
        val retrofitClient = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(jsonConverter)
            .build()
        val service = retrofitClient.create(WSCountryInterface::class.java)
        val callback : Callback<List<CountryObject>> = object : Callback<List<CountryObject>> {
            override fun onFailure(call: Call<List<CountryObject>>, t: Throwable) {
                TODO("Not yet implemented")
                nb_conf.setText("error")
            }

            override fun onResponse(call: Call<List<CountryObject>>, response: Response<List<CountryObject>>) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        val data : List<CountryObject> = response.body()!!
                        if (data.get(0).Date.subSequence(0,4).toString().toInt() > y) {
                            nb_conf1.setText("no data")
                            nb_dead1.setText("no data")
                            nb_recov1.setText("no data")
                        }
                        else if (data.get(0).Date.subSequence(0,4).toString().toInt() < y){
                            var i : Int = 0
                            var cond: Boolean = true
                            while (i < data.size && cond)
                            {
                                if (data.get(i).Date.subSequence(0,4).toString().toInt() == y && data.get(i).Date.subSequence(5,7).toString().toInt() == m && data.get(i).Date.subSequence(8,10).toString().toInt() == d){
                                    nb_conf1.setText(data.get(i).Confirmed.toString())
                                    nb_dead1.setText(data.get(i).Deaths.toString())
                                    nb_recov1.setText(data.get(i).Recovered.toString())
                                    cond = false
                                }
                                i++
                            }
                            if (cond)
                            {
                                nb_conf1.setText("no data")
                                nb_dead1.setText("no data")
                                nb_recov1.setText("no data")
                            }
                        }
                        else{

                            if (data.get(0).Date.subSequence(5,7).toString().toInt() > m) {
                                nb_conf1.setText("no data")
                                nb_dead1.setText("no data")
                                nb_recov1.setText("no data")
                            }
                            else if (data.get(0).Date.subSequence(5,7).toString().toInt() < m){
                                var i : Int = 0
                                var cond: Boolean = true
                                while (i < data.size && cond)
                                {
                                    if (data.get(i).Date.subSequence(0,4).toString().toInt() == y && data.get(i).Date.subSequence(5,7).toString().toInt() == m && data.get(i).Date.subSequence(8,10).toString().toInt() == d){
                                        nb_conf1.setText(data.get(i).Confirmed.toString())
                                        nb_dead1.setText(data.get(i).Deaths.toString())
                                        nb_recov1.setText(data.get(i).Recovered.toString())
                                        cond = false
                                    }
                                    i++
                                }
                                if (cond)
                                {
                                    nb_conf1.setText("no data")
                                    nb_dead1.setText("no data")
                                    nb_recov1.setText("no data")
                                }
                            }
                            else{
                                if (data.get(0).Date.subSequence(8,10).toString().toInt() > y) {
                                    nb_conf1.setText("no data")
                                    nb_dead1.setText("no data")
                                    nb_recov1.setText("no data")
                                }
                                else if (data.get(0).Date.subSequence(8,10).toString().toInt() < y){
                                    var i : Int = 0
                                    var cond: Boolean = true
                                    while (i < data.size && cond)
                                    {
                                        if (data.get(i).Date.subSequence(0,4).toString().toInt() == y && data.get(i).Date.subSequence(5,7).toString().toInt() == m && data.get(i).Date.subSequence(8,10).toString().toInt() == d){
                                            nb_conf1.setText(data.get(i).Confirmed.toString())
                                            nb_dead1.setText(data.get(i).Deaths.toString())
                                            nb_recov1.setText(data.get(i).Recovered.toString())
                                            cond = false
                                        }
                                        i++
                                    }
                                    if (cond)
                                    {
                                        nb_conf1.setText("no data")
                                        nb_dead1.setText("no data")
                                        nb_recov1.setText("no data")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        service.get_peru().enqueue(callback)
    }

    fun get_france(d: Int, m:Int, y: Int){
        val baseUrl = "https://api.covid19api.com/"
        val jsonConverter = GsonConverterFactory.create(GsonBuilder().create())
        val retrofitClient = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(jsonConverter)
            .build()
        val service = retrofitClient.create(WSCountryInterface::class.java)
        val callback : Callback<List<CountryObject>> = object : Callback<List<CountryObject>> {
            override fun onFailure(call: Call<List<CountryObject>>, t: Throwable) {
                TODO("Not yet implemented")
                nb_conf.setText("error")
            }

            override fun onResponse(call: Call<List<CountryObject>>, response: Response<List<CountryObject>>) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        val data : List<CountryObject> = response.body()!!
                        if (data.get(0).Date.subSequence(0,4).toString().toInt() > y) {
                            nb_conf1.setText("no data")
                            nb_dead1.setText("no data")
                            nb_recov1.setText("no data")
                        }
                        else if (data.get(0).Date.subSequence(0,4).toString().toInt() < y){
                            var i : Int = 0
                            var cond: Boolean = true
                            while (i < data.size && cond)
                            {
                                if (data.get(i).Date.subSequence(0,4).toString().toInt() == y && data.get(i).Date.subSequence(5,7).toString().toInt() == m && data.get(i).Date.subSequence(8,10).toString().toInt() == d){
                                    nb_conf1.setText(data.get(i).Confirmed.toString())
                                    nb_dead1.setText(data.get(i).Deaths.toString())
                                    nb_recov1.setText(data.get(i).Recovered.toString())
                                    cond = false
                                }
                                i++
                            }
                            if (cond)
                            {
                                nb_conf1.setText("no data")
                                nb_dead1.setText("no data")
                                nb_recov1.setText("no data")
                            }
                        }
                        else{

                            if (data.get(0).Date.subSequence(5,7).toString().toInt() > m) {
                                nb_conf1.setText("no data")
                                nb_dead1.setText("no data")
                                nb_recov1.setText("no data")
                            }
                            else if (data.get(0).Date.subSequence(5,7).toString().toInt() < m){
                                var i : Int = 0
                                var cond: Boolean = true
                                while (i < data.size && cond)
                                {
                                    if (data.get(i).Date.subSequence(0,4).toString().toInt() == y && data.get(i).Date.subSequence(5,7).toString().toInt() == m && data.get(i).Date.subSequence(8,10).toString().toInt() == d){
                                        nb_conf1.setText(data.get(i).Confirmed.toString())
                                        nb_dead1.setText(data.get(i).Deaths.toString())
                                        nb_recov1.setText(data.get(i).Recovered.toString())
                                        cond = false
                                    }
                                    i++
                                }
                                if (cond)
                                {
                                    nb_conf1.setText("no data")
                                    nb_dead1.setText("no data")
                                    nb_recov1.setText("no data")
                                }
                            }
                            else{
                                if (data.get(0).Date.subSequence(8,10).toString().toInt() > y) {
                                    nb_conf1.setText("no data")
                                    nb_dead1.setText("no data")
                                    nb_recov1.setText("no data")
                                }
                                else if (data.get(0).Date.subSequence(8,10).toString().toInt() < y){
                                    var i : Int = 0
                                    var cond: Boolean = true
                                    while (i < data.size && cond)
                                    {
                                        if (data.get(i).Date.subSequence(0,4).toString().toInt() == y && data.get(i).Date.subSequence(5,7).toString().toInt() == m && data.get(i).Date.subSequence(8,10).toString().toInt() == d){
                                            nb_conf1.setText(data.get(i).Confirmed.toString())
                                            nb_dead1.setText(data.get(i).Deaths.toString())
                                            nb_recov1.setText(data.get(i).Recovered.toString())
                                            cond = false
                                        }
                                        i++
                                    }
                                    if (cond)
                                    {
                                        nb_conf1.setText("no data")
                                        nb_dead1.setText("no data")
                                        nb_recov1.setText("no data")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        service.get_france().enqueue(callback)
    }

    fun get_iran(d: Int, m:Int, y: Int){
        val baseUrl = "https://api.covid19api.com/"
        val jsonConverter = GsonConverterFactory.create(GsonBuilder().create())
        val retrofitClient = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(jsonConverter)
            .build()
        val service = retrofitClient.create(WSCountryInterface::class.java)
        val callback : Callback<List<CountryObject>> = object : Callback<List<CountryObject>> {
            override fun onFailure(call: Call<List<CountryObject>>, t: Throwable) {
                TODO("Not yet implemented")
                nb_conf.setText("error")
            }

            override fun onResponse(call: Call<List<CountryObject>>, response: Response<List<CountryObject>>) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        val data : List<CountryObject> = response.body()!!
                        if (data.get(0).Date.subSequence(0,4).toString().toInt() > y) {
                            nb_conf1.setText("no data")
                            nb_dead1.setText("no data")
                            nb_recov1.setText("no data")
                        }
                        else if (data.get(0).Date.subSequence(0,4).toString().toInt() < y){
                            var i : Int = 0
                            var cond: Boolean = true
                            while (i < data.size && cond)
                            {
                                if (data.get(i).Date.subSequence(0,4).toString().toInt() == y && data.get(i).Date.subSequence(5,7).toString().toInt() == m && data.get(i).Date.subSequence(8,10).toString().toInt() == d){
                                    nb_conf1.setText(data.get(i).Confirmed.toString())
                                    nb_dead1.setText(data.get(i).Deaths.toString())
                                    nb_recov1.setText(data.get(i).Recovered.toString())
                                    cond = false
                                }
                                i++
                            }
                            if (cond)
                            {
                                nb_conf1.setText("no data")
                                nb_dead1.setText("no data")
                                nb_recov1.setText("no data")
                            }
                        }
                        else{

                            if (data.get(0).Date.subSequence(5,7).toString().toInt() > m) {
                                nb_conf1.setText("no data")
                                nb_dead1.setText("no data")
                                nb_recov1.setText("no data")
                            }
                            else if (data.get(0).Date.subSequence(5,7).toString().toInt() < m){
                                var i : Int = 0
                                var cond: Boolean = true
                                while (i < data.size && cond)
                                {
                                    if (data.get(i).Date.subSequence(0,4).toString().toInt() == y && data.get(i).Date.subSequence(5,7).toString().toInt() == m && data.get(i).Date.subSequence(8,10).toString().toInt() == d){
                                        nb_conf1.setText(data.get(i).Confirmed.toString())
                                        nb_dead1.setText(data.get(i).Deaths.toString())
                                        nb_recov1.setText(data.get(i).Recovered.toString())
                                        cond = false
                                    }
                                    i++
                                }
                                if (cond)
                                {
                                    nb_conf1.setText("no data")
                                    nb_dead1.setText("no data")
                                    nb_recov1.setText("no data")
                                }
                            }
                            else{
                                if (data.get(0).Date.subSequence(8,10).toString().toInt() > y) {
                                    nb_conf1.setText("no data")
                                    nb_dead1.setText("no data")
                                    nb_recov1.setText("no data")
                                }
                                else if (data.get(0).Date.subSequence(8,10).toString().toInt() < y){
                                    var i : Int = 0
                                    var cond: Boolean = true
                                    while (i < data.size && cond)
                                    {
                                        if (data.get(i).Date.subSequence(0,4).toString().toInt() == y && data.get(i).Date.subSequence(5,7).toString().toInt() == m && data.get(i).Date.subSequence(8,10).toString().toInt() == d){
                                            nb_conf1.setText(data.get(i).Confirmed.toString())
                                            nb_dead1.setText(data.get(i).Deaths.toString())
                                            nb_recov1.setText(data.get(i).Recovered.toString())
                                            cond = false
                                        }
                                        i++
                                    }
                                    if (cond)
                                    {
                                        nb_conf1.setText("no data")
                                        nb_dead1.setText("no data")
                                        nb_recov1.setText("no data")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        service.get_iran().enqueue(callback)
    }

    fun get_united_kingdom(d: Int, m:Int, y: Int){
        val baseUrl = "https://api.covid19api.com/"
        val jsonConverter = GsonConverterFactory.create(GsonBuilder().create())
        val retrofitClient = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(jsonConverter)
            .build()
        val service = retrofitClient.create(WSCountryInterface::class.java)
        val callback : Callback<List<CountryObject>> = object : Callback<List<CountryObject>> {
            override fun onFailure(call: Call<List<CountryObject>>, t: Throwable) {
                TODO("Not yet implemented")
                nb_conf.setText("error")
            }

            override fun onResponse(call: Call<List<CountryObject>>, response: Response<List<CountryObject>>) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        val data : List<CountryObject> = response.body()!!
                        if (data.get(0).Date.subSequence(0,4).toString().toInt() > y) {
                            nb_conf1.setText("no data")
                            nb_dead1.setText("no data")
                            nb_recov1.setText("no data")
                        }
                        else if (data.get(0).Date.subSequence(0,4).toString().toInt() < y){
                            var i : Int = 0
                            var cond: Boolean = true
                            while (i < data.size && cond)
                            {
                                if (data.get(i).Date.subSequence(0,4).toString().toInt() == y && data.get(i).Date.subSequence(5,7).toString().toInt() == m && data.get(i).Date.subSequence(8,10).toString().toInt() == d){
                                    nb_conf1.setText(data.get(i).Confirmed.toString())
                                    nb_dead1.setText(data.get(i).Deaths.toString())
                                    nb_recov1.setText(data.get(i).Recovered.toString())
                                    cond = false
                                }
                                i++
                            }
                            if (cond)
                            {
                                nb_conf1.setText("no data")
                                nb_dead1.setText("no data")
                                nb_recov1.setText("no data")
                            }
                        }
                        else{

                            if (data.get(0).Date.subSequence(5,7).toString().toInt() > m) {
                                nb_conf1.setText("no data")
                                nb_dead1.setText("no data")
                                nb_recov1.setText("no data")
                            }
                            else if (data.get(0).Date.subSequence(5,7).toString().toInt() < m){
                                var i : Int = 0
                                var cond: Boolean = true
                                while (i < data.size && cond)
                                {
                                    if (data.get(i).Date.subSequence(0,4).toString().toInt() == y && data.get(i).Date.subSequence(5,7).toString().toInt() == m && data.get(i).Date.subSequence(8,10).toString().toInt() == d){
                                        nb_conf1.setText(data.get(i).Confirmed.toString())
                                        nb_dead1.setText(data.get(i).Deaths.toString())
                                        nb_recov1.setText(data.get(i).Recovered.toString())
                                        cond = false
                                    }
                                    i++
                                }
                                if (cond)
                                {
                                    nb_conf1.setText("no data")
                                    nb_dead1.setText("no data")
                                    nb_recov1.setText("no data")
                                }
                            }
                            else{
                                if (data.get(0).Date.subSequence(8,10).toString().toInt() > y) {
                                    nb_conf1.setText("no data")
                                    nb_dead1.setText("no data")
                                    nb_recov1.setText("no data")
                                }
                                else if (data.get(0).Date.subSequence(8,10).toString().toInt() < y){
                                    var i : Int = 0
                                    var cond: Boolean = true
                                    while (i < data.size && cond)
                                    {
                                        if (data.get(i).Date.subSequence(0,4).toString().toInt() == y && data.get(i).Date.subSequence(5,7).toString().toInt() == m && data.get(i).Date.subSequence(8,10).toString().toInt() == d){
                                            nb_conf1.setText(data.get(i).Confirmed.toString())
                                            nb_dead1.setText(data.get(i).Deaths.toString())
                                            nb_recov1.setText(data.get(i).Recovered.toString())
                                            cond = false
                                        }
                                        i++
                                    }
                                    if (cond)
                                    {
                                        nb_conf1.setText("no data")
                                        nb_dead1.setText("no data")
                                        nb_recov1.setText("no data")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        service.get_united_kingdom().enqueue(callback)
    }
}