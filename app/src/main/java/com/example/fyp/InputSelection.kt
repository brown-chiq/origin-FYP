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
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.io.IOException
import java.io.OutputStream
import java.util.UUID


class InputSelection : ComponentActivity() {
    var TAG = "-----------"
    private var macAddress = ""
    var uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
    var bluetoothAdapter: BluetoothAdapter? = null
    var bluetoothDevice: BluetoothDevice? = null
    var bluetoothSocket: BluetoothSocket? = null
    var outputStream: OutputStream? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_selection)

        var inputOneHigh = findViewById<Button>(R.id.input_one_high)
        var inputOneLow = findViewById<Button>(R.id.input_one_low)
        var inputTwoHigh = findViewById<Button>(R.id.input_two_high)
        var inputTwoLow = findViewById<Button>(R.id.input_two_low)
        var inputThreeHigh = findViewById<Button>(R.id.input_three_high)
        var inputThreeLow = findViewById<Button>(R.id.input_three_low)
        var inputFourHigh = findViewById<Button>(R.id.input_four_high)
        var inputFourLow = findViewById<Button>(R.id.input_four_low)
        var inputFiveHigh = findViewById<Button>(R.id.input_five_high)
        var inputFiveLow = findViewById<Button>(R.id.input_five_low)
        var inputSixHigh = findViewById<Button>(R.id.input_six_high)
        var inputSixLow = findViewById<Button>(R.id.input_six_low)
        var btConnectionStatus = findViewById<TextView>(R.id.connection_status)

        // Request Bluetooth permissions
        requestBluetoothPermissions()

        // Get Bluetooth adapter
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

        // Find paired devices
        findPairedDevices()

        // Connect to selected Bluetooth device
        connectToDevice(btConnectionStatus)

        var backButton = findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            val intent = Intent(this, KitMain::class.java)
            startActivity(intent)
            @Suppress("DEPRECATION")
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

        inputOneHigh.setOnClickListener {
            sendCommand(1, 1)
            Toast.makeText( this@InputSelection,"Input 1 is set to HIGH",Toast.LENGTH_LONG).show()
        }
        inputOneLow.setOnClickListener {
            sendCommand(1, 0)
            Toast.makeText( this@InputSelection,"Input 1 is set to LOW",Toast.LENGTH_LONG).show()
        }
        inputTwoHigh.setOnClickListener {
            sendCommand(2, 1)
            Toast.makeText( this@InputSelection,"Input 2 is set to HIGH",Toast.LENGTH_LONG).show()
        }
        inputTwoLow.setOnClickListener {
            sendCommand(2, 0)
            Toast.makeText( this@InputSelection,"Input 2 is set to LOW",Toast.LENGTH_LONG).show()
        }
        inputThreeHigh.setOnClickListener {
            sendCommand(3, 1)
            Toast.makeText( this@InputSelection,"Input 3 is set to HIGH",Toast.LENGTH_LONG).show()
        }
        inputThreeLow.setOnClickListener {
            sendCommand(3, 0)
            Toast.makeText( this@InputSelection,"Input 3 is set to LOW",Toast.LENGTH_LONG).show()
        }
        inputFourHigh.setOnClickListener {
            sendCommand(4, 1)
            Toast.makeText( this@InputSelection,"Input 4 is set to HIGH",Toast.LENGTH_LONG).show()
        }
        inputFourLow.setOnClickListener {
            sendCommand(4, 0)
            Toast.makeText( this@InputSelection,"Input 4 is set to LOW",Toast.LENGTH_LONG).show()
        }
        inputFiveHigh.setOnClickListener {
            sendCommand(5, 1)
            Toast.makeText( this@InputSelection,"Input 5 is set to HIGH",Toast.LENGTH_LONG).show()
        }
        inputFiveLow.setOnClickListener {
            sendCommand(5, 0)
            Toast.makeText( this@InputSelection,"Input 5 is set to LOW",Toast.LENGTH_LONG).show()
        }
        inputSixHigh.setOnClickListener {
            sendCommand(6, 1)
            Toast.makeText( this@InputSelection,"Input 6 is set to HIGH",Toast.LENGTH_LONG).show()
        }
        inputSixLow.setOnClickListener {
            sendCommand(6, 0)
            Toast.makeText( this@InputSelection,"Input 6 is set to LOW",Toast.LENGTH_LONG).show()
        }

    }

    private fun requestBluetoothPermissions() {
        if (Build.VERSION.SDK_INT > 31 &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED
        ) {
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
    private fun connectToDevice(connectionStatus : TextView ) {
        if (bluetoothDevice != null) {
            Thread {
                try {
                    bluetoothSocket = bluetoothDevice!!.createRfcommSocketToServiceRecord(uuid)
                    bluetoothAdapter?.cancelDiscovery()
                    bluetoothSocket?.connect()
                    outputStream = bluetoothSocket?.outputStream
                    runOnUiThread {
                        Log.d("Bluetooth","Bluetooth successfully connected")
                        Toast.makeText(
                            this@InputSelection,
                            "Bluetooth successfully connected",
                            Toast.LENGTH_LONG
                        ).show()
                        connectionStatus.setText("Connected to LogiKit")
                        connectionStatus.setTextColor(Color.parseColor("#7CFC00"))
                    }
                } catch (e: IOException) {
                    runOnUiThread {
                        Log.d("Bluetooth","Turn on Bluetooth, connect with HC-05 and restart the app")
                        Toast.makeText(
                            this@InputSelection,
                            "Turn on Bluetooth, connect with HC-05 and restart the app",
                            Toast.LENGTH_SHORT
                        ).show()
                        connectionStatus.setText("Not connected to LogiKit")
                        connectionStatus.setTextColor(Color.parseColor("#FF0000"))
                    }
                    e.printStackTrace()
                }
            }.start()
        }
    }

    private fun sendCommand(LEDIndex: Int, value: Int) {
        val command = "$LEDIndex,$value,9\n"
        try {
            outputStream?.write(command.toByteArray())
            Log.d("Command", command)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            outputStream?.close()
            bluetoothSocket?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}