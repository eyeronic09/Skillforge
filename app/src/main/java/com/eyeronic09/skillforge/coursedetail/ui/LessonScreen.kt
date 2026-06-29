package com.eyeronic09.skillforge.coursedetail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.eyeronic09.skillforge.HomeScreen.domain.Model.Course
import com.eyeronic09.skillforge.HomeScreen.domain.Model.Lesson

data class LessonScreen(val course: Course, val lesson: Lesson) : Screen {
    @Composable
    override fun Content() {
        LessonContent(course = course, initialLesson = lesson)
    }
}

@Composable
fun LessonContent(course: Course, initialLesson: Lesson) {
    val navigator = LocalNavigator.currentOrThrow
    var currentLesson by remember { mutableStateOf(initialLesson) }
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Lessons", "Notes", "Resources")

    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        // Video Player Area
        VideoPlayerPlaceholder(
            onBackClick = { navigator.pop() }
        )

        // Lesson Details
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = "LESSON ${course.lessons.indexOf(currentLesson) + 1} · ${course.title.uppercase()}",
                color = Color(0xFF00C4B4),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = currentLesson.title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = currentLesson.content,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }

        // Tabs
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = Color.White,
            contentColor = Color(0xFF00C4B4),
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                    color = Color(0xFF00C4B4)
                )
            },
            divider = {}
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = {
                        Text(
                            text = title,
                            fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Medium,
                            fontSize = 14.sp
                        )
                    }
                )
            }
        }

        // List
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(course.lessons) { lesson ->
                LessonItemInPlayer(
                    lesson = lesson,
                    isPlaying = lesson == currentLesson,
                    onClick = { 
                        if (lesson.isFree || course.lessons.indexOf(lesson) == 0) {
                            currentLesson = lesson 
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun VideoPlayerPlaceholder(onBackClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16 / 9f)
            .background(Color(0xFF0F1720)) // Dark background for video area
    ) {
        // Overlay Controls
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.2f))
        ) {
            Icon(Icons.Default.ArrowBackIosNew, contentDescription = "Back", tint = Color.White, modifier = Modifier.size(20.dp))
        }

        IconButton(
            onClick = { },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.2f))
        ) {
            Icon(Icons.Default.Fullscreen, contentDescription = "Fullscreen", tint = Color.White)
        }

        // Play Button
        Surface(
            modifier = Modifier.align(Alignment.Center),
            shape = CircleShape,
            color = Color.White,
            shadowElevation = 4.dp
        ) {
            Icon(
                Icons.Default.PlayArrow,
                contentDescription = "Play",
                tint = Color(0xFF0F1720),
                modifier = Modifier.padding(12.dp).size(32.dp)
            )
        }

        // Progress Bar (Simple)
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("02:14", color = Color.White, fontSize = 10.sp)
                Text("06:00", color = Color.White, fontSize = 10.sp)
            }
            Spacer(modifier = Modifier.height(4.dp))
            LinearProgressIndicator(
                progress = { 0.37f },
                modifier = Modifier.fillMaxWidth().height(4.dp).clip(CircleShape),
                color = Color(0xFF00C4B4),
                trackColor = Color.White.copy(alpha = 0.3f)
            )
        }
    }
}

@Composable
fun LessonItemInPlayer(lesson: Lesson, isPlaying: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isPlaying) Color(0xFF00C4B4).copy(alpha = 0.05f) else Color.White
        ),
        border = if (isPlaying) androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF00C4B4).copy(alpha = 0.2f)) else null,
        elevation = CardDefaults.cardElevation(defaultElevation = if (isPlaying) 0.dp else 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(
                        when {
                            isPlaying -> Color(0xFF00C4B4)
                            lesson.isFree -> Color(0xFF00C4B4).copy(alpha = 0.1f)
                            else -> Color(0xFFF3F4F6)
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = when {
                        isPlaying -> Icons.Default.Pause
                        lesson.isFree -> Icons.Default.PlayArrow
                        else -> Icons.Default.Lock
                    },
                    contentDescription = null,
                    tint = if (isPlaying) Color.White else if (lesson.isFree) Color(0xFF00C4B4) else Color.Gray,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = lesson.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = if (isPlaying) Color(0xFF00C4B4) else Color.Black
                )
                Text(
                    text = if (isPlaying) "Now playing · ${lesson.durationMinutes} min" else "${lesson.durationMinutes} min",
                    color = if (isPlaying) Color(0xFF00C4B4).copy(alpha = 0.7f) else Color.Gray,
                    fontSize = 12.sp
                )
            }
            if (!isPlaying && lesson.isFree) {
                Text(
                    text = "FREE",
                    color = Color(0xFF00C4B4),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(0xFF00C4B4).copy(alpha = 0.1f))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
        }
    }
}
