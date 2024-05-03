package com.example.telecommandeuniverselle

import Telec
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusTargetModifierNode
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp

@Composable
fun Menu() {
    val menuItems = listOf("LG", "Samsung")

    var selectedItem by remember { mutableStateOf<String?>(null) }

    Column (
        modifier = Modifier
            .padding(40.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Liste des televiseurs ",
            fontSize = 26.sp,
            fontWeight = Bold
        )

        // Utilisation de LazyColumn pour ajouter le défilement
        LazyColumn(
            modifier = Modifier.padding(8.dp)
        ) {
            items(menuItems) { item ->
                Button(
                    onClick = { selectedItem = item },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(), // Utilisation de fillMaxWidth pour occuper toute la largeur disponible
                ) {
                    Text(
                        text = item,
                        fontSize = 20.sp
                    )
                }
            }
        }

        selectedItem?.let { selectedItem ->
            Telec(selectedItem)
        }
    }
}