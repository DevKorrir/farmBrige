package dev.korryr.farmbrige.ui.features.home.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import java.util.Calendar
import dev.korryr.farmbrige.R
import dev.korryr.farmbrige.ui.features.home.model.TaskItem
import dev.korryr.farmbrige.ui.features.home.presentation.TaskItemCard

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    //dashboardItems: List<DashboardItem> = emptyList(),
    //taskItems: List<TaskItem> = emptyList(),

    ) {

    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser
    val userName = user?.displayName ?: "Guest"

    val dashboardItems: List<DashboardItem> = listOf(
        DashboardItem("Crop Status", "75%", Color(0xFF9CCC65), "All crops growing well"),
        DashboardItem("Weather", "Sunny", Color(0xFFFFB74D), "30°C, Light breeze"),
        DashboardItem("Water Usage", "65%", Color(0xFF4FC3F7), "Efficiency improved by 10%"),
        DashboardItem("Harvest", "2 Days", Color(0xFFBA68C8), "Tomatoes ready soon")
    )

    // Sample task items
    val taskItems = listOf(
        TaskItem("Water tomato plants", "Today", true),
        TaskItem("Apply fertilizer", "Tomorrow", false),
        TaskItem("Check irrigation system", "Today", false),
        TaskItem("Harvest corn", "3 days", false)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.background
            )
            .verticalScroll(rememberScrollState())
            .padding(16.dp)

    ) {
//        Text(
//            text = "Home Screen",
//            modifier = modifier
//        )
        var query by remember { mutableStateOf("") }

        Surface(
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(1.dp)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Search",
                )

                Spacer(Modifier.width(8.dp))

                BasicTextField(
                    value = query,
                    onValueChange = { query = it },
                    singleLine = true,
                    //modifier = Modifier.weight(1f),
                    textStyle = MaterialTheme.typography.bodyMedium,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Search,
                        keyboardType = KeyboardType.Text
                    ),
                    decorationBox = { innerTextField ->
                        if (query.isEmpty()) {
                            Text(
                                text = "Search products, tasks…",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                            )
                            innerTextField()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

            }
        }

        Spacer(Modifier.height(16.dp))

        // category  chip
        val categories = listOf(
            "seeds",
            "Fertilizers",
            "liveStock",
            "liveStock2",
            "liveStock3",
            "liveStock4",

        )

        LazyRow (
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            items(categories) { category ->
                CategoryChip(
                    label = category
                ) { }
            }

        }

        Spacer(Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer.copy(0.7f)
            )
        ){
            val currentTime = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) //same as modulus 24
            val greeting = when (currentTime) {
                in 3 .. 11 -> "Good Morning🌞"
                in 12 .. 16 -> "Good Afternoon☀"
                in 17 .. 20 -> "Good Evening"
                else -> "Good Night"
            }

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ){
                Column (
                    modifier = Modifier.weight(1f)
                ){
                    Text(
                        text = greeting,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    Text(
                        text = userName,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Perfect day for farming! Your crops are doing well.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                    )
                }

                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    //wether icon
                    Icon(
                        painter = painterResource(id = R.drawable.sun),
                        contentDescription = "Sun",
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.size(40.dp)

                    )

                }
            }
        }

        //farm dash board
        Text(
            text = "Farm Dashboard",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(vertical = 12.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp)
        ) {
            items(dashboardItems) { item ->
                DashboardItemCard(item)
            }
        }

        // Task section
        Text(
            text = "Today's Tasks",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 20.dp, bottom = 12.dp)
        )

        taskItems.forEach { task ->
            TaskItemCard(task)
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Tips section
        Text(
            text = "Farming Tips",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 20.dp, bottom = 12.dp)
        )



    }
}

@Composable
fun CategoryChip(
    label: String,
    onClick: () -> Unit
) {
    Surface(
        tonalElevation = 2.dp,
        color = MaterialTheme.colorScheme.primaryContainer,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .clickable(onClick = onClick)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        )
    }
}

data class DashboardItem(
    val title: String,
    val value: String,
    val color: Color,
    val description: String
)

@Composable
fun DashboardItemCard(item: DashboardItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Title row with colored indicator
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(item.color, CircleShape)
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = item.title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            // Value with large text
            Text(
                text = item.value,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = item.color
            )

            // Description
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}