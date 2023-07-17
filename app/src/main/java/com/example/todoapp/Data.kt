package com.example.todoapp

class Data {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
     var data:String?=null
    var key:String?=null
    constructor()
    constructor(data: String?, key: String?) {
        this.data = data
        this.key = key
    }


}