import android.widget.GridView
import android.widget.LinearLayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.telecommandeuniverselle.R
import org.json.JSONObject

@Composable
fun Telec(nomDeLaTv: String) {
    var isOn: Boolean = false
    var volume: Int = 0
    var currentChannel: Int = 1

    Text(text = "Nom de la tele $nomDeLaTv")
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                //.background(Color.Blue)
                .align(Alignment.Center)
                .size(500.dp, 250.dp)
                //.padding(20.dp),
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MyPowerButton()
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    modifier = Modifier
                        .size(250.dp, 230.dp)
                        //.background(Color.Black)
                        .padding(20.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .size(250.dp, 230.dp)
                            //.background(Color.Red, shape = RoundedCornerShape(16.dp))
                            .align(Alignment.CenterHorizontally)
                            .clip(RoundedCornerShape(20.dp))
                            .border(2.dp, Color.Black, RoundedCornerShape(16.dp))
                    ){


                        IconButton(
                            onClick = {/*TODO*/},
                            modifier = Modifier
                                .size(50.dp)
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Icon(
                                Icons.Default.KeyboardArrowUp,
                                contentDescription = "Flèche montante",
                                modifier = Modifier
                                    .size(50.dp),
                            )
                        }

                        Text(text = "VOL",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(8.dp,18.dp)
                        )

                        IconButton(
                            onClick = {/*TODO*/},
                            modifier = Modifier
                                .size(50.dp)
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Icon(
                                Icons.Default.KeyboardArrowDown,
                                contentDescription = "Flèche descendante",
                                modifier = Modifier
                                    .size(50.dp),
                            )
                        }
                    }
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    modifier = Modifier
                        .size(250.dp, 230.dp)
                        //.background(Color.Black)
                        .padding(20.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .size(250.dp, 230.dp)
                            //.background(Color.Red, shape = RoundedCornerShape(16.dp))
                            .align(Alignment.CenterHorizontally)
                            .clip(RoundedCornerShape(20.dp))
                            .border(2.dp, Color.Black, RoundedCornerShape(16.dp))
                    ){


                        IconButton(
                            onClick = {/*TODO*/},
                            modifier = Modifier
                                .size(50.dp)
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Icon(
                                Icons.Default.Add,
                                contentDescription = "chaine plus",
                                modifier = Modifier
                                    .size(50.dp),
                            )
                        }

                        Text(text = "Chaine",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(8.dp,18.dp)
                        )

                        IconButton(
                            onClick = {/*TODO*/},
                            modifier = Modifier
                                .size(50.dp)
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Icon(
                                Icons.Default.ArrowBack,
                                contentDescription = "CH",
                                modifier = Modifier
                                    .size(50.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun MyPowerButton() {

    val play = painterResource(id = R.drawable.fermer)


    IconButton(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .padding(8.dp)
            .size(150.dp)
            .clip(CircleShape)
            .width(300.dp)
            .height(300.dp)
    ) {

            Image(
                painter = play,
                contentDescription = "Play",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color(0x5485FA64)),
            )


    }
}


// Fonction pour récupérer les informations d'un téléviseur à partir de son nom
fun getTVInfo(tvName: String, jsonString: String): JSONObject? {
    // Convertir la chaîne JSON en objet JSON
    val jsonObject = JSONObject(jsonString)

    // Vérifier si le nom du téléviseur est présent dans les données
    if (jsonObject.has("MesTeleviseurs")) {
        val televiseursArray = jsonObject.getJSONArray("MesTeleviseurs")

        // Parcourir chaque téléviseur dans le tableau
        for (i in 0 until televiseursArray.length()) {
            val tvObject = televiseursArray.getJSONObject(i)

            // Vérifier si le nom du téléviseur correspond à celui recherché
            if (tvObject.has(tvName)) {
                return tvObject.getJSONObject(tvName)
            }
        }
    }

    // Retourner null si le téléviseur n'est pas trouvé
    return null
}

