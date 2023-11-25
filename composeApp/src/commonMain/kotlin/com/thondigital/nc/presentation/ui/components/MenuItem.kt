package com.thondigital.nc.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thondigital.nc.domain.model.ClickableMenuItem
import com.thondigital.nc.presentation.ui.theme.primaryLightBlue
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun MenuItem(
    item: ClickableMenuItem,
    onItemClick: () -> Unit,
) {
    Column(
        modifier = Modifier.clickable { onItemClick() },
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Box(
            contentAlignment = androidx.compose.ui.Alignment.Center,
            modifier =
                Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .background(color = primaryLightBlue, shape = RoundedCornerShape(size = 10.dp)),
        ) {
            Image(
                modifier =
                    Modifier
                        .width(80.dp)
                        .height(80.dp),
                contentDescription = item.name,
                painter =
                    painterResource(
                        "images/${item.resourceId}",
                    ),
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            text = item.name,
            style =
                TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight(300),
                    color = Color(0xFF494A59),
                    textAlign = TextAlign.Center,
                ),
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}
