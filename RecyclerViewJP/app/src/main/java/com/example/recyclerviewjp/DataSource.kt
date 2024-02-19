package com.example.recyclerviewjp

data class DataSource(
    val name: String,
    val contactNo: String
)

fun dummyData():List<DataSource>{
    return listOf(
        DataSource("Mom","01630132945"),
        DataSource("Abir","01948494203"),
        DataSource("Prinon","01682424623"),
        DataSource("Prithul","01682169669"),
        DataSource("Akter","01845693325"),
        DataSource("Jumayer","01687451299"),
        DataSource("Shuvo","01680062102"),
        DataSource("Milon","01673727664"),
        DataSource("Hasan","01682424622"),
    )
}
