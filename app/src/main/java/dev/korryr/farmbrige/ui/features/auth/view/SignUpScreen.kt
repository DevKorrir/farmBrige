package dev.korryr.farmbrige.ui.features.auth.view


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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.korryr.farmbrige.R
import dev.korryr.farmbrige.ui.SharedUi.SharedTextField
import dev.korryr.farmbrige.ui.features.auth.viewModel.AuthUiState
import dev.korryr.farmbrige.ui.features.auth.viewModel.AuthViewModel
import org.jetbrains.annotations.Contract

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    onNavigateToLogin: () -> Unit = {},
    onSignedUp: (uid: String) -> Unit = {},
    authViewModel: AuthViewModel = hiltViewModel()// did here
) {

    // state variables
    var userName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    //var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    var nameError by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var confirmPasswordError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var showErrows by remember { mutableStateOf(false) }

    // password strength (0-4)
    var passwordStrength by remember { mutableStateOf(0) }

    var isFormValid by remember { mutableStateOf(false) }

    val emailIcon = painterResource(id = R.drawable.mail)
    val passwordIcon = painterResource(id = R.drawable.padlock)
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.surface,
            MaterialTheme.colorScheme.surface.copy(alpha = 0.95f),
            MaterialTheme.colorScheme.surfaceVariant.copy(0.9f)
        )
    )

    val authState by authViewModel.authState.collectAsState()

    LaunchedEffect(authState) {
        if (authState is AuthUiState.Success) {
            onSignedUp((authState as AuthUiState.Success).user.uid)
        }
    }// did here

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(
                    RoundedCornerShape(
                        bottomEnd = 40.dp, bottomStart = 40.dp
                    )
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.cow_background),
                contentDescription = "signUp Background",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            //semi transparent overaly
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                            )
                        )
                    )
            )


            // kalogo iconic
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    MaterialTheme.colorScheme.primary,
                                    MaterialTheme.colorScheme.tertiary
                                )
                            )
                        )
                        .padding(12.dp),
                    contentAlignment = Alignment.Center

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.farmbrigelogo),
                        contentDescription = "farm bridge logo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(CircleShape)
                    )
                }
            }
        }


// main content here
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 190.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // card for login
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                shape = RoundedCornerShape(24.dp),
                shadowElevation = 6.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {

                    // Title
                    Text(
                        text = "Create Account",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    )

                    Text(
                        text = "Join our FarmBridge community",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
                    )

                    // Name field
                    SharedTextField(
                        value = userName,
                        onValueChange = {
                            userName = it
                            if (nameError.isNotEmpty()) nameError = ""
                        },
                        label = "Full Name",
                        hint = "John Doe",
                        leadingIcon = painterResource(id = R.drawable.user),
                        error = nameError,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Words,
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // email field
                    SharedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                            if (emailError.isNotEmpty()) emailError = ""
                        },
                        label = "Email",
                        hint = "username123@gmail.com",
                        error = emailError,
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
                            passwordStrength = calculatePasswordStrength(it)
                            if (passwordError.isNotEmpty()) passwordError = ""
                        },
                        label = "Password",
                        leadingIcon = passwordIcon,
                        isPassword = true,
                        error = passwordError,
                        showStrength = true,
                        strength = passwordStrength,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 1,
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Confirm Password field
                    SharedTextField(
                        value = confirmPassword,
                        onValueChange = {
                            confirmPassword = it
                            if (confirmPasswordError.isNotEmpty()) confirmPasswordError = ""
                        },
                        label = "Confirm Password",
                        leadingIcon = passwordIcon,
                        isPassword = true,
                        error = confirmPasswordError,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        modifier = Modifier
                            .height(56.dp)
                            .fillMaxWidth(),
                        onClick = {
                            showErrows = true

                            //validate form
                            validateForm(
                                name = userName,
                                email = email,
                                password = password,
                                confirmPassword = confirmPassword,
                                { nameErr -> nameError = nameErr },
                                { emailErr -> emailError = emailErr },
                                { passwordErr -> passwordError = passwordErr },
                                { confirmErr -> confirmPasswordError = confirmErr },
                                { valid -> isFormValid = valid }
                            )
                            if (isFormValid) authViewModel.signUp(email, password, userName)// here
                        },
                        enabled = authState !is AuthUiState.Loading, // did here
                        shape = RoundedCornerShape(16.dp),
                        contentPadding = PaddingValues(vertical = 16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary

                        )

                    ) {

                        if (authState is AuthUiState.Loading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                color = MaterialTheme.colorScheme.onPrimary,
                                strokeWidth = 2.dp
                            )
                        } else {
                            Text(
                                text = "Register",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        } // did here
                    }

                    if ( authState is AuthUiState.Error) {

                        Spacer(Modifier.height(8.dp))

                        Text(
                            text = (authState as AuthUiState.Error).message,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )

                    } // did here

                    Spacer(Modifier.height(16.dp))

                    // already have an account
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "Already have an account?",
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            style = MaterialTheme.typography.bodyMedium
                        )

                        TextButton(
                            onClick = onNavigateToLogin

                        ) {
                            Text(
                                text = "Login",
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            //terms and condition text at bottom
            Text(
                text = buildAnnotatedString {
                    append("By Signing up, you agree to our ")
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Medium
                        )
                    ) {
                        append(" Terms of Service")
                    }
                    append(" and ")
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Medium
                        )
                    ) {
                        append(" Privacy Policy")
                    }
                },
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.crops_potatos),
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

private fun calculatePasswordStrength(password: String): Int {
    if (password.isEmpty()) return 0
    var score = 0

    //lenght checking
    if (password.length >= 8) score++

    //contains uppercase
    if (password.any { it.isUpperCase() }) score++

    //have lowercase
    if (password.any { it.isLowerCase() }) score++

    //have digit
    if (password.any { it.isDigit() }) score++

    //have a symbol
    if (password.any { !it.isLetterOrDigit() }) score++

    return minOf(score, 4)
}

private fun validateForm(
    name: String,
    email: String,
    password: String,
    confirmPassword: String,
    setNameError: (String) -> Unit,
    setEmailError: (String) -> Unit,
    setPasswordError: (String) -> Unit,
    setConfirmPasswordError: (String) -> Unit,
    setFormValid: (Boolean) -> Unit
) {
    var isValid = true

    // Name validation
    if (name.trim().isEmpty()) {
        setNameError("Name is required")
        isValid = false
    } else if (name.trim().length < 3) {
        setNameError("Name must be at least 3 characters")
        isValid = false
    } else {
        setNameError("")
    }

    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

// email validation
    if (email.trim().isEmpty()) {
        setEmailError("Email is required")
        isValid = false
    } else if (!isValidEmail(email)) {
        setEmailError("Enter a valid email address")
        isValid = false
    } else {
        setEmailError("")
    }

    // Password validation
    if (password.isEmpty()) {
        setPasswordError("Password is required")
        isValid = false
    } else if (password.length < 8) {
        setPasswordError("Password must be at least 8 characters")
        isValid = false
    } else if (calculatePasswordStrength(password) < 3) {
        setPasswordError("Password is too weak")
        isValid = false
    } else {
        setPasswordError("")
    }

    // Confirm password validation
    if (confirmPassword.isEmpty()) {
        setConfirmPasswordError("Please confirm your password")
        isValid = false
    } else if (confirmPassword != password) {
        setConfirmPasswordError("Passwords don't match")
        isValid = false
    } else {
        setConfirmPasswordError("")
    }

    setFormValid(isValid)

}