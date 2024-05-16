package com.example.scannerqr.model

import androidx.fragment.app.Fragment

data class Detail(var title: String ="", val icon : Int = 0, val fragmentOpen : Class<*>? = null , val action: (()-> Unit)? =null)