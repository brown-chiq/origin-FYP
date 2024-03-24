package com.example.fyp

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.util.UUID
import kotlin.math.pow

class LogiKitTruthTable : ComponentActivity() {

    var TAG = "-----------"
    private var macAddress = ""
    var uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
    var bluetoothAdapter: BluetoothAdapter? = null
    var bluetoothDevice: BluetoothDevice? = null
    var bluetoothSocket: BluetoothSocket? = null
    var outputStream: OutputStream? = null
    var inputStream: InputStream? = null

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logi_kit_truth_table)

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

        val buttonGenerate = findViewById<Button>(R.id.buttonGenerate)
        val tableLayout = findViewById<TableLayout>(R.id.tableLayout)
        val i1 = findViewById<Switch>(R.id.switch1)
        val i2 = findViewById<Switch>(R.id.switch2)
        val i3 = findViewById<Switch>(R.id.switch3)
        val i4 = findViewById<Switch>(R.id.switch4)
        val i5 = findViewById<Switch>(R.id.switch5)
        val i6 = findViewById<Switch>(R.id.switch6)
        val o1 = findViewById<Switch>(R.id.output1)
        val o2 = findViewById<Switch>(R.id.output2)
        val o3 = findViewById<Switch>(R.id.output3)
        val switches = arrayOf(i1, i2, i3, i4, i5, i6, o1, o2, o3)
        val scope = CoroutineScope(Job() + Dispatchers.Main)

        var rows = 0
        var numInput: Int = 0
        var columns = 0
        var switchesChecked = arrayListOf<String>()
        var action = false

        buttonGenerate.setOnClickListener {

            for (switch in switches) {
                if (switch.isChecked) {
                    columns++
                    val switchChecked = switch.text.toString()
                    Log.d("TruthTable", "$switchChecked is checked")
                    switchesChecked.add(switchChecked)
                }

                if (switch.isChecked && (switch == i1 || switch == i2 || switch == i3 || switch == i4 || switch == i5 || switch == i6)) {
                    numInput++
                }
            }

            Log.d("TruthTable", "num of input $numInput")
            Log.d("TruthTable", "Switches Checked are : $switchesChecked")

            rows = 1 + 2.0.pow(numInput).toInt()

            Log.d("TruthTable", "Rows: $rows, Columns: $columns")

            generateTable(rows, columns, numInput, switchesChecked, tableLayout)




            rows = 0
            columns = 0
            numInput = 0
            switchesChecked.clear()

        }

//        inputOneHigh.setOnClickListener {
//            sendCommand(1, 1)
//            Toast.makeText( this@InputSelection,"Input 1 is set to HIGH", Toast.LENGTH_LONG).show()
//        }

    }

    private fun generateTable(
        rows: Int,
        columns: Int,
        numInput: Int,
        switchesChecked: ArrayList<String>,
        tableLayout: TableLayout
    ) : Boolean {
        tableLayout.removeAllViews() // Clear existing content

        val inputSequenceGenerated: ArrayList<String> = generateTruthTableInputSequence(numInput)
        var binNum : String
        var binInArray = arrayListOf<String>()

        for (i in 0 until rows) {
            val tableRow = TableRow(this)
            tableRow.layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT,
            )
            binInArray.clear()

            binNum = inputSequenceGenerated[i]
            Log.d("TruthTable", "binNum: $binNum")
            for (char in binNum) {
                binInArray.add(char.toString())
                Log.d("TruthTable", "binInArray : $binInArray")
            }

            for (j in 0 until columns) {
                val textView = TextView(this)
                textView.textAlignment = TextView.TEXT_ALIGNMENT_CENTER

                if (i == 0) { //header title
                    textView.text = switchesChecked[j]
                    textView.setTypeface(null, Typeface.BOLD)
                } else { //binary combinations

                    if (j < numInput) {//generate truthtable input
                        var switch  = switchesChecked[j].toString()
                        var input =binInArray[j]
                        Log.d("TruthTable", "Row $i $switch $input")
                        var inputNum = switch[6].digitToInt()
                        sendCommand(inputNum, input.toInt()) //send command to arduino
                        stopSending()
                        receive()
                        stopReceiving()

                        textView.text = input
                    } else { // listen output from arduino
//
                    }
                }
                textView.setPadding(10, 10, 10, 10)

                tableRow.addView(textView)
            }

            tableLayout.addView(tableRow)
        }
        return true
    }

    private fun generateTruthTableInputSequence(numInputs: Int): ArrayList<String> {
        var inputSequence = arrayListOf<String>()
        val maxNumber = (1 shl numInputs) - 1 // Calculate the maximum number using bit shifting
        Log.d("TruthTable", "Truth Table Input Sequence with $numInputs inputs")
        Log.d("TruthTable", "Truth Table Input Sequence Max Number $maxNumber ")
        inputSequence.add(toBinary(maxNumber, numInputs))
        for (i in 0..maxNumber) {
            inputSequence.add(toBinary(i, numInputs))
        }
        Log.d("TruthTable", "Input Sequence : $inputSequence")
        return inputSequence
    }

    private fun toBinary(num: Int, length: Int): String {
        return String.format("%" + length + "s", Integer.toBinaryString(num)).replace(' ', '0')
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
                            this@LogiKitTruthTable,
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
                            this@LogiKitTruthTable,
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

    private fun sendCommand(LEDIndex: Int, value: Int) {
        val command = "$LEDIndex,$value,9\n"
        Log.d("SendCommand", command)
        try {
            outputStream?.write(command.toByteArray())
            Log.d("Command", command)
        } catch (e: IOException) {
            e.printStackTrace()
            Log.e(TAG, "Error sending data: ${e.message}")
        }
    }

    fun stopSending() {
        // Close the output stream
        try {
            outputStream?.close()
            Log.d(TAG, "Output stream closed")
        } catch (e: IOException) {
            Log.e(TAG, "Error closing output stream: ${e.message}")
        }
    }

    fun receive() {
        try {
            val buffer = ByteArray(1024)
            val numBytes = inputStream?.read(buffer)
            val command = "!"
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
         var bytes = 0 // bytes returned from read()
         var numberOfReadings = 0 //to control the number of readings from the Arduino
         var readMessage = ""
            // Keep listening to the InputStream until an exception occurs.

         while (numberOfReadings < 1) {
             try { 
                 val byte = inputStream?.read(buffer) ?: -1
                 if (byte != -1) {
                     val charRead = buffer[0].toChar() // assuming it's a single character message
                     if (charRead == '\n') {
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
