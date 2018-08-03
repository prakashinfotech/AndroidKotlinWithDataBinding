package model

/**
 * Created by
 * Prakash Software Solution
 * on 11-05-2018.
 */
class PostEntity {
    /**
     * userId : 1
     * id : 1
     * title : sunt aut facere repellat provident occaecati excepturi optio reprehenderit
     * body : quia et suscipit
     * suscipit recusandae consequuntur expedita et cum
     * reprehenderit molestiae ut ut quas totam
     * nostrum rerum est autem sunt rem eveniet architecto
     */

    private var userId: Int = 0
    private var id: Int = 0
    private var title: String? = null
    private var body: String? = null

    fun getUserId(): Int {
        return userId
    }

    fun setUserId(userId: Int) {
        this.userId = userId
    }

    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun getBody(): String? {
        return body
    }

    fun setBody(body: String) {
        this.body = body
    }
}