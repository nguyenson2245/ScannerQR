package com.example.scannerqr.repository

class Repository {
    companion object {
        private var instance: Repository? = null

        @Synchronized
        fun newInstance(): Repository {
            if (instance == null) instance = Repository()
            return instance!!
        }
    }
}