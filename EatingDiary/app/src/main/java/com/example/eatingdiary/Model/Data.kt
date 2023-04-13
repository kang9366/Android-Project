package com.example.eatingdiary.Model

data class C005(
    val RESULT: RESULT,
    val row: List<Row>,
    val total_count: String
)

data class Data(
    val C005: C005
)

data class RESULT(
    val CODE: String,
    val MSG: String
)

data class Row(
    val BAR_CD: String,
    val BSSH_NM: String,
    val CLSBIZ_DT: String,
    val END_DT: String,
    val INDUTY_NM: String,
    val POG_DAYCNT: String,
    val PRDLST_DCNM: String,
    val PRDLST_NM: String,
    val PRDLST_REPORT_NO: String,
    val PRMS_DT: String,
    val SITE_ADDR: String
)