package model

/**
 * Created by
 * Prakash Software Solution on
 * 14-05-2018.
 */
class ProfileEntity {
    /**
     * data : {"id":2,"first_name":"Janet","last_name":"Weaver","avatar":"https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg"}
     */

    private var data: DataBean? = null

    fun getData(): DataBean? {
        return data
    }

    fun setData(data: DataBean) {
        this.data = data
    }

    class DataBean {
        /**
         * id : 2
         * first_name : Janet
         * last_name : Weaver
         * avatar : https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg
         */

        var id: Int = 0
        var first_name: String? = null
        var last_name: String? = null
        var avatar: String? = null
    }
}