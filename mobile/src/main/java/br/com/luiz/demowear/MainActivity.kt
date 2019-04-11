package br.com.luiz.demowear

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import br.com.luiz.shared.Car
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.wearable.Node
import com.google.android.gms.wearable.Wearable
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
        CarListAdapter.Callback,
        GoogleApiClient.ConnectionCallbacks{

    private lateinit var client: GoogleApiClient
    private var connectedNode: List<Node>? = null

    override fun onConnected(p0: Bundle?) {
        Wearable.NodeApi.getConnectedNodes(client).setResultCallback {
            connectedNode = it.nodes
        }
    }

    override fun onConnectionSuspended(p0: Int) {
        connectedNode = null
    }

    override fun carroClicked(car: Car) {
        val gson = Gson()
        connectedNode?.forEach {
            Wearable.MessageApi.sendMessage(
                    client,
                    it.id,
                    "/car",
                    gson.toJson(car).toByteArray()
            )
        }
    }

    private var adapter: CarListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val carros = CarRepository.searchAll(this)
        adapter = CarListAdapter(carros, this)
        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(this)

        client = GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addApi(Wearable.API)
                .build()

        client.connect()
    }
}

