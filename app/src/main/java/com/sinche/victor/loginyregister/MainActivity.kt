package com.sinche.victor.loginyregister

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMapClickListener
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MainActivity : AppCompatActivity(), OnMapReadyCallback, OnMapClickListener,
    OnMapLongClickListener {
    var txtLatitud: EditText? = null
    var txtLongitud: EditText? = null
    var mMap: GoogleMap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtLatitud = findViewById(R.id.txtLatitud)
        txtLongitud = findViewById(R.id.txtLongitud)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap!!.setOnMapClickListener(this)
        mMap!!.setOnMapLongClickListener(this)
        val peru = LatLng(-12.044532528471509, -76.96179563403719)
        //mMap!!.addMarker(MarkerOptions().position(peru).title("tu vieja"))
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(peru))
        agregarMarcadoresAdicionales()
    }

    override fun onMapClick(latLng: LatLng) {
        txtLatitud!!.setText(latLng.latitude.toString())
        txtLongitud!!.setText(latLng.longitude.toString())
        mMap!!.clear()
        val peru = LatLng(latLng.latitude, latLng.longitude)
        //mMap!!.addMarker(MarkerOptions().position(peru).title(""))
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(peru))
    }

    override fun onMapLongClick(latLng: LatLng) {
        txtLatitud!!.setText(latLng.latitude.toString())
        txtLongitud!!.setText(latLng.longitude.toString())
        mMap!!.clear()
        val peru = LatLng(latLng.latitude, latLng.longitude)
        //mMap!!.addMarker(MarkerOptions().position(peru).title(""))
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(peru))
    }
    private fun agregarMarcadoresAdicionales() {
        val coordenadas1 = LatLng(-12.0455679,-76.9534466)
        mMap?.addMarker(MarkerOptions().position(coordenadas1).title("Buen sabor"))

        val coordenadas2 = LatLng(-12.045342075454162, -76.95363342761993)
        mMap?.addMarker(MarkerOptions().position(coordenadas2).title("restaurante calle 6"))

        // Agrega más marcadores de coordenadas según tus necesidades
    }
}











