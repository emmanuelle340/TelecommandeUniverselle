package com.example.telecommandeuniverselle

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.ConsumerIrManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.json.JSONObject
import java.io.InputStream


@Composable
fun Telec(nomDeLaTv: String) {
    val context = LocalContext.current

    var info: InformationTV?
    val telecommande= Telecommande(context)


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Nom de la tele $nomDeLaTv " )
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
                info = getInfoFrequence("POWER",nomDeLaTv,context)
                info?.let { MyPowerButton(it,telecommande) }
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
                            onClick = {
                                info = getInfoFrequence("VOL+",nomDeLaTv,context)
                                info?.let { telecommande.sendIrSignal(it.frequency, info!!.data.toIntArray()) }
                            },
                            modifier = Modifier
                                .size(50.dp)
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Icon(
                                Icons.Default.KeyboardArrowUp,
                                contentDescription = "volume plus",
                                modifier = Modifier
                                    .size(50.dp),
                            )
                        }

                        Text(text = "VOLUME",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(8.dp,18.dp)
                        )

                        IconButton(
                            onClick = {
                                info = getInfoFrequence("VOL-",nomDeLaTv,context)
                                info?.let { telecommande.sendIrSignal(it.frequency, info!!.data.toIntArray()) }
                            },
                            modifier = Modifier
                                .size(50.dp)
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Icon(
                                Icons.Default.KeyboardArrowDown,
                                contentDescription = "volume moins",
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
                            onClick = {
                                info = getInfoFrequence("CH+",nomDeLaTv,context)
                                info?.let { telecommande.sendIrSignal(it.frequency, info!!.data.toIntArray()) }
                            },
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

                        Text(text = "CHAINE",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(8.dp,18.dp)
                        )

                        IconButton(
                            onClick = {
                                info = getInfoFrequence("CH-",nomDeLaTv,context)
                                info?.let { telecommande.sendIrSignal(it.frequency, info!!.data.toIntArray()) }
                            },
                            modifier = Modifier
                                .size(50.dp)
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Text(
                                text = "-",
                                fontWeight = FontWeight.Bold,
                                fontSize = 34.sp
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun MyPowerButton(Tele:InformationTV ,telecommande: Telecommande ) {

    val play = painterResource(id = R.drawable.fermer)

    IconButton(
        onClick = { telecommande.sendIrSignal(Tele.frequency,Tele.data.toIntArray()) },
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
fun getTVInfo(tvName: String, context: Context): JSONObject? {
    // Charger le fichier JSON à partir des ressources de l'application
    val inputStream: InputStream = context.resources.openRawResource(R.raw.config)
    val jsonString = inputStream.bufferedReader().use { it.readText() }

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

fun getInfoFrequence(buttonType: String, nomDeLaTv: String, context: Context): InformationTV? {
    val tvInfo = getTVInfo(nomDeLaTv, context)

    val buttonObject = tvInfo?.optJSONObject(buttonType) ?: return null

    val frequency = tvInfo.getString("frequency").toIntOrNull()

    val patternArray = buttonObject.getJSONArray("pattern")
    val patternList = mutableListOf<Int>()
    for (i in 0 until patternArray.length()) {
        patternList.add(patternArray.getInt(i))
    }

    return frequency?.let { InformationTV(it, patternList) }
}


