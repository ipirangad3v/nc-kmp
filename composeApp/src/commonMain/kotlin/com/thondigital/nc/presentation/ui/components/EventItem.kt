package com.thondigital.nc.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.thondigital.nc.data.remote.responses.EventDetailsResponse
import com.thondigital.nc.presentation.ui.theme.primaryLightBlue

@Composable
fun EventsList(
    warnings: List<EventDetailsResponse>,
    onEventClick: (EventDetailsResponse) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Próximos eventos")
        Spacer(modifier = Modifier.height(8.dp))
        warnings.forEach { event ->
            Box(
                modifier =
                    Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .height(77.dp)
                        .background(color = Color(0xFF486BDC), shape = RoundedCornerShape(size = 10.dp))
                        .background(
                            color = primaryLightBlue,
                            shape = RoundedCornerShape(size = 10.dp),
                        ).clickable { onEventClick(event) },
                contentAlignment = Alignment.Center,
            ) {
                Row {
                    Text(
                        text = event.date,
                        modifier = Modifier.padding(8.dp).fillMaxHeight(),
                        color = Color.White,
                    )
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                                .height(68.dp)
                                .background(
                                    color = Color(0xFF486BDC),
                                    shape = RoundedCornerShape(size = 10.dp),
                                )
                                .background(
                                    color = Color(0xFFA5BAFF),
                                    shape = RoundedCornerShape(size = 10.dp),
                                ),
                    ) {
                        Text(
                            text = event.title,
                            modifier = Modifier.padding(8.dp),
                            color = Color.White,
                        )
                    }
                }
            }
        }
    }
}
