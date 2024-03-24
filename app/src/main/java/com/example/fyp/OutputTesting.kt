package com.example.fyp

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.widget.TableLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.util.UUID

class OutputTesting : ComponentActivity() {

    var TAG = "-----------"
    private var macAddress = ""
    var uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
    var bluetoothAdapter: BluetoothAdapter? = null
    var bluetoothDevice: BluetoothDevice? = null
    var bluetoothSocket: BluetoothSocket? = null
    var outputStream: OutputStream? = null
    var inputStream: InputStream? = null

    @SuppressLint("MissingInflatedId", "UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_output_testing)
        var btConnectionStatus = findViewById<TextView>(R.id.connection_status)

        requestBluetoothPermissions() // Request Bluetooth permissions

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter() // Get Bluetooth adapter

        findPairedDevices() // Find paired devices

        connectToDevice(btConnectionStatus) // Connect to selected Bluetooth device

        var backButton = findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            val intent = Intent(this, KitMain::class.java)
            startActivity(intent)
            @Suppress("DEPRECATION")
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

        val generateOutput = findViewById<Button>(R.id.generate)
        val o1 = findViewById<Switch>(R.id.o1)
        val o2 = findViewById<Switch>(R.id.o2)
        val o3 = findViewById<Switch>(R.id.o3)
        val switches = arrayOf(o1, o2, o3)

        var switchesChecked = arrayListOf<String>()

        generateOutput.setOnClickListener {



            getOutput()

            for (switch in switches) {
                if (switch.isChecked) {
                    val switchChecked = switch.text.toString()
                    Log.d("TruthTable", "$switchChecked is checked")
                    switchesChecked.add(switchChecked)
                }
            }

            val outputCharArray= getOutput().toCharArray()//receives byte [000 ; 101; 100] / 3digit for each row
            o1.text= outputCharArray[1].toInt().toString()
            o2.text= outputCharArray[1].toInt().toString()
            o3.text= outputCharArray[1].toInt().toString()

            Log.d("OUTPUT TEst", "o1 : $o1, o2 : $o2, o3 : $o3")
        }


    }

        private fun requestBluetoothPermissions() {
            if (Build.VERSION.SDK_INT > 31 && ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.BLUETOOTH_CONNECT),
                    100
                )
            }
        }

        @SuppressLint("MissingPermission")
        private fun findPairedDevices() {
            val pairedDevices = bluetoothAdapter?.bondedDevices
            pairedDevices?.forEach { device ->
                if (device.name == "HC-05") {
                    macAddress = device.address
                    bluetoothDevice = device
                }
            }
        }

        @SuppressLint("MissingPermission")
        private fun connectToDevice(connectionStatus: TextView) {
            if (bluetoothDevice != null) {
                Thread {
                    try {
                        bluetoothSocket = bluetoothDevice!!.createRfcommSocketToServiceRecord(uuid)
                        bluetoothAdapter?.cancelDiscovery()
                        bluetoothSocket?.connect()
                        outputStream = bluetoothSocket?.outputStream
                        inputStream = bluetoothSocket?.inputStream
                        runOnUiThread {
                            Log.d("Bluetooth", "Bluetooth successfully connected")
                            Toast.makeText(
                                this@OutputTesting,
                                "Bluetooth successfully connected",
                                Toast.LENGTH_LONG
                            ).show()
                            connectionStatus.setText("Connected to LogiKit")
                            connectionStatus.setTextColor(Color.parseColor("#7CFC00"))
                        }
                    } catch (e: IOException) {
                        runOnUiThread {
                            Log.d(
                                "Bluetooth",
                                "Turn on Bluetooth, connect with HC-05 and restart the app"
                            )
                            Toast.makeText(
                                this@OutputTesting,
                                "Turn on Bluetooth, connect with HC-05 and restart the app",
                                Toast.LENGTH_LONG
                            ).show()
                            connectionStatus.setText("Not connected to LogiKit, Please Try Again")
                            connectionStatus.setTextColor(Color.parseColor("#FF0000"))
                        }
                        e.printStackTrace()
                    }
                }.start()
            }
        }

    fun receive() {
        try {
            val buffer = ByteArray(1024)
            val numBytes = inputStream?.read(buffer)
            val receivedData = numBytes?.let { String(buffer, 0, it) }
            Log.d(TAG, "Received data: $receivedData")
        } catch (e: IOException) {
            Log.e(TAG, "Error receiving data: ${e.message}")
        }
    }

    fun stopReceiving() {
        // Close the input stream
        try {
            inputStream?.close()
            Log.d(TAG, "Input stream closed")
        } catch (e: IOException) {
            Log.e(TAG, "Error closing input stream: ${e.message}")
        }
    }

        fun getOutput(): String {
            val buffer = ByteArray(1024)
            var numberOfReadings = 0 //to control the number of readings from the Arduino
            var readMessage = ""// Keep listening to the InputStream until an exception occurs.
            var numBytes: Int

            while (numberOfReadings < 1) {
                try {
                    val byte = inputStream?.read(buffer) ?: -1
                    val receivedData = String(buffer, 0, byte)
                    Log.d(TAG, "Received data: $receivedData.")
                    if (byte != -1) {
                        val charRead = buffer[7].toInt() // assuming it's a 3 character message
                        if (charRead == 9) {
                            Log.d(TAG, readMessage)
                            numberOfReadings++
                        } else {
                            readMessage += charRead
                        }
                    }
                } catch (e: IOException) {
                    Log.d(TAG, "Input stream was disconnected", e)
                    break
                }
            }
            return readMessage
        }
        override fun onDestroy() {
            super.onDestroy()
            try {
                outputStream?.close()
                inputStream?.close()
                bluetoothSocket?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }