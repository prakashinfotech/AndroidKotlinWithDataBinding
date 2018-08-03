package model

/**
 * Created by Prakash Software Solution
 * on 20-12-2017.
 */

class LocationEntity {

    /**
     * code : 200
     * status : success
     * data : [{"id":1,"display_text":"[BRU] - Bridge Aviation","notes":null,"is_internal":true,"is_deleted":false,"modified_date":"27/06/2017 09:18:00","customer_ids":""},{"id":112,"display_text":"[LHR] - Airbase GSE Head Office","notes":null,"is_internal":true,"is_deleted":false,"modified_date":"27/06/2017 09:13:00","customer_ids":""},{"id":113,"display_text":"[LHR] - Airbase GSE Airside Workshop","notes":null,"is_internal":false,"is_deleted":false,"modified_date":"27/06/2017 09:21:00","customer_ids":""},{"id":114,"display_text":"[MAN] - AB-AWS","notes":"Vicki Brazendale\nEmail: Vicki.brazendale@uk-aws.co.uk\nMobile: 07504 973361\nOffice: 01619 461708\n\nDave Wilson\nEmail: Dave.Wilson@aero-technics.com\nMobile: 07841 775066\n","is_internal":false,"is_deleted":false,"modified_date":"27/06/2017 09:30:00","customer_ids":""},{"id":115,"display_text":"[ARN] - MBH Aviation Services AB","notes":null,"is_internal":false,"is_deleted":false,"modified_date":"02/08/2017 06:42:00","customer_ids":",1032,1064,1065,"},{"id":118,"display_text":"[BFS] - Catering","notes":null,"is_internal":false,"is_deleted":false,"modified_date":"14/08/2017 09:39:00","customer_ids":",1032,"},{"id":119,"display_text":"[BHX] - Catering","notes":null,"is_internal":false,"is_deleted":false,"modified_date":"25/08/2017 05:54:00","customer_ids":",1032,"},{"id":120,"display_text":"[CWL] - Bond & Catering","notes":null,"is_internal":false,"is_deleted":false,"modified_date":"29/07/2017 07:37:00","customer_ids":",1065,"},{"id":121,"display_text":"[GLA] - Catering","notes":null,"is_internal":false,"is_deleted":false,"modified_date":"27/06/2017 08:56:00","customer_ids":""},{"id":122,"display_text":"[LGW] - Bond & Catering","notes":null,"is_internal":false,"is_deleted":false,"modified_date":"27/06/2017 08:54:00","customer_ids":""},{"id":123,"display_text":"[MAN] - Catering","notes":null,"is_internal":false,"is_deleted":false,"modified_date":"27/06/2017 08:57:00","customer_ids":""},{"id":124,"display_text":"[NCL] - Catering","notes":null,"is_internal":false,"is_deleted":false,"modified_date":"29/07/2017 08:57:00","customer_ids":",1065,"},{"id":125,"display_text":"[STN] - Bond & Catering","notes":null,"is_internal":false,"is_deleted":false,"modified_date":"27/06/2017 08:52:00","customer_ids":""},{"id":126,"display_text":"[BFS] - Bond","notes":null,"is_internal":false,"is_deleted":false,"modified_date":"22/08/2017 13:48:00","customer_ids":",1032,1042,1064,1065,"},{"id":127,"display_text":"[BHX] - Bond","notes":null,"is_internal":false,"is_deleted":false,"modified_date":"27/06/2017 09:00:00","customer_ids":",1032,"},{"id":128,"display_text":"[GLA] - Bond","notes":null,"is_internal":false,"is_deleted":false,"modified_date":"22/08/2017 13:48:00","customer_ids":",1042,"},{"id":129,"display_text":"[MAN] - Bond","notes":null,"is_internal":false,"is_deleted":false,"modified_date":"27/06/2017 09:02:00","customer_ids":""},{"id":130,"display_text":"[NCL] - Bond","notes":null,"is_internal":false,"is_deleted":false,"modified_date":"27/06/2017 09:02:00","customer_ids":""},{"id":131,"display_text":"[LHR] - Dnata c/o Emirates","notes":null,"is_internal":false,"is_deleted":false,"modified_date":"27/06/2017 09:24:00","customer_ids":",1035,"},{"id":132,"display_text":"[LHR] - Singapore {SQ}","notes":"Éder Alexandre Parolini \nOperations Officer \nEder_Parolini@singaporeair.com.sg\n+44 (0) 208 283 2660 \n\n","is_internal":false,"is_deleted":false,"modified_date":"27/06/2017 13:20:00","customer_ids":",1047,"},{"id":133,"display_text":"[LHR] - WFS","notes":"\n\nGlenn Collier | ULD Controller\nT: +44 208 745 1426\ngcollier@wfs.aero\n","is_internal":false,"is_deleted":false,"modified_date":"27/06/2017 13:23:00","customer_ids":",1044,"},{"id":134,"display_text":"[LHR] - Swissport","notes":"Richard Miller.\nCargo Operations Manager UK,Ireland, Israel, Middle East and India\nTel: +44 (0)208 276 6769 Cell: +44 (0) 7711443995\n","is_internal":false,"is_deleted":false,"modified_date":"28/07/2017 10:48:00","customer_ids":",1035,1040,1041,1043,"},{"id":135,"display_text":"[LHR] - Heathrow Cargo Handling {HCH}","notes":"Ian Laws\nIan.Law@swissport.com\nTel: 02087504142\nTel: 02087504190\n","is_internal":false,"is_deleted":false,"modified_date":"27/06/2017 13:25:00","customer_ids":""},{"id":144,"display_text":"Default Customer Pickup/Drop","notes":null,"is_internal":false,"is_deleted":false,"modified_date":"16/11/2017 09:47:00","customer_ids":""}]
     */

    var code: String? = null
    var status: String? = null
    var data: List<DataBean>? = null

    class DataBean {
        /**
         * id : 1
         * display_text : [BRU] - Bridge Aviation
         * notes : null
         * is_internal : true
         * is_deleted : false
         * modified_date : 27/06/2017 09:18:00
         * customer_ids :
         */

        var id: Int = 0
        var display_text: String? = null
        var notes: Any? = null
        var isIs_internal: Boolean = false
        var isIs_deleted: Boolean = false
        var modified_date: String? = null
        var customer_ids: String? = null
    }
}
