package com.demo.moviesmvvm.data.repository

class NetworkState(val status: Status, msg: String) {
    companion object {
        var loaded: NetworkState? = null
        var laoding: NetworkState? = null
        var error: NetworkState? = null
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
