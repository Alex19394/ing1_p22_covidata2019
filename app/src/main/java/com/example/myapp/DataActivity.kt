package com.example.myapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)
        showStats()
        get_usa()
        get_united_kingdom()
        get_france()
        get_peru()
        get_brazil()
        get_russia()
        get_india()
        get_spain()
        get_italy()
        get_iran()

        val graph = findViewById(R.id.button1) as Button

        graph.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                // Handler code here.
                val intent = Intent(applicationContext, CountryDataActivity::class.java);
                intent.putExtra("country", "USA")
                startActivity(intent);
            }
        })

        val graph2 = findViewById(R.id.button2) as Button

        graph2.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                // Handler code here.
                val intent = Intent(applicationContext, CountryDataActivity::class.java);
                intent.putExtra("country", "Brazil")
                startActivity(intent);
            }
        })

        val graph3 = findViewById(R.id.button3) as Button

        graph3.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                // Handler code here.
                val intent = Intent(applicationContext, CountryDataActivity::class.java);
                intent.putExtra("country", "Russia")
                startActivity(intent);
            }
        })

        val graph4 = findViewById(R.id.button4) as Button

        graph4.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                // Handler code here.
                val intent = Intent(applicationContext, CountryDataActivity::class.java);
                intent.putExtra("country", "India")
                startActivity(intent);
            }
        })

        val graph5 = findViewById(R.id.button5) as Button

        graph5.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                // Handler code here.
                val intent = Intent(applicationContext, CountryDataActivity::class.java);
                intent.putExtra("country", "Spain")
                startActivity(intent);
            }
        })

        val graph6 = findViewById(R.id.button6) as Button

        graph6.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                // Handler code here.
                val intent = Intent(applicationContext, CountryDataActivity::class.java);
                intent.putExtra("country", "Italy")
                startActivity(intent);
            }
        })

        val graph7 = findViewById(R.id.button7) as Button

        graph7.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                // Handler code here.
                val intent = Intent(applicationContext, CountryDataActivity::class.java);
                intent.putExtra("country", "Peru")
                startActivity(intent);
            }
        })

        val graph8 = findViewById(R.id.button8) as Button

        graph8.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                // Handler code here.
                val intent = Intent(applicationContext, CountryDataActivity::class.java);
                intent.putExtra("country", "France")
                startActivity(intent);
            }
        })

        val graph9 = findViewById(R.id.button9) as Button

        graph9.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                // Handler code here.
                val intent = Intent(applicationContext, CountryDataActivity::class.java);
                intent.putExtra("country", "Iran")
                startActivity(intent);
            }
        })

        val graph10 = findViewById(R.id.button10) as Button

        graph10.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                // Handler code here.
                val intent = Intent(applicationContext, CountryDataActivity::class.java);
                intent.putExtra("country", "United-Kingdom")
                startActivity(intent);
            }
        })
    }
    fun showStats(){
        val baseUrl = "https://api.covid19api.com/"
        val jsonConverter = GsonConverterFactory.create(GsonBuilder().create())
        val retrofitClient = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(jsonConverter)
            .build()
        val service = retrofitClient.create(WSWorldInterface::class.java)
        val callback : Callback <WorldObject> = object : Callback <WorldObject> {
            override fun onFailure(call: Call<WorldObject>, t: Throwable) {
                TODO("Not yet implemented")
                nb_conf.setText("error")
            }

            override fun onResponse(call: Call<WorldObject>, response: Response<WorldObject>) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        val data : WorldObject = response.body()!!
                        nb_conf.setText(data.TotalConfirmed.toString())
                        nb_recov.setText(data.TotalRecovered.toString())
                        nb_dead.setText(data.TotalDeaths.toString())
                    }
                }
            }


        }
        service.getworldtotal().enqueue(callback)

    }

    fun get_usa(){
        val baseUrl = "https://api.covid19api.com/"
        val jsonConverter = GsonConverterFactory.create(GsonBuilder().create())
        val retrofitClient = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(jsonConverter)
            .build()
        val service = retrofitClient.create(WSCountryInterface::class.java)
        val callback : Callback <List<CountryObject>> = object : Callback <List<CountryObject>> {
            override fun onFailure(call: Call<List<CountryObject>>, t: Throwable) {
                TODO("Not yet implemented")
                nb_conf.setText("error")
            }

            override fun onResponse(call: Call<List<CountryObject>>, response: Response<List<CountryObject>>) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        val data : List<CountryObject> = response.body()!!
                        usa_conf.setText(data.last().Confirmed.toString())
                        usa_dead.setText(data.last().Deaths.toString())
                        usa_recov.setText(data.last().Recovered.toString())
                    }
                }
            }
        }
        service.get_usa().enqueue(callback)
    }

    fun get_peru(){
        val baseUrl = "https://api.covid19api.com/"
        val jsonConverter = GsonConverterFactory.create(GsonBuilder().create())
        val retrofitClient = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(jsonConverter)
            .build()
        val service = retrofitClient.create(WSCountryInterface::class.java)
        val callback : Callback <List<CountryObject>> = object : Callback <List<CountryObject>> {
            override fun onFailure(call: Call<List<CountryObject>>, t: Throwable) {
                TODO("Not yet implemented")
                nb_conf.setText("error")
            }

            override fun onResponse(call: Call<List<CountryObject>>, response: Response<List<CountryObject>>) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        val data : List<CountryObject> = response.body()!!
                        peru_conf.setText(data.last().Confirmed.toString())
                        peru_dead.setText(data.last().Deaths.toString())
                        peru_recov.setText(data.last().Recovered.toString())
                    }
                }
            }
        }
        service.get_peru().enqueue(callback)
    }

    fun get_brazil(){
        val baseUrl = "https://api.covid19api.com/"
        val jsonConverter = GsonConverterFactory.create(GsonBuilder().create())
        val retrofitClient = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(jsonConverter)
            .build()
        val service = retrofitClient.create(WSCountryInterface::class.java)
        val callback : Callback <List<CountryObject>> = object : Callback <List<CountryObject>> {
            override fun onFailure(call: Call<List<CountryObject>>, t: Throwable) {
                TODO("Not yet implemented")
                nb_conf.setText("error")
            }

            override fun onResponse(call: Call<List<CountryObject>>, response: Response<List<CountryObject>>) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        val data : List<CountryObject> = response.body()!!
                        brazil_conf.setText(data.last().Confirmed.toString())
                        brazil_dead.setText(data.last().Deaths.toString())
                        brazil_recov.setText(data.last().Recovered.toString())
                    }
                }
            }


        }
        service.get_brazil().enqueue(callback)

    }

    fun get_russia(){
        val baseUrl = "https://api.covid19api.com/"
        val jsonConverter = GsonConverterFactory.create(GsonBuilder().create())
        val retrofitClient = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(jsonConverter)
            .build()
        val service = retrofitClient.create(WSCountryInterface::class.java)
        val callback : Callback <List<CountryObject>> = object : Callback <List<CountryObject>> {
            override fun onFailure(call: Call<List<CountryObject>>, t: Throwable) {
                TODO("Not yet implemented")
                nb_conf.setText("error")
            }

            override fun onResponse(call: Call<List<CountryObject>>, response: Response<List<CountryObject>>) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        val data : List<CountryObject> = response.body()!!
                        russia_conf.setText(data.last().Confirmed.toString())
                        russia_dead.setText(data.last().Deaths.toString())
                        russia_recov.setText(data.last().Recovered.toString())
                    }
                }
            }


        }
        service.get_russia().enqueue(callback)

    }

    fun get_india(){
        val baseUrl = "https://api.covid19api.com/"
        val jsonConverter = GsonConverterFactory.create(GsonBuilder().create())
        val retrofitClient = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(jsonConverter)
            .build()
        val service = retrofitClient.create(WSCountryInterface::class.java)
        val callback : Callback <List<CountryObject>> = object : Callback <List<CountryObject>> {
            override fun onFailure(call: Call<List<CountryObject>>, t: Throwable) {
                TODO("Not yet implemented")
                nb_conf.setText("error")
            }

            override fun onResponse(call: Call<List<CountryObject>>, response: Response<List<CountryObject>>) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        val data : List<CountryObject> = response.body()!!
                        india_conf.setText(data.last().Confirmed.toString())
                        india_dead.setText(data.last().Deaths.toString())
                        india_recov.setText(data.last().Recovered.toString())
                    }
                }
            }


        }
        service.get_india().enqueue(callback)

    }

    fun get_united_kingdom(){
        val baseUrl = "https://api.covid19api.com/"
        val jsonConverter = GsonConverterFactory.create(GsonBuilder().create())
        val retrofitClient = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(jsonConverter)
            .build()
        val service = retrofitClient.create(WSCountryInterface::class.java)
        val callback : Callback <List<CountryObject>> = object : Callback <List<CountryObject>> {
            override fun onFailure(call: Call<List<CountryObject>>, t: Throwable) {
                TODO("Not yet implemented")
                nb_conf.setText("error")
            }

            override fun onResponse(call: Call<List<CountryObject>>, response: Response<List<CountryObject>>) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        val data : List<CountryObject> = response.body()!!
                        uk_conf.setText(data.last().Confirmed.toString())
                        uk_dead.setText(data.last().Deaths.toString())
                        uk_recov.setText(data.last().Recovered.toString())
                    }
                }
            }


        }
        service.get_united_kingdom().enqueue(callback)

    }


    fun get_spain(){
        val baseUrl = "https://api.covid19api.com/"
        val jsonConverter = GsonConverterFactory.create(GsonBuilder().create())
        val retrofitClient = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(jsonConverter)
            .build()
        val service = retrofitClient.create(WSCountryInterface::class.java)
        val callback : Callback <List<CountryObject>> = object : Callback <List<CountryObject>> {
            override fun onFailure(call: Call<List<CountryObject>>, t: Throwable) {
                TODO("Not yet implemented")
                nb_conf.setText("error")
            }

            override fun onResponse(call: Call<List<CountryObject>>, response: Response<List<CountryObject>>) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        val data : List<CountryObject> = response.body()!!
                        spain_conf.setText(data.last().Confirmed.toString())
                        spain_dead.setText(data.last().Deaths.toString())
                        spain_recov.setText(data.last().Recovered.toString())
                    }
                }
            }
        }
        service.get_spain().enqueue(callback)
    }

    fun get_italy(){
        val baseUrl = "https://api.covid19api.com/"
        val jsonConverter = GsonConverterFactory.create(GsonBuilder().create())
        val retrofitClient = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(jsonConverter)
            .build()
        val service = retrofitClient.create(WSCountryInterface::class.java)
        val callback : Callback <List<CountryObject>> = object : Callback <List<CountryObject>> {
            override fun onFailure(call: Call<List<CountryObject>>, t: Throwable) {
                TODO("Not yet implemented")
                nb_conf.setText("error")
            }

            override fun onResponse(call: Call<List<CountryObject>>, response: Response<List<CountryObject>>) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        val data : List<CountryObject> = response.body()!!
                        italy_conf.setText(data.last().Confirmed.toString())
                        italy_dead.setText(data.last().Deaths.toString())
                        italy_recov.setText(data.last().Recovered.toString())
                    }
                }
            }
        }
        service.get_italy().enqueue(callback)
    }

    fun get_iran(){
        val baseUrl = "https://api.covid19api.com/"
        val jsonConverter = GsonConverterFactory.create(GsonBuilder().create())
        val retrofitClient = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(jsonConverter)
            .build()
        val service = retrofitClient.create(WSCountryInterface::class.java)
        val callback : Callback <List<CountryObject>> = object : Callback <List<CountryObject>> {
            override fun onFailure(call: Call<List<CountryObject>>, t: Throwable) {
                TODO("Not yet implemented")
                nb_conf.setText("error")
            }

            override fun onResponse(call: Call<List<CountryObject>>, response: Response<List<CountryObject>>) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        val data : List<CountryObject> = response.body()!!
                        iran_conf.setText(data.last().Confirmed.toString())
                        iran_dead.setText(data.last().Deaths.toString())
                        iran_recov.setText(data.last().Recovered.toString())
                    }
                }
            }
        }
        service.get_iran().enqueue(callback)
    }

    fun get_france(){
        val baseUrl = "https://api.covid19api.com/"
        val jsonConverter = GsonConverterFactory.create(GsonBuilder().create())
        val retrofitClient = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(jsonConverter)
            .build()
        val service = retrofitClient.create(WSCountryInterface::class.java)
        val callback : Callback <List<CountryObject>> = object : Callback <List<CountryObject>> {
            override fun onFailure(call: Call<List<CountryObject>>, t: Throwable) {
                TODO("Not yet implemented")
                nb_conf.setText("error")
            }

            override fun onResponse(call: Call<List<CountryObject>>, response: Response<List<CountryObject>>) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        val data : List<CountryObject> = response.body()!!
                        france_conf.setText(data.last().Confirmed.toString())
                        france_dead.setText(data.last().Deaths.toString())
                        france_recov.setText(data.last().Recovered.toString())
                    }
                }
            }
        }
        service.get_france().enqueue(callback)
    }
}