package layout

class Item(temperature: Int, d: String, imgUrl: String) {
    //constructor
    private var temperature: String = temperature.toString() //convert to string
    private var desc: String = d //refactoring to description
    private var img_url: String = imgUrl

    public fun getImgUrl(): String {
        return img_url
    }

    public fun getTemp(): String {
        return temperature
    }

    public fun getDay(): String {
        return desc
    }

}