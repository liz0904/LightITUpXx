package com.example.lightitupxx.api

import io.realm.RealmObject

open class userClass (var id : String="",
                      var pwd : String="",
                      var birth: String="")
    :RealmObject(){}