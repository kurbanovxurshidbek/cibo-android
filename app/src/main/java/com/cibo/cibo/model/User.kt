package com.cibo.cibo.model

class User {
    var name : String = ""
    var phoneNumber : String = ""

    var device_id = ""
    var device_type = "A"
    var device_token = ""

    constructor(name : String, phoneNumber: String){
        this.name = name
        this.phoneNumber = phoneNumber
    }
}