import com.example.chemp_podject.api.BlockModel
import com.example.chemp_podject.models.PolzovatModel

object Person {
    private val MY_SETTINGS = "my_settings"
    var person: PolzovatModel? = null
    var listOrder: List<BlockModel> = ArrayList()
    var email: String? = null
    lateinit var password: String
    //var summa: Int? = 0
}