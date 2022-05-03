package com.cosmocolor.coffe.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cosmocolor.coffe.R
import com.cosmocolor.coffe.ui.theme.CoffeTheme
import com.cosmocolor.coffe.ui.theme.PlatziBlue
import com.cosmocolor.coffe.ui.theme.PlatziGreen

enum class CountryISO(val iso: String) {
    COL("Colombia"),
    BRA("BRA"),
    CRI("CRI"),
    NIC("NIC");

    fun getBackgrounImage(): Int {
        return when (this) {
            COL -> R.drawable.co
            BRA -> R.drawable.br
            CRI -> R.drawable.ri
            NIC -> R.drawable.ni
        }
    }

    fun getCountryFlag(): Int {
        return when (this) {
            COL -> R.drawable.flagco
            BRA -> R.drawable.flagbr
            CRI -> R.drawable.flagri
            NIC -> R.drawable.flagni
        }
    }

    fun getSurfaceColor(): Color {
        return when (this) {
            COL, NIC -> PlatziBlue
            BRA, CRI -> PlatziGreen
        }
    }
}

@Composable
fun ProductCard(
    name: String,
    summary: String,
    price: Double,
    currency: String,
    country: CountryISO
) {
    Card(
        modifier = Modifier
            .fillMaxWidth() //Ocupar el ancho de la pantalla
            .padding(16.dp)
            .clickable { }
            .size(480.dp),
        elevation = 10.dp,
        shape = MaterialTheme.shapes.small
    ) {
        Image(painterResource(country.getBackgrounImage()), contentDescription = null)
        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            color = country.getSurfaceColor().copy(alpha = 0.2f) //alpha para darle un filtro
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(name, style = MaterialTheme.typography.h4)
                Text(summary, style = MaterialTheme.typography.body1)
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Row {
                        Image(
                            painter = painterResource(id = country.getCountryFlag()),
                            contentDescription = null,
                            modifier = Modifier.size(32.dp, 32.dp)
                        )
                        Text(
                            "$ $price $currency", modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.h4
                        )
                    }

                }
            }
        }
    }
}

@Preview(
    showBackground = true
)

@Composable
fun ProductCardPreview() {
    CoffeTheme {
        ProductCard(
            "Café de Colombia",
            "Café de origen de las montañas",
            35.0,
            "USD",
             CountryISO.NIC
        )
    }
}