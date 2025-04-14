package com.reconface

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.reconface.ui.theme.ReconFaceTheme

@Composable
fun SplashScreen(
    onNavigateToHomeScreen: () -> Unit,
    onNavigateToLoginScreen: () -> Unit,
) {
    var showSplashScreen by remember { mutableStateOf(true) }
    var acceptedTerms by remember { mutableStateOf(false) }
    var showTermsAndConditionsDialog by remember { mutableStateOf(false) }

    if (showSplashScreen) {
        SplashContent(
            onLogin = {
                showSplashScreen = false
                onNavigateToLoginScreen()
            },
            onAcceptTerms = { acceptedTerms = it },
            onContinue = {
                if (acceptedTerms) {
                    showSplashScreen = false
                    onNavigateToHomeScreen()
                }
            },
            onShowTermsAndConditionsDialog = { showTermsAndConditionsDialog = true },
            acceptedTerms = acceptedTerms
        )
    }

    if (showTermsAndConditionsDialog) {
        TermsAndConditionsDialog(onDismiss = { showTermsAndConditionsDialog = false })
    }
}

@Composable
fun SplashContent(
    onLogin: () -> Unit,
    onAcceptTerms: (Boolean) -> Unit,
    onContinue: () -> Unit,
    onShowTermsAndConditionsDialog: () -> Unit,
    acceptedTerms: Boolean
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground), // Reemplaza con tu logo
                contentDescription = "Logo de la Aplicación",
                modifier = Modifier.height(150.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = onLogin) {
                Text("Iniciar Sesión")
            }
            Spacer(modifier = Modifier.height(16.dp))
            TermsAndConditionsCheckbox(
                onAcceptTerms = onAcceptTerms,
                onShowTermsAndConditionsDialog = onShowTermsAndConditionsDialog,
                acceptedTerms = acceptedTerms
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onContinue,
                enabled = acceptedTerms
            ) {
                Text("Continuar")
            }
        }
    }
}
@Composable
fun TermsAndConditionsCheckbox(
    onAcceptTerms: (Boolean) -> Unit,
    onShowTermsAndConditionsDialog: () -> Unit,
    acceptedTerms: Boolean
) {
    var isChecked by remember { mutableStateOf(acceptedTerms) }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = {
                    isChecked = it
                    onAcceptTerms(it)
                }
            )
            Text(
                text = "Acepto los términos y condiciones",
                textAlign = TextAlign.Center,modifier = Modifier.clickable {
                    onShowTermsAndConditionsDialog()
                }
            )
        }
    }
}

