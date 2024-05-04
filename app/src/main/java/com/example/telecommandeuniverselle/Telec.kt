package com.example.telecommandeuniverselle

import android.content.Context
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
                            onClick = {/*TODO*/},
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

                        Text(text = "CHAINE",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(8.dp,18.dp)
                        )

                        val info= getInfoFrequence(buttonType = "CH-", nomDeLaTv = nomDeLaTv )
                        InformationTV.afficherListe(info)
                        IconButton(
                            onClick = {  },
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


@Composable
fun getInfoFrequence(buttonType: String, nomDeLaTv: String): List<InformationTV> {
    val context = LocalContext.current
    val mesInfos = mutableListOf<InformationTV>()
    val infoTV = remember {
        getTVInfo(nomDeLaTv, context)
    }

    var infoBouton = infoTV?.getJSONObject(buttonType)
        ?.optJSONObject("AvecProtocol")

    // Si "AvecProtocol" est présent, récupérer également "SansProtocol"
    if(infoBouton!=null){
        val tmpProtocol = infoBouton?.getString("protocol")
        val tmpAddress = infoBouton?.getString("address")
        val tmpCommand = infoBouton?.getString("command")
        var tmpCommand2:String?=null
        var tmpCommand1: String?=null

        if (infoBouton?.optString("protocol1")==null && infoBouton?.optString("command1")==null ){
             tmpCommand1 = infoBouton?.optString("command1")
             tmpCommand2 = infoBouton?.optString("command2")
        }else if(infoBouton?.optString("protocol1")!=null){
            val tmpProtocol1 = infoBouton?.getString("protocol1")
            val tmpAddress1 = infoBouton?.getString("address1")
            val tmpCommand4 = infoBouton?.getString("command1")
            val infoNormaleFrequence = InformationTV(
                type = "AvecProtocol",
                protocol = tmpProtocol1,
                address = tmpAddress1,
                command = listOf(tmpCommand4)
            )
            mesInfos.add(infoNormaleFrequence)
        }
        var infoNormaleFrequence = InformationTV(
            type = "AvecProtocol",
            protocol = tmpProtocol,
            address = tmpAddress,
            command = listOf(tmpCommand,tmpCommand1,tmpCommand2)
        )

        mesInfos.add(infoNormaleFrequence)

        //Recuperation des info Sans Protocol
        val infoBoutonSansProtocol= infoTV?.getJSONObject(buttonType)
            ?.optJSONObject("SansProtocol")
        val  frequency = infoBoutonSansProtocol?.getString("frequency")
        val duty_cycle = infoBoutonSansProtocol?.getString("duty_cycle")
        val data = infoBoutonSansProtocol?.getString("data")
        val data1 = infoBoutonSansProtocol?.getString("data1")

        infoNormaleFrequence = InformationTV(
            type = "SansProtocol",
            frequency = frequency,
            duty_cycle = duty_cycle,
            data = listOf(data, data1)
        )

        mesInfos.add(infoNormaleFrequence)

    }else{
        infoBouton = infoTV?.getJSONObject(buttonType)
        val tmpProtocol = infoBouton?.getString("protocol")
        val tmpAddress = infoBouton?.getString("address")
        val tmpCommand = infoBouton?.getString("command")
        var tmpCommand2:String?=null
        var tmpCommand1: String?=null

        if (infoBouton?.optString("protocol1")==null && infoBouton?.optString("command1")==null ){
            tmpCommand1 = infoBouton?.optString("command1")
            tmpCommand2 = infoBouton?.optString("command2")
        }else if(infoBouton?.optString("protocol1")!=null){
            val tmpProtocol1 = infoBouton?.getString("protocol1")
            val tmpAddress1 = infoBouton?.getString("address1")
            val tmpCommand4 = infoBouton?.getString("command1")
            val infoNormaleFrequence = InformationTV(
                type = "AvecProtocol",
                protocol = tmpProtocol1,
                address = tmpAddress1,
                command = listOf(tmpCommand4)
            )
            mesInfos.add(infoNormaleFrequence)
            if(infoBouton?.optString("protocol2")!=null) {
                val tmpProtocol2 = infoBouton?.getString("protocol2")
                val tmpAddress2 = infoBouton?.getString("address2")
                val tmpCommand5 = infoBouton?.getString("command2")
                val infoNormaleFrequence1 = InformationTV(
                    type = "AvecProtocol",
                    protocol = tmpProtocol2,
                    address = tmpAddress2,
                    command = listOf(tmpCommand5)
                )
                mesInfos.add(infoNormaleFrequence1)
            }

        }
        val infoNormaleFrequence = InformationTV(
            type = "AvecProtocol",
            protocol = tmpProtocol,
            address = tmpAddress,
            command = listOf(tmpCommand,tmpCommand1,tmpCommand2)
        )

        mesInfos.add(infoNormaleFrequence)

    }

    return mesInfos
}