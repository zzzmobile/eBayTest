package com.test.ebay.view

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.test.ebay.R
import com.test.ebay.di.Injection
import com.test.ebay.model.Earthquakes
import com.test.ebay.viewmodel.EarthquakeListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var listViewModel: EarthquakeListViewModel
    private lateinit var adapter: EarthquakeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // connectivity manager for checking network available
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val builder: NetworkRequest.Builder = NetworkRequest.Builder()
        connectivityManager.registerNetworkCallback(
            builder.build(),
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {    // network available
                    runOnUiThread {
                        onResume()
                    }
                }

                override fun onLost(network: Network) {     // network unavailable
                    Snackbar.make(
                        contentView,
                        getString(R.string.network_unavailable),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        )

        setupViewModel()
        setupUI()

        // load data
        listViewModel.loadData(true, 44.1, -9.9, -22.4, 55.2, "mkoppelman")
    }

    // view
    private fun setupUI() {
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.hide()

        adapter = EarthquakeAdapter(listViewModel.earthquakes.value ?: Earthquakes())
        rvEarthquakes.layoutManager = LinearLayoutManager(this)
        rvEarthquakes.adapter = adapter
        // item click listener for recyclerview
        rvEarthquakes.addOnItemTouchListener(RecyclerTouchListener(this, object : ClickListener {
                    override fun onClick(view: View?, position: Int) {
                        val earthquake = listViewModel.earthquakes.value?.earthQuakes?.get(position)
                        val lat = earthquake?.latitude
                        val lng = earthquake?.longitude
                        val lblString = earthquake?.eqid
                        val urlAddress =
                            "http://maps.google.com/maps?q=$lat,$lng($lblString)&iwloc=A&hl=es"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlAddress))
                        startActivity(intent)
                    }
            })
        )
    }

    // view model
    private fun setupViewModel() {
        listViewModel = ViewModelProvider(
            this,
            Injection.provideViewModelFactory()
        ).get(EarthquakeListViewModel::class.java)

        listViewModel.earthquakes.observe(this, renderData)
        listViewModel.isViewLoading.observe(this, isViewLoadingObserver)
        listViewModel.onMessageError.observe(this, onMessageErrorObserver)
    }

    //observers
    private val renderData = Observer<Earthquakes> {
        adapter.update(it)
    }

    private val isViewLoadingObserver = Observer<Boolean> {
        val visibility = if (it) View.VISIBLE else View.GONE
        progressBar.visibility = visibility
    }

    private val onMessageErrorObserver = Observer<Any> {
        Snackbar.make(
            contentView,
            getString(R.string.error_fetch_data),
            Snackbar.LENGTH_SHORT
        ).show()
    }
}