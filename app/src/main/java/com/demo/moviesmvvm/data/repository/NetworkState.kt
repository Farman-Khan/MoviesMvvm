package com.demo.moviesmvvm.data.repository

class NetworkState(val status: Status, msg: String) {
    companion object {
        lateinit var loaded: NetworkState
        lateinit var laoding: NetworkState
        lateinit var error: NetworkState
    }

    init {
        loaded = NetworkState(Status.SUCCESS, "Success")
        laoding = NetworkState(Status.RUNNING, "Loading")
        error = NetworkState(Status.FAILED, "Error")
    }
}

enum class Status {
    RUNNING, SUCCESS, FAILED
}