@Composable
fun TermsAndConditionsDialog(onDismiss: () -> Unit) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Términos y Condiciones") },
        text = {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Text(
                    "Política de Privacidad y Manejo de Datos Personales\n" +
                            "\n" +
                            "\n" +
                            "Esta Política de Privacidad describe cómo ReconFace recopila, utiliza, almacena y comparte los datos personales de sus usuarios. Al descargar, instalar y utilizar ReconFace, el usuario acepta de forma libre, informada y expresa el tratamiento de sus datos personales conforme a los términos establecidos en el presente documento.\n" +
                            "\n" +
                            "1. Información que recopilamos\n" +
                            "Cuando utilizas nuestra aplicación, recopilamos ciertos datos personales necesarios para prestar y mejorar nuestros servicios. Esta información puede incluir, entre otros:\n" +
                            "\n" +
                            "- Datos biométricos, específicamente imágenes faciales y vectores de reconocimiento facial.\n" +
                            "- Nombre, apellidos y dirección de correo electrónico.\n" +
                            "- Información del dispositivo utilizado (modelo, sistema operativo, dirección IP, ID del dispositivo, etc.).\n" +
                            "- Información de ubicación (cuando el usuario autorice el acceso).\n" +
                            "- Información de uso de la app y comportamiento de navegación.\n" +
                            "\n" +
                            "2. Uso de la información\n" +
                            "La información recopilada es utilizada para las siguientes finalidades:\n" +
                            "\n" +
                            "- Permitir el funcionamiento adecuado de las funciones de reconocimiento facial.\n" +
                            "- Autenticación e identificación del usuario.\n" +
                            "- Prevención de fraudes y accesos no autorizados.\n" +
                            "- Personalización de la experiencia del usuario.\n" +
                            "- Desarrollo y mejora continua de nuestros servicios y funcionalidades.\n" +
                            "- Realización de análisis estadísticos y estudios de comportamiento.\n" +
                            "- Cumplimiento de obligaciones legales o requerimientos de autoridades competentes.\n" +
                            "\n" +
                            "3. Compartición de datos con terceros\n" +
                            "Al aceptar esta política, el usuario autoriza expresamente a ReconFace a compartir sus datos personales, incluidos datos biométricos, con terceros proveedores de servicios tecnológicos, empresas asociadas, afiliadas o socios estratégicos, con el propósito de:\n" +
                            "\n" +
                            "- Optimizar los algoritmos y sistemas de reconocimiento facial.\n" +
                            "- Integrar soluciones tecnológicas complementarias.\n" +
                            "- Realizar análisis de datos para mejorar el servicio.\n" +
                            "- Facilitar servicios de autenticación de identidad para otras plataformas.\n" +
                            "- Cumplir con requerimientos legales, regulatorios o contractuales.\n" +
                            "\n" +
                            "Dichos terceros estarán obligados a proteger la confidencialidad de los datos y a utilizarlos únicamente para los fines autorizados por ReconFace\n" +
                            "\n" +
                            "4. Conservación y seguridad dela información\n" +
                            "Los datos personales del usuario serán almacenados de forma segura en nuestros servidores o en servicios de almacenamiento en la nube que cumplan con estándares internacionales de seguridad. Implementamos medidas técnicas, administrativas y organizativas razonables para proteger los datos contra accesos no autorizados, pérdida o alteración.\n" +
                            "Los datos se conservarán durante el tiempo necesario para cumplir con las finalidades descritas o hasta que el usuario solicite su eliminación.\n" +
                            "\n" +
                            "5. Derechos del usuario\n" +
                            "El usuario puede ejercer los siguientes derechos en cualquier momento:\n" +
                            "- Acceder a sus datos personales.\n" +
                            "- Solicitar la corrección o actualización de sus datos.\n" +
                            "- Solicitar la eliminación de sus datos personales.\n" +
                            "- Revocar su consentimiento para el tratamiento de datos.\n" +
                            "- Restringir u oponerse al tratamiento de datos en determinados casos.\n" +
                            "Para ejercer estos derechos, el usuario puede contactarnos a través del correo: (correo@adrianelesclavo.com)\n" +
                            "\n" +
                            "6. Menores de edad\n" +
                            "El uso de ReconFace está destinado únicamente a personas mayores de 18 años. No recopilamos intencionadamente información de menores. En caso de que detectemos que un menor ha proporcionado datos personales sin el consentimiento de sus padres o tutores, procederemos a eliminar dicha información.\n" +
                            "\n" +
                            "7. Cambios en esta política\n" +
                            "Nos reservamos el derecho de actualizar esta Política de Privacidad en cualquier momento. Cualquier cambio será notificado a través de la app o mediante correo electrónico. El uso continuo de la aplicación después de dichas modificaciones implica la aceptación de los nuevos términos.\n" +
                            "\n" +
                            "8. Contacto\n" +
                            "Para cualquier consulta relacionada con esta política o el tratamiento de tus datos, puedes comunicarte con nosotros en: (correo@emilchupapija.com)\n" +
                            "\n" +
                            "Al utilizar ReconFace, confirmas que has leído, comprendido y aceptado esta Política de Privacidad."
                )
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Aceptar")
            }
        }
    )
}
@Composable
fun LoginScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier= Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Pantalla de Inicio de Sesión")
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SplashScreenPreview() {
    ReconFaceTheme {
        SplashScreen(
            onNavigateToHomeScreen = {},
            onNavigateToLoginScreen = {},
        )
    }}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SplashContentPreview() {
    ReconFaceTheme {
        SplashContent(
            onLogin = {},
            onAcceptTerms = {},
            onContinue = {},
            onShowTermsAndConditionsDialog = {},
            acceptedTerms = false
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TermsAndConditionsDialogPreview() {
    ReconFaceTheme {
        TermsAndConditionsDialog(onDismiss = {})
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LoginScreenPreview() {
    ReconFaceTheme {
        LoginScreen()
    }
}