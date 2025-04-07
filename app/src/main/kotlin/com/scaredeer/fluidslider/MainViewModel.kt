package com.scaredeer.fluidslider

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    companion object {
        private val ALPHABET: List<String> = mutableListOf(
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
            "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
        )
        private val CLOCK: List<String> = mutableListOf(
            "00:00", "01:00", "02:00", "03:00", "04:00", "05:00",
            "06:00", "07:00", "08:00", "09:00", "10:00", "11:00",
            "12:00", "13:00", "14:00", "15:00", "16:00", "17:00",
            "18:00", "19:00", "20:00", "21:00", "22:00", "23:00",
            "24:00"
        )
        private val NUMBER: List<String> = mutableListOf(
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
        )
    }

    private val _list: MutableLiveData<List<String>> = MutableLiveData(NUMBER)

    private val _start: MutableLiveData<String> = MutableLiveData()
    val start: LiveData<String>
        get() = _start

    private val _end: MutableLiveData<String> = MutableLiveData()
    val end: LiveData<String>
        get() = _end

    private val _position: MutableLiveData<Float> = MutableLiveData(0.5f)
    fun setPosition(position: Float) {
        _position.value = position
    }

    private val _current: MediatorLiveData<String>
    val current: LiveData<String>
        get() = _current

    private var count: Int = 0

    private fun changeList() {
        when (count % 3) {
            1 -> _list.setValue(ALPHABET)
            2 -> _list.setValue(CLOCK)
            else -> _list.setValue(NUMBER)
        }
        ++count

        val list = _list.value!!
        _start.value = list[0]
        _end.value = list[list.size - 1]
    }

    init {
        changeList()
        _current = MediatorLiveData()
        _current.addSource(_list) { list: List<String> ->
            _current.setValue(
                list[Math.round(
                    _position.value!! * (list.size - 1)
                )]
            )
        }
        _current.addSource(_position) { position: Float ->
            _current.setValue(
                _list.value!![Math.round(position * (_list.value!!.size - 1))]
            )
        }
    }

    fun onClick(view: View?) {
        changeList()
    }
}