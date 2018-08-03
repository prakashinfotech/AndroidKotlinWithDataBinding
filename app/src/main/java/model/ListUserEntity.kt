package model

/**
 * Created by Prakash Software Solution
 * on 14-05-2018.
 */
class ListUserEntity {
    /**
     * page : 1
     * per_page : 3
     * total : 12
     * total_pages : 4
     * data : [{"id":1,"name":"cerulean","year":2000,"color":"#98B2D1","pantone_value":"15-4020"},{"id":2,"name":"fuchsia rose","year":2001,"color":"#C74375","pantone_value":"17-2031"},{"id":3,"name":"true red","year":2002,"color":"#BF1932","pantone_value":"19-1664"}]
     */

    private var page: Int = 0
    private var per_page: Int = 0
    private var total: Int = 0
    private var total_pages: Int = 0
    private var data: List<DataBean>? = null

    fun getPage(): Int {
        return page
    }

    fun setPage(page: Int) {
        this.page = page
    }

    fun getPer_page(): Int {
        return per_page
    }

    fun setPer_page(per_page: Int) {
        this.per_page = per_page
    }

    fun getTotal(): Int {
        return total
    }

    fun setTotal(total: Int) {
        this.total = total
    }

    fun getTotal_pages(): Int {
        return total_pages
    }

    fun setTotal_pages(total_pages: Int) {
        this.total_pages = total_pages
    }

    fun getData(): List<DataBean>? {
        return data
    }

    fun setData(data: List<DataBean>) {
        this.data = data
    }

    class DataBean {
        /**
         * id : 1
         * name : cerulean
         * year : 2000
         * color : #98B2D1
         * pantone_value : 15-4020
         */

        var id: Int = 0
        var name: String? = null
        var year: Int = 0
        var color: String? = null
        var pantone_value: String? = null
    }
}