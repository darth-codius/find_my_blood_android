package live.adabe.findmyblood.utils

data class Resource<out T>(val  status: Status, val data: T?, val message: String?){
    companion object{
        fun <T> success(data: T): Resource<T>{
            return Resource(status = Status.SUCCESS, data = data, message = null)
        }
        fun <T> loading(data: T?, message: String? = null): Resource<T>{
            return Resource(status = Status.LOADING, data = data, message = message)
        }
        fun <T> error(data: T?, message: String?=null): Resource<T>{
            return Resource(status = Status.FAILED, data = data, message = message)
        }
    }
}
