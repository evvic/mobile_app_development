package layout

class Item {
    private lateinit var img_url: String
    private lateinit var temperature: String
    private lateinit var day: String

    fun Item (img: String, temp: String, d: String) {
        img_url = img
        temperature = temp
        day = d
    }

    public fun getImgUrl(): String {
        return img_url
    }

    public fun getTemp(): String {
        return temperature
    }

    public fun getDay(): String {
        return day
    }

}