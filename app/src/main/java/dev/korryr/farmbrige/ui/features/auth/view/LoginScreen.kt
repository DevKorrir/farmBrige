package dev.korryr.farmbrige.ui.features.auth.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.korryr.farmbrige.ui.theme.FarmBrigeTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import dev.korryr.farmbrige.R
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.TextButton
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.text.font.FontWeight
import dev.korryr.farmbrige.ui.SharedUi.SharedTextField
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {

    // state variables
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    //var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    val emailIcon = painterResource(id = R.drawable.mail)
    val passwordIcon = painterResource(id = R.drawable.padlock)
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.surface,
            MaterialTheme.colorScheme.surface.copy(alpha = 0.95f),
            MaterialTheme.colorScheme.surfaceVariant.copy(0.95f)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clip(
                    RoundedCornerShape(
                        bottomEnd = 40.dp, bottomStart = 40.dp
                    )
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.cabbage_backgroud),
                contentDescription = "Login Background",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box (
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
                                MaterialTheme.colorScheme.tertiary.copy(0.4f)
                            )
                        )
                    )
            )


            // kalogo iconic
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Box(
                    modifier = Modifier
                        .size(90.dp)
                        .clip(CircleShape)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
                                    MaterialTheme.colorScheme.tertiary.copy(alpha = 0.4f)
                                )
                            )
                        )
                        .padding(12.dp),
                    contentAlignment = Alignment.Center

                ){
                    Image(
                        painter = painterResource(id = R.drawable.farmbrigelogo),
                        contentDescription = "farm brige logo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(CircleShape)
                    )
                }

                Spacer(Modifier.height(16.dp))

                Text(
                    text = "Welcome Back",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.White
                )
                Text(
                    text = "Sign in to continue",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )
            }
        }



// main content here
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 200.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // card for login
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                shape = RoundedCornerShape(28.dp),
                shadowElevation = 6.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 28.dp),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {

                    SharedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                        },
                        label = "Email",
                        hint = "kipyegom@gmail.com",
                        leadingIcon = emailIcon,
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        maxLines = 1,
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    SharedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                        },
                        label = "Password",
                        leadingIcon = passwordIcon,
                        isPassword = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 1,
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(
                            onClick = {},
                            contentPadding = PaddingValues(4.dp)
                        ) {
                            Text(
                                text = "Forgot Password",
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        modifier = Modifier
                            .height(56.dp)
                            .fillMaxWidth(),
                        onClick = {},
                        shape = RoundedCornerShape(16.dp),
                        contentPadding = PaddingValues(vertical = 16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary

                        )

                    ) {
                        Text(
                            text = "Login",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    Spacer(Modifier.height(24.dp))

                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        HorizontalDivider(
                            modifier = Modifier.weight(1f),
                            color = MaterialTheme.colorScheme.outlineVariant
                        )

                        Text(
                            text = "OR",
                            modifier = Modifier
                                .padding(12.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.outline
                        )

                        HorizontalDivider(
                            modifier = Modifier.weight(1f),
                            color = MaterialTheme.colorScheme.outlineVariant
                        )
                    }

                    Spacer(Modifier.height(24.dp))

                    //social login

                    OutlinedButton(
                        onClick = {
                            //toast meassage
                        },
                        shape = RoundedCornerShape(16.dp),
                        contentPadding = PaddingValues(vertical = 12.dp),
                        border = ButtonDefaults.outlinedButtonBorder.copy(
                            width = 1.5.dp
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    ){
                        Row {
                            Icon(
                                painter = painterResource(id = R.drawable.google),
                                contentDescription = "google",
                                tint = Color.Unspecified,
                                modifier = Modifier.size(24.dp)
                            )

                            Spacer(Modifier.size(12.dp))

                            Text(
                                text = " Continue with google",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Medium
                                ),
                                color = MaterialTheme.colorScheme.onSurface
                            )


                        }
                    }

                    Spacer(Modifier.height(12.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "I don't have an account?",
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            style = MaterialTheme.typography.bodyMedium
                        )

                        TextButton(
                            onClick = {}

                        ) {
                            Text(
                                text = "Sign up",
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }


                }
            }

            Image(
                painter = painterResource(id = R.drawable.guava_footer),
                contentDescription = "Login Background",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth(),
                alpha = 0.8f
            )

            Spacer(Modifier.height(60.dp))
        }
    }
}