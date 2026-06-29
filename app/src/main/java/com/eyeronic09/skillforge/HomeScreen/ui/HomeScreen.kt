package com.eyeronic09.skillforge.HomeScreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.eyeronic09.skillforge.coursedetail.ui.CourseDetailScreen
import com.eyeronic09.skillforge.HomeScreen.ui.component.CategoryCard
import com.eyeronic09.skillforge.HomeScreen.ui.component.CourseCard

import org.koin.androidx.compose.koinViewModel

    object HomeScreen : Screen {
        @Composable
        override fun Content() {
            HomeScreenRoot()
        }
    }

    @Composable
    fun HomeScreenRoot(homeScreenVM : HomeScreenVM = koinViewModel()){
        val uiState by homeScreenVM.uiState.collectAsStateWithLifecycle()
        val navigator = LocalNavigator.currentOrThrow

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color(0xFFF9FAFB)
        ) { innerPadding ->
            if (uiState.loading) {
                Box(modifier = Modifier.fillMaxSize().padding(innerPadding), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Color(0xFF00C4B4))
                }
            } else if (uiState.error != null) {
                Box(modifier = Modifier.fillMaxSize().padding(innerPadding), contentAlignment = Alignment.Center) {
                    Text(text = uiState.error ?: "Unknown error", color = MaterialTheme.colorScheme.error)
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentPadding = PaddingValues(bottom = 24.dp)
                ) {

                    item {
                        HomeHeader()
                    }

                    item {
                        HomeSearchBar()
                    }

                    item {
                        SectionHeader(title = "Categories", onSeeAllClick = {})
                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(horizontal = 20.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(uiState.category) { category ->
                                CategoryCard(category = category)
                            }
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                    item {
                        SectionHeader(title = "Popular courses", onSeeAllClick = {})
                    }

                    items(uiState.popularCourses) { course ->
                        Box(modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)) {
                            CourseCard(
                                title = course.title,
                                instructor = course.instructor.name,
                                level = course.level,
                                rating = course.rating,
                                duration = "${course.durationHours}h",
                                thumbnailUrl = course.thumbnailUrl,
                                onClick = {
                                    navigator.push(CourseDetailScreen(course))
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun HomeHeader() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Welcome back",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                Text(
                    text = "Find your next skill",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.White)
                        .size(44.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.NotificationsNone,
                        contentDescription = "Notifications",
                        tint = Color.Black
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF00C4B4)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "AS",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }

    @Composable
    fun HomeSearchBar() {
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 24.dp),
            placeholder = {
                Text(
                    text = "Search courses, topics...",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color.Gray
                )
            },
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                unfocusedBorderColor = Color(0xFFEEEEEE),
                focusedBorderColor = Color(0xFF00C4B4)
            ),
            singleLine = true
        )
    }

    @Composable
    fun SectionHeader(
        title: String,
        onSeeAllClick: () -> Unit
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            TextButton(onClick = onSeeAllClick) {
                Text(
                    text = "See all",
                    color = Color(0xFF00C4B4),
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
